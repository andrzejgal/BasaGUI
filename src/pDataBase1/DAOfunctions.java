/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDataBase1;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
//import static pDataBase.DAOFirst.DAOloggger;
import pGlobals.globals;
import pErrorCodes.*;
import pDialogs.*;
import java.util.Calendar;
import java.util.logging.Logger;





/**
 *plik założony 17-01-2019
 *zawiera klasę do operacji na bazie MySql
 *może być klasą bazową dla innych klas operujących na innych bazach danych
 * 
 * 27-01-2019 connection można podac w konstruktorze przy tworzzeniu klasy
 * @author dell
 */
public class DAOfunctions implements DaoInterface{
    private String className=this.getClass().getCanonicalName();
    private Connection conn;
    private static DataBaseTypesEnum DataBaseType;
     static public  Logger DAOloggger=Logger.getLogger(globals.DataBaseLoggerName);
//    private PreparedStatement ptt;
    
    public DAOfunctions( final Connection Conn) {
        assert Conn!=null:"Connection Conn w konstruktorze klasy DAOfunction jest null";
        conn=Conn;
    }
   
    public static void SetDataBaseType(final DataBaseTypesEnum Type) {
       DataBaseType=Type; 
    }
    
    public static DataBaseTypesEnum GetDataBaseType() {
        return DataBaseType;
    }
    
    @Override
    public synchronized  boolean InsertEPC(final String TableName,final String EPC, final String Status) throws DAOfunctionException {
        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        DAOloggger.entering(className, MethodName);
        DAOloggger.logp(Level.FINEST,className,MethodName,"Tablica :"+TableName);
        DAOloggger.logp(Level.FINEST,className,MethodName,"EPC :"+EPC);
        DAOloggger.logp(Level.FINEST,className,MethodName,"Status: :"+Status);
        boolean result=true;
        PreparedStatement ptt=null;
        String sql="INSERT INTO "+TableName + " (EPC ,"+ " Status) " +" VALUES ( ? ,  ?)";    
        try {
                    ptt=conn.prepareStatement(sql);
                    assert ptt!=null:"Assertion PreparedStatement in InsertEPC";
                    ptt.setString(1, EPC);
                    ptt.setString(2, Status);
                    ptt.executeUpdate();
                    DAOloggger.exiting(className, className);
    return result;
        }
        catch (SQLException e) {
            String df=e.toString();
            if ((df.contains("Duplicate entry")) || (df.contains("SQLITE_CONSTRAINT_UNIQUE"))) {
                result=false;
                return result;
            }
                    else {
                DAOloggger.logp(Level.FINE,className,MethodName,"Status: :"+Status);
                    int exit_code=ErrorCodes.InsertEPCError.ordinal();
                    String message="Błąd w funkcji InsertEPC( ";
                    DAOloggger.logp(Level.SEVERE,className,MethodName,e+"\n\r"+message);
                    DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                    throw dae;
            }
        }
        catch (Exception e) {
                    int exit_code=ErrorCodes.InsertEPCError.ordinal();
                    String message="Wyjątke ogólny w funkcji InsertEPC ";
                    DAOloggger.logp(Level.SEVERE,className,MethodName,e+"\n\r"+message);
                    DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                    throw dae;            
        }
        finally {
            try {
                if (ptt!=null) ptt.close(); 
            } catch (SQLException  e) 
                {
                    int exit_code=ErrorCodes.InsertEPCError.ordinal();
                    String message="Bład w zamknięciu prepareStatement";
                    DAOloggger.logp(Level.SEVERE,className,MethodName,e+"\n\r"+message);
                    DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                    throw dae;
                }//finally
        }
    }
    
