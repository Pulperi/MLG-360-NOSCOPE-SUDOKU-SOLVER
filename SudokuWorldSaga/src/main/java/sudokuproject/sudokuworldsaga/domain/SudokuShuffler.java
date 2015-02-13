/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.Random;

/** Shuffling algorithm for sudokus to generate 'better' sudokus
 *
 * @author Henri
 */
public class SudokuShuffler {
    
    /**
     * Method runs shuffling algorithms for given sudoku number table
     * Shuffling doesn't affect the validity of sudoku
     *
     * @param rows Number of rows
     * @param cols Number of columns
     * @param sudoku Table that contains the sudoku data we want to shuffle
     * 
     */
    
    public static void doTheShuffle(int rows, int cols, int[][] sudoku) {
        if (sudoku == null || rows < 2 || cols < 2 || rows * cols != sudoku.length) {
            return;
        }
        Random random = new Random();
        doSubsetRowShuffle(rows, cols, sudoku, random, 1);
        doSubsetColShuffle(rows, cols, sudoku, random, 1);
    }
    private static void doSubsetRowShuffle(int rows, int cols, int[][] sudoku, Random random, int n) {
        for (int i = 0; i < n; i++) {
            int row1 = random.nextInt(rows) * rows; // {0, 3, 6}
            int row2 = (row1 + cols) % (rows * cols);  // {3, 6, 0}
            
            for (int j = 0; j < cols; j++) {
                swapSubsets(j + row1, j + row2, sudoku);
            }
        }
    }
    private static void doSubsetColShuffle(int rows, int cols, int[][] sudoku, Random random, int n) {
        for (int i = 0; i < n; i++) {
            int col1 = random.nextInt(cols); // { 0, 1, 2}
            int col2 = (col1 + 1) % rows;    // { 1, 2, 0}
            
            for (int j = 0; j < rows; j++) {
                swapSubsets(col1 + (j * 3), col2 + (j * 3) , sudoku);
            }
            
        }
    }
        
    private static void swapSubsets(int subset1, int subset2, int[][] sudoku) {
        int[] temp = sudoku[subset1];
        sudoku[subset1] = sudoku[subset2];
        sudoku[subset2] = temp;
    }
}
