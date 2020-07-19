/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pFilesOperations;

import pParentClass.ParentClass;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


/**
 *
 * @author admin
 */
public class WriteToFile extends ParentClass{
    private String path;
    private boolean file_rewrite=false;
    
    public WriteToFile(String APath) {
        path=APath;
    }
    
    public WriteToFile(String APath,boolean Afile_rewrite) {
        path=APath;
        file_rewrite=Afile_rewrite;
    }
    
    public void WriteStringToFile(String AString) throws IOException {
       FileWriter wrt = new FileWriter(path, file_rewrite);
       assert wrt!=null:"Assertion FileWriter in WriteStringToFile";
       PrintWriter printOut = new PrintWriter(wrt);
       assert printOut!=null:"Assertion PrintWriter in WriteStringToFile";
       printOut.print(AString);
       printOut.close();
    }
    
}
