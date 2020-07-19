/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDataBase1;

import java.util.ArrayList;
import java.sql.Connection;


/**
 *
 * @author dell
 */
    public interface DaoInterface {
        public abstract boolean InsertEPC(final String TableName,final String EPC, final String Status) throws Exception;
        public abstract void CreateTable(final String TableName) throws Exception;    
        public abstract ArrayList<String[]> ReadEPCs(final String TableName) throws Exception ;
        public abstract String ReadLabelStatus(final String EPC, final String TableName) throws Exception;
        public abstract String ReadLabelStatus(final Integer Id, final String TableName) throws Exception;
        public abstract void DropTable(String TableName) throws Exception;
        public abstract void ClearTable(String TableName) throws Exception;
        public abstract int ChangeStatus(final String TableName,final String Status, final String EPC) throws Exception;
        public abstract int ChangeStatus(final String TableName,final String Status, final Integer Id) throws Exception;
/*
 Funkcja TestIfDataBaseExists nie ma podanej nazwy tabeli na wejściu, ponieważ ona sprawdza,
        czy istnieje tabela systemowa.
        */
//        public  boolean TestIfDataBaseExists() throws Exception;
    
    
}

