/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.io.File;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xiaoh
 */
public class Transaction {
    private String productID;
    private String transactionType;
    private double transactionQty;
    private Date transactionDate;
    private double transactionUnitPrice;
    
    public boolean writeToFile(String fileName)  {
        File file=new File(fileName);
        FileWriter fw=null;
        PrintWriter pw=null;

        try {
            fw = new FileWriter(file,true);
        } catch (IOException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        pw=new PrintWriter(fw);
        pw.printf("%s|%s|%.2f|%.2f|%s\n", 
            getProductID(),
            getTransactionType(),
            getTransactionQty(),
            getTransactionUnitPrice(),
            new SimpleDateFormat("yyyyMMdd").format(getTransactionDate())
            );
        pw.close();
        try {
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

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

    
    public Transaction(String productID,String type){
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
