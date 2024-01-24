/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KhachHang;

import com.khoa.kiemthu.HangHoa;
import com.khoa.kiemthu.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class KhachHangService {
        public List<KhachHang> search(String kw ) throws SQLException{
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
            }
        }
        return khach;
    }
}
