package com.farm.search;

/**
 * Created by Administrator on 2016/10/24.
 */
import com.farm.model.Farm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;





/**
 * 获取数据库表列名
 * @author wangzn
 * @version 2015年3月19日 上午11:17:39
 */
public class BaseDAOX {

    /**
     * 使用resultSet获取数据库表列名
     * @param tableName
     * @return
     */
    public static List<String> getAllConlumn(String tableName){
        List<String> list= new ArrayList<String>();
        Connection conn = BaseConnection.getConn();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        final String sql = "select *from "+tableName+" where 1=2";
        try{
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int column = metaData.getColumnCount();
            for (int i = 1;i<=column;i++) {
                String columnName= metaData.getColumnName(i);//列名
                list.add(columnName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            BaseConnection.releaseConn(conn, ptmt, rs);
        }
        return list;
    }

    /**
     * 使用databaseMetaData对象获取数据库表列名
     * @param tableName
     * @return
     */

    public static List<String> getAllConlumns(String tableName){
        List<String> list= new ArrayList<String>();
        Connection conn = BaseConnection.getConn();
        ResultSet rs = null;
        try{
            DatabaseMetaData metaData = conn.getMetaData();
            /**
             * 参数catalog : 类别名称;
             * 参数schema,用户方案名称;匹配是不是所有列
             * 参数tableName : 数据库表名称;
             * 参数columnName : 列名称
             */
            rs = metaData.getColumns(null, "%", tableName, "%");
            while(rs.next()){
                String columnName = rs.getString("COLUMN_NAME");
                String columnType = rs.getString("TYPE_NAME");
                int dataSize = rs.getInt("COLUMN_SIZE");
                int digits = rs.getInt("DECIMAL_DIGITS");
                int nullable = rs.getInt("NULLABLE");
                System.out.println("列名: "+columnName);
                System.out.println("列类型: "+columnType);
                System.out.println("列长度: "+dataSize);
                System.out.println("列小数点位数: "+digits);
                System.out.println("列是否为空: "+nullable);
                list.add(columnName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            BaseConnection.releaseConn(conn, null, rs);
        }
        return list;

    }


    /**
     * 查找全部
     */
    public static List getAllList(Class cl,String tableName){
        List list = new ArrayList();
        Connection conn = BaseConnection.getConn();
        PreparedStatement ptmt = null;
        ResultSet rs = null;
        //获取表中列名，测试时这种只适合单表
        List<String> columnNames = BaseDAOX.getAllConlumn(tableName);
        //获取字段名
        Field[] fields = cl.getDeclaredFields();
        StringBuffer sb = new StringBuffer("select ");
        for (int i=0;i<columnNames.size();i++) {
            sb.append(columnNames.get(i));
            if(i!=columnNames.size()-1){
                sb.append(" , ");
            }
        }
        sb.append(" from "+tableName);
        try{
            ptmt = conn.prepareStatement(sb.toString());
            rs = ptmt.executeQuery();
            while(rs.next()){
                Object ob = cl.newInstance();
                for (String str : columnNames) {
                    for (Field ff: fields) {
                        if(str.equals(ff.getName())){
                            ff.setAccessible(true);;
                            ff.set(ob, rs.getObject(ff.getName()));
                            break;
                        }
                    }
                }
                list.add(ob);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            BaseConnection.releaseConn(conn,ptmt, rs);
        }
        return list;
    }

    public static void main(String[] args) {
        BaseDAOX baseDAOX = new BaseDAOX();
        System.out.println(baseDAOX.getAllConlumn("tb_animal"));
        System.out.println(baseDAOX.getAllConlumns("tb_animal"));
        System.out.println("====================================");
        System.out.println(baseDAOX.getAllList(Farm.class, "tb_animal"));

    }
}
