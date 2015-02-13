/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Henri
 */
public class SudokuShufflerTest {
    
    public SudokuShufflerTest() {
    }

    @Test
    public void constructorTest() {
        assertTrue("Juuh Okei Tehää Nyt Tälläenenki Sit", new SudokuShuffler() != null);
    }
    
    @Test
    public void nullTest() {
        int[][] sudoku = null;
        try {
            SudokuShuffler.doTheShuffle(2, 2, sudoku);
        } catch (Exception ex) {
            assertFalse("Exception thrown", true);
        }
        assertTrue("Null sudoku should be null, even after shuffled.", sudoku == null);
        
        int[][] sudoku1 = new int[3][3];
        for (int i = 0; i < sudoku1.length; i++) {
            for (int j = 0; j < sudoku1[i].length; j++) {
                sudoku1[i][j] = i;
            }
        }
        
        int[][] sudoku2 = matrixCopy(sudoku1);
        SudokuShuffler.doTheShuffle(1, 2, sudoku1);
        SudokuShuffler.doTheShuffle(2, 1, sudoku1);
        SudokuShuffler.doTheShuffle(3, 4, sudoku1);
        assertTrue("Wrong rows/cols parameters, sudoku should be untouched", superDeepEquals(sudoku1, sudoku2));
          
    }
    
    @Test
    public void testFullShuffle() {
        int[][] sudoku1 = new int[9][9];
        for (int i = 0; i < sudoku1.length; i++) {
            for (int j = 0; j < sudoku1[i].length; j++) {
                sudoku1[i][j] = i;
            }
        }
        int[][] sudoku2 = matrixCopy(sudoku1);
        assertTrue("No shuffling done should return true.", superDeepEquals(sudoku1, sudoku2));
        SudokuShuffler.doTheShuffle(3, 3, sudoku1);
        
        System.out.println(new Sudoku(3,3, sudoku1));
        System.out.println(new Sudoku(3,3, sudoku2));
        
        assertFalse("Tables should be different after shuffle.\n" + sudoku1.toString() + "\n" + sudoku2.toString(), superDeepEquals(sudoku1, sudoku2));
    }
    
    @Test
    public void validityTest() {
        int[][] b = SudokuGenerator.genNewSudoku(3, 3, 0).getSudokuData();
        SudokuShuffler.doTheShuffle(3,3, b);
        
        Sudoku sudoku = new Sudoku(3,3, b);
        assertTrue("Shuffled sudoku should still be street legal", sudoku.isValid());
    }
    
    private int[][] matrixCopy(int[][] source) {
        int[][] copy = new int[source.length][];
        int i = 0;
        for (int[] a : source) {
            copy[i] = Arrays.copyOf(a, a.length);
            i++;
        }
        return copy;
    }
    
    private boolean superDeepEquals(int[][] m1, int[][] m2) {
        if (m1 == null && m2 == null) {
            return true;
        }
        if (m1 == null || m2 == null) {
            return false;
        }
        if (m1.length != m2.length) {
            return false;
        }
        for (int i = 0; i < m1.length; i++) {
            if (m1[i] == null && m2[i] == null) {
                return true;
            }
            if (m1[i] == null || m2[i] == null) {
                return false;
            }
            if (m1[i].length != m2[i].length) {
                return false;
            }
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
