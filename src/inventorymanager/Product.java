/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

/**
 *
 * @author xiaoh
 */
public class Product {
    private String productID;
    private String productDescription;
    private Location location;
    private double qty;
    private double sellingPrice;
    private double purchasingPrice;
    
    public static String[] getSortKey(){
        String[] result={"Product ID","Description", "Location", "Qty", "Selling Price", "Purchasing Price"};
        return result;
    }
}
