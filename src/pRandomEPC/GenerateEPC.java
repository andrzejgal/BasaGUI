/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pRandomEPC;

//import RandomEPC;
import pFifo.FifoClass;
import pGlobals.globals;
import pParentClass.ParentClass;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class GenerateEPC  extends ParentClass{
 //   final Integer EPCLength=24;
    final String SEPARATOR=",";
    private Integer epcs_cc;
    private String header;
    private String pEPC=null;
    
    public GenerateEPC (final Integer epc_count,final String Header) {
        this.epcs_cc=epc_count;
        this.header=Header;
    }
    
    public GenerateEPC() {
       
    }
    
   public String GenerateOneEPC() {
       RandomString rns=new RandomString(globals.EPCLength);
       String NewEPC=rns.nextString();
       return NewEPC;
   }  
    
   public String GetEPCs() {
       RandomString RS=new RandomString(globals.EPCLength);
       pEPC=String.format("%05d",CalculateLength())+header+SEPARATOR;
       for (Integer n=0;n<epcs_cc;n++){
           pEPC+=RS.nextString()+SEPARATOR;
       }      
       return pEPC;
   }
   
   public String GetEPCs(ArrayList<String> wej) {
       pEPC=String.format("%05d",CalculateLength())+header+SEPARATOR;
       for (Integer n=0;n<epcs_cc;n++){
           pEPC+=wej.get(n)+SEPARATOR;
       }      
       return pEPC;
       
   }
   
   public String GetEPCsWithDuplicates() {
       RandomString RS=new RandomString(globals.EPCLength);
       String epc="",ep="";
       pEPC=String.format("%05d",CalculateLength())+header+SEPARATOR;
       for (Integer n=0;n<epcs_cc;n++){
           epc=RS.nextString();
           FifoClass.AddToList(epc);
//           pEPC+=epc+SEPARATOR;
       } 
//       pEPC="";
       ep=FifoClass.ReadFromList();
       while (ep!="") {
           pEPC+=ep;
           pEPC+=SEPARATOR;
           ep=FifoClass.ReadFromList();
       }
       return pEPC;      
   }

   
   public Integer CalculateLength() {
     Integer ll=globals.EPCLength*epcs_cc+epcs_cc;  //+epcs_cc separators
     return ll; 
   }
    
}
