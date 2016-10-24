package com.farm.dao;

import com.farm.model.Farm;
import com.farm.utill.JDBCTools;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/24.
 */
public class FarmDao {

    public List queryColumn(Map<String,String> map, String orderByField, String limit) {

        Class cl = Farm.class;
        Connection conn = JDBCTools.openDB();
        List list = new ArrayList();
        try
        {
            PreparedStatement ptStatement = null;
            ResultSet resultSet = null;
            String sql = "";
            if(map != null && map.size()>0){
                StringBuffer terms = new StringBuffer(10);
                int count = 0;
                int size = map.size();
                for(Map.Entry<String,String> entry : map.entrySet()){
                    count ++;
                    terms.append(entry.getKey());
                    terms.append(" like ");
                    terms.append("\"%"+entry.getValue()+"%\"");
                    if (count==size) {
                        break;
                    }
                    terms.append(" and ");
                }
                sql = "select * from " + cl.getSimpleName().toLowerCase() + " WHERE " + terms.toString();
            }else {
                sql = "select * from " + cl.getSimpleName().toLowerCase();
            }
            if(orderByField !=null){
                sql = sql + " order by " + orderByField;
            }else {
                sql = sql + " order by time";
            }
            if(limit != null){
                sql = sql + " limit " + limit;
            }else {
                sql = sql + " limit 100";
            }
            System.out.println(sql);
            Field[] fi = cl.getDeclaredFields();

            ptStatement = conn.prepareStatement(sql);
            resultSet = ptStatement.executeQuery();
            while(resultSet.next()){
                Object ob = cl.newInstance();
                for (Field field : fi) {
                    field.setAccessible(true);
                    field.set(ob, resultSet.getObject(field.getName()));
                }
                list.add(ob);
            }
            return list;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally
        {
            JDBCTools.closeDB(conn);
        }
        return null;
    }
}
