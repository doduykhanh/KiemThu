/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Testdemo;

import KhachHang.KhachHang;
import static Testdemo.test1.update;
import User.UserInf;
import com.khoa.kiemthu.HangHoa;
import com.khoa.kiemthu.HoaDon;
import static com.khoa.kiemthu.HoaDonService.update;
import com.khoa.kiemthu.JDBC;
import com.khoa.kiemthu.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ACER
 */
public class test1 {
    private static Connection Connect;
    @BeforeAll 
    public static void BeforeAll() throws SQLException{
    Connect= JDBC.ConnectDb();
    }
    @AfterAll
    public static void AfterAll() throws SQLException{
        if (Connect != null)
            Connect.close();
    }
    @Test
    public void testUnquie () throws SQLException {
        Statement stm = Connect.createStatement();
        ResultSet rs =stm.executeQuery("SELECT * FROM hanghoa");
        while(rs.next()){
            String name = rs.getString("tenhang");
            System.out.println(name);
        }
    } 
    
    @Test
    public void testGetHoadon () throws SQLException {
        Statement stm = Connect.createStatement();
        ResultSet rs =stm.executeQuery("SELECT * FROM hoadon");
        while(rs.next()){
            String name = rs.getString("namehh");
            System.out.println(name);
        }
    } 
        @Test
    public void testGetUser () throws SQLException {
        Statement stm = Connect.createStatement();
        ResultSet rs =stm.executeQuery("SELECT * FROM user");
        while(rs.next()){
            String name = rs.getString("username");
            System.out.println(name);
        }
    } 
            @Test
    public void testGetKhach () throws SQLException {
        Statement stm = Connect.createStatement();
        ResultSet rs =stm.executeQuery("SELECT * FROM khachhang");
        while(rs.next()){
            String name = rs.getString("tenkh");
            System.out.println(name);
        }
    }
    @Test
    
