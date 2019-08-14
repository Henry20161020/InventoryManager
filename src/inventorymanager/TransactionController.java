/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;
import javax.swing.JOptionPane;

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
    
    FXMLDocumentController myParent;
    private String transactionType;
    private boolean process=true;
    
    private boolean validate() {
        if (!Validator.isDouble(txtfield_qty.getText()) || !Validator.isDouble(txtfield_price.getText())) {
            JOptionPane.showMessageDialog(null, "Incorrect format of qty or price ", "alert", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Location.contains(txtfield_location.getText())){
            JOptionPane.showMessageDialog(null, "Incorrect location ", "alert", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validator.isInRange(Double.parseDouble(txtfield_qty.getText()),0.0, 1000000.0)) {
            JOptionPane.showMessageDialog(null, "Qty must be between 0 and 1,000,000", "alert", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validator.isInRange(Double.parseDouble(txtfield_price.getText()),0.0, 10000.0)) {
            JOptionPane.showMessageDialog(null, "Price must be between 0 and 10,000", "alert", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    
    public void setScreenParent(FXMLDocumentController screenParent) {
        myParent=screenParent;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void initdata(Transaction transaction, Product product){
        this.transactionType=transaction.getTransactionType();
        label_title.setText(transactionType.toUpperCase());
        
        txtfield_product_id.setText(product.getProductID());
        if (product.getProductDescription()!=null) {
            txtfield_description.setText(product.getProductDescription());
            txtfield_location.setText(product.getLocation().toString());
        } 
        if (product.getProductDescription()==null||transaction.getTransactionType().equals("adjust")) {
            txtfield_description.setDisable(false);
            txtfield_location.setDisable(false);
        }
        if (transaction.getTransactionType().equals("adjust")){
            txtfield_qty.setText(String.valueOf(product.getQty()));
            txtfield_price.setText(String.valueOf(product.getPurchasingPrice()));
        }

        btn_cancel.setOnAction(e->{
            Stage stage = (Stage) btn_cancel.getScene().getWindow();
            stage.close();
        });
        btn_save.setOnAction(e->{
            if (validate()) {

                process=true;
                if (transactionType.equals("receive")) {
                    product.setPurchasingPrice(Math.round((product.getPurchasingPrice()*product.getQty()
                            +Double.parseDouble(txtfield_qty.getText())*Double.parseDouble(txtfield_price.getText()))
                            /(product.getQty()+Double.parseDouble(txtfield_qty.getText()))*100.0)/100.0);
                    product.setQty(product.getQty()+Double.parseDouble(txtfield_qty.getText()));
                    if (product.getProductDescription()==null) {
                        product.setProductDescription(txtfield_description.getText());
                        product.setLocation(Location.valueOf(txtfield_location.getText()));
                        myParent.getAllInventory().add(product);

                    }


                }  
                else if  (transactionType.equals("deliver")) {
                    if (Double.parseDouble(txtfield_qty.getText())<=product.getQty()) {
                        product.setSellingPrice(Double.parseDouble(txtfield_price.getText()));   
                        product.setQty(product.getQty()-Double.parseDouble(txtfield_qty.getText()));
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Deliver qty can't exceed on-hand qty", "alert", JOptionPane.ERROR_MESSAGE);
                        process=false;
                    }
                }
                else {
                    product.setProductDescription(txtfield_description.getText());
                    product.setLocation(Location.valueOf(txtfield_location.getText()));
                    product.setPurchasingPrice(Double.parseDouble(txtfield_price.getText()));
                    product.setQty(Double.parseDouble(txtfield_qty.getText()));
                }
                if (process==true) {
                    transaction.setProductID(txtfield_product_id.getText());
                    transaction.setTransactionDate(new Date());
                    transaction.setTransactionQty(Double.parseDouble(txtfield_qty.getText()));
                    transaction.setTransactionUnitPrice(Double.parseDouble(txtfield_price.getText()));

                    myParent.getAllInventory().writeToFile("inventory.dat");
                    transaction.writeToFile("transaction.dat");
                    myParent.display();
                    ((Stage) btn_save.getScene().getWindow()).close();
                }
            }
        });
        
    }
    
}
