/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henri
 */
public class SudokuTest {
    
    public SudokuTest() {
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
     * Test of getXY method, of class Sudoku.
     
    @Test
    public void testGetXY() {
        System.out.println("getXY");
        int x = 0;
        int y = 0;
        Sudoku instance = null;
        int expResult = 0;
        int result = instance.getXY(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isSolved method, of class Sudoku.
     */
    
    @Test
    public void testIsSolved() {
        System.out.println("Testing isSolved method");
        assertFalse("check isSolved method, should return false on empty", (new Sudoku(3,3)).isSolved());
        Random random = new Random();
        assertTrue("check isSolved, should return true on full", genSudoku(3,3).isSolved());
        for (int i = 0; i < 10; i++) {
            int rows = random.nextInt(1) + 2;
            int cols = random.nextInt(1) + 2;
            Sudoku sudoku = genSudoku(rows, cols);
            sudoku.set(random.nextInt(rows*cols), random.nextInt(rows*cols), 0);
            assertFalse("check isSolved method", sudoku.isSolved());
        }
    }

    /**
     * Test of toString method, of class Sudoku.
     */
    @Test
    public void testToStringGeneral() {
        System.out.println("Testing that toString method returns something and the syntax is correct");
        int rows = 4, cols = 3;
        
        String result = (new Sudoku(rows,cols)).toString();
        assertTrue("toString returned null", result != null);
        assertTrue("toString returned empty", !result.isEmpty());
        
        String[] resultParsed = result.split("\n");
        
        assertTrue("Wrong number of rows", rows * (cols + 1) - 1 == resultParsed.length);
        
        for (String s : resultParsed) {
            assertTrue(((rows + 1) * cols - 1) + " Wrong length row: " + s, (rows + 1) * cols - 1 == s.length());
        }
    }
    
    @Test
    public void testToStringEmpty() {
        System.out.println("Testing toString method with empty sudoku. Should print a sudoku filled with zeros.");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            testToStringEmpty(random.nextInt(10) + 2, random.nextInt(10) + 2);   
        }
    }

    private void testToStringEmpty(int rows, int cols) {
        String[] result = (new Sudoku(rows,cols)).toString().split("\n");
        for (String s : result) {
            String checkString = s.replace("|", "");
            checkString = checkString.replace("-", "");
            checkString = checkString.replaceAll("0", "");
            assertTrue("Empty sudoku contains something other than it's supposed to: " + checkString, checkString.isEmpty());
        }
    }
    
    @Test
    public void testSpecificSudoku() {
        System.out.println("Testing toString method with non-empty sudokus.");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            testSpecificSudoku(random.nextInt(1) + 2, random.nextInt(1) + 2);
            // TESTIRAJAT MUUTETTAVA KUN SUDOKU TULOSTIN KORJATTU (yli 9 numerot kirjaimiksi)
        }
    }
    
    private void testSpecificSudoku(int rows, int cols) {
        
        Sudoku sudoku = genSudoku(rows,cols);
        String[] results = sudoku.toString().split("\n");
        int counter = 1;
        for (String str : results) {
            if (!str.contains("-")) {
                String row = str.replace("|", "").replace(counter + "", "");
                assertTrue("Sudoku contains something other than it's supposed to: " + row, row.isEmpty());
                counter++;
            }
        }
    }
    
    
    /**
     * Test of getEntries method, of class Sudoku.
     
    @Test
    public void testGetEntries() {
        System.out.println("getEntries");
        int x = 0;
        int y = 0;
        Sudoku instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getEntries(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Sudoku.
     
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Sudoku instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Sudoku.
     
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Sudoku instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class Sudoku.
     
    @Test
    public void testSet() {
        System.out.println("set");
        int x = 0;
        int y = 0;
        int value = 0;
        Sudoku instance = null;
        instance.set(x, y, value);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/
    
    
    
    
    
    
    private Sudoku genSudoku(int rows, int cols) {
        Sudoku sudoku = new Sudoku(rows, cols);
        for (int x = 0; x < rows*cols; x++) {
            for (int y = 0; y < rows*cols; y++) {
                sudoku.set(x, y, y+1);
            }
        }
        return sudoku;
    }

    @Test
    public void testGetRows() {
    }

    @Test
    public void testGetCols() {
    }

    @Test
    public void testGetXY() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testGetEntries() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testSet() {
    }
}
