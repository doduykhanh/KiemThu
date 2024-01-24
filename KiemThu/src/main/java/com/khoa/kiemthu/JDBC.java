/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.khoa.kiemthu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ACER
 */
public class JDBC {
//     private static Connection connect ;
//
//
//    
//    static {
//    try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//    }
//    public static Connection getConnect() throws SQLException {
//        return DriverManager.getConnection("jdbc:mysql://localhost/dbbl", "root", "khoavo00");
//    }
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dbbl","root","khoavo00");

            return conn;
        } catch (Exception e) {
        
            return null;
        }
    
    }
    

    
}
