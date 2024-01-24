/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.khoa.kiemthu;

import javafx.scene.control.Alert;

/**
 *
 * @author ACER
 */
public class ThongBao {
        public static Alert getBox(String content,Alert.AlertType type){
            Alert a =new Alert(type);
            a.setContentText(content);
            return a;
        }
}
