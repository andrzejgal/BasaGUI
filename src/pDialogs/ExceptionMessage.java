/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDialogs;

import javax.swing.JOptionPane;
import java.awt.Component;
import pGlobals.globals;
import java.util.logging.*;
import pLogging.LoggerConfigurationClass;

/**
 *
 * @author Andrzej
 */
public class ExceptionMessage extends JOptionPane{
    
    
    static public void ShowExceptionMessage(/*final Component parent,*/final String message) {
        showMessageDialog(null, message, "Poważny błąd",JOptionPane. ERROR_MESSAGE);
    }
    
/*
     static public void ShowExceptionMessageAndExit(final String message, final int errorCode) {
        showMessageDialog(null, message, "Poważny błąd - kod wyjścia:  "+errorCode,JOptionPane. ERROR_MESSAGE);
//        LoggerConfigurationClass.CloseFileHandler();
 //       System.exit(errorCode);
    }
*/
    
     static public void ShowExceptionMessageAndExit1(final String message, final int errorCode) {
        showMessageDialog(null, message, "Poważny błąd - kod wyjścia:  "+errorCode,JOptionPane. ERROR_MESSAGE);
//        LoggerConfigurationClass.CloseFileHandler();
        System.exit(errorCode);
    }

 
/*
      static public void ShowExceptionMessageAndExit(final String message) {
        showMessageDialog(null, message+globals.NEW_LINE, "Poważny błąd - program zostanie przerwany",JOptionPane. ERROR_MESSAGE);
          System.exit(0);
    }
*/    

    
}
