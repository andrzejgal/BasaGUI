/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pServerWriteEPC;

import pFifo.FifoClass;
import java.util.logging.Level;     
import java.util.logging.Logger;
//import pBasaCmdLineInterfaces.ExceptionDescription;
import pCheckReadLabels.ReadLabelsList;
//import pDataBase.*;
//import static pDataBase.DAOFirst.DAOloggger;
import pDialogs.ExceptionMessage;
import pGlobals.globals;
import pLogging.LoggerConfigurationClass;
import pMainWindow.Okno;
//import pMainWindow.showEtykietyException;
//import pServerReadEPC.RFIDServer;


/**
 *
 * @author admin
 */
public class WritingToBase implements Runnable /*implements ExceptionDescription*/{
     public static Logger WritingToBaseLogger=Logger.getLogger(globals.WrittingEpcLogger);
     private Integer size=0;
      private Integer EpcCount=0;
      private boolean can_run=false,w_t_list=false;
    private int cnt=1;
    
    public WritingToBase() {
     }
    
    public Logger GetLoggger() {
        return WritingToBaseLogger;
    }
    
    @Override
    public void run () {
       String kom=this.getClass().getCanonicalName();
       String kom1=Thread.currentThread().getStackTrace()[1].getMethodName();
       WritingToBaseLogger.entering(kom, kom1);
//        w_t_list= ! FifoClass.GetWriteToListLock();
//       WritingToBaseLogger.logp(Level.FINEST, kom, kom1,"w_t_list="+String.valueOf(w_t_list));
        while(true) {
            can_run=globals.ReadingEPCFromAntenna /*&& w_t_list*/;
            if (can_run) {  
              while(FifoClass.Getsize()>0)
                {
                    WritingToBaseLogger.logp(Level.FINEST,kom,kom1,"writting="+String.valueOf(FifoClass.GetWritingStatus()));
                    while  (FifoClass.GetWritingStatus()) {};
                    String epc=FifoClass.ReadFromList();
                    WritingToBaseLogger.logp(Level.FINEST, kom, kom1,"EPC from list="+epc);
                    globals.LabelList.AddRow(cnt, epc);
                    cnt++;
            }//while(FifoClass.Getsize()>0)
 //    FifoClass.SetReadFromListLock(false);
      }//while(can_run)
        }      
//    WritingToBaseLogger.exiting(kom, kom1);
    }//public void run ()
    
}
