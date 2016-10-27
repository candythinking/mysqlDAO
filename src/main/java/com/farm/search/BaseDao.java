package com.farm.search;

/**
 * Created by Administrator on 2016/10/24.
 */
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {

    public List getList(Class cl) throws SQLException, InstantiationException, IllegalAccessException{
        List list = new ArrayList();
        Connection conn = BaseConnection.getConn();
        PreparedStatement ptStatement = null;
        ResultSet resultSet = null;
        String sql = "select *from "+cl.getSimpleName();
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
}