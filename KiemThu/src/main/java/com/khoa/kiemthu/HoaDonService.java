/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.khoa.kiemthu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class HoaDonService {
       public static void update(String a1,String a2,String a3,String a4,String a5,String a6,String a7,String a8,String a9,String a10) throws SQLException {
       
       Connection conn = JDBC.ConnectDb();
        conn.setAutoCommit(false); 
      String sql = "UPDATE hoadon SET idhd=?, namenv=?, idhh=?, namehh=?, soluonghh=?, giahh=?, thanhtien=?, loaikhach=?, xuatxu=?, idkh=? WHERE idhd =?";

//       String sql = "UPDATE hanghoa SET id_hh='"+a1+",'SET tenhang='"+a2+",'SET giahang='"+a3+",'SET xuatxu='"+a4+ ",'SET mota='"+a5+",'SET soluong='"+a6+"'";
                                       
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setString(1, a1);
       stm.setString(2, a2);
       stm.setString(3, a3);
       stm.setString(4, a4);
       stm.setString(5, a5);
       stm.setString(6, a6);
       stm.setString(7, a7);
       stm.setString(8, a8);
       stm.setString(9, a9);
       stm.setString(10, a10);
       stm.setString(11, a1);
       stm.executeUpdate();
       conn.commit();
       
     
   }
       public static void deleteHangHoaDon(String id_hh) throws SQLException {
       String sql = "DELETE FROM hoadon WHERE idhd=?";
       Connection conn = JDBC.ConnectDb();
       conn.setAutoCommit(false);
       
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setString(1, id_hh);
       stm.executeUpdate();
       
       conn.commit();
     
   }
        public static void themHangHoaHD(String a1,String a2,String a3,String a4,double a5,String a6,double a7,String a8,String a9,String a10) throws SQLException{
        try(Connection conn = JDBC.ConnectDb()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hoadon(idhd, namenv, idhh, namehh, soluonghh, giahh, thanhtien, loaikhach, xuatxu,idkh) VALUES(?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, a1);
            stm.setString(2, a2);
            stm.setString(3, a3);
            stm.setString(4, a4);
            stm.setDouble(5, a5);
            stm.setString(6, a6);
            stm.setDouble(7, a7);
            stm.setString(8, a8);
            stm.setString(9, a9);
            stm.setString(10, a10);
            
            stm.executeUpdate();
        }
        
    }
      public static List<HoaDon> napHoaDon(String kw ) throws SQLException{
        List<HoaDon> hanghoa =new ArrayList<>();
        try (Connection conn = JDBC.ConnectDb()){
            String sql = "SELECT *FROM hoadon ";
            if(kw != null && !kw.isEmpty() )
                sql+="WHERE idkh like concat ('%',?,'%')";
                PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty() )
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){               
                HoaDon hh =new HoaDon(rs.getString("idhd"), rs.getString("namenv"), rs.getString("idhh"),rs.getString("namehh"),
                        rs.getString("soluonghh"), rs.getString("giahh"), rs.getDouble("thanhtien"),rs.getString("loaikhach"),
                        rs.getString("xuatxu"),rs.getString("idkh"));
                hanghoa.add(hh);
            }
        }
        return hanghoa;
    }
        public static List<HoaDon>getHoaDon() throws SQLException{
        List<HoaDon> results = new ArrayList<>();
        try(Connection conn = JDBC.ConnectDb()){
            Statement stm = conn.createStatement();
            ResultSet rs =stm.executeQuery("SELECT * FROM hoadon");
            while(rs.next()){
                HoaDon h = new HoaDon(rs.getString("idhd"), rs.getString("namenv"), rs.getString("idhh"), rs.getString("namehh"), rs.getString("soluonghh"), rs.getString("giahh"), rs.getDouble("thanhtien"), rs.getString("loaikhach"), rs.getString("xuatxu"),rs.getString("idkh"));
                results.add(h);
            }
        }
        return results;
        
    }

}
