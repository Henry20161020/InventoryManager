/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author xiaoh
 */
public class InventoryManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        
        Parent loginRoot=FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene=new Scene(loginRoot);
        Stage loginStage=new Stage();
        loginStage.setScene(loginScene);
        
        loginStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