    public boolean InsertEPC(final String TableName, final ArrayList<String> row) throws  DAOfunctionException {
        String className=this.getClass().getCanonicalName();
        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        boolean result=true;
        PreparedStatement ptt=null;
        DAOloggger.entering(className, MethodName);
        DAOloggger.logp(Level.FINEST,className,MethodName,"Tablica :"+TableName);
            Calendar currenttime = Calendar.getInstance();
            Date date = new Date((currenttime.getTime()).getTime());
        String sql="INSERT INTO "+TableName + " ( EPC ,Status,Data,Nazwa,Rozmiar,Kolor ) " +"  VALUES ( ? ,  ?,  ?,  ?,  ?,  ?)";    
        try {
                    ptt=conn.prepareStatement(sql);
                    assert ptt!=null:"Assertion PreparedStatement in InsertEPC";
                    ptt.setString(1, row.get(0));                   //EPC
                    ptt.setString(2,  row.get(1));                   //Status
                    ptt.setDate(3, date);                                   //data
//                    ptt.setString(3, row.get(2));                                   //data
                    ptt.setString(4,row.get(3));                   //Nazwa
                    ptt.setString(5,row.get(4));                    //Rozmiar
                    ptt.setString(6,row.get(5));                    //Kolor
                     ptt.execute();
                    DAOloggger.exiting(className, MethodName);
    return result;
        }
        catch (SQLException e) {
            String df=e.toString();
            if ((df.contains("Duplicate entry")) || (df.contains("SQLITE_CONSTRAINT_UNIQUE")))     {
                result=false;
                return result;
            }
                    else {
//                DAOloggger.logp(Level.FINE,className,MethodName,"Status: :"+Status);
                    int exit_code=ErrorCodes.InsertEPCError.ordinal();
                    String message="Błąd w funkcji InsertEPC( ";
                    DAOloggger.logp(Level.SEVERE,className,MethodName,e+"\n\r"+message);
                    DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                    throw dae;
            }
        }
        catch (Exception e) {
                    int exit_code=ErrorCodes.InsertEPCError.ordinal();
                    String message="Wyjątke ogólny w funkcji InsertEPC ";
                    DAOloggger.logp(Level.SEVERE,className,MethodName,e+"\n\r"+message);
                    DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                    throw dae;            
        }
        finally {
            try {
                if (ptt!=null) ptt.close(); 
            } catch (SQLException  e) 
                {
                    int exit_code=ErrorCodes.InsertEPCError.ordinal();
                    String message="Bład w zamknięciu prepareStatement";
                    DAOloggger.logp(Level.SEVERE,className,MethodName,e+"\n\r"+message);
                    DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                    throw dae;
 //                   ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji InsertEPC-zamknięcie prepareStatement",exit_code);                };
               }//finally
        }
    }
    
    
   @Override
    public synchronized void CreateTable(final String TableName) throws DAOfunctionException
    {
        Statement stat=null;
        final String MySql_create = "CREATE TABLE IF NOT EXISTS  " +TableName+
                   " ( Id INT NOT NULL AUTO_INCREMENT, " +
                   "  EPC  VARCHAR(24)  NOT NULL," + 
                    " Status SMALLINT(1) DEFAULT 1 , "+
                  "Data  DATETIME NOT NULL, "+
                  "Nazwa VARCHAR(50),"+
                   "Rozmiar  TINYTEXT,"+
                   "Kolor TINYTEXT,"+
                    "PRIMARY KEY (Id) ,"+
                   " UNIQUE KEY (EPC) )"+
                    "CHARACTER SET utf8,"+
                    "COLLATE utf8_polish_ci"; 
/*
         final String Sqlite_create = "CREATE TABLE IF NOT EXISTS "+  TableName+
                   " ( Id INTEGER  PRIMARY KEY NOT NULL   , " +
                   "  EPC  TEXT UNIQE," + 
                    " Status INTEGER DEFAULT 1 , "+
                  "Data  INTEGER NOT NULL, "+
                  "Nazwa TEXT,"+
                   "Rozmiar  TEXT,"+
                   "Kolor TEXT)";
 */
 
 final String Sqlite_create="CREATE TABLE IF NOT EXISTS main.etykiety ("+
   " Id      INTEGER     PRIMARY KEY AUTOINCREMENT NOT NULL,"+
   " EPC     TEXT (24)   UNIQUE  NOT NULL,"+
    "Status  INTEGER (1),"+
    "Data    DATE,"+
   " Nazwa   TEXT,"+
    "Rozmiar TEXT,"+
"    Kolor   TEXT )";
  
final String Sql_create="CREATE TABLE etykiety (\n" +
"  Id int not null IDENTITY(1,1) PRIMARY KEY ,\n" +
"  EPC VARCHAR(24) NOT NULL UNIQUE  ,\n" +
"  Status TINYINT DEFAULT 1,\n" +
"  Data DATETIME NOT NULL,\n" +
"  Nazwa VARCHAR(50),\n" +
"  Rozmiar VARCHAR(50),\n" +
"  Kolor VARCHAR(50),\n" +
")";

         

        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();

        try {
          DAOloggger.entering(className, MethodName);
          stat = conn.createStatement();
           assert stat!=null:"Assertion Statement in  CreateTable";
             if (DataBaseType==DataBaseTypesEnum.MySqlType) {
                  stat.execute(MySql_create);
           }
           else
            if (DataBaseType==DataBaseTypesEnum.LiteSqlType) {
                  stat.execute(Sqlite_create);
           }
            else
           if (DataBaseType==DataBaseTypesEnum.SqlType) {
                     stat.execute(Sql_create);
            }
           System.out.println("Table Created");
           DAOloggger.exiting(className, MethodName);
                   }

        catch (SQLException e) {
             if (DataBaseType==DataBaseTypesEnum.SqlType) {
                   String df=e.toString();
                   String kr="object named "+"'"+TableName+"'";
                   if (df.contains(kr))
                       return;
            }

            String message="Błąd w funkcji tworzenia tabelii";
            DAOloggger.logp(Level.SEVERE, className, MethodName,e+" \n\r"+message);
            int exit_code=ErrorCodes.CreateTableError.ordinal();
            DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
            throw dae;
    //        ExceptionMessage.ShowExceptionMessageAndExit("Błąd przy tworzeniu tabeli",exit_code);
        }
        catch (Exception e) {
             String message="Błąd ogólny w funkcji tworzenia tabelii";
            DAOloggger.logp(Level.SEVERE, className, MethodName,e+" \n\r"+message);
            int exit_code=ErrorCodes.CreateTableError.ordinal();
            DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
            throw dae;
           
        }
        finally {
                    try {
                          if (stat!=null) stat.close();
 //                        if (conn!=null) conn.close();
                     }
                    catch (SQLException e) {
                        String message="Błąd w funkcji tworzenia tabelii - błąd przy zamknięciu Statement";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+" \n\rBłąd w funkcji tworzenia tabelii - błąd przy zamknięciu Statement");
                        int exit_code=ErrorCodes.CreateTableErrorStatementClosing.ordinal();
    //                     ExceptionMessage.ShowExceptionMessageAndExit("Błąd przy tworzeniu tabeli - zamknięcie Statement",exit_code);
                        DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                       throw dae;
                    }
                }
    }
    
    @Override
    public synchronized ArrayList<String[]> ReadEPCs(final String TableName)  throws DAOfunctionException
    {
        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        Statement stat=null;
        ResultSet rsset=null;
        int index=0;
        String sql="SELECT * FROM "+TableName;
        ArrayList<String[]> ar=new ArrayList<>();
        try {
            stat=conn.createStatement();
            assert stat!=null:"Assertion Statement stat jest null w funckji ReadEPCs";
            rsset=stat.executeQuery(sql);
            assert rsset!=null:"Assertion ResultSet in funckji ReadEPCs";
//            String[] row=new String[10];
            while (rsset.next()) {
                ar.add(new String[] {
                    rsset.getString("Id"),
                    rsset.getString("EPC"),
                    rsset.getString("Status"),
                    rsset.getString("Data"),
                    rsset.getString("Nazwa"),
                    rsset.getString("Rozmiar"),
                    rsset.getString("Kolor")
                            });
            };
            return ar;
        }
       catch (SQLException e) {
           String message="Błąd funkcji readEPCs";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ReadEPCsError.ordinal();
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
       catch (Exception e) {
           String message="Błąd ogólny w funkcji readEPCs";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ReadEPCsError.ordinal();
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
           
       }
        finally {
                    try {
                           if (rsset!=null) rsset.close();
                    }
                    catch (SQLException e) {
                        String message="Błąd funkcji readEPCs- zamykanie rsset";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
                       int exit_code=ErrorCodes.ReadEPCsRssetClosing.ordinal();
 //                      ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji readEPCs- zamykanie rsset",exit_code);
                       DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                       throw dae;
                        
                    }
                    try {
                        if (stat!=null) stat.close();
                    }
                    catch (SQLException e) {
                        String message="Błąd funkcji readEPCs- zamykanie Statementt";
                      DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
                       int exit_code=ErrorCodes.ReadEPCsStatementClosing.ordinal();
//                       ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji readEPCs- zamykanie Statement",exit_code);
                       DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                       throw dae;
                        
                    }
                }

 //    return ar;   
    }
    
    public synchronized String ReadLabelStatus(final Integer Id, final String TableName) throws DAOfunctionException {
        PreparedStatement ptt=null;
        ResultSet rsset=null;
        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        DAOloggger.entering(className,MethodName);
        String result="";
        String sql="SELECT  Status FROM "+ TableName+ " WHERE Id="+"?";
//        DAOloggger.logp(Level.FINE,className,kom1,"SQL: "+sql);     
        DAOloggger.logp(Level.FINE,className,MethodName,"Id : "+Id);      
           try {
            ptt=conn.prepareStatement(sql);
            assert ptt!=null:"Assertion PreparedStatement in ReadLabelStatus";
            ptt.setInt(1,Id);
            rsset=ptt.executeQuery();
            assert rsset!=null:"Assertion ResultSet inReadLabelStatus";
            while (rsset.next()) {
              result=rsset.getString("Status");
            };
             DAOloggger.logp(Level.FINE,className,MethodName,"result: "+result);     
             DAOloggger.exiting(className,MethodName);
       }
        catch (SQLException e) {
           String message="Błąd funkcji ReadLabelStatus ";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ReadLabelStatusSQLExceptionError.ordinal();
//          ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek",exit_code);            
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
        catch (Exception e) {
           String message="Błąd ogólny funkcji ReadLabelStatus";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ReadLabelStatusSQLExceptionError.ordinal();
//          ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek",exit_code);            
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
        finally {
                    try {
                           if (rsset!=null) rsset.close();
                    }
                    catch (SQLException e)
                    {
                        String message="Błąd funkcji ReadLabelStatus- wyjątek -  zmknięcie rsset";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
                       int exit_code=ErrorCodes.ReadLabelStatusRssetClosing.ordinal();
 //                      ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek -  zmknięcie rsset",exit_code);            
                       DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                      throw dae;
                        
                    }
                    try {
                          if (ptt!=null) ptt.close();
                    }
               catch (SQLException e)
                    {
                        String message="Błąd funkcji ReadLabelStatus- zamknięcie PrepareStatement";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\rWyjątek SQLException w funkcji ReadLabelStatus - zmknięcie PrepareStatement");
                       int exit_code=ErrorCodes.ReadLabelStatusPrepareStatementClosing.ordinal();
//                       ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek -  zmknięcie PrepareStatement",exit_code);            
                      DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                      throw dae;

                    }
           }
        return result;
        
    }
            
    
    @Override
    public synchronized String ReadLabelStatus(final String EPC, final String TableName) throws DAOfunctionException {
        PreparedStatement ptt=null;
        ResultSet rsset=null;
        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        DAOloggger.entering(className,MethodName);
        String result="";
        String sql="SELECT  Status FROM "+ TableName+ " WHERE EPC="+"?";
//        DAOloggger.logp(Level.FINE,className,kom1,"SQL: "+sql);     
        DAOloggger.logp(Level.FINE,className,MethodName,"EPC : "+EPC);      
           try {
            ptt=conn.prepareStatement(sql);
            assert ptt!=null:"Assertion PreparedStatement in ReadLabelStatus";
            ptt.setString(1,EPC);
            rsset=ptt.executeQuery();
            assert rsset!=null:"Assertion ResultSet inReadLabelStatus";
            while (rsset.next()) {
              result=rsset.getString("Status");
            };
             DAOloggger.logp(Level.FINE,className,MethodName,"result: "+result);     
             DAOloggger.exiting(className,MethodName);
       }
        catch (SQLException e) {
           String message="Błąd funkcji ReadLabelStatus ";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ReadLabelStatusSQLExceptionError.ordinal();
//          ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek",exit_code);            
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
        catch (Exception e) {
           String message="Błąd ogólny funkcji ReadLabelStatus";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ReadLabelStatusSQLExceptionError.ordinal();
//          ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek",exit_code);            
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
        finally {
                    try {
                           if (rsset!=null) rsset.close();
                    }
                    catch (SQLException e)
                    {
                        String message="Błąd funkcji ReadLabelStatus- wyjątek -  zmknięcie rsset";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
                       int exit_code=ErrorCodes.ReadLabelStatusRssetClosing.ordinal();
 //                      ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek -  zmknięcie rsset",exit_code);            
                       DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                      throw dae;
                        
                    }
                    try {
                          if (ptt!=null) ptt.close();
                    }
               catch (SQLException e)
                    {
                        String message="Błąd funkcji ReadLabelStatus- zamknięcie PrepareStatement";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\rWyjątek SQLException w funkcji ReadLabelStatus - zmknięcie PrepareStatement");
                       int exit_code=ErrorCodes.ReadLabelStatusPrepareStatementClosing.ordinal();
//                       ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek -  zmknięcie PrepareStatement",exit_code);            
                      DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                      throw dae;

                    }
           }
        return result;
    }//public synchronized String ReadLabelStatus(final String EPC, final String TableNam
    
    @Override
    public synchronized void DropTable(String TableName) throws DAOfunctionException {
            String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
            Statement stat=null;
            DAOloggger.entering(className, MethodName);
            DAOloggger.logp(Level.FINER,className,MethodName,"Tablica:: "+TableName);
            try {
                stat=conn.createStatement();
                String sql="DROP TABLE "+TableName;
                assert(stat!=null);
                stat.executeUpdate(sql);          
                DAOloggger.exiting(className,MethodName);
            }
            catch (SQLException e) {
                String message="Błąd w funkcji DropTable";
               DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
               int exit_code=ErrorCodes.DropTableSQLExceptionError.ordinal();
 //              ExceptionMessage.ShowExceptionMessageAndExit("Wyjątek SQLException w funkcji DropTable",exit_code);            
               DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
               throw dae;
                }
            catch (Exception e) {
                String message="Błąd ogólny w funkcji DropTable";
               DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
               int exit_code=ErrorCodes.DropTableSQLExceptionError.ordinal();
 //              ExceptionMessage.ShowExceptionMessageAndExit("Wyjątek SQLException w funkcji DropTable",exit_code);            
               DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
               throw dae;
                
            }
            finally {
                try {
                 if (stat!=null) stat.close();
                }
                  catch (SQLException e)
                    {
                        String message="Błąd funkcji Droptable- zamknięcie Statement";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
                       int exit_code=ErrorCodes.DropTableSQLExceptionStatemmentClosingError.ordinal();
//                       ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji Droptable- wyjątek -  zmknięcie Statement",exit_code);            
                       DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                       throw dae;

                    }
             }
       }
    
    @Override
       public synchronized void ClearTable(String TableName) throws DAOfunctionException {
         Statement stat=null;
            String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
            DAOloggger.entering(className, MethodName);
            DAOloggger.logp(Level.FINER,className,MethodName,"Tablica:: "+TableName);
            try {
            stat=conn.createStatement();
            String sql="TRUNCATE TABLE "+TableName;
                 assert(stat!=null);
                DAOloggger.exiting(className,MethodName);
               stat.executeUpdate(sql);          
            }
            catch (SQLException e) {
                String message="Błąd w funkcji ClearTable";
               DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
               int exit_code=ErrorCodes.CleartableSQLException.ordinal();
               DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
               throw dae;
                                    }
            catch (Exception e) {
                String message="Błąd ogólny w funkcji ClearTable";
               DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
               int exit_code=ErrorCodes.CleartableSQLException.ordinal();
               DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
               throw dae;
                
            }
            finally {
                try {
                if (stat!=null) stat.close();
                }
                catch (SQLException e) {
                        String message="Błąd funkcji ClearTable -  zmknięcie Statement";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\rWyjątek SQLException w funkcji Createtable - zmknięcie Statement");
                       int exit_code=ErrorCodes.ClearTableStatementclosingError.ordinal();
//                       ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ClearTable- wyjątek -  zmknięcie Statement",exit_code);            
                       DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                       throw dae;
                    
                }
            }
       }//public     void ClearTable(String TableName)
/*
    @Override
    public synchronized boolean TestIfDataBaseExists() throws DAOfunctionException {
      String ClassName=this.getClass().getCanonicalName();
      String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
      ResultSet rsset=null;
      DAOloggger.entering(ClassName, MethodName);
      boolean exists=false;
      try {
           rsset = conn.getMetaData().getCatalogs();
           while (rsset.next()) {

          String databaseName = rsset.getString(1);
            if(databaseName.equals(globals.BasaDateName)){
                exists=true;
                break;
            }
        }
            DAOloggger.exiting(ClassName, MethodName);
      }
      catch (SQLException e) {
          String message="Błąd funkcji Test";
           DAOloggger.logp(Level.SEVERE, MethodName, ClassName,e+"\n\r" +message);
           int exit_code=ErrorCodes.TestDataBaseError.ordinal();
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
                             }
      catch (Exception e) {
          String message="Błąd funkcji Test";
           DAOloggger.logp(Level.SEVERE, MethodName, ClassName,e+"\n\r" +message);
           int exit_code=ErrorCodes.TestDataBaseError.ordinal();
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
          
      }
      finally {
                try {
                    if (rsset!=null) rsset.close();
                    }
                catch (SQLException e) {
                    String message="Błąd funkcji Test- zamknięcie rsset";
                    DAOloggger.logp(Level.SEVERE, MethodName, ClassName,e+" Wyjątek w funkcji Test- zamknięcie rsset" );
                    int exit_code=ErrorCodes.TestDataBaseRssetClosing.ordinal();
                    ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji Test- zamknięcie rsset",exit_code);
                   DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                   throw dae;
                    }
              }
          return exists;  
        
    }
*/    
public synchronized int ChangeStatus(final String TableName,final String Status, final Integer Id) throws DAOfunctionException {
        PreparedStatement ptt=null;
        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        DAOloggger.entering(className,MethodName);
 //       String result="";
        String update="UPDATE "+TableName+ " SET Status="+Status +" WHERE Id="+"'"+Id+"'";

        DAOloggger.logp(Level.FINE,className,MethodName,"SQL: "+update);     
        DAOloggger.logp(Level.FINE,className,MethodName,"Id : "+Id);      
           try {
            ptt=conn.prepareStatement(update);
            int wyn=ptt.executeUpdate(update);
 //            DAOloggger.logp(Level.FINE,className,MethodName,"result: "+result);     
             DAOloggger.exiting(className,MethodName);
             DAOloggger.logp(Level.FINE,className,MethodName,"wynik zmiany statusu:: "+Integer.toString(wyn));     
             return wyn;
       }
        catch (SQLException e) {
           String message="Błąd funkcji ChangeStatus ";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ChangeStatusExceptionError.ordinal();
//          ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek",exit_code);            
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
        catch (Exception e) {
           String message="Błąd ogólny funkcji ChangeStatus";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ChangeStatusExceptionError.ordinal();
//          ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek",exit_code);            
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
        finally {
                    try {
                          if (ptt!=null) ptt.close();
                    }
               catch (SQLException e)
                    {
                        String message="Błąd funkcji ChangeStatus- zamknięcie PrepareStatement";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\rWyjątek SQLException w funkcji ReadLabelStatus - zmknięcie PrepareStatement");
                       int exit_code=ErrorCodes.ChangeStatusClosingStatementError.ordinal();
//                       ExceptionMessage.ShowExceptionMessageAndExit("Błąd funkcji ReadLabelStatus- wyjątek -  zmknięcie PrepareStatement",exit_code);            
                      DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                      throw dae;

                    }
           }
    
}
    
    @Override
    public synchronized int ChangeStatus( final String TableName,final String status,final String EPc) throws DAOfunctionException {
        PreparedStatement ptt=null;
        String MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        DAOloggger.entering(className,MethodName);
         int status_id=Integer.parseInt(status);
         int wyn=0;
 //       String result="";
 //           String update="UPDATE "+TableName+" SET Status=?"+"  WHERE  EPC="+"'"+EPc+"'";
         String update="UPDATE "+TableName+ " SET Status="+status_id +" WHERE EPC="+"'"+EPc+"'";
           DAOloggger.logp(Level.FINE,className,MethodName,"SQL: "+update);     
          DAOloggger.logp(Level.FINE,className,MethodName,"EPC : "+EPc);      
           try {
                    ptt=conn.prepareStatement(update);
//                    ptt.setString(1, TableName);
//                  ptt.setInt(1,status_id);
//                    ptt.setString(1, EPc);
               if (DataBaseType==DataBaseTypesEnum.MySqlType) {
                        wyn=ptt.executeUpdate(update);
               }
               else
               if (DataBaseType==DataBaseTypesEnum.LiteSqlType) {
                     ptt.execute();
                 };
             DAOloggger.exiting(className,MethodName);
             DAOloggger.logp(Level.FINE,className,MethodName,"wynik zmiany statusu:: "+Integer.toString(wyn));     
             return wyn;
       }
           
        catch (SQLException e) {
           String message="Błąd funkcji ChangeStatus ";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ChangeStatusExceptionError.ordinal();
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }

        catch (Exception e) {
           String message="Błąd ogólny funkcji ChangeStatus";
           DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\r"+message);
           int exit_code=ErrorCodes.ChangeStatusExceptionError.ordinal();
           DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
           throw dae;
        }
        finally {
                    try {
                          if (ptt!=null) ptt.close();
                    }
               catch (SQLException e)
                    {
                        String message="Błąd funkcji ChangeStatus- zamknięcie PrepareStatement";
                       DAOloggger.logp(Level.SEVERE, className, MethodName,e+"\n\rWyjątek SQLException w funkcji ChangeStatus - zamknięcie PrepareStatement");
                       int exit_code=ErrorCodes.ChangeStatusClosingStatementError.ordinal();
                      DAOfunctionException dae=new DAOfunctionException(e,message,exit_code);
                      throw dae;

                    }
           }
    }


   
}
