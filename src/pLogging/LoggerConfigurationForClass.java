/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pLogging;

import java.util.logging.*;
import java.io.IOException;
import pGlobals.globals;
//import java.util.logging.Level;
import java.util.logging.Logger;
import pParentClass.ParentClass;

/**
 *
 * @author admin
 */
public class LoggerConfigurationForClass extends ParentClass{
    static private Logger log;
    
 
   static  protected void ExceptionHandlerA(Exception e, String kom) throws LoggingConfigException {
       /*
       if (ExceptionDescription) {
            Logger.getAnonymousLogger().log(Level.SEVERE, kom, e);
            System.exit(-1);
        } else {
            throw new LoggingConfigException (kom);
        }
        */
    }//protected void ExceptionHandler(Exception e,String kom

    
    static public void   SetLogger(Logger Alog) {
        log=Alog;
    }
    
    static public void SetLoggerLevel(Logger log,final Level lv) {
            log.setLevel(lv);
    }
    
    static public void  AddConsoleHandler() {
        ConsoleHandler hc=new ConsoleHandler();
        assert hc!=null:"Assertacja hc=null";
        log.addHandler(hc);
    }
    
     static public void  AddConsoleHandler(Logger log) {
        ConsoleHandler hc=new ConsoleHandler();
        assert hc!=null:"Assertacja hc=null";
        log.addHandler(hc);
    }
   
    
   static  public void RemoveConsoleHandler() {
        Handler[] handlers=log.getHandlers();
        Handler hd;
        for (Integer n=0;n<handlers.length;n++) {
            hd=handlers[n];
            assert hd!=null:"Assertacja hd=null";
            if (hd instanceof  ConsoleHandler) {
                log.removeHandler(hd);
            }
        }
    }
 
   
        static public void RemoveFileHandler(Logger log) {
        Handler[] handlers=log.getHandlers();
        Handler hd;
        for (Integer n=0;n<handlers.length;n++) {
            hd=handlers[n];
            assert hd!=null:"Assertacja hd=null";
            if (hd instanceof  FileHandler) {
                log.removeHandler(hd);
            }
        }
    }
        
   static  public void RemoveAllHandlers() {
        Handler[] handlers=log.getHandlers();
        Handler hd;
        for (Integer n=0;n<handlers.length;n++) {
            hd=handlers[n];
            assert hd!=null:"Assertacja hd=null";
            log.removeHandler(hd);
        }
    }
   
    static  public void RemoveAllHandlers(Logger log) {
        Handler[] handlers=log.getHandlers();
        Handler hd;
        for (Integer n=0;n<handlers.length;n++) {
            hd=handlers[n];
            assert hd!=null:"Assertacja hd=null";
            log.removeHandler(hd);
        }
    }
  
   
    static  public void DisableParentLogger() {
       log.setUseParentHandlers(false);
   }
    
   static  public void DisableParentLogger(Logger log) {
       log.setUseParentHandlers(false);
   }
    

   static  public void AddFileHandler(final boolean append) throws  LoggingConfigException {
        try {
            FileHandler fh=new FileHandler(globals.FilePattern,append);
            assert fh!=null:"Assertacja fh=null";
            log.addHandler(fh);
        }
        catch (IOException e) {
            ExceptionHandlerA(e,"Wyjątek w klasie  LoggerConfiguration przy tworzeniu fileHandler");
      }
        };// public void AddFileHandler(final boolean append) throws IOException
        
   static  public void AddFileHandler(Logger log,final boolean append) throws LoggingConfigException  {
        try {
            FileHandler fh=new FileHandler(globals.FilePattern,append);
            assert fh!=null:"Assertacja fh=null";
            log.addHandler(fh);
        }
        catch (IOException e) {
                       ExceptionHandlerA(e,"Wyjątek w klasie  LoggerConfiguration przy tworzeniu fileHandler");
        };// public void AddFileHandler(final boolean append) throws IOException
   }

    static  public FileHandler CreateFileHandler(final boolean append) throws LoggingConfigException {
        FileHandler fh=null;
        try {
            fh=new FileHandler(globals.FilePattern,append);
            assert fh!=null:"Assertacja fh=null";
            return fh;
        }
        catch (IOException e) {
                      ExceptionHandlerA(e,"Wyjątek w klasie  LoggerConfiguration przy tworzeniu fileHandler");
        };// public void AddFileHandler(final boolean append) throws IOException
        return fh;
    }

        
   }





    

