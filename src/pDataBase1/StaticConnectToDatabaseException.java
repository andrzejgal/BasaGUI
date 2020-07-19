/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDataBase1;

import pMyException.*;

/**
 *
 * @author dell
 */
public class StaticConnectToDatabaseException extends ExceptionAG{
    
    
    public StaticConnectToDatabaseException(Exception e,String message,Integer exit) {
        super(e,message,exit);
    }
    
 public StaticConnectToDatabaseException(Exception e,String message) {
        super(e,message);
    }
@Override    
    public Integer GetExitCode() {
        return super.GetExitCode();
    }
    
    public String GetInterMessage() {
        return super.GetInterMessage();
    }
    
    
}
