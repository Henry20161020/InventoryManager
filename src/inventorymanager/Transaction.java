/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.util.Date;

/**
 *
 * @author xiaoh
 */
public class Transaction {
    private String productID;
    private String transactionType;
    private double transactionQty;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionQty() {
        return transactionQty;
    }

    public void setTransactionQty(double transactionQty) {
        this.transactionQty = transactionQty;
    }

    public double getTransactionUnitPrice() {
        return transactionUnitPrice;
    }

    public void setTransactionUnitPrice(double transactionUnitPrice) {
        this.transactionUnitPrice = transactionUnitPrice;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    private double transactionUnitPrice;
    private Date transactionDate;
    
    public Transaction(String productID, String type){
        this.productID=productID;
        this.transactionType=type;
    }
    
    public Transaction(String productID, String transactionType, double transactionQty, double transactionUnitPrice, Date transactionDate) {
        this.productID = productID;
        this.transactionType = transactionType;
        this.transactionQty = transactionQty;
        this.transactionUnitPrice = transactionUnitPrice;
        this.transactionDate = transactionDate;
    }
}
