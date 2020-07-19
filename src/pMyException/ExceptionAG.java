/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
02-02-2019 data założenia pliku
Definiuje nadklasę Exception, która przekazuje exit code
*/
package pMyException;

/**
 *
 * @author dell
 */
public class ExceptionAG extends Exception {
    
    private Integer exitcode=null;
    private String inter_message=null;
    
    public ExceptionAG(Exception e,String message,Integer exit) {
        super(e);
        exitcode=exit;
        inter_message=message;
    }
    
 public ExceptionAG(Exception e,String message) {
        super(message,e);
        inter_message=message;
    }
    
    public Integer GetExitCode() {
        return exitcode;
    }
  
    public void SetExitCode(final Integer exit) {
        exitcode=exit;
    }
    
    public String GetInterMessage() {
        return inter_message;
    }
    
}
