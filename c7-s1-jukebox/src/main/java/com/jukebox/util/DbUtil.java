package com.jukebox.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConnection(){

        //Load the Drivers

        // Get the connection from database
        Connection connect = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/jukebox";
            String user = "root";
            String password = "Command@1923";
            connect = DriverManager.getConnection(url,user,password);}
        catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return connect;
    }
}
