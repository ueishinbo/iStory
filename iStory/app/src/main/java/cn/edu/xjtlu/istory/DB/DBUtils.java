package cn.edu.xjtlu.istory.DB;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import cn.edu.xjtlu.istory.Object.User;

/**
 * 数据库工具类：连接数据库用、获取数据库数据用
 * 相关操作数据库的方法均可写在该类
 */
public class DBUtils {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "istory";
    private static String password = "iStory123";
    private static String url = "jdbc:mysql://rm-uf6ab300y7vo7qh0eqo.mysql.rds.aliyuncs.com:3306/istory";


    private static Connection getConn(){
        Connection connection = null;
        try{
            Class.forName(driver);
            // 尝试建立到给定数据库URL的连接
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }


    public static String createUserId() {
        String userId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        System.out.println(userId);
        return userId;
    }

    public static User getUser(String userName) {
        User user = null;
        Connection connection = getConn();
        try {
            String sql = "select * from user where username = " + "\'" + userName + "\'";
            Statement stmt = null;
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                user = new User(rs.getString("user_id"), rs.getString("username"),rs.getString("password"),rs.getString("signature"),rs.getString("tag"));
            }
            rs.close();
            stmt.close();
            connection.close();
            //System.out.println("获取到的用户名为"+user.getUserName());
            //System.out.println("获取到的id为" + user.getUserId());
        }catch (Exception e){
            e.printStackTrace();
            Log.e("DBUtils","异常：" + e.getMessage());
            return null;
        }
        return user;
    }

    public static Boolean createUser(int userId, String name, String password) {
        System.out.println("传过来的参数为" + userId + " " + name + " " + password);
        Connection connection = getConn();
        System.out.println("与数据库建立连接成功");
        String sql = "INSERT INTO user(user_id) VALUES (?)";
        try {
           /* String sql = "insert into user(user_id,username,password) value(\'"+userId+"\', \'"+name+"\', \'"+"\',  \'"+password+"\')";
            PreparedStatement pst = connection.prepareStatement(sql);
            System.out.println("插入的用户名是" + name);
*//*            pst.setString(1,userId);
            pst.setString(2,name);
            pst.setString(3,password);*/
            PreparedStatement pst = connection.prepareStatement(sql);
            System.out.println("插入的用户名是" + name+"插入的userid是" + userId);
            pst.setInt(1,userId);
            /*pst.setString(2,name);
            pst.setString(3,password);*/
            pst.executeUpdate();
            pst.close();
            connection.close();
        }catch (Exception e){
            System.out.println("插入失败了");
            e.printStackTrace();
            Log.e("DBUtils","异常：" + e.getMessage());
            return false;
        }
        return true;
    }
}

