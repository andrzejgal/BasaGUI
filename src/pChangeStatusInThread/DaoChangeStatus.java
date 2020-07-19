/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pChangeStatusInThread;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import pDataBase1.DAOfunctions;
import pDataBase1.*;
import pGlobals.globals;

/**
 *
 * @author admin
 */
public class DaoChangeStatus implements Runnable{
    private Connection ConnChangeStatus;
    private DAOfunctions daof;
    private String EPc;
    private String tableName;
    private String status;
    protected Thread       runningThread= null;
    static public  Logger LabelChngeStatus=Logger.getLogger(globals.DataBaseConnectionClassLoaderName);
    private String ClassName=null;
    private String MethodName=null;
    
    public DaoChangeStatus(String TableName,String EPC,Connection Conn) {
        EPc=EPC;
        tableName=TableName;
        ClassName=this.getClass().getCanonicalName();
        MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        
    }
    
    @Override
    public void run() {
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        try {
            LabelChngeStatus.entering(ClassName,MethodName);
//            System.out.println("Początek "+System.currentTimeMillis());
             ConnChangeStatus=StaticConnectToDatabase.Connect();
            daof=new DAOfunctions(ConnChangeStatus);
            status=daof.ReadLabelStatus(EPc, tableName);
            String odp=null;
            if (status.equals(globals.NOT_SOLD)) odp=globals.SOLD;
             else
            odp=globals.NOT_SOLD;
            daof.ChangeStatus(globals.TableName, odp,EPc);
            LabelChngeStatus.exiting(ClassName,MethodName);
//            System.out.println("Koniec : "+System.currentTimeMillis());
        }
        catch (DAOfunctionException | StaticConnectToDatabaseException e) {
            String kom="Wyjątek przy zaapise zmiany status :"+e;
           LabelChngeStatus.logp(Level.SEVERE,ClassName,MethodName,kom);
            new RuntimeException(kom);
        }
        finally {
            try {
                if (ConnChangeStatus!=null) ConnChangeStatus.close();
            }
            catch (SQLException e) {
                String kom="Wyjątek przy zamknięciu połączenia dla zmiany statusu :"+e;
               LabelChngeStatus.logp(Level.SEVERE,ClassName,MethodName,kom);
                new RuntimeException(kom);
            }
        }

        
    }       
    
}
