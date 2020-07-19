/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
plik założono 22-01-2019
plik zawiera formater, który będzie używany do wyświetlania komunikatów z wyjątków
przez loggery.
*/
package pDialogs;

/**
 *
 * @author dell
 */
public class ExceptionStringFormatter {
 private String kk;  
 
    public  String FormatExceptionString(final Exception e, final String opis) {
        StringBuilder sb=new StringBuilder(e.getMessage());
        sb.append("\n\r");
        sb.append(opis);
//        kk=String.format("%s%n%s%n%s",opis,e.getMessage(),e);
        return kk=sb.toString();
    }
    
    public String ExtendedExceptionString(final Exception e, final String opis) {
        StringBuilder sb=new StringBuilder(e.getMessage());
        sb.append("\n\r");
        sb.append(opis);
        sb.append("\n\r");
        StackTraceElement[] traceElements = e.getStackTrace();
        for (StackTraceElement element : traceElements) {
            sb.append(element.getClassName());
            sb.append("\n\r");
            sb.append(element.getFileName());
            sb.append("\n\r");
            sb.append(element.getLineNumber());
            sb.append("\n\r");
            sb.append(element.getMethodName());
        }
//        sb.append(e.getStackTrace());
//        kk=String.format("%s%n%s%n%s",opis,e.getMessage(),e);
        return kk=sb.toString();
    }
}
