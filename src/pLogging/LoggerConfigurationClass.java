/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//http://www.vogella.com/tutorials/Logging/article.html
package pLogging;

//import Main.Main;
//import pInitialization.Main;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Formatter;
import pGlobals.globals;
import pFormatter.FormatterClass;
import java.lang.AssertionError;
import java.util.logging.FileHandler;

/**
 *
 * @author admin
 */
public class LoggerConfigurationClass {
    static private ArrayList<Logger>  ArrLoggers=new ArrayList<>();
    static private FileHandler fh;
//    static private ArrayList<FileHandler> FileHandlerArray=new ArrayList<>();
    
  public  static void ReadLoggerConfiguration() {     
    final InputStream inpS = /*Main.class.*/ LoggerConfigurationClass.class.getResourceAsStream("/default/logging.properties");
    try
        {   
            assert(inpS!=null):"Assertion: inputStream is null";
            LogManager.getLogManager().readConfiguration(inpS);
        }
    catch (final IOException e)
        {
            Logger.getAnonymousLogger().severe("Could not load default logging.properties file");
            Logger.getAnonymousLogger().severe(e.getMessage());
        }   
 }
   
 
 static public void  AddLogger(final Logger lg) {
     assert lg!=null:"Asertacja lg=null ";
     ArrLoggers.add(lg);
 }
 
static public Level GetLevel(Logger lg)  {
    assert lg!=null:"Asertacja lg=null ";
    return lg.getLevel();
}  

 
 static public Logger GetLogger(final Integer  ind) {
     return ArrLoggers.get(ind);
 }
 
 
 static public void RemoveConsoleHandlerFromAll() {
      Iterator it=ArrLoggers.iterator();
     Logger lg;
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
         LoggerConfigurationForClass.RemoveFileHandler(lg);
     }    
 }
 
  
 static public void AddFileHandlerToAll(boolean append)   throws LoggingConfigException {
     Iterator it=ArrLoggers.iterator();
     Logger lg;
     fh=LoggerConfigurationForClass.CreateFileHandler(append);
     FormatterClass formatter=new FormatterClass();
     fh.setFormatter(formatter);
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
         lg.addHandler(fh);
     }    
  }
 
 static public  void AddConsoleHandlerToAll() {
     Iterator it=ArrLoggers.iterator();
     Logger lg;
     ConsoleHandler hc=new ConsoleHandler();
     FormatterClass formatter=new FormatterClass();
     hc.setFormatter(formatter);
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
//         LoggerConfigurationForClass.AddConsoleHandler(lg);
         lg.addHandler(hc);
     }    
 }

 
 static public void RemoveFileHandlerFromAll() {
      Iterator it=ArrLoggers.iterator();
     Logger lg;
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
        LoggerConfigurationForClass.RemoveFileHandler(lg);
      }    
 }


//static public void CloseFileHandler() {
//    fh.close();
//}

 
 static public void RemoveAllHandlersFromAll() {
      Iterator it=ArrLoggers.iterator();
     Logger lg;
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
        LoggerConfigurationForClass.RemoveAllHandlers(lg);
      }    
    }
 
 static public void SetLoggerLevelToAll(Level lv) {
     Iterator it=ArrLoggers.iterator();
     Logger lg;
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
        LoggerConfigurationForClass.SetLoggerLevel(lg,lv);
      }    
 }
 
 static public Logger FindLogger(final String name) {
     Iterator it=ArrLoggers.iterator();
     Logger lg=null;
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
        if (!lg.getName().equalsIgnoreCase(name))  continue;
        else return lg;
   }
     return lg;
 }
 
  static public void DisableParentToAlllToAll() {
     Iterator it=ArrLoggers.iterator();
     Logger lg;
     while (it.hasNext()) {
         lg=(Logger)it.next();
         assert lg!=null:"Asertacja lg=null ";
        LoggerConfigurationForClass.DisableParentLogger(lg);
      }    
 }
  
static public Level GetLoggerLevel(final String name) {
    Level lv=null;
    Logger lg;
    lg=FindLogger(name);
    if (lg==null) return lv;
    else {
        return(lg.getLevel());
    }
}


 


 
  
   
/*  
 public static void SetRootHandlersLevel(final Level level) {
       Handler[] handlers =
      Logger.getLogger( "" ).getHandlers();
    for ( int index = 0; index < handlers.length; index++ ) {
      handlers[index].setLevel( level );
    }
*/    
    
 /*
    LUB
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINE);
    */
/*  

 }
 
 public static void AddFileHandlerToRootHandlers() throws IOException{
    RemoveDefaultConsoleHandler();
    try {
          FileHandler hn=new FileHandler(globals.FilePattern);
          Logger.getLogger( "" ).addHandler(hn);
        }
    catch (IOException e) {
        e.getMessage();
    }
  }
 
 public static void RemoveDefaultConsoleHandler() {
        Logger globalLogger = Logger.getLogger("global");
        Handler[] handlers = globalLogger.getHandlers();
        for(Handler handler : handlers) {
            globalLogger.removeHandler(handler);
            } 

 }
 
 
    
*/
}//public class LoggerConfigurationClass
/*  CIEKAWY LINK: https://books.google.pl/books?id=UEdjAgAAQBAJ&pg=PA597&lpg=PA597&dq=java+konfigurowania+loggera&source=bl&ots=IjWV0kGLOv&sig=YvmQXSTHwsm7FVZCaQStrv5h4CU&hl=pl&sa=X&ved=0ahUKEwiF3IKL_I_VAhUkMJoKHcLQC2sQ6AEIWzAH#v=onepage&q=java%20konfigurowania%20loggera&f=false
          
          LADOWANIE WŁASNEJ KONFIGURACJI LOGGERA

plik konfiguracyjny powinien się nazywać logging.properties i znajdować w pakiecie default
*/


/*
LUB INNA WERSJA

Logger log = Logger.getLogger("myApp");
log.setLevel(Level.ALL);
log.info("initializing - trying to load configuration file ...");

//Properties preferences = new Properties();
try {
    //FileInputStream configFile = new //FileInputStream("/path/to/app.properties");
    //preferences.load(configFile);
    InputStream configFile = myApp.class.getResourceAsStream("app.properties");
    LogManager.getLogManager().readConfiguration(configFile);
} catch (IOException ex)
{
    System.out.println("WARNING: Could not open configuration file");
    System.out.println("WARNING: Logging not configured (console output only)");
}
log.info("starting myApp");
this is working..:) you have to pass InputStream in readConfiguration().
*/
