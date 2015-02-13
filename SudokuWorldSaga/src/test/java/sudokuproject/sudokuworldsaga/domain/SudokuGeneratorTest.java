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
    
    public SudokuGeneratorTest() {
    }
    
    @Test
    public void constructorTest() {
        assertTrue("Juuh Okei Tehää Nyt Tälläenenki Sit", new SudokuGenerator() != null);
    }

    @Test
    public void testFalseInputsGenNewSudoku() {
        Sudoku sudoku = null;
        try {
            sudoku = SudokuGenerator.genNewSudoku(1,1,30);
        } catch (Exception ex) {
            assertTrue("Illegal arguments: " + ex.getMessage(), true);
        }
        try {
            sudoku = SudokuGenerator.genNewSudoku(3,3,-5);
        } catch (Exception ex) {
            assertTrue("Illegal arguments: " + ex.getMessage(), true);
        }
        assertTrue("Sudoku should be null: ", sudoku == null);
    }
    
    @Test
    public void sudokuGenTest() {
        Sudoku sudoku = SudokuGenerator.genNewSudoku(3,3,50);
        assertTrue("Dimensions wrong.", sudoku.getCols() == 3 && sudoku.getRows() == 3);
        ArrayList<Sudoku> solutions = SudokuSolver.solveAll(sudoku);
        assertTrue("Generated sudoku should be solvable", solutions != null);
        assertTrue("Generated sudoku should have only 1 solution.", SudokuSolver.solveAll(sudoku).size() == 1);
        assertTrue("Generated sudoku should have 50 or less unsolved cells.", sudoku.countUnsolved() <= 50);
        
        int[][] sudokuValues = sudoku.getSudokuData();
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
    
    @Test
    public void sudokuGenTest2() {
        Sudoku sudoku = SudokuGenerator.genNewSudoku(3,3,1);
        
        assertTrue("Generated sudoku should have 1 unsolved cell.", sudoku.countUnsolved() == 1);
    }
}
