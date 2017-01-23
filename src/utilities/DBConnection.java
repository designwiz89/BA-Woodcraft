/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Lemuel Apa
 */
public class DBConnection {
    Connection conn = null;
    public static Connection connectDb(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bawcraftdb","root","");
            return conn;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
