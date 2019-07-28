/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author xiaoh
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField textfield_password;
    
    @FXML
    private Button button_signin;
    
    @FXML
    private void checkPassword(ActionEvent event) {
        if (textfield_password.getText().equals("admin")) {
            Stage stage = (Stage) button_signin.getScene().getWindow();
            stage.close();
        }else {
            JOptionPane.showMessageDialog(null, "Login Failure.", "alert", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
