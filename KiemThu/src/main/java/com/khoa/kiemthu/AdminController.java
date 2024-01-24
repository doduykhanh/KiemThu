/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.khoa.kiemthu;

import Services.HangHoaService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.khoa.kiemthu.JDBC;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class AdminController implements Initializable {
    @FXML
    private TableColumn<?, ?> col_gia;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_mota;

    @FXML
    private TableColumn<?, ?> col_soluong;

    @FXML
    private TableColumn<?, ?> col_ten;

    @FXML
    private TableColumn<?, ?> col_xx;

    @FXML
    private TableView table;

    @FXML
    private TextField tf1;
    @FXML
    private TextField tf0;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextField tf5;
    @FXML
    private TextField txtkw;
    
   
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            this.loadTable();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtkw.textProperty().addListener((evt)->{
                try {
                    this.loadTableData(this.txtkw.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        
    }
//    public void editTable(HangHoa a) throws SQLException{
//         String sql = "UPDATE hanghoa set ";
//       Connection conn = JDBC.ConnectDb();
//       conn.setAutoCommit(false);
//       PreparedStatement stm = conn.prepareStatement(sql);
//       
//       stm.executeUpdate();
//       
//       conn.commit();
//    }
//    public void select(MouseEvent event) throws SQLException{
//        loadTableData("");
//        HangHoa a = new HangHoa();
//        a = (HangHoa) table.getSelectionModel().getSelectedCells();
//        
//        
//    }
    public void themhanghoa(ActionEvent event) throws SQLException{
            
            HangHoa h=new HangHoa(UUID.randomUUID().toString(), this.tf1.getText(), this.tf2.getText(), 
                    this.tf3.getText(), this.tf4.getText(), this.tf5.getText());
            HangHoaService hh= new HangHoaService();
            HangHoaService a = new HangHoaService();
            ArrayList<HangHoa> rs = (ArrayList<HangHoa>) a.getHangHoa();          
            try{
            hh.themHangHoa(h);
            loadTableData("");
            
            ThongBao.getBox("Tao san pham thanh cong !", Alert.AlertType.INFORMATION).show();
            }catch(SQLException ex){
             ThongBao.getBox("Tao san pham khong thanh cong !", Alert.AlertType.WARNING).show();       
            }
    }
        @FXML
        public void suaHang(ActionEvent event) throws SQLException{
           String t = tf0.getText();
           String a1 = tf0.getText();
           String a2 = tf1.getText();
           String a3 = tf2.getText();
           String a4 = tf3.getText();
           String a5 = tf4.getText();
           String a6 = tf5.getText();
            HangHoa a = new HangHoa();
            HangHoaService b = new HangHoaService();
            ArrayList<HangHoa> c = (ArrayList<HangHoa>) b.getHangHoa();
            try{
                b.Edit(a1,a2,a3,a4,a5,a6);
            
            
            ThongBao.getBox("Sua pham thanh cong !", Alert.AlertType.INFORMATION).show();
            }catch(SQLException ex){
             ThongBao.getBox("Sua pham khong thanh cong !", Alert.AlertType.WARNING).show();       
            }
            loadTableData("");
            
            
    }
    @FXML
    private void loadTable(){
        TableColumn colID = new TableColumn("ID");
        colID.setCellValueFactory(new PropertyValueFactory("id_hh"));
        colID.setPrefWidth(123);
        
        TableColumn colName = new TableColumn("Tên");
        colName.setCellValueFactory(new PropertyValueFactory("tenhang"));
        colName.setPrefWidth(100);
        
        TableColumn colGia = new TableColumn("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory("giahang"));
        colGia.setPrefWidth(100);
        
        TableColumn colXuatXu = new TableColumn("Xuất xứ");
        colXuatXu.setCellValueFactory(new PropertyValueFactory("xuatxu"));
        colXuatXu.setPrefWidth(100);
        
        TableColumn colMoTa = new TableColumn("Khuyến mãi ");
        colMoTa.setCellValueFactory(new PropertyValueFactory("mota"));
        colMoTa.setPrefWidth(100);
        
        TableColumn colSoLuong = new TableColumn("Số lượng/Kg");
        colSoLuong.setCellValueFactory(new PropertyValueFactory("soluong"));
        colSoLuong.setPrefWidth(50);
        
        TableColumn delColumn = new TableColumn();
        colSoLuong.setPrefWidth(50);
        delColumn.setCellFactory(p->{
            Button btn = new Button("Xóa");
            btn.setOnAction(et ->{
                    Alert al = ThongBao.getBox("Bạn có muốn xóa mặt hàng này không ?", Alert.AlertType.WARNING);
                    al.setContentText("Bạn có muốn xóa mặt hàng này không ?");
                    al.showAndWait().ifPresent(res->{
                        TableCell cell = (TableCell)((Button)et.getSource()).getParent();
                        HangHoa hang = (HangHoa) cell.getTableRow().getItem();
                    try{
                        
                        HangHoaService.deleteHangHoa(hang.getId_hh());
                        this.table.getItems().clear();
                        this.table.setItems(FXCollections.observableArrayList(HangHoaService.napHangHoa("")));
                        ThongBao.getBox("Xoa thanh cong ", Alert.AlertType.INFORMATION).show();
                        } catch (SQLException ex) {
                            ThongBao.getBox("Xóa Thất Bại  " +ex.getMessage(), Alert.AlertType.INFORMATION).show();
                        }
                    });    
                 });     
                TableCell cell = new TableCell();
                cell.setGraphic(btn);
                return cell;
        
        });
        TableColumn fixColumn = new TableColumn();
        colSoLuong.setPrefWidth(50);
        fixColumn.setCellFactory(p->{
            Button btn = new Button("Sửa");
            btn.setOnAction(et ->{
                    Alert al = ThongBao.getBox("Bạn có muốn sửa thông tin mặt hàng này không ?", Alert.AlertType.WARNING);
                    al.setContentText("Bạn có muốn sửa mặt hàng này không ?");
                    al.showAndWait().ifPresent(res->{
                        TableCell cell = (TableCell)((Button)et.getSource()).getParent();
                        HangHoa han = (HangHoa) cell.getTableRow().getItem();
                    try{
                        fixHangHoa(han.getId_hh());
                        this.table.getItems();
                        
                        ThongBao.getBox("Sửa thành công ", Alert.AlertType.INFORMATION);
                        } catch (SQLException ex) {
                            ThongBao.getBox("Sửa thất bại  " +ex.getMessage(), Alert.AlertType.INFORMATION);
                        } catch (IOException ex) {
                            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });    
                 });     
                TableCell cell = new TableCell();
                cell.setGraphic(btn);
                return cell;
        
        });
        this.table.getColumns().addAll(colID,colName,colGia,colXuatXu,colMoTa,colSoLuong,delColumn,fixColumn);
        
        
    }
    
    private void loadTableData(String kw) throws SQLException{
        HangHoaService d = new HangHoaService();
        this.table.setItems(FXCollections.observableList(d.napHangHoa(kw)));
    }
        public void dangKyUser(ActionEvent event) throws IOException{
         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("dangky.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();                   
                    stage.setScene(scene);
                    stage.setTitle("Đăng ký");
                    stage.show();
     }
            public void fixHangHoa(String id) throws IOException, SQLException {
            HangHoa a = new HangHoa();
            HangHoaService b = new HangHoaService();
            ArrayList<HangHoa> c = (ArrayList<HangHoa>) b.getHangHoa();
            for(int i=0;i<=c.size();i++){
                if(id.equals(c.get(i).getId_hh())){
                        
                        tf0.setText(c.get(i).getId_hh());
                        tf1.setText(c.get(i).getTenhang());
                        tf2.setText(c.get(i).getGiahang());
                        tf3.setText(c.get(i).getXuatxu());
                        tf4.setText(c.get(i).getMota());
                        tf5.setText(c.get(i).getSoluong());
                        
                }
                    
                    
            }
           
    }
            


}
