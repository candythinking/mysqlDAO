package com.farm.dao;

import com.farm.model.Farm;
import com.farm.utill.Descartes;
import com.farm.utill.JDBCTools;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2016/10/24.
 */
public class FarmDao {

    public static String province = "select province from farm";
    public static String market = "select market from farm";
    public static String type = "select type from farm";
    public static String name = "select name from farm";
    public static String color = "select color from farm";
    public static String time = "select time from farm";
//    protected static Set<String> provinceSet = queryField(FarmDao.province);

    public static Set<String> queryField(String sql)
    {
        Connection connection = JDBCTools.openDB();

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Set<String> set = new HashSet<String>();
            while (resultSet.next())
            {
                set.add(resultSet.getString(1));
            }
            return set;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCTools.closeDB(connection);
        }
        return null;
    }

    public List query(String queryString,String orderByField,String sort, String limit){
        /*
        orderbyFeild:表示根据那个自动进行排序
        sort:表示升序还是降序，默认降序
        limit:表示返回的条数，默认100条，如果传进来的参数为all，那么就返回所有
         */
        List<String> time = Descartes.time(queryString);
        for(String s : time){
            queryString = queryString.replace(s,"");
            System.out.println(s);
        }

        String[] strs = queryString.split(",|，");
        List<String> province = new ArrayList<>();
        Set<String> field = new HashSet<>();
        String provinceFiled = "山东、江苏、安徽、浙江、福建、上海、广东、广西、海南、湖北、湖南、河南、江西、北京、天津、河北、山西、内蒙古、宁夏、新疆、青海、陕西、甘肃、四川、云南、贵州、西藏、重庆、辽宁、吉林、黑龙江";
        for(String s : strs){
            if(s.equalsIgnoreCase(""))
                continue;
            boolean flag = true;
           /* for(String ss : provinceSet){
               if(ss.contains(s)){
                   province.add(ss);
                   flag = false;
                   System.out.println("s:"+s+",ss:"+ss);
                   break;
               }
            }*/
            if(provinceFiled.contains(s)) {
                province.add(s);
                flag = false;
                System.out.println("s:"+s);
            }
            if(flag)
                field.add(s);
        }

        Map<String,Set<String>> map = new HashMap<String,Set<String>>();
        if(province.size()==0){
            map.put("province",field);
        }
        map.put("name",field);
        map.put("market",field);
        map.put("type",field);
        List list = queryColumn(map,province,time,null,null,null);
        return list;
    }

    public List queryColumn(Map<String,Set<String>> map,List<String> province,List<String> time,String orderByField,String sort, String limit) {

        Class cl = Farm.class;
        Connection conn = JDBCTools.openDB();
        List list = new ArrayList();
        try
        {
            PreparedStatement ptStatement = null;
            ResultSet resultSet = null;
            String sql = "";
            if(map != null && map.size()>0){
                List<List<String>> dimvalue = new ArrayList<List<String>>();
                for(Map.Entry<String,Set<String>> entry : map.entrySet()){
                    List<String> v = new ArrayList<String>();
                    Set<String> values = entry.getValue();
                    for(String s : values){
                        StringBuffer term = new StringBuffer();
                        if (s.trim().equals(""))
                            continue;
                        term.append(entry.getKey());
                        term.append(" like ");
                        term.append("\"%"+s.trim()+"%\"");
                        v.add(term.toString());
                    }
                    if (v.size()>0){
                        dimvalue.add(v);
                    }
                }
                List<String> result = new ArrayList<String>();
                Descartes.run(dimvalue,result,0,"");
                System.out.println(result.size());
                StringBuffer terms = new StringBuffer();

                if (province.size()>0){
                    terms.append("(");
                    int ct = 0;
                    for (String s:province){
                        ct ++;
                        terms.append(" province like \"%" + s + "%\"");
                        if (ct==province.size())
                            break;
                        terms.append(" or ");
                    }
                    terms.append(")");
                    terms.append(" and ");
                }

                if (time.size()>0){
                    terms.append("(");
                    int c = 0;
                    for (String s:time){
                        c ++;
                        terms.append(" time like \"%" + s + "%\"");
                        if (c==time.size())
                            break;
                        terms.append(" or ");
                    }
                    terms.append(")");
                    terms.append(" and ");
                }

                int cn = 0;
                terms.append("(");
                for(String s : result){
                    cn ++;
                    terms.append("("+s.substring(4)+")");
                    if (cn==result.size())
                        break;
                    terms.append(" or ");
                }
                terms.append(")");


                sql = "select * from " + cl.getSimpleName().toLowerCase() + " WHERE " + terms.toString();
            }else {
                sql = "select * from " + cl.getSimpleName().toLowerCase();
            }
            if(orderByField !=null){
                sql = sql + " order by " + orderByField;
            }else {
                sql = sql + " order by time";
            }
            if (sort != null && sort.trim().equalsIgnoreCase("desc")){
                sql = sql + " DESC";
            }
            if(limit != null){
                if(!limit.equalsIgnoreCase("all"))
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
