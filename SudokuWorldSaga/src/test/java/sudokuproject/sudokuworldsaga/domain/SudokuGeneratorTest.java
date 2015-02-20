/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Henri
 */
public class SudokuGeneratorTest {

    private Sudoku validSudoku;

    
    public SudokuGeneratorTest() {
        validSudoku = SudokuGenerator.genNewSudoku(3, 3, 50);
    }
    
    @Test
    public void constructorTest() {
        assertTrue("Test that doesn't test anything failed. GJ", new SudokuGenerator() != null);
    }

    /*
     * Test genNewSudoku() with illegal arguments, should throw exception.
     */
    
    @Test
    public void testFalseInputsGenNewSudoku() {
        Sudoku sudoku = null;
        try {
            sudoku = SudokuGenerator.genNewSudoku(1, 1, 30);
        } catch (Exception ex) {
            assertTrue("Illegal arguments: " + ex.getMessage(), true);
        }
        try {
            sudoku = SudokuGenerator.genNewSudoku(3, 3, -5);
        } catch (Exception ex) {
            assertTrue("Illegal arguments: " + ex.getMessage(), true);
        }
        assertTrue("Sudoku should be null: ", sudoku == null);
    }
    
    /*
     * Test that the generated sudoku's dimensions are correct
     */
    @Test
    public void sudokuGenDimTest() {
        assertTrue("Dimensions wrong.", validSudoku.getCols() == 3 && validSudoku.getRows() == 3);
    }
    
    /*
     * Tests that the generated sudoku is solvable and it has exactly 1 solution 
     */
    @Test
    public void sudokuGenSolvableTest() {
        ArrayList<Sudoku> solutions = SudokuSolver.solveAll(validSudoku);
        assertTrue("Generated sudoku should be solvable", solutions != null);
        assertTrue("Generated sudoku should have only 1 solution.", SudokuSolver.solveAll(validSudoku).size() == 1);
        assertTrue("Generated sudoku should have 50 or less unsolved cells.", validSudoku.countUnsolved() <= 50);
    }
    
    /*
     * Tests that the generated sudoku contains only valid entries.
     */
    @Test
    public void sudokuGenTest() {
        int[][] sudokuValues = validSudoku.getSudokuData();
        ArrayList<Integer> valueSet = new ArrayList<Integer>();
        
        for (int i = 0; i < 10; i++) {
            valueSet.add(i);
        }
        
        boolean valueCheckPassed = true;
        
        for (int[] i : sudokuValues) {
            for (int j : i) {
                if (!valueSet.contains(j)) {
                    valueCheckPassed = false;
                }
            }
        }
        assertTrue("Generated sudoku contains wrong values", valueCheckPassed);
    }
    
    /*
     * When nEmpty == 1 also the result sudoku should have same number of unsolved.
     */
    
    @Test
    public void sudokuGenTest2() {
        Sudoku sudoku = SudokuGenerator.genNewSudoku(3, 3, 1);
        assertTrue("Generated sudoku should have 1 unsolved cell.", sudoku.countUnsolved() == 1);
    }
}
