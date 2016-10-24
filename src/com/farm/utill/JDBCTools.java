package com.farm.utill;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author Every
 * 数据库测打开关闭以及操作语句
 *
 */
public class JDBCTools
{
	//驱动、连接、数据库的用户名、密码
	private static String driver;
	private static String url;
	private static String name;
	private static String password;
	public static Connection conn ;

	static
	{
		Properties properties = new Properties();      //建立属性对象
		Reader reader;                                 //新建读对象
		try
		{
			reader = new FileReader("src\\config.propreties"); //将配置文件里的数据读出来
			properties.load(reader);                           //加载到属性中
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		driver = properties.getProperty("driver");            //这里的几个字段要用引号，这是配置文件中的字段
		url = properties.getProperty("url");
		name = properties.getProperty("name");
		password = properties.getProperty("password");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, name, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 加载数据库驱动，获取数据库连接
	 */
	public static Connection openDB()
	{
		try
		{
			if (conn == null | conn.isClosed()){
				Class.forName(driver);
				conn = DriverManager.getConnection(url, name, password);
			}
			return conn;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
    /*
     * 关闭对应连接的数据库
     */
	public static void closeDB(Connection connection)
	{
		if (connection != null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
