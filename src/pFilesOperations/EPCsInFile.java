/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pFilesOperations;

import pParentClass.ParentClass;
import  java.io.*;
import  java.io.IOException;
import   java.util.*;


/**
 *
 * @author Andrzej
 */
public class EPCsInFile  extends ParentClass {
    
    final Integer EPCquantity=100;
    final String EPCFileName="EPCs.txt";
    private File PathToAppData;
    
    public EPCsInFile()  throws Exception {
         PathToAppData= getAppData();
    }
    
    public void WriteEPCsToFile() {
        String ppth=  PathToAppData.getAbsolutePath();
        String WholePath=ppth+"\\"+EPCFileName;
        
    }
    
   private  File getAppData()  throws Exception {
    ProcessBuilder builder = new ProcessBuilder(new String[]{"cmd", "/C echo %APPDATA%"});
    assert builder!=null:"Assert ProcessBuilder in getAppData";
    BufferedReader br = null;
    try {
        Process start = builder.start();
        br = new BufferedReader(new InputStreamReader(start.getInputStream()));
        assert br!=null:"Assert BufferedReader in getAppData";
        String path = br.readLine();
        // TODO HACK do not know why but I get an extra '"' at the end
        if(path.endsWith("\"")){
            path = path.substring(0, path.length()-1);
        }
        return new File(path.trim());


    } catch (IOException ex) {
 //       Logger.getLogger(Util.class.getName()).log(Level.SEVERE, "Cannot get Application Data Folder", ex);
    } finally {
        if(br != null){
            try {
                br.close();
            } catch (IOException ex) {
  //              Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    return null;
} 
    
}
