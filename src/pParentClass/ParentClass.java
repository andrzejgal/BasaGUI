/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pParentClass;

import java.io.IOException;
import java.util.logging.*;
import pGlobals.globals;

/**
 *
 * @author Andrzej
 */
public class ParentClass {
    
    protected void ExceptionHandler(Exception e,String kom) throws Exception { };
    
    static public void SetLevel(Logger lg,Level lv) {
        lg.setLevel(lv);
    }
}
