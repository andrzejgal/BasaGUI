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
public class ShowStandartMessage extends JOptionPane{
    
    static public void ShowStandartMessage(final Component cm,
                                           final Object message,
                                           final String title,
                                           final Integer type) {
        
        showMessageDialog(cm,message,title,type);
        
    }
    
}
