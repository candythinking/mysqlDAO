package com.farm.search;

/**
 * Created by Administrator on 2016/10/24.
 */
import java.sql.*;

public class BaseConnection {
    //============orcal=============
/*	final static String className="oracle.jdbc.driver.OracleDriver";
	final static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	final static String user = "wzn";
	final static String password = "web";*/
    //=============mysql==================
    final static String className="com.mysql.jdbc.Driver";
    final static String url = "jdbc:mysql://localhost:3306/test";
    final static String user = "root";
    final static String password = "root";

    static {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public static Connection getConn(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param connection
     * @param ptmt
     * @param resultSet
     */
    public static void releaseConn(Connection connection,PreparedStatement ptmt, ResultSet resultSet){
        try{
            if(resultSet!=null){
                resultSet.close();
            }
            if(ptmt!=null){
                ptmt.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException {
        System.out.print(getConn());
    }
}


 class DataBaseConnection1 {
    private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/test";
    private static final String DBUSER = "root";
    private static final String PASSWORD = "admin";
    public Connection conn;

    //饿汉式数据库连接
    public Connection getConnection() {
        try {
            if (conn == null && conn.isClosed())
                Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    public void close(){
        if(conn != null){
            try {
                this.conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

     class DatabaseConnection2 {
         private static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
         private static final String DBURL = "jdbc:mysql://localhost:3306/test";
         private static final String DBUSER = "root";
         private static final String DBPASSWORD = "admin";
         private Connection conn = null;

         // 如果要实例化本类对象，实际上就是为了取得数据库连接，那么就把连接过程交给构造方法
         public DatabaseConnection2() {
             try {
                 Class.forName(DBDRIVER);
                 this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }

         public Connection getConnection() {
             return this.conn;
         }

         public void close() {
             if (this.conn != null) {
                 try { // 关闭时几乎不会有问题
                     this.conn.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
}