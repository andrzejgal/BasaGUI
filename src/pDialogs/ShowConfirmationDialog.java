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
public class ShowConfirmationDialog extends JOptionPane {
 
    
   
    static public int ShowConfirmtionDialog(final Component cm,
                                           final Object message,
                                           final String title,
                                           final Integer type) {
    
    return showConfirmDialog(cm,message,title,type);
    
}
  

    
            
}
