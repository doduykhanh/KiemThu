/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import com.khoa.kiemthu.AdminController;
import com.khoa.kiemthu.App;
import com.khoa.kiemthu.HangHoa;
import com.khoa.kiemthu.JDBC;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ACER
 */

public class HangHoaService {
      public void Edit(String a1,String a2,String a3,String a4,String a5,String a6) throws SQLException {
       
       Connection conn = JDBC.ConnectDb();
        conn.setAutoCommit(false); 
      String sql = "UPDATE hanghoa SET id_hh=?,tenhang=?, giahang=?, xuatxu=?, mota=?, soluong=? WHERE id_hh =?";

//       String sql = "UPDATE hanghoa SET id_hh='"+a1+",'SET tenhang='"+a2+",'SET giahang='"+a3+",'SET xuatxu='"+a4+ ",'SET mota='"+a5+",'SET soluong='"+a6+"'";
                                       
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setString(1, a1);
       stm.setString(2, a2);
       stm.setString(3, a3);
       stm.setString(4, a4);
       stm.setString(5, a5);
       stm.setString(6, a6);
       stm.setString(7, a1);
       stm.executeUpdate();
       conn.commit();
       
     
   }
        public static void deleteHangHoa(String id_hh) throws SQLException {
       String sql = "DELETE FROM hanghoa WHERE id_hh=?";
       Connection conn = JDBC.ConnectDb();
       conn.setAutoCommit(false);
       
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setString(1, id_hh);
       stm.executeUpdate();
       
       conn.commit();
     
   }


    public void themHangHoa(HangHoa h) throws SQLException{
        try(Connection conn = JDBC.ConnectDb()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hanghoa(id_hh,tenhang,giahang,xuatxu,mota,soluong) VALUES(?,?,?,?,?,?)");
            stm.setString(1, h.getId_hh());
            stm.setString(2, h.getTenhang());
            stm.setString(3, h.getGiahang());
            stm.setString(4, h.getXuatxu());
            stm.setString(5, h.getMota());
            stm.setString(6, h.getSoluong());
            
            stm.executeUpdate();
        }
        
    }
    
    public static List<HangHoa> napHangHoa(String kw ) throws SQLException{
        List<HangHoa> hanghoa =new ArrayList<>();
        try (Connection conn = JDBC.ConnectDb()){
            String sql = "SELECT *FROM hanghoa ";
            if(kw != null && !kw.isEmpty() )
                sql+="WHERE tenhang like concat ('%',?,'%')";
                PreparedStatement stm = conn.prepareStatement(sql);
            if(kw != null && !kw.isEmpty() )
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                HangHoa hh =new HangHoa(rs.getString("id_hh"), rs.getString("tenhang"), rs.getString("giahang"),
                        rs.getString("xuatxu"), rs.getString("mota"), rs.getString("soluong"));
                hanghoa.add(hh);
            }
        }
        return hanghoa;
    }
        public List<HangHoa>getHangHoa() throws SQLException{
        List<HangHoa> results = new ArrayList<>();
        try(Connection conn = JDBC.ConnectDb()){
            Statement stm = conn.createStatement();
            ResultSet rs =stm.executeQuery("SELECT * FROM hanghoa");
            while(rs.next()){
                HangHoa h =new HangHoa(rs.getString("id_hh"),rs.getString("tenhang"),rs.getString("giahang"),rs.getString("xuatxu"),rs.getString("mota"),rs.getString("soluong"));
                results.add(h);
            }
        }
        return results;
        
    }
}
