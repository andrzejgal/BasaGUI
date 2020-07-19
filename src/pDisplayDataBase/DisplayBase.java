/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDisplayDataBase;
import java.awt.EventQueue;
import pDialogs.ExceptionMessage;
import pMainWindow.Okno;
//import pMainWindow.showEtykietyException;

/**
 *
 * @author admin
 */
public class DisplayBase implements Runnable {
    
    public void run() {
        try {
            while(true) {
                 EventQueue.invokeLater(new Runnable()  {
                     public void run() {
//                         try {
                              Okno.RefreshTable();
 //                             }
 //                        catch ( showEtykietyException e) {                            
//                         }
                     };
               });
                     Thread.sleep(200);
             }//while(true)
            
             }
        catch (InterruptedException ex) {
            
        }//try - catch
    
}//run
    
}
/*    
 @Override
  public void run() {
   while(true) {
       try {
            Thread.sleep(200);
            Okno.RefreshTable();
//                Okno.repaint();
            }
      catch (InterruptedException | showEtykietyException e) {
      ExceptionMessage.ShowExceptionMessageAndExit(" exception in DisplayBase");
          }
   }
}
}
*/