/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henri
 */
public class SudokuDataTest {
    
    public SudokuDataTest() {
    }

    @Test
    public void testEqual() {
        Sudoku emptySudoku = new Sudoku(3,3);
        Sudoku nullSudoku = null;
        String stringSudoku = "sudoku";
        
        Sudoku solved1 = SudokuSolver.solve(emptySudoku);
        Sudoku copyOfSolved1 = new Sudoku(solved1);
        
        assertFalse("Should return false", emptySudoku.equals(nullSudoku));
        assertFalse("Should return false", emptySudoku.equals(stringSudoku));
        assertFalse("Should return false", emptySudoku.equals(solved1));
        assertTrue("Should return true", solved1.equals(copyOfSolved1));
    }
}
