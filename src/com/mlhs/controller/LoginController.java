package com.mlhs.controller;
import com.mlhs.Asistencia;
import com.mlhs.util.SceneManager;
import org.mindrot.jbcrypt.BCrypt; 
import com.mlhs.model.UserModel;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
/**
 *
 * @author magde
 */
public class LoginController {
    
    private final SceneManager sceneManager;
    private final Alert menssageAlert; 
    
    //inyecion de dependencias 
    public LoginController(SceneManager sceneManager){
     this.sceneManager = sceneManager; 
     this.menssageAlert = new Alert(Alert.AlertType.INFORMATION); 
    }
    

    
public void handleLogin(String nombre, String contrasena) {

    if (nombre == null || nombre.isBlank() ||
        contrasena == null || contrasena.isBlank()) {
        System.out.println("Datos incompletos");
        return;
    }

    String hashBD = UserModel.getHashUser(nombre);

    if (hashBD == null) {
        System.out.println("Usuario no existe");
        return;
    }

    if (BCrypt.checkpw(contrasena, hashBD)) {
        sceneManager.showDashBoardView();
    } else {
        System.out.println("Contraseña incorrecta" + hashBD);
    }
}

    public void handleLoginConTarjeta(String codigoTarjeta){
        String carnet = UserModel.getCarneUser(codigoTarjeta);
       // System.out.println(codigoTarjeta+"carnet" + carnet);
        
        if(carnet == null ? codigoTarjeta != null : !carnet.equals(codigoTarjeta)){
            System.out.println("usuario no registrador");
        }else{
        sceneManager.showDashBoardView();
        }
    }
    
    public void handleRegister(String codigoTarjeta){
       // menssageAlert.initModality(Modality.WINDOW_MODAL);
        String carnet = UserModel.getCarneUser(codigoTarjeta);
       // System.out.println(codigoTarjeta+"carnet" + carnet);
        
        if(carnet == null ? codigoTarjeta != null : !carnet.equals(codigoTarjeta)){
            System.out.println("usuario no registrador");
              
        }else{
          sceneManager.showRegisterView();
        }

    }
    
   
    
}
