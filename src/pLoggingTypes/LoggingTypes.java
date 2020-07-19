//http://javarevisited.blogspot.com/2011/08/enum-in-java-example-tutorial.html#axzz4nSqL7PzL
//http://www.java67.com/2012/08/string-to-enum-in-java-conversion.html
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pLoggingTypes;

import java.util.logging.Level;


/**
 *
 * @author admin
 */
public enum LoggingTypes {
    TALL(Level.ALL),
    TSEVERE(Level.SEVERE),
    TFINE(Level.FINE),
    TFINEST(Level.FINEST),
    TOFF(Level.OFF);
    
    private  Level level;
    
    private LoggingTypes(Level lv) {
        level=lv;
    }
    
    public Level GetLevel() {
        return level;
    }
    
    public void SetLevel(Level lv) {
        level=lv;
    }
    
}//public enum LoggingTypes
