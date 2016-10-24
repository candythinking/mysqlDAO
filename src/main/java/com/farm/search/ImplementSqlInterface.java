package com.farm.search;

import com.farm.model.Farm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据库的增、删、改 、查的方法
 * @author Every
 *
 */
public class ImplementSqlInterface implements SqlInterface
{
	/*
	 * (non-Javadoc)
	 * @see SqlInterface#insertData(Students)
	 * 插入数据
	 */
	@Override
	public void insertData(Students students)
	{
		Connection connection = JDBCTools.openDB();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(JDBCTools.insertSql);
			preparedStatement.setLong(1, students.getId());
			preparedStatement.setInt(2, students.getAge());
			preparedStatement.setString(3, students.getName());
			preparedStatement.setString(4, students.getSex());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTools.closeDB(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see SqlInterface#deleteDataById(long)
	 * 删除数据
	 */
	@Override
	public void deleteDataById(long id)
	{
		Connection connection = JDBCTools.openDB();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(JDBCTools.deleteSql);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTools.closeDB(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see SqlInterface#updateData(Students)
	 * 更新数据
	 */
	@Override
	public void updateData(Students students)
	{
		Connection connection = JDBCTools.openDB();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(JDBCTools.updateSql);
			preparedStatement.setLong(4, students.getId());
			preparedStatement.setInt(1, students.getAge());
			preparedStatement.setString(2, students.getName());
			preparedStatement.setString(3, students.getSex());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCTools.closeDB(connection);
		}
	}
/*
 * (non-Javadoc)
 * @see SqlInterface#querySingalDataById(long)
 * 查询单个数据
 */
	@Override
	public Students querySingalDataById(long id)
	{
		Connection connection = JDBCTools.openDB();
		Students students = new Students();
		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(JDBCTools.querySingalSql);
			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				int age = resultSet.getInt(2);
				String name = resultSet.getString(3);
				String sex = resultSet.getString(4);
				students.setId(id);
				students.setAge(age);
				students.setName(name);
				students.setSex(sex);
			}
			return students;
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
/*
 * (non-Javadoc)
 * @see SqlInterface#queryDate()
 * 查询所有数据
 */
	@Override
	public List<Students> queryDate()
	{
		Connection connection = JDBCTools.openDB();

		try
		{
			PreparedStatement preparedStatement = connection.prepareStatement(JDBCTools.queryallSql);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Students> list = new ArrayList<>();
			while (resultSet.next())
			{
				long id = resultSet.getLong(1);
				int age = resultSet.getInt(2);
				String name = resultSet.getString(3);
				String sex = resultSet.getString(4);
				Students students = new Students();
				students.setId(id);
				students.setAge(age);
				students.setName(name);
				students.setSex(sex);
				list.add(students);
			}
			return list;
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

	@Override
	public List queryAll(Class cl) {
		Connection conn = JDBCTools.openDB();
		List list = new ArrayList();
		try
		{
			PreparedStatement ptStatement = null;
			ResultSet resultSet = null;
			String sql = "select * from "+cl.getSimpleName().toLowerCase() + " LIMIT 10";
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

	@Override
	public List queryColumn(Map<String,String> map, String orderByField,String limit) {

		Class cl = Farm.class;
		Connection conn = JDBCTools.openDB();
		List list = new ArrayList();
		try
		{
			PreparedStatement ptStatement = null;
			ResultSet resultSet = null;

			StringBuffer terms = new StringBuffer(10);
			int count = 0;
			int size = map.size();
			for(Map.Entry<String,String> entry : map.entrySet()){
				count ++;
				terms.append(entry.getKey());
				terms.append("=");
				terms.append("\""+entry.getValue()+"\"");
				if (count==size) {
					break;
				}
				terms.append(" and ");
			}
			String sql = "";
			if(orderByField !=null){
				sql = "select * from " + cl.getSimpleName().toLowerCase() + " WHERE " + terms.toString() + " order by " + orderByField;
			}else {
				sql = "select * from " + cl.getSimpleName().toLowerCase() + " WHERE " + terms.toString() + " order by time";
			}
			if(limit != null){
				sql = sql + "limit " + limit;
			}else {
				sql = sql + "limit 100";
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
