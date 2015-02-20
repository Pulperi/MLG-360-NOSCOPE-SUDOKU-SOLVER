/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.ui;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Henri
 */
public class SudokuWorldSagaTest {
    
    public SudokuWorldSagaTest() {
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

    @Test
    public void testLaunch() {
        try {
            SudokuWorldSaga.launch();
        } catch (Exception ex) {
            fail("The test failed due to unhandled exception. " + ex.getMessage());
        }
    }
}
