/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDataBase1;

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
public class StaticConnectToDatabaseExceptionTest {
    
    public StaticConnectToDatabaseExceptionTest() {
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
     * Test of GetExitCode method, of class StaticConnectToDatabaseException.
     */
    @Test
    public void testGetExitCode() {
        System.out.println("GetExitCode");
        StaticConnectToDatabaseException instance = null;
        Integer expResult = null;
        Integer result = instance.GetExitCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetInterMessage method, of class StaticConnectToDatabaseException.
     */
    @Test
    public void testGetInterMessage() {
        System.out.println("GetInterMessage");
        StaticConnectToDatabaseException instance = null;
        String expResult = "";
        String result = instance.GetInterMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
