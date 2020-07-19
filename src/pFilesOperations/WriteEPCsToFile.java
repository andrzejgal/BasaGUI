/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pFilesOperations;

import pRandomEPC.GenerateEPC;
import pParentClass.ParentClass;
import pGlobals.globals;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author Andrzej
 */
public class WriteEPCsToFile extends ParentClass{
    private final static Logger logger=Logger.getLogger(WriteEPCsToFile.class.getName());
    
  @Override  
    protected void ExceptionHandler(Exception e,String kom) throws EPCFileException {
    /*    
        if (ExceptionDescription) {
                logger.log(Level.INFO, kom, e);
            }
            else {
                throw new  EPCFileException(kom);
                }
                */
    }

    
  
  public void WriteEPCsToTextFile(String ppth,boolean append)  throws  EPCFileException{
      try {
         FileWriter wrt= new FileWriter(ppth,append);
        assert wrt!=null:"Assertion FileWriter in WriteEPCsToTextFile";
        PrintWriter prn=new PrintWriter(wrt);
        assert prn!=null:"Assertion PrintWriter in WriteEPCsToTextFile";
        GenerateEPC gepc=new GenerateEPC();
        assert gepc!=null:"Assertion GenerateEPC in WriteEPCsToTextFile";
        for (Integer n=0;n<globals.EPCquantity-1;n++) {
            String epcs=gepc.GenerateOneEPC();
            prn.println(epcs);
        }
       wrt.close();
       prn.close();
      }
           catch (IOException e) {
                ExceptionHandler(e,"Wyjątek przy zapisie etykiet do pliku");
                   }
       }
    }
  
    
//}
