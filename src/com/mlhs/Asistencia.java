/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mlhs;


import com.mlhs.controller.RegisterController;
import javafx.application.Application;
import javafx.stage.Stage;
import com.mlhs.util.SceneManager;
import javafx.stage.Modality;



/**
 *
 * @author magde
 */
public class Asistencia extends Application {
    private static Stage primary; 
    
    @Override
    public void start(Stage mainScene) {
      primary = mainScene; 
      
      mainScene.setTitle("Asistencia");
      SceneManager manager = new SceneManager(primary);
      manager.showLoginView();
      mainScene.show(); 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
