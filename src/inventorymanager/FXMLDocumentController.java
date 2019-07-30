/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        Stage stage=showTransactionDialog(product);
    }
    
    private Stage showTransactionDialog(Product product) {
        Stage stage=new Stage();
        return stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        allInventory.loadFromFile("inventory.dat");
        displayInventory=allInventory;
        display();
        combo_sort.getItems().addAll(FXCollections.observableArrayList(Product.getSortKey()));
    }    
    
}
