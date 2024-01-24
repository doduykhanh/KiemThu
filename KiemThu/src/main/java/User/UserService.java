/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;


import User.UserInf;
import com.khoa.kiemthu.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.khoa.kiemthu.HangHoa;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class UserService {
        public void themNguoiDung(UserInf h) throws SQLException{
        try(Connection conn = JDBC.ConnectDb()){
            PreparedStatement stm = conn.prepareStatement("INSERT INTO user(user_id,username,password,role) VALUES(?,?,?,?)");
            stm.setString(1, h.getId());
            stm.setString(2, h.getName());
            stm.setString(3, h.getPass());
            stm.setString(4, h.getRole());

            
            stm.executeUpdate();
        }
        
    }
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
        public boolean checkUserIinf(String u, String p) throws SQLException{
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
            
}
