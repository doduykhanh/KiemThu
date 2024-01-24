/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.khoa.kiemthu;

import User.UserInf;
import User.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class LoginController implements Initializable {
//    String username= "khoa";
//    String password="1";
    String role1="admin.fxml";
    String role2="user.fxml";
    String dkform="dangky.fxml";
    @FXML
    private ComboBox comb;
    @FXML
    private Button vao;
    
     @FXML
    private Button dangky;
    
    @FXML
    
    private TextField user;
    @FXML
    
    private PasswordField pass;
    
//    public String getText(){
//        String name =this.user.getText();
//        return name;
//    }  
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               ObservableList a = FXCollections.observableArrayList("Admin", "Nhân Viên");
               comb.setItems(a);
               }
//     public void dangKyUser(ActionEvent event) throws IOException{
//         loadWindow(dkform);
//     }

    public void handleLog(ActionEvent event) throws IOException, SQLException
    {   
          String name = user.getText();
          String passw = pass.getText();
//          String role = comb.getValue().toString();
          
          UserService s = new UserService();
          if(comb.getValue()=="Admin"){
            if(user.getText().isEmpty() || pass.getText().isEmpty()){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText(null);
                  alert.setContentText("Không được trống thông tin");
                  alert.showAndWait();
              }else 
                if(s.checkUserIinf(name,passw)==true)
                    loadWindow(role1);
                else{
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setHeaderText(null);
                      alert.setContentText("Thông tin sai");
                      alert.showAndWait();
                  }
            }
            if(comb.getValue()=="Nhân Viên"){
                if(user.getText().isEmpty() || pass.getText().isEmpty()){
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setHeaderText(null);
                      alert.setContentText("Không được trống thông tin");
                      alert.showAndWait();
                  }else 
                    if(s.checkUserIinf(name,passw)==true){
                        
                        loadWindow(role2);
                    }
                    else{
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setHeaderText(null);
                          alert.setContentText("Thông tin sai");
                          alert.showAndWait();
                      }
            }
                  
              
          

//        if(comb.getValue()=="Admin"){
//            if(name.isEmpty() || passw.isEmpty()){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Không được trống thông tin");
//                alert.showAndWait();
//            }else 
//                { if (name.equals(username)&&passw.equals(password))
//                            loadWindow(role1);
//
//                else{
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Thông tin sai");
//                    alert.showAndWait();
//                }
//            }
//        
//        }
//        if(comb.getValue()=="Nhân Viên"){
//            if(name.isEmpty() || passw.isEmpty()){
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Không được trống thông tin");
//                alert.showAndWait();
//            }else 
//                { if (name.equals(username)&&passw.equals(password))
//                            loadWindow(role2);
//
//                else{
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Thông tin sai");
//                    alert.showAndWait();
//                }
    }
//        
        
    
    

    public void loadWindow(String role) throws IOException{
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(role));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                  
                    stage.setScene(scene);
                    stage.setTitle(role);
                    stage.show();
    }

    
}
