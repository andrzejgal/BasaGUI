/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pGlobals;

import java.lang.*;
//import pDataBase.DAO;
import  pCheckReadLabels.ReadLabelsList;

/**
 *
 * @author Andrzej
 */
public class globals {
    public  final static  Integer EPCquantity=1000;
    public final static String EPCFileName="EPCs.txt";
    public final static Integer EPCLength=24;
    public final static String  BasaDateName="rfid";
    public final static String  TableName="etykiety";
    //adresacja bazy mysql
    public final static String  jdbcDriver = "com.mysql.jdbc.Driver";
    public final static String  dbAddress  = "jdbc:mysql://localhost/";
    public final static String userName = "root" ;
    public final static String password = "" ;  
    //adresacja bazy sql
    public final static String ServerName="localhost";
    public final static String InstanceName="";
    public final static String PortNumber="";
    //driver dla slite
    public final static String jdbcDriverSqlite="sqlite-jdbc-3.23.1";
    public final static String SqliteDataBaseName="c:/baza/rfid.db";
    
    
    public final static String DefaultDataBaseName1="test";
    public final static String DefaultDataBaseName2="mysql";
    public final static String DefaultDataBaseName3="information_schema";
    public  final static String NEW_LINE=System.lineSeparator();
    public final static Integer Port=50000;
    public  static boolean ServerRun=true;
    public volatile  static boolean ReadingEPCFromAntenna=true;   //true: writes epc to database, false: check status in database
    public final static String SEPARATOR = ",";
    public final static String NOT_SOLD = "0";
    public final static String SOLD = "1";
    public final static String FilePattern="rfid.log";
//
    public final static String DataBaseLoggerName="DatabaseLogger";
    public final static String ReadingEpcLogger="Server_read_epcLogger";
    public final static String WrittingEpcLogger="Server_write_epcLogger";
    public final static String FilesOperationLogger="Files_operationLogger";
    public final static String TestIfDataBase="TestIfDataBaseLogger";
    public final static String ReadingStartLogger="ReadingStartLogger"  ;
    public final static String LookAndFeelLogger="LookAndFeelLogger";
    public final static String MainWindowLoggerName="MainWindowLogger";
    public final static String DataBaseConnectionClassLoaderName="DataBaseConnectionClassLoaderName";
 //   public final static String DataBaseConnectionClassLoaderName="DataBaseConnectionClassLoaderName";
    
 //
//   public static DAO /*DAO0,DAO1,*/DAO2;
   public volatile static ReadLabelsList LabelList=null;
   
 
   




    
}
