/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Lemuel Apa
 */
public class Utils {
    
    public static void showMessage(Component o, String action){
        switch(action){
            case "insert":
                JOptionPane.showMessageDialog(o,"Record has been added successfully!","System Message",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "update":
                JOptionPane.showMessageDialog(o,"Record has been updated successfully!","System Message",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "delete":
                JOptionPane.showMessageDialog(o,"Record has been deleted successfully!","System Message",JOptionPane.INFORMATION_MESSAGE);
                break;
            case "error":
                JOptionPane.showMessageDialog(o,"Operation not allowed","System Message",JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
    
}
