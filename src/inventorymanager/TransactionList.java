/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author xiaoh
 */
public class TransactionList {
    private ObservableList<Transaction> transactionList=FXCollections.observableArrayList();
    
    public boolean loadFromFile(String fileName) {
        File file=new File(fileName);
        Scanner scan=null;
        try {
           scan = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scan.hasNext()){
            String[] result=(scan.nextLine()).split(Pattern.quote("|"));
            try {
                transactionList.add(
                        new Transaction(
                                result[0],
                                result[1],
                                Double.parseDouble(result[2]),
                                Double.parseDouble(result[3]),
                                new SimpleDateFormat("yyyymmdd").parse(result[4])
                        )
                );
            } catch (ParseException ex) {
                Logger.getLogger(TransactionList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        scan.close();
        return true;
    }
}
