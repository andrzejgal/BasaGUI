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

/**
 *
 * @author dell
 */
public class DaoInterfaceTest {
    
    public DaoInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of InsertEPC method, of class DaoInterface.
     */
    @Test
    public void testInsertEPC() throws Exception {
        System.out.println("InsertEPC");
        String TableName = "";
        String EPC = "";
        String Status = "";
        DaoInterface instance = new DaoInterfaceImpl();
        boolean expResult = false;
        boolean result = instance.InsertEPC(TableName, EPC, Status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CreateTable method, of class DaoInterface.
     */
    @Test
    public void testCreateTable() throws Exception {
        System.out.println("CreateTable");
        String TableName = "";
        DaoInterface instance = new DaoInterfaceImpl();
        instance.CreateTable(TableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ReadEPCs method, of class DaoInterface.
     */
    @Test
    public void testReadEPCs() throws Exception {
        System.out.println("ReadEPCs");
        String TableName = "";
        DaoInterface instance = new DaoInterfaceImpl();
        ArrayList expResult = null;
        ArrayList result = instance.ReadEPCs(TableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ReadLabelStatus method, of class DaoInterface.
     */
    @Test
    public void testReadLabelStatus_String_String() throws Exception {
        System.out.println("ReadLabelStatus");
        String EPC = "";
        String TableName = "";
        DaoInterface instance = new DaoInterfaceImpl();
        String expResult = "";
        String result = instance.ReadLabelStatus(EPC, TableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ReadLabelStatus method, of class DaoInterface.
     */
    @Test
    public void testReadLabelStatus_Integer_String() throws Exception {
        System.out.println("ReadLabelStatus");
        Integer Id = null;
        String TableName = "";
        DaoInterface instance = new DaoInterfaceImpl();
        String expResult = "";
        String result = instance.ReadLabelStatus(Id, TableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of DropTable method, of class DaoInterface.
     */
    @Test
    public void testDropTable() throws Exception {
        System.out.println("DropTable");
        String TableName = "";
        DaoInterface instance = new DaoInterfaceImpl();
        instance.DropTable(TableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ClearTable method, of class DaoInterface.
     */
    @Test
    public void testClearTable() throws Exception {
        System.out.println("ClearTable");
        String TableName = "";
        DaoInterface instance = new DaoInterfaceImpl();
        instance.ClearTable(TableName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ChangeStatus method, of class DaoInterface.
     */
    @Test
    public void testChangeStatus_3args_1() throws Exception {
        System.out.println("ChangeStatus");
        String TableName = "";
        String Status = "";
        String EPC = "";
        DaoInterface instance = new DaoInterfaceImpl();
        int expResult = 0;
        int result = instance.ChangeStatus(TableName, Status, EPC);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ChangeStatus method, of class DaoInterface.
     */
    @Test
    public void testChangeStatus_3args_2() throws Exception {
        System.out.println("ChangeStatus");
        String TableName = "";
        String Status = "";
        Integer Id = null;
        DaoInterface instance = new DaoInterfaceImpl();
        int expResult = 0;
        int result = instance.ChangeStatus(TableName, Status, Id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DaoInterfaceImpl implements DaoInterface {

        public boolean InsertEPC(String TableName, String EPC, String Status) throws Exception {
            return false;
        }

        public void CreateTable(String TableName) throws Exception {
        }

        public ArrayList<String[]> ReadEPCs(String TableName) throws Exception {
            return null;
        }

        public String ReadLabelStatus(String EPC, String TableName) throws Exception {
            return "";
        }

        public String ReadLabelStatus(Integer Id, String TableName) throws Exception {
            return "";
        }

        public void DropTable(String TableName) throws Exception {
        }

        public void ClearTable(String TableName) throws Exception {
        }

        public int ChangeStatus(String TableName, String Status, String EPC) throws Exception {
            return 0;
        }

        public int ChangeStatus(String TableName, String Status, Integer Id) throws Exception {
            return 0;
        }
    }
    
}
