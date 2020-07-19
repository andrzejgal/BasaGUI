/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDataBase1;

import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;

/**
 *
 * @author dell
 */
public class StaticConnectToDatabaseTest {
    
    public StaticConnectToDatabaseTest() {
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
     * Test of Connect method, of class StaticConnectToDatabase.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("Connect");
        Connection expResult = null;
        Connection result = StaticConnectToDatabase.Connect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SetDataBaseType method, of class StaticConnectToDatabase.
     */
    @Test
    public void testSetDataBaseType() {
        System.out.println("SetDataBaseType");
        DataBaseTypesEnum DT = null;
        StaticConnectToDatabase.SetDataBaseType(DT);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetDataBaseType method, of class StaticConnectToDatabase.
     */
    @Test
    public void testGetDataBaseType() {
        System.out.println("GetDataBaseType");
        DataBaseTypesEnum expResult = null;
        DataBaseTypesEnum result = StaticConnectToDatabase.GetDataBaseType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
