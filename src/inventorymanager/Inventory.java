/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author xiaoh
 */
public class Inventory {
    private ObservableList<Product> inventory=FXCollections.observableArrayList();
    
    public boolean loadFromFile(String fileName)  {
        File file=new File(fileName);
        Scanner scan=null;
        try {
           scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scan.hasNext()){
            String[] result=(scan.nextLine()).split(Pattern.quote("|"));
            inventory.add(
                    new Product(
                            new SimpleStringProperty(result[0]),
                            new SimpleStringProperty(result[1]),
                            Location.valueOf(result[2]),
                            new SimpleDoubleProperty(Double.parseDouble(result[3])),
                            new SimpleDoubleProperty(Double.parseDouble(result[4])),
                            new SimpleDoubleProperty(Double.parseDouble(result[5]))
                    )
            );
        }
        scan.close();
        return true;
    }

    public ObservableList<Product> getInventory() {
        return inventory;
    }

    public void setInventory(ObservableList<Product> inventory) {
        this.inventory = inventory;
    }
    
    public Inventory findProductByDescription(String filterkey) {
        Inventory result=new Inventory();
        return result;
    }
    
    public Inventory sortBy(String sortkey) {
        Inventory result=new Inventory();
        return result;
    }
}
