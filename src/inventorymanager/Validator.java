package inventorymanager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xiaoh
 */
public class Validator {
    public static boolean isDouble(String s){
        try{
            Double.parseDouble(s);
        } catch(Exception e){
            return false;
        }
        return true;
    }
    
    
    public static boolean isInRange(double v, double min, double max) {
        if (v>=min && v<=max) return true;
        else return false;
    }
}
