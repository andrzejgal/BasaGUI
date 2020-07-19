/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
plik założony 16-01-2019 połączenie z bazą MySql przez ClassLoader jako klasa statyczna, aby nie trzeba było tworzyć klasy.
*/
package pDataBase1;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.*;
import java.util.logging.Level;
//import static pDataBase.DAOFirst.DAOloggger;
//import pDialogs.ExceptionMessage;
import pGlobals.globals;
import pDialogs.ExceptionStringFormatter;
import pErrorCodes.ErrorCodes;


/**
 *
 * @author dell
 */
public class StaticConnectToDatabase {
        static private DataBaseTypesEnum TypeOfDataBase;
        static private Connection conn;
        static String kom="StaticConnectToDatabaseMySQL";
        static String kom1="ConnectToDataBase";
        private static String kkm;
        static public Logger StaticConnectionToDataBase=Logger.getLogger(globals.DataBaseConnectionClassLoaderName);
    
        
    public  static  synchronized  Connection Connect() throws StaticConnectToDatabaseException
    {
                 
         try {
             if (TypeOfDataBase==DataBaseTypesEnum.MySqlType) 
             {
                Class.forName(globals.jdbcDriver).newInstance();
               conn=DriverManager.getConnection(globals.dbAddress + globals.BasaDateName, globals.userName,globals.password);
             }
             else 
              if (TypeOfDataBase==DataBaseTypesEnum.LiteSqlType) 
             {
 //                String url = "jdbc:sqlite:c:/baza/rfid.db";    //nie może być spacji między sqlite: a c:/baza/rfid.db
                String url="jdbc:sqlite:"+globals.SqliteDataBaseName;
                 conn = DriverManager.getConnection(url);
  //              Class.forName(/*globals.jdbcDriverSqlite*/"org.sqlite.JDBC").newInstance();
//                conn=DriverManager.getConnection("jdbc:sqlite: "+globals.SqliteDataBaseName);
             }
              else {    //ms sqlserver
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=rfid;user=sa;password=A1drz2e3j4g;";               
                    conn = DriverManager.getConnection(url);
              }
             
            StaticConnectionToDataBase.logp(Level.SEVERE, kom, kom1, "Connection to database is established");
            return conn;
        } 
   
        catch (SQLException e) {
               ExceptionStringFormatter esf=new ExceptionStringFormatter();
               if (TypeOfDataBase==DataBaseTypesEnum.MySqlType) {
                   kkm=esf.FormatExceptionString(e, "Nie ma takiej bazy danych: " + globals.BasaDateName + globals.NEW_LINE + "lub nie włączony serwer APACHE");
               }
               else 
               if (TypeOfDataBase==DataBaseTypesEnum.LiteSqlType) {
                   kkm=esf.FormatExceptionString(e, "Nie ma takiej bazy danych: " + globals.BasaDateName);                  
               }
               StaticConnectionToDataBase.logp(Level.SEVERE, kom, kom1,kkm);
                int exit_code=ErrorCodes.ClassNotFoundError.ordinal();
               StaticConnectToDatabaseException stb=new StaticConnectToDatabaseException(e,kkm,exit_code);
               throw stb;
                }
        
        catch(    ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                int exit_code=0;
                String message=null,message1=null;
                if (e instanceof ClassNotFoundException)
                {
                    exit_code=ErrorCodes.ClassNotFoundError.ordinal();
                    message="Nie ma takiej bazy danych: " + globals.BasaDateName + globals.NEW_LINE + "lub nie włączony serwer APACHE";
                    message1=message;
                }
                else
                if (e instanceof InstantiationException)
                {
                   exit_code=ErrorCodes.DataBaseInstantiationExceptionError.ordinal();
                   message="Błąd połączenia z bazą danych";
                   message1="Błąd utworzenia instancji";
                }
                else
                if (e instanceof IllegalAccessException) {
                    exit_code=ErrorCodes.DataBaseIllegalAccessExceptionError.ordinal();
                   message="Błąd połączenia z bazą danych";
                   message1="Błąd nielegalnego dostępu";
                }
                    
               StaticConnectionToDataBase.logp(Level.ALL, kom, kom1,e.getMessage()+"\n\r"+message); 
//               ExceptionMessage.ShowExceptionMessageAndExit("Błąd połączenie z bazą danych",exit_code);
               StaticConnectToDatabaseException stb=new StaticConnectToDatabaseException(e,"\n\r"+message+" "+message1,exit_code);
               throw stb;
               
         }
        
      }
    
    public static synchronized void SetDataBaseType(final DataBaseTypesEnum DT )   {
       TypeOfDataBase=DT;
    } 

    public static synchronized DataBaseTypesEnum GetDataBaseType( )   {
      return TypeOfDataBase;
    } 
    
    
}
