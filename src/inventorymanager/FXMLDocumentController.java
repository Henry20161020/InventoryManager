/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author xiaoh
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button button_search, button_receive, button_deliver, button_adjust, button_delete;
    @FXML
    private TextField textfield_search;
    @FXML
    private ComboBox combo_sort;
    @FXML
    private TableView<Product> tblview_inventory;
    @FXML
    private TableColumn column_productID,column_description,column_location,column_selling_price,column_purchasing_price,column_qty;
    
    private Inventory allInventory=new Inventory();
    private Inventory displayInventory=new Inventory();
    
    private void display() {
    
    }
    
    @FXML
    private void filter(ActionEvent event) {
        displayInventory=displayInventory.findProductByDescription(textfield_search.getText());
        display();
    }
    
    @FXML
    private void sort(ActionEvent event) {
        displayInventory=displayInventory.sortBy((String)combo_sort.getValue());
        display();
    }
    
    @FXML
    private void transact(ActionEvent event) {
        Product product=tblview_inventory.getSelectionModel().getSelectedItem();
        Transaction transaction=new Transaction(product);
        Stage stage=showTransactionDialog(transaction);
        stage.show();
    }
    
    private Stage showTransactionDialog(Transaction transaction) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaction.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane)loader.load()));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TransactionController controller = loader.<TransactionController>getController();
        controller.initdata(transaction);
        
        return stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        allInventory.loadFromFile("inventory.dat");
        displayInventory=allInventory;
        column_productID.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
        column_description.setCellValueFactory(new PropertyValueFactory<Product, String>("productDescription"));
        column_location.setCellValueFactory(new PropertyValueFactory<Product, String>("location"));
        column_selling_price.setCellValueFactory(new PropertyValueFactory<Product, String>("sellingPrice"));
        column_purchasing_price.setCellValueFactory(new PropertyValueFactory<Product, String>("purchasingPrice"));
        column_qty.setCellValueFactory(new PropertyValueFactory<Product, String>("qty"));
        
        tblview_inventory.setItems(displayInventory.getInventory());
        combo_sort.getItems().addAll(FXCollections.observableArrayList(Product.getSortKey()));
    }    
    
}
