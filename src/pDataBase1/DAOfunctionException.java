/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDataBase1;
import pMyException.*;

/*
02-02-2019 dta założenia pliku
Definiuje wyjątek dla klasy DAOfunction
*/

/**
 *
 * @author dell
 */
public class DAOfunctionException extends ExceptionAG{

    public DAOfunctionException(Exception e,String message,Integer exit) {
        super(e,message,exit);
    }
    
 public DAOfunctionException(Exception e,String message) {
        super(e,message);
    }
@Override    
    public Integer GetExitCode() {
        return super.GetExitCode();
    }
@Override    
    public String GetInterMessage() {
        return super.GetInterMessage();
    }
    
    
}
