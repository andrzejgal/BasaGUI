/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDialogs;

import javax.swing.JOptionPane;
import java.awt.Component;


/**
 *
 * @author Andrzej
 */
public class ShowSimplestInputDialog extends JOptionPane{
    
    static public String ShowSimplestInputDialog(Component parent,Object message) {
         return showInputDialog(parent, message);
    }
    

    
}
