package pl.blazej.connection;

import java.sql.*;

public class Connector {
    private static Connector ourInstance = new Connector();

    public static Connector getInstance() {
        return ourInstance;
    }

    private static final String SQL_LINK = "jdbc:mysql://5.135.218.27:3306/BlazejOlesiak";
    private static final String SQL_USER = "BlazejOlesiak";
    private static final String SQL_PASS = "????????";
    private static final String SQL_CLASS = "com.mysql.jdbc.Driver";

    private Connection connection;

    private Connector() {
        connect();
        System.out.println("Połączono");    }

    private void connect() {
        try {
            Class.forName(SQL_CLASS).newInstance();
            connection = DriverManager.getConnection(SQL_LINK, SQL_USER, SQL_PASS);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Statement getNewStatement(){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PreparedStatement getNewPreparedStatement(String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
