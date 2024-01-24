/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.khoa.kiemthu;

import Services.HangHoaService;
import User.UserInf;
import User.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class DangkyController implements Initializable {
    @FXML
    private ComboBox combo;
    @FXML TextField tendk;
    @FXML TextField passdk;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList a = FXCollections.observableArrayList("Admin", "Nhân Viên");
               combo.setItems(a);
               }
    public void dangKy(ActionEvent event){
        String name=tendk.getText();
        String pass=passdk.getText();
        if(name.isEmpty() || pass.isEmpty()){
            Alert ale = new Alert(Alert.AlertType.ERROR);
                ale.setHeaderText(null);
                ale.setContentText("Không được trống thông tin");
                ale.showAndWait();
                }
        else{
            UserInf user = new UserInf(UUID.randomUUID().toString(), name, pass, this.combo.getValue().toString());
            UserService hh= new UserService();
            try{
            hh.themNguoiDung(user);
            
            
            ThongBao.getBox("Them nguoi dung thanh cong !", Alert.AlertType.INFORMATION).show();
            }catch(SQLException ex){
             ThongBao.getBox("Them nguoi dung khong thanh cong !", Alert.AlertType.WARNING).show();       
            }
        }
    }

}
    
    
    

