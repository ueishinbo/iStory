package com.example.iStory.Database;

import com.example.iStory.Object.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class DB {
    private static String diver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://10.0.2.2:3306/istory";
    private static String user = "root";//用户名
    private static String password = "Ux8mgUYj";//密码

    /*
     * 连接数据库
     * */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(diver);
            conn = (Connection) DriverManager.getConnection(url, user, password);//获取连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static String createUserId() {
        String userId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        System.out.println(userId);
        return userId;
    }

    public static Boolean createUser(String userId, String username, String password) {
        Connection conn = null;
        conn = (Connection) DB.getConn();
        String sql = "INSERT INTO user (user_id, username, password) VALUES ('" + userId + "','" + username + "','" + password + "')";
        Statement st;
        try {
            st = (Statement) conn.createStatement();
            st.executeUpdate(sql);
            st.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void editUserName(String userId, String username) {
        Connection conn = null;
        conn = (Connection) DB.getConn();
        String sql = "UPDATE user SET username = " + username + " WHERE userId = " + userId;
        Statement st;
        try {
            st = (Statement) conn.createStatement();
            st.executeQuery(sql);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editUserPassword(String userId, String password) {
        Connection conn = null;
        conn = (Connection) DB.getConn();
        String sql = "UPDATE user SET password = " + password + " WHERE userId = " + userId;
        Statement st;
        try {
            st = (Statement) conn.createStatement();
            st.executeQuery(sql);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editUserSignature(String userId, String signature) {
        Connection conn = null;
        conn = (Connection) DB.getConn();
        String sql = "UPDATE user SET signature = " + signature + " WHERE userId = " + userId;
        Statement st;
        try {
            st = (Statement) conn.createStatement();
            st.executeQuery(sql);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String userId) {
        Connection conn = null;
        conn = (Connection) DB.getConn();
        String sql = "DELETE FROM user WHERE userId = " + userId;
        Statement st;
        try {
            st = (Statement) conn.createStatement();
            st.executeQuery(sql);
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByName(String username){
        Connection conn = null;
        conn = (Connection) DB.getConn();
        String sql = "SELECT * FROM User WHERE username = " + username;
        Statement st;
        User user = null;
        try {
            st = (Statement) conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                user = new User(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

    public static User getUser(String userId) {
        Connection conn = null;
        conn = (Connection) DB.getConn();
        String sql = "SELECT * FROM User WHERE userId = " + userId;
        Statement st;
        User user = null;
        try {
            st = (Statement) conn.createStatement();
            ResultSet result = st.executeQuery(sql);
            while (result.next()) {
                user = new User(userId, result.getString(2), result.getString(3), result.getString(4), result.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return user;
    }

}
