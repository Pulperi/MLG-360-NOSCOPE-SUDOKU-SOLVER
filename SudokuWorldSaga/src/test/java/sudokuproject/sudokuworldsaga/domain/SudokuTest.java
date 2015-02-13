/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.Arrays;
import java.util.Random;
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
     * Test Constructors
     */
    
    @Test
    public void sudokuConstructorTest() {
        int[][] sudokuDataArray = {{5,3,0,6,0,0,0,9,8}, 
                                   {0,7,0,1,9,5,0,0,0}, 
                                   {0,0,0,0,0,0,0,6,0}, 
                                   {8,0,0,4,0,0,7,0,0}, 
                                   {0,6,0,8,0,3,0,2,0}, 
                                   {0,0,3,0,0,1,0,0,6}, 
                                   {0,6,0,0,0,0,0,0,0}, 
                                   {0,0,0,4,1,9,0,8,0}, 
                                   {2,8,0,0,0,5,0,7,9}};
        // Test dimensioncheck
        
        assertTrue("Constructor should throw illegalArgumentException", sudokuConstructorTest(-1, 3, sudokuDataArray));
        assertTrue("Constructor should throw illegalArgumentException", sudokuConstructorTest(2, -3, sudokuDataArray));
        assertTrue("Constructor should throw illegalArgumentException", sudokuConstructorTest(3, 3, null));
        
        assertTrue("Constructor should throw illegalArgumentException", sudokuConstructorTest(-1, 3));
        assertTrue("Constructor should throw illegalArgumentException", sudokuConstructorTest(2, -3));
        
        assertTrue("Constructor should throw illegalArgumentException", sudokuConstructorTest(null));
    }
    
    private boolean sudokuConstructorTest(int x, int y, int[][] sudokuDataArray) {
        boolean errorCaught = false;
        try {
            Sudoku sudoku = new Sudoku(x,y,sudokuDataArray);
        } catch (IllegalArgumentException ex) {
            errorCaught = true;
        }
        return errorCaught;
    }
    
    private boolean sudokuConstructorTest(int x, int y) {
        boolean errorCaught = false;
        try {
            Sudoku sudoku = new Sudoku(x,y);
        } catch (IllegalArgumentException ex) {
            errorCaught = true;
        }
        return errorCaught;
    }

    private boolean sudokuConstructorTest(Sudoku sudoku) {
        boolean errorCaught = false;
        try {
            Sudoku newSudoku = new Sudoku(sudoku);
        } catch (IllegalArgumentException ex) {
            errorCaught = true;
        }
        return errorCaught;
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
        
        assertTrue("Wrong number of rows", rows * (cols + 1) == resultParsed.length);
        
        for (String s : resultParsed) {
            if (!s.contains("Unsolved")) {
                assertTrue(((rows + 1) * cols - 1) + " Wrong length row: " + s, (rows + 1) * cols - 1 == s.length());
            }
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
        for (String s : Arrays.copyOfRange(result, 0, result.length-1)) {
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
    
    @Test
    public void testGetXY() {
        Sudoku sudoku = genSudoku(3,3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && i != 1) {
                    assertTrue("Should throw IllegalArgumentException when called with false arguments.", testGetXY(12-i*9, 14-j*9, sudoku));
                    //assertTrue("Should throw IllegalArgumentException when called with false arguments.", testGetEntries(12-i*9, 14-j*9, sudoku));
                }
            }
        }
       
    }
    @Test
    public void testGetEntries() {
        Sudoku sudoku = genSudoku(3,3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && i != 1) {
                    //assertTrue("Should throw IllegalArgumentException when called with false arguments.", testGetXY(12-i*9, 14-j*9, sudoku));
                    assertTrue("Should throw IllegalArgumentException when called with false arguments.", testGetEntries(12-i*9, 14-j*9, sudoku));
                }
            }
        }
       
    }
    
    private boolean testGetXY(int x, int y, Sudoku sudoku) {
        boolean errorCaught = false;
        try {
            sudoku.getXY(x, y);
        } catch (IllegalArgumentException ex) {
            errorCaught = true;
        }
        return errorCaught;
    }
    
    private boolean testGetEntries(int x, int y, Sudoku sudoku) {
        boolean errorCaught = false;
        try {
            sudoku.getEntries(x, y);
        } catch (IllegalArgumentException ex) {
            errorCaught = true;
        }
        return errorCaught;
    }
    
    private void testSpecificSudoku(int rows, int cols) {
        
        Sudoku sudoku = genSudoku(rows,cols);
        String[] results = sudoku.toString().split("\n");
        int counter = 1;
        for (String str : Arrays.copyOfRange(results, 0, results.length-1)) {
            if (!str.contains("-")) {
                String row = str.replace("|", "").replace(counter + "", "");
                assertTrue("Sudoku contains something other than it's supposed to: " + row, row.isEmpty());
                counter++;
            }
        }
    }
    
    
    
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
    public void testEqual() {
        Sudoku emptySudoku = new Sudoku(3,3);
        Sudoku nullSudoku = null;
        String stringSudoku = "sudoku";
        
        Sudoku solved1 = SudokuSolver.solve(emptySudoku);
        Sudoku copyOfSolved1 = new Sudoku(solved1);
        
        assertFalse("Should return false", emptySudoku.equals(nullSudoku));
        assertFalse("Should return false.", emptySudoku.equals(stringSudoku));
        assertFalse("Should return false", emptySudoku.equals(solved1));
        assertTrue("Should return true", solved1.equals(copyOfSolved1));
    }
    
    
    
    
    
    
}
