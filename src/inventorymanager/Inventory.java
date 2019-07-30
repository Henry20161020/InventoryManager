/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author xiaoh
 */
public class Inventory {
    private ArrayList<Product> inventory=new ArrayList();
    
    public boolean loadFromFile(String fileName) {
        return true;
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
