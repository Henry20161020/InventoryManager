/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

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
    
    public Product(String productID, String productDescription, Location location, double qty, double sellingPrice, double purchasingPrice) {
        this.productID = productID;
        this.productDescription = productDescription;
        this.location = location;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
        this.purchasingPrice = purchasingPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(double purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }
    
    public static String[] getSortKey(){
        String[] result={"Product ID","Description", "Location", "Qty", "Selling Price", "Purchasing Price"};
        return result;
    }
}
