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
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author dell
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({pDataBase1.StaticConnectToDatabaseTest.class, pDataBase1.DAOfunctionExceptionTest.class, pDataBase1.DAOfunctionsTest.class, pDataBase1.DaoInterfaceTest.class, pDataBase1.DataBaseTypesEnumTest.class, pDataBase1.StaticConnectToDatabaseExceptionTest.class})
public class PDataBase1Suite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
