/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pFilesOperations;

import pParentClass.ParentClass;
import pGlobals.globals;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Andrzej
 */
public class ReadEPCsFromFile extends ParentClass{
    private String ppth;
    private final static Logger logger=Logger.getLogger(ReadEPCsFromFile.class.getName());
    
    public ReadEPCsFromFile(String path) {
        ppth=path;
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
    
        
     public String[] ReadFromEPCFile() throws EPCFileException {
                 String bfr[]=new String[globals.EPCquantity];
         try {
                FileReader fr = new FileReader(ppth);
                assert fr!=null:"Assertion FileReader in ReadFromEPCFile";
                BufferedReader rt= new BufferedReader(fr);
                assert rt!=null:"Assertion FileReader in ReadFromEPCFile";
                for (Integer n=0;n<globals.EPCquantity-1;n++) {
                   bfr[n]=rt.readLine();
               }
               fr.close();
               rt.close();
         }
            catch (IOException e) {
                ExceptionHandler(e,"Wyjątek w funkcji ReadFromEPCFile");
            }
                  return bfr;
   }

    
}
