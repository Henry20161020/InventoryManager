/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author xiaoh
 */
public class TransactionController implements Initializable {
    @FXML
    private Label label_title;
    @FXML
    private TextField txtfield_product_id, txtfield_description,txtfield_location,txtfield_qty,txtfield_price;
    @FXML
    private Button btn_cancel, btn_save;
    
    FXMLDocumentController myController;
    
    
    public void setScreenParent(FXMLDocumentController screenParent) {
        myController=screenParent;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void initdata(Transaction transaction, Product product){
        label_title.setText(transaction.getTransactionType().toUpperCase());
        if (product!=null) {
            txtfield_product_id.setText(product.getProductID());
            txtfield_description.setText(product.getProductDescription());
            txtfield_location.setText(product.getLocation().toString());
        }
        btn_cancel.setOnAction(e->{
            Stage stage = (Stage) btn_cancel.getScene().getWindow();
            stage.close();
        });
        btn_save.setOnAction(e->{
            transaction.setProductID(txtfield_product_id.getText());
            transaction.setTransactionDate(new Date());
            transaction.setTransactionQty(Double.parseDouble(txtfield_qty.getText()));
            transaction.setTransactionUnitPrice(Double.parseDouble(txtfield_price.getText()));
            
            if (transaction.getTransactionType()=="receive")
                if(product==null) {
                    product.setProductID(txtfield_product_id.getText());
                        
//                        txtfield_description.getText(),
//                        Location.valueOf(txtfield_location.getText()),
//                        Double.parseDouble(txtfield_qty.getText()),
//                        Double.parseDouble(txtfield_price.getText()),
//                        Double.parseDouble(txtfield_price.getText())
                } else {

//                    product.setProductID(txtfield_product_id.getText());
//                    product.setProductDescription(txtfield_description.getText());
                    product.setPurchasingPrice((product.getPurchasingPrice()*product.getQty()
                            +Double.parseDouble(txtfield_qty.getText())*Double.parseDouble(txtfield_price.getText()))
                            /(product.getQty()+Double.parseDouble(txtfield_qty.getText())));
                    product.setQty(product.getQty()+Double.parseDouble(txtfield_qty.getText()));
                }
            
        });
        
    }
    
}
