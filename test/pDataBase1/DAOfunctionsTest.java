/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDataBase1;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import pDialogs.ExceptionMessage;
import pGlobals.globals;
//import pDataBase1.*;

/**
 *
 * @author dell
 */
public class DAOfunctionsTest {
    static private Connection conn;
    static private DAOfunctions daof;
    
    public DAOfunctionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       StaticConnectToDatabase .SetDataBaseType(DataBaseTypesEnum.MySqlType);
       try {
               conn=StaticConnectToDatabase.Connect();
       }
       catch (StaticConnectToDatabaseException e) {
           int exit_code=e.GetExitCode();
           String mess=e.GetInterMessage();
           String message_full=mess+"\n\r"+"Błąd połączenie z bazą danych";
           ExceptionMessage.ShowExceptionMessageAndExit1(message_full,exit_code);
       }
           daof=new DAOfunctions(conn);
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          if (conn!=null) conn.close();
        }
        catch(SQLException e) {
            System.out.print(e);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of SetDataBaseType method, of class DAOfunctions.
     */
    @Test
    public void testSetDataBaseType() {
        System.out.println("SetDataBaseType");
        DataBaseTypesEnum Type = null;
        DAOfunctions.SetDataBaseType(Type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetDataBaseType method, of class DAOfunctions.
     */
    @Test
    public void testGetDataBaseType() {
        System.out.println("GetDataBaseType");
        DataBaseTypesEnum expResult = null;
        DataBaseTypesEnum result = DAOfunctions.GetDataBaseType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of InsertEPC method, of class DAOfunctions.
     */
    @Test
    public void testInsertEPC_3args() throws Exception {
        System.out.println("InsertEPC");
        String TableName = globals.TableName;
        String EPC = "abcdefgh";
        String Status = "1";
        DAOfunctions instance = daof;
        boolean expResult = false;
        boolean result = instance.InsertEPC(TableName, EPC, Status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of InsertEPC method, of class DAOfunctions.
     */
    @Test
    public void testInsertEPC_String_ArrayList() throws Exception {
        System.out.println("InsertEPC");
        String TableName = "";
        ArrayList<String> row = null;
        DAOfunctions instance = null;
        boolean expResult = false;
        boolean result = instance.InsertEPC(TableName, row);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CreateTable method, of class DAOfunctions.
     */
    @Test
    public void testCreateTable() throws Exception {
        System.out.println("CreateTable");
        String TableName = "";
        DAOfunctions instance = null;
        instance.CreateTable(TableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ReadEPCs method, of class DAOfunctions.
     */
    @Test
    public void testReadEPCs() throws Exception {
        System.out.println("ReadEPCs");
        String TableName = "";
        DAOfunctions instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.ReadEPCs(TableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ReadLabelStatus method, of class DAOfunctions.
     */
    @Test
    public void testReadLabelStatus_Integer_String() throws Exception {
        System.out.println("ReadLabelStatus");
        Integer Id = null;
        String TableName = "";
        DAOfunctions instance = null;
        String expResult = "";
        String result = instance.ReadLabelStatus(Id, TableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ReadLabelStatus method, of class DAOfunctions.
     */
    @Test
    public void testReadLabelStatus_String_String() throws Exception {
        System.out.println("ReadLabelStatus");
        String EPC = "";
        String TableName = "";
        DAOfunctions instance = null;
        String expResult = "";
        String result = instance.ReadLabelStatus(EPC, TableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DropTable method, of class DAOfunctions.
     */
    @Test
    public void testDropTable() throws Exception {
        System.out.println("DropTable");
        String TableName = "";
        DAOfunctions instance = null;
        instance.DropTable(TableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ClearTable method, of class DAOfunctions.
     */
    @Test
    public void testClearTable() throws Exception {
        System.out.println("ClearTable");
        String TableName = "";
        DAOfunctions instance = null;
        instance.ClearTable(TableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ChangeStatus method, of class DAOfunctions.
     */
    @Test
    public void testChangeStatus_3args_1() throws Exception {
        System.out.println("ChangeStatus");
        String TableName = "";
        String Status = "";
        Integer Id = null;
        DAOfunctions instance = null;
        int expResult = 0;
        int result = instance.ChangeStatus(TableName, Status, Id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ChangeStatus method, of class DAOfunctions.
     */
    @Test
    public void testChangeStatus_3args_2() throws Exception {
        System.out.println("ChangeStatus");
        String TableName = "";
        String status = "";
        String EPc = "";
        DAOfunctions instance = null;
        int expResult = 0;
        int result = instance.ChangeStatus(TableName, status, EPc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
