package com.mlhs.controller;
import com.mlhs.view.edit.EditInfoView;
import com.mlhs.util.SceneManager;

/**
 *
 * @author Magno Solis
 */
public class EditInfoController {
    
    private final SceneManager sceneManager; 
    private EditInfoView viewEditInfo ; 
    
    public EditInfoController(SceneManager sceneManager){
    this.sceneManager = sceneManager; 
    viewEditInfo = new EditInfoView(this); 
    setInfo(); 
    }
    
    public void cerrar(){   
    sceneManager.hideEditView();
    
    }
    
    public void setInfo(){
        
       
        
    }
}
