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
    Sudoku sudoku, sudokuControl, solved;
    public SudokuSolverTest() {
        int[][] sudokuDataArray = {{5, 3, 0, 6, 0, 0, 0, 9, 8},
            {0, 7, 0, 1, 9, 5, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0, 6, 0}, 
            {8, 0, 0, 4, 0, 0, 7, 0, 0}, 
            {0, 6, 0, 8, 0, 3, 0, 2, 0}, 
            {0, 0, 3, 0, 0, 1, 0, 0, 6}, 
            {0, 6, 0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 4, 1, 9, 0, 8, 0}, 
            {2, 8, 0, 0, 0, 5, 0, 7, 9}};
        
        int[][] solvedSudokuDataArray = {{5, 3, 4, 6, 7, 2, 1, 9, 8},
            {6, 7, 8, 1, 9, 5, 3, 4, 2},
            {9, 1, 2, 3, 4, 8, 5, 6, 7},
            {8, 5, 9, 4, 2, 6, 7, 1, 3},
            {7, 6, 1, 8, 5, 3, 9, 2, 4},
            {4, 2, 3, 7, 9, 1, 8, 5, 6},
            {9, 6, 1, 2, 8, 7, 3, 4, 5},
            {5, 3, 7, 4, 1, 9, 2, 8, 6},
            {2, 8, 4, 6, 3, 5, 1, 7, 9}};
        
        sudoku = new Sudoku(3, 3, sudokuDataArray);
        sudokuControl = new Sudoku(3, 3, solvedSudokuDataArray);
        solved = SudokuSolver.solve(sudoku);
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
    public void constructorTest() {
        assertTrue("Test that doesn't test anything failed. GJ", new SudokuSolver() != null);
    }
    
    /*
     * Tests that solver returns a solved sudoku 
     */
    @Test
    public void testSolveBasic() {
        assertTrue("Solving test failed.", solved.isSolved());
    }
    
    /*
     * Tests that solver returns correct answer when running solver with already solved sudoku
     */
    @Test
    public void testSolveSolved() {
        assertTrue("Should return same sudoku when solver called on already solved sudoku.", solved.equals(SudokuSolver.solve(solved)));
    }
    
    /*
     * Tests the solver's answer against the control sudoku
     */
    @Test
    public void testSolveCorrect() {
        assertTrue("Solver not giving correct answer.", solved.equals(sudokuControl));
    }

    /*
     * Tests solver methods with invalid sudokus.
     * Should return null/empty list
     */
    @Test
    public void testSolveInvalid() {
        Sudoku invalidSudoku = new Sudoku(sudoku);
        invalidSudoku.set(0, 0, 3);
        assertTrue("Should return empty if invalid sudoku.", SudokuSolver.solveAll(invalidSudoku).isEmpty());
        assertTrue("Should return null if invalid sudoku.", SudokuSolver.solve(invalidSudoku) == null);
    }

    @Test
    public void testSolveInvalid2() {
        Sudoku generatedSudoku = genSudoku(3, 3);
        
        generatedSudoku.set(5, 2, 0);
        generatedSudoku.set(1, 3, 0);
        generatedSudoku.set(7, 7, 0);
        
        assertTrue("Solving unsolvable failed", SudokuSolver.solve(generatedSudoku) == null);
    }

    // Generates full invalid (unsolvable) sudoku
    private Sudoku genSudoku(int rows, int cols) {
        Sudoku generatedSudoku = new Sudoku(rows, cols);
        for (int x = 0; x < rows * cols; x++) {
            for (int y = 0; y < rows * cols; y++) {
                generatedSudoku.set(x, y, y + 1);
            }
        }
        return generatedSudoku;
    }
}
