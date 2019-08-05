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
public enum Location {
    Fruits,Vegetables,Beverages, Cleaning, Dairy;
    
    public static boolean contains (String s) {
        for (Location c : Location.values()) {
            if (c.name().equals(s)) {
                return true;
            }
        }

        return false;
    }
}
