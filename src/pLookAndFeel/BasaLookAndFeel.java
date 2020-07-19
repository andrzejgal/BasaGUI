/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pLookAndFeel;

import java.util.logging.Level;   
import java.util.logging.Logger;
import pParentClass.ParentClass;
import pGlobals.globals;
import pLogging.LoggingConfigException;

/**
 *
 * @author Andrzej
 */
public class BasaLookAndFeel extends ParentClass{
    
    static public  Logger LookAndFeelLogger=Logger.getLogger(globals.LookAndFeelLogger);
    
    
   @Override
    protected void ExceptionHandler(Exception e, String kom) throws  LookAndFeelException {
/*
        if (ExceptionDescription) {
            LookAndFeelLogger.log(Level.SEVERE, kom, e);
            System.exit(-1);
        } else {
            throw new LookAndFeelException(kom);
        }
    */
    }//protected void ExceptionHandler(Exception e,String kom
    

    
    public void CreateLookAndFeel ( final String name) throws LookAndFeelException {
     /*
                For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        String kom=this.getClass().getCanonicalName();
        String kom1=Thread.currentThread().getStackTrace()[1].getMethodName();
         LookAndFeelLogger.entering(kom1, kom1);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (name.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
          LookAndFeelLogger.exiting(kom, kom1);
        } 
        catch (ClassNotFoundException ex) {
            ExceptionHandler(ex,"Look and Feel  - nie znaleziono klasy");      
        } catch (InstantiationException ex) {
            ExceptionHandler(ex,"Look and Feel  - wyjątek instancji");   
        } catch (IllegalAccessException ex) {
            ExceptionHandler(ex,"Look and Feel  - nielegalny dostęp");    
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ExceptionHandler(ex,"Look and Feel  - niewspierany wyjatek");
        }

    }
        //</editor-fold>

    
    
    
    
}
