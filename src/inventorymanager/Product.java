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

    private SimpleStringProperty productID;
    private SimpleStringProperty productDescription;
    private Location location;
    private SimpleDoubleProperty qty;
    private SimpleDoubleProperty sellingPrice;
    private SimpleDoubleProperty purchasingPrice;
    
    public Product(SimpleStringProperty productID, SimpleStringProperty productDescription, Location location, SimpleDoubleProperty qty, SimpleDoubleProperty sellingPrice, SimpleDoubleProperty purchasingPrice) {
        this.productID = productID;
        this.productDescription = productDescription;
        this.location = location;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
        this.purchasingPrice = purchasingPrice;
    }

    public String getProductID() {
        return productID.get();
    }

    public void setProductID(SimpleStringProperty productID) {
        this.productID = productID;
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public void setProductDescription(SimpleStringProperty productDescription) {
        this.productDescription = productDescription;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getQty() {
        return qty.get();
    }

    public void setQty(SimpleDoubleProperty qty) {
        this.qty = qty;
    }

    public double getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(SimpleDoubleProperty sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getPurchasingPrice() {
        return purchasingPrice.get();
    }

    public void setPurchasingPrice(SimpleDoubleProperty purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }
    
    public static String[] getSortKey(){
        String[] result={"Product ID","Description", "Location", "Qty", "Selling Price", "Purchasing Price"};
        return result;
    }
}
