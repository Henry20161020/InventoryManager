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

    private SimpleStringProperty productID=new SimpleStringProperty();
    private SimpleStringProperty productDescription=new SimpleStringProperty();
    private Location location;
    private SimpleDoubleProperty qty=new SimpleDoubleProperty();
    private SimpleDoubleProperty sellingPrice=new SimpleDoubleProperty();
    private SimpleDoubleProperty purchasingPrice=new SimpleDoubleProperty();
    
    public Product(String productID, String productDescription, Location location, double qty, double sellingPrice,double purchasingPrice ) {
        this.productID.set(productID);
        this.productDescription.set( productDescription);
        this.location = location;
        this.qty.set(qty);
        this.sellingPrice.set(sellingPrice);
        this.purchasingPrice.set( purchasingPrice);
    }
    
    public Product(String productID){
        this.productID.set( productID);
    }

    public String getProductID() {
        return productID.get();
    }

    public void setProductID(String productID) {
        this.productID.set( productID);
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public void setProductDescription(String productDescription) {
        this.productDescription.set(productDescription);
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

    public void setQty(double qty) {
        this.qty.set( qty);
    }

    public double getSellingPrice() {
        return sellingPrice.get();
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice.set( sellingPrice);
    }

    public double getPurchasingPrice() {
        return purchasingPrice.get();
    }

    public void setPurchasingPrice(double purchasingPrice) {
        this.purchasingPrice.set(purchasingPrice);
    }
    
    public static String[] getSortKey(){
        String[] result={"Product ID","Description", "Location", "Qty", "Selling Price", "Purchasing Price"};
        return result;
    }
}
