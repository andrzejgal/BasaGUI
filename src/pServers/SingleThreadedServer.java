/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 * 
 * synchronizacja
 * http://www2.sys-con.com/itsg/virtualcd/java/archives/0701/goenka/index.html
 * http://www2.sys-con.com/itsg/virtualcd/java/archives/0701/goenka/index.html#s3
 * https://www.javahelps.com/2015/04/thread-synchronization-in-java.html
 */
package pServers;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import pGlobals.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import pFifo.FifoClass;
//import pDataBase.*;
//import static pServerReadEPC.RFIDServer.RFIDServerLogger;
import java.sql.Connection;
import pDataBase1.DAOfunctions;
import pLogging.LoggerConfigurationClass;



/*
07-03-2019 synchronizacja wątku konieczna
*/


public class SingleThreadedServer implements Runnable{

    final static String EPCSpr="Sprawdzanie w bazie";
    final static String EPCZap="Zapis do bazy";
    protected int          serverPort   ;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    static public  Logger RFIDServerLogger=Logger.getLogger(globals.ReadingEpcLogger);
    private String ClassName=null;
    private String MethodName=null;
 //   private Connection conWew;
    private DAOfunctions daof=null;
//   static public Logger RFIDServerLogger=Logger.getLogger(globals.ReadingEpcLogger);


    public SingleThreadedServer(int port,Connection cnt){
        this.serverPort = port;
//        Socket clientSocket = null;
        ClassName=this.getClass().getCanonicalName();
        MethodName=Thread.currentThread().getStackTrace()[1].getMethodName();
        daof=new DAOfunctions(cnt);
   }

    public void run(){
        RFIDServerLogger.logp(Level.SEVERE, ClassName, MethodName,"Watek serwera czytającego działa");
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
       
        while(! isStopped()) {
            Socket clientSocket = null;
            try {
                RFIDServerLogger.logp(Level.FINEST,ClassName,MethodName," Czekam na przyjście etykiety","" );
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            try {
                processClientRequest(clientSocket);
            } catch (Exception e) {
                //log exception and go on to next request.
                RFIDServerLogger.logp(Level.SEVERE,ClassName,MethodName," Wyjątek w funkcji processClientReques","" );
             }
            try {
                clientSocket.close();
            }
            catch (IOException e) {
               throw new RuntimeException("Błąd zamknięcia clientSocket", e); 
            }
        }
       stop();
        System.out.println("Server Stopped.");
        
//        clientSocket.
    }

    private void processClientRequest(Socket clientSocket)
    throws Exception {
        InputStream  in  = clientSocket.getInputStream();
        BufferedReader input= new BufferedReader(new InputStreamReader(in));            
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
        String inputLine = input.readLine();
        if ((inputLine != null) && (inputLine.length()>5)) {
            int totalTags = Integer.parseInt(inputLine.substring(0, 5));
            RFIDServerLogger.logp(Level.FINEST,ClassName,MethodName," Input line message length: " + totalTags + " Header: " + inputLine.substring(5, 8));
            inputLine = inputLine.substring(9);
            RFIDServerLogger.logp(Level.FINEST,ClassName,MethodName," InputLine bez nagłowka :"+inputLine);
                    
                    // Write response
            String[] sellStatus = inputLine.split(globals.SEPARATOR);
            StringBuffer resp = new StringBuffer();
            for (int i = 0; i < sellStatus.length; i++) {
 //               int ll=sellStatus[i].length();
//                    RFIDServerLogger.logp(Level.FINEST,kom,kom1,"EPC "+"i "+"length: "+ll.toString());
 //               if (ll!=globals.EPCLength) continue;
                if (!globals.ReadingEPCFromAntenna) {
//                    String Res=base.ReadLabelStatus(sellStatus[i]);
                      String Res=daof.ReadLabelStatus(sellStatus[i],globals.TableName);
                    RFIDServerLogger.logp(Level.FINEST,ClassName,MethodName,EPCSpr+" Status etykiety w bazie :"+Res);
                    resp.append(Res);
                    if (i!=(sellStatus.length-1)) {
                       resp.append(globals.SEPARATOR);
                          }
                }//if (!globals.ReadingEPCFromAntenna)
                else {
                       String Epc=sellStatus[i];
                        RFIDServerLogger.logp(Level.FINEST,ClassName,MethodName,EPCZap+" Etykieta odczytana przez antenę :"+Epc);
                        RFIDServerLogger.logp(Level.FINEST,ClassName,MethodName,"reading="+String.valueOf(FifoClass.GetReadingStatus()));
                        while(FifoClass.GetReadingStatus()) {};
                        FifoClass.AddToList(Epc);    
                        resp.append(globals.SOLD);    //preparing response to RFID system
                        if (i!=(sellStatus.length-1)) {
                             resp.append(globals.SEPARATOR);
                                }
                      }//  if (!globals.ReadingEPCFromAntenna) {
                    }// for (int i = 0; i < sellStatus.length; i++) {
                    output.println(resp.toString());
                    RFIDServerLogger.logp(Level.FINEST,ClassName,MethodName,"out = " + resp.toString());
                   RFIDServerLogger.exiting(ClassName, MethodName);

                }// if ((inputLine != null) && (inputLine.length()>5)) {
        output.close();
         input.close();
         in.close();
 //       System.out.println("Request processed: " + time);
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port :"+ globals.Port, e);
        }
    }
}