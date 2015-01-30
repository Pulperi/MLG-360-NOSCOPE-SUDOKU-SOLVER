/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Henri
 */
public class SudokuSolverTest {
    
    public SudokuSolverTest() {
    }

    @Test
    public void testSolveNull() {
        boolean nullTestFail = true;
        try {
            SudokuSolver.solve(null);
        } catch (IllegalArgumentException iAE) {
            nullTestFail = false;
        }
        assertFalse("Null test failed.", nullTestFail);
    }
    

    @Test
    public void testSolveBasic() {
        int[][] sudokuDataArray = {{5,3,0,6,0,0,0,9,8}, 
                                   {0,7,0,1,9,5,0,0,0}, 
                                   {0,0,0,0,0,0,0,6,0}, 
                                   {8,0,0,4,0,0,7,0,0}, 
                                   {0,6,0,8,0,3,0,2,0}, 
                                   {0,0,3,0,0,1,0,0,6}, 
                                   {0,6,0,0,0,0,0,0,0}, 
                                   {0,0,0,4,1,9,0,8,0}, 
                                   {2,8,0,0,0,5,0,7,9}};
        
        int[][] solvedSudokuDataArray = {{5,3,4,6,7,2,1,9,8}, 
                                         {6,7,8,1,9,5,3,4,2}, 
                                         {9,1,2,3,4,8,5,6,7}, 
                                         {8,5,9,4,2,6,7,1,3}, 
                                         {7,6,1,8,5,3,9,2,4}, 
                                         {4,2,3,7,9,1,8,5,6},
                                         {9,6,1,2,8,7,3,4,5}, 
                                         {5,3,7,4,1,9,2,8,6}, 
                                         {2,8,4,6,3,5,1,7,9}};
        
        Sudoku sudoku = new Sudoku(3,3, sudokuDataArray);
        Sudoku sudokuControl = new Sudoku(3,3, solvedSudokuDataArray);
        
        try {
            Sudoku solved = SudokuSolver.solve(sudoku);
            System.out.println(solved);
            assertTrue("Solving test failed.", solved.isSolved());
            assertTrue("Solver not giving correct answer.", solved.equals(sudokuControl));
        } catch (Exception ex) {
            assertFalse("Solving test failed: " + ex.getMessage() , true);
        }
    }
    
    /* To Be Implemented
     * 
     *  - More extensive testing with randomly generated sudokus and test against correct answer
     * 
     * public void testSolveExtensive()
     */
    
    @Test
    public void testSolveUnsolvable() {
        Sudoku sudoku = genSudoku(3,3);
        
        sudoku.set(5, 2, 0);
        sudoku.set(1, 3, 0);
        sudoku.set(7, 7, 0);
        
        assertTrue("Solving unsolvable failed", SudokuSolver.solve(sudoku) == null);
    }

    // Generates full invalid (unsolvable) sudoku
    private Sudoku genSudoku(int rows, int cols) {
        Sudoku sudoku = new Sudoku(rows, cols);
        for (int x = 0; x < rows*cols; x++) {
            for (int y = 0; y < rows*cols; y++) {
                sudoku.set(x, y, y+1);
            }
        }
        return sudoku;
    }
}
