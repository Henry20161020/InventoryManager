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
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

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
    // Wrap allInventory in a FilteredList (initially display all data).
    FilteredList<Product> filteredData = new FilteredList<>(allInventory.getInventory(), p -> true);
    // Wrap the FilteredList in a SortedList. 
    SortedList<Product> sortedData = new SortedList<>(filteredData);
    Product selectedProduct=null;

    public Inventory getAllInventory() {
        return allInventory;
    }

    public void setAllInventory(Inventory allInventory) {
        this.allInventory = allInventory;
    }
    
    public void display() {

        tblview_inventory.setItems(sortedData);
        tblview_inventory.refresh();
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
    private void delete(ActionEvent event){
        Product product=tblview_inventory.getSelectionModel().getSelectedItem();
        if (product==null) JOptionPane.showMessageDialog(null, "Please select a product.", "alert", JOptionPane.ERROR_MESSAGE);
        else {
            allInventory.remove(product);

            allInventory.writeToFile("inventory.dat");
            display();
        }
    }
    
    @FXML
    private void transact(ActionEvent event) {
        Product product=tblview_inventory.getSelectionModel().getSelectedItem();
        String transactionType=((Button)event.getSource()).getId().split(Pattern.quote("_"))[1];
        Transaction transaction=null;
        if (product==null && !transactionType.equals("receive"))
                JOptionPane.showMessageDialog(null, "Please select a product.", "alert", JOptionPane.ERROR_MESSAGE);
        else {
            if (product==null) {
                transaction=new Transaction(allInventory.getNextID(),transactionType);
                product=new Product(allInventory.getNextID());
            }
            else   
                transaction=new Transaction(product.getProductID(),transactionType);
            Stage stage=showTransactionDialog(transaction,product);
            stage.show();
        }
    }
    
    public void releaseFocus(MouseEvent e){
            tblview_inventory.getSelectionModel().clearSelection();
    }
    
    private Stage showTransactionDialog(Transaction transaction,Product product) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaction.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(new Scene((Pane)loader.load()));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TransactionController controller = loader.<TransactionController>getController();
        controller.setScreenParent(this);
        controller.initdata(transaction,product);
        stage.initModality(Modality.APPLICATION_MODAL);  // Use this to make the 2nd window modal (must close 2nd window to return to main window)
        return stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        allInventory.loadFromFile("inventory.dat");

        column_productID.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
        column_description.setCellValueFactory(new PropertyValueFactory<Product, String>("productDescription"));
        column_location.setCellValueFactory(new PropertyValueFactory<Product, String>("location"));
        column_selling_price.setCellValueFactory(new PropertyValueFactory<Product, String>("sellingPrice"));
        column_purchasing_price.setCellValueFactory(new PropertyValueFactory<Product, String>("purchasingPrice"));
        column_qty.setCellValueFactory(new PropertyValueFactory<Product, String>("qty"));
        
//        tblview_inventory.focusedProperty().addListener((obs, oldVal, newVal) -> {
//            if (!newVal) {
//                tblview_inventory.getSelectionModel().clearSelection();
//            }
//            else
//                selectedProduct=tblview_inventory.getSelectionModel().getSelectedItem();
//        });

        
        
        
        // Set the filter Predicate whenever the filter changes.
        textfield_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                // If filter text is empty, display all products.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare product description with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (product.getProductDescription().toLowerCase().contains(lowerCaseFilter))
                    return true; // Filter matches product description
                else
                    return false; // Does not match.
            });
        });
        

        
        // Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tblview_inventory.comparatorProperty());
        
        display();
        
    }    
    
}
