/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pFilesOperations;

import java.util.logging.Level;     //https://stackoverflow.com/questions/14606293/how-to-log-as-much-information-as-possible-for-a-java-exception
import java.util.logging.Logger;
import  java.io.*;
//import  java.io.IOException;
import  java.util.*;
import pGlobals.globals;
import pRandomEPC.GenerateEPC;
import pParentClass.ParentClass;


/**
 *
 * @author Andrzej
 */



public class EPCFilePath  extends ParentClass {
    
     private File PathToAppData;
    private final static Logger logger=Logger.getLogger(EPCFilePath.class.getName());
     
     
    
    public EPCFilePath()  throws  EPCFileException {
         PathToAppData= getAppData();
    }

@Override    
    protected  void ExceptionHandler(Exception e,String kom) throws EPCFileException {
        /*
            if (ExceptionDescription) {
                logger.log(Level.INFO, kom, e);
            }
            else {
                throw new  EPCFileException(kom);
                }
        */  
      }
        
       
       
    public  String GetPathToEPCFile() {
          String ppth=  PathToAppData.getAbsolutePath();
        String WholePath=ppth+"\\"+globals.EPCFileName;
        return WholePath;
     }
    
   private  File getAppData()  throws  EPCFileException {
        File ff;
        try {
                ProcessBuilder builder = new ProcessBuilder(new String[]{"cmd", "/C echo %APPDATA%"});
                assert builder!=null:"Assertion ProcessBulder in getAppData()";
                BufferedReader br = null;
               Process start = builder.start();
               br = new BufferedReader(new InputStreamReader(start.getInputStream()));
               assert br!=null:"Assertion BufferedReadewr in getAppData";
               String path = br.readLine();
               // TODO HACK do not know why but I get an extra '"' at the end
                if(path.endsWith("\"")){
                 path = path.substring(0, path.length()-1);
                 ff= new File(path.trim());
                   return ff;
                 }
        }
        catch (IOException e) {
            ExceptionHandler(e,"Wyjątek w  File getAppData");
        }
                  return null;
    } 
    /*
   private  File getAppData()  throws  EPCFileException {
    ProcessBuilder builder = new ProcessBuilder(new String[]{"cmd", "/C echo %APPDATA%"});
    BufferedReader br = null;
    try {
        Process start = builder.start();
        br = new BufferedReader(new InputStreamReader(start.getInputStream()));
        String path = br.readLine();
        // TODO HACK do not know why but I get an extra '"' at the end
        if(path.endsWith("\"")){
            path = path.substring(0, path.length()-1);
        return new File(path.trim());
                    }
    }
    } catch (IOException e) {
            ExceptionHandler(e,"Wyjątek w funkcji getAppData");
    } finally {
        if(br != null){
            try {
                br.close();
            } catch (IOException e) {
            ExceptionHandler(e,"Wyjątek w funkcji getAppData - zamykanie pliku");
            }
        }
    }

    return null;
} 
 */   
}