    public void themHangHoa() throws SQLException{
        HangHoa hh = new HangHoa(UUID.randomUUID().toString(), "Unit Test", "Unit test", "Unit test", "unitTest", "1");
        try(Connection conn = JDBC.ConnectDb()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hanghoa(id_hh,tenhang,giahang,xuatxu,mota,soluong) VALUES(?,?,?,?,?,?)");
            stm.setString(1, hh.getId_hh());
            stm.setString(2, hh.getTenhang());
            stm.setString(3, hh.getGiahang());
            stm.setString(4, hh.getXuatxu());
            stm.setString(5, hh.getMota());
            stm.setString(6, hh.getSoluong());
            
            stm.executeUpdate();
            
            System.out.println("===================");
            System.out.println("Them Hang Hoa Thanh Cong");
        }
        
    }
    @Test
     public void themHangHoaHD() throws SQLException{
         HoaDon hd = new HoaDon(UUID.randomUUID().toString(), "Test", "Tset", "tset", "1", "test", 0, "Test", "test", "Test");
        try(Connection conn = JDBC.ConnectDb()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hoadon(idhd, namenv, idhh, namehh, soluonghh, giahh, thanhtien, loaikhach, xuatxu,idkh) VALUES(?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1, hd.getIdhd());
            stm.setString(2, hd.getNamenv());
            stm.setString(3, hd.getIdhh());
            stm.setString(4, hd.getNamehh());
            stm.setString(5,hd.getSoluonghh() );
            stm.setString(6, hd.getGiahh());
            stm.setDouble(7, hd.getThanhtien());
            stm.setString(8, hd.getLoaikhach());
            stm.setString(9, hd.getXuatxu());
            stm.setString(10, hd.getIdkh());
            
            stm.executeUpdate();
        }
        System.out.println("Them hoa don thanh cong !");
        
    }
        @Test
        public List<KhachHang> napTenKhach() throws SQLException{
            String kw = "Khoa";
        List<KhachHang> khach =new ArrayList<>();
        try (Connection conn = JDBC.ConnectDb()){
            String sql = "SELECT *FROM khachhang ";
            if(kw != null && !kw.isEmpty() )
                sql+="WHERE tenkh like concat ('%',?,'%')";
                PreparedStatement stm = conn.prepareStatement(sql);
                
            if(kw != null && !kw.isEmpty() )
                stm.setString(1, kw);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                KhachHang hh =new KhachHang(rs.getString("idkhachhang"),
                        rs.getString("tenkh"), rs.getString("ngaysinh"));
                khach.add(hh);
                System.out.println(hh.getTenkh());
            }
        }
        return khach;
    }
        @Test
        public List<UserInf>getNguoiDung() throws SQLException{
        List<UserInf> results = new ArrayList<>();
        try(Connection conn = JDBC.ConnectDb()){
            Statement stm = conn.createStatement();
            ResultSet rs =stm.executeQuery("SELECT * FROM user");
            while(rs.next()){
                UserInf h =new UserInf(rs.getString("user_id"),rs.getString("username"),
                        rs.getString("password"),rs.getString("role"));
                results.add(h);
            }
        }
            
        return results;
        
    }
        @Test
        public void layThongTinUser() throws SQLException{
            List<UserInf> results = new ArrayList<>();
            results = this.getNguoiDung();
            for (int i = 0; i < 5; i++) {
                System.out.println("========");
                System.out.println(results.get(i).getName() +"        "+ results.get(i).getPass());
            }
        }
        @Test
        public boolean checkUserIinf() throws SQLException{
                   String u = "1";
                   String p ="1";
            ArrayList<UserInf> res = new ArrayList<>();
            
            boolean check = false;
            res = (ArrayList<UserInf>) this.getNguoiDung();
            try{
            for(int i =0;i<res.size();i++){
                if(u.equals(res.get(i).getName()) && 
                   p.equals(res.get(i).getPass())
//                   r.equals(res.get(i).getRole()) )
                        )
                    check = true;
                    
                }
            }catch(ArrayIndexOutOfBoundsException e)
            {e.addSuppressed(e);}

            return check;
            
        }
        @Test
        public void checkinf() throws SQLException{
            
             if(checkUserIinf()==true){
                System.out.println("===============================");
                System.out.println("Check thanh cong");
            }
            else
            {
                System.out.println("===============================");
                System.out.println("Check thanh khong thanh cong");
            }
        }
       @Test 
       public static void deleteHangHoa(String id_hh) throws SQLException {
       String sql = "DELETE FROM hanghoa WHERE id_hh=?";
       Connection conn = JDBC.ConnectDb();
       conn.setAutoCommit(false);
       
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setString(1, id_hh);
       stm.executeUpdate();
       
       conn.commit();
     
   }
       @Test
       public void testDeleteHangHoa() throws SQLException{
           String id = "12c46a15-9121-43bf-97af-32a75c488ab1";
           deleteHangHoa(id);
           System.out.println("===============++===============");
           System.out.println("Xoa thanh cong hang hoa");
           System.out.println("===============++==============");
       }
       
       @Test
        public static void deleteHangHoaDon(String id_hh) throws SQLException {
       String sql = "DELETE FROM hoadon WHERE idhd=?";
       Connection conn = JDBC.ConnectDb();
       conn.setAutoCommit(false);
       
       PreparedStatement stm = conn.prepareStatement(sql);
       stm.setString(1, id_hh);
       stm.executeUpdate();
       
       conn.commit();
     
   }
        @Test
        public void testDeleteHangHoaDon() throws SQLException{
                       String id = "aea2a6eb-624d-4dd6-b8d9-127dd55a4a85";
           deleteHangHoaDon(id);
           System.out.println("===============++===============");
           System.out.println("Xoa thanh cong hoa don ");
           System.out.println("===============++==============");
        
        }
     @Test   
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
        @Test
        public static List<HoaDon> testNapHoaDon(String kw ) throws SQLException{
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
     @Test
     public void testUpdate() throws SQLException{
         update("d06d18d3-ef0d-499e-9fba-c692f544752a","Test update","Test update", "Test update", 
                 "Test update", "Test update", "100", "Test update", "Test update", "Test update");
         System.out.println("+++++++++++Updated !++++++++");
         ArrayList<HoaDon>a=(ArrayList<HoaDon>) testNapHoaDon("");
         for(int i=0;i<a.size();i++)
         {   
             if(a.get(i).getNamehh().equals("Test update"));
                
                    System.out.print(a.get(i).getNamehh()+"      ");
                    System.out.print(a.get(i).getGiahh()+"\n");
         }
         System.out.println("+++++++++++Updated !++++++++");
     }
     @Test
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
       
     
   }    @Test
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
         @Test
          public void testEdit() throws SQLException{
              Edit("15544d4b-34e8-49a0-81f7-8f151e4c0294", "Tet edits", "Tét edits", "Tét edits", "Tét edits", "Tét edits");
         System.out.println("+++++++++++Edited !++++++++");
         ArrayList<HangHoa>a=(ArrayList<HangHoa>) napHangHoa("Tet edits");
         for(int i=0;i<a.size();i++)
         {   
             if(a.get(i).getTenhang().equals("Test update"));
                
                    System.out.print(a.get(i).getTenhang()+"      ");
                    System.out.print(a.get(i).getGiahang()+"\n");
         }
         System.out.println("+++++++++++Updated !++++++++");
     }
}
