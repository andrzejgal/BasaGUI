/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pDialogs;

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
public class ExceptionStringFormatterTest {
    
    public ExceptionStringFormatterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
//        opis="OpisTest";
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testFormatExceptionString() {
        System.out.println("FormatExceptionString");
        Exception e=new NullPointerException("testNullException");
        String opis = "testOpis";
        ExceptionStringFormatter instance = new ExceptionStringFormatter();
        String expResult = "";
        String result = instance.FormatExceptionString(e, opis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testExtendedExceptionString() {
        System.out.println("ExtendedExceptionString");
        Exception e=new NullPointerException("testNullException");
        String opis = "testOpis";
        ExceptionStringFormatter instance = new ExceptionStringFormatter();
        String expResult = "";
        String result = instance.ExtendedExceptionString(e, opis);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
