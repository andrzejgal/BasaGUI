/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pFifo;

import java.util.logging.Level;     
import java.util.logging.Logger;
import java.util.*;
import pParentClass.*;

/**
 *
 * @author admin
 */
public class FifoClass extends ParentClass {
    private static boolean reading=false;
    private static boolean writing=false;
    private static boolean reading_from_list_lock=false;
    private static boolean writing_to_list_lock=false;
    private final static Logger logger=Logger.getLogger(FifoClass.class.getName());
    private static Vector<String> EPCList=new Vector<>();
    
    public static boolean GetReadingStatus() {
        return reading;
    }
    
    public static boolean GetWritingStatus() {
        return writing;
    }
    
    public static boolean GetReadFromListLock() {
        return reading_from_list_lock;
    }
    
    public static void SetReadFromListLock(final boolean lock) {
         reading_from_list_lock=lock;
    }
    
    public static boolean  GetWriteToListLock() {
        return writing_to_list_lock;
    }
    
       public static void SetWriteToListLock(final boolean lock) {
        writing_to_list_lock=lock;
    }

    
//     @Override
      protected static void ExceptionHandlerA(Exception e,String kom) throws FifoClassException {
          /*
            if (ExceptionDescription) {
                logger.log(Level.INFO, kom, e);
                System.exit(-1);
            }
            else {
                throw new  FifoClassException(kom);
                }
                */
          }
      
    public static boolean GetReading () {
        return reading;
    }
    
    public static boolean GetWritting() {
        return writing;
    }
     
     public static synchronized void AddToList(String EPC) {
         writing=true;
         if (!EPCList.contains(EPC)){
             EPCList.addElement(EPC);
         }
         writing=false;
     }
     
     public static  synchronized String ReadFromList() {
        String rr="";
        reading=true;
        if (EPCList.size()>0){
            rr=EPCList.get(0);
            EPCList.remove(0);
        }
        reading=false;
        return rr;
      }
     
     public static  synchronized Integer Getsize() {
         return EPCList.size();
     }

    public static synchronized String ShowListcontest() {
        return EPCList.toString();
    }
}
