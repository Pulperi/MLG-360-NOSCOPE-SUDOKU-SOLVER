/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Henri
 */
public class SudokuDataTest {
    
    public SudokuDataTest() {
    }

    /*
     * Testing SudokuData class equal method
     */
    @Test
    public void testEqual() {
        SudokuData emptySudokuData = new SudokuData(3, 3);
        SudokuData nullSudoku = null;
        String stringSudokuData = "sudoku";
        
        SudokuData solvedData1 = new SudokuData(3, 3, SudokuSolver.solve(new Sudoku(3, 3)).getSudokuData());
        SudokuData copyOfSolvedData1 = new SudokuData(3, 3, solvedData1.getData());
        
        //Comparing to null
        assertFalse("Should return false", emptySudokuData.equals(nullSudoku));
        //Comparing to wrong class
        assertFalse("Should return false.", emptySudokuData.equals(stringSudokuData));
        //Comparing to not same
        assertFalse("Should return false", emptySudokuData.equals(solvedData1));
        //Comparing to copy
        assertTrue("Should return true", solvedData1.equals(copyOfSolvedData1));
    }
    
    /*
     * Testing for the isValid() method
     */
    
    @Test
    public void basicValidityTest() {
        ArrayList<Sudoku> sudokuList = sudokuListGenerator(10);
        
        for (Sudoku s : sudokuList) {
            assertTrue("Should return true when checking validity of valid sudoku.", s.isValid());
        }
    }
    
    /*
     * Generate 10 random sudokus and change one value in each of them to make them invalid.
     * isValid() should return false.
     */
    
    @Test
    public void randomInValidityTest() {
        ArrayList<Sudoku> sudokuList = sudokuListGenerator(10);
        Random random = new Random();
        for (Sudoku s : sudokuList) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            
            // increment one number by one and make the sudoku invalid
            int val = s.getXY(x, y);
            val = (val + 1) % 9;
            if (val == 0) {
                val++;
            }
            s.set(x, y, val);
            assertFalse("Should return false when checking validity of non-valid sudoku.", s.isValid());
        }
    }
    
    /*
     * Specifically crafted tests to test different aspects of isValid method's validity checks.
     * isValid() should return false.
     */
    
    @Test
    public void specificInValidityTest() {  
        // SPECIFIC TARGETTED TESTS
        Sudoku st1 = new Sudoku(3, 3);
        st1.set(0, 0, 3);
        st1.set(2, 2, 3);
        assertFalse("Should return false when checking validity of non-valid sudoku. (SUBSET FAIL).", st1.isValid());
        
        Sudoku st2 = new Sudoku(3, 3);
        st2.set(0, 0, -5);
        assertFalse("Should return false when checking validity of non-valid sudoku. (VALUE FAIL).", st2.isValid());

        Sudoku st3 = new Sudoku(3, 3);
        st3.set(0, 0, 13);
        assertFalse("Should return false when checking validity of non-valid sudoku. (VALUE FAIL).", st3.isValid());
    }
    
    private ArrayList<Sudoku> sudokuListGenerator(int n) {
        ArrayList<Sudoku> sudokuList = new ArrayList<Sudoku>();
        for (int i = 0; i < n; i++) {
            sudokuList.add(SudokuGenerator.genNewSudoku(3, 3, 0));
        }
        return sudokuList;
    }
}
