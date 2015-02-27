/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.Random;

/** 
 * Shuffling algorithm for sudokus to generate 'better' sudokus
 *
 * The algorithm swaps rows and columns of the sudoku with each 
 * other in a way that doesn't affect the validity of sudoku.<p>
 * 
 * Legal operations:<p>
 * - swapping a row/column of subsets with each other<br>
 * - swapping a row/column inside of a row of subset with each other<br>
 * 
 * @author Henri
 */
public class SudokuShuffler {
    
    /**
     * Method runs shuffling algorithms for given sudoku number table<p>
     * Shuffling doesn't affect the validity of sudoku.
     * 
     * @param rows Number of rows
     * @param cols Number of columns
     * @param sudoku Table that contains the sudoku data we want to shuffle
     * 
     */
    
    public static void doTheShuffle(int rows, int cols, int[][] sudoku) {
        if (notValidSudoku(rows, cols, sudoku)) {
            return;
        }
        
        // Shuffling only works with 3x3
        
        Random random = new Random();

        doRowShuffle(sudoku, random, 5);
        doColShuffle(sudoku, random, 3);
        doSubsetRowShuffle(rows, cols, sudoku, random, 1);
        doSubsetColShuffle(rows, cols, sudoku, random, 1);
    }
    
    private static boolean notValidSudoku(int rows, int cols, int[][] sudoku) {
        return (sudoku == null || rows < 2 || cols < 2 || rows * cols != sudoku.length);
    }
    
    /*
     * SUBSET ROW CHANGE 
     * 
     * 0|1|2
     * -----
     * 3|4|5
     * -----
     * 6|7|8
     * 
     * row1 = 6 => row2 = 0
     * 
     * 000|111|222       666|777|888
     * 000|111|222       666|777|888
     * 000|111|222       666|777|888
     * -----------       -----------
     * 333|444|555       333|444|555
     * 333|444|555 --->  333|444|555
     * 333|444|555       333|444|555
     * -----------       -----------
     * 666|777|888       000|111|222
     * 666|777|888       000|111|222
     * 666|777|888       000|111|222
     * 
     */

    private static void doSubsetRowShuffle(int rows, int cols, int[][] sudoku, Random random, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            int row1 = random.nextInt(rows) * rows; // {0, 3, 6}
            int row2 = (row1 + cols) % (rows * cols);  // {3, 6, 0}
            
            for (int j = 0; j < cols; j++) {
                swapSubsets(j + row1, j + row2, sudoku);
            }
        }
    }
    
    /*
     * SUBSET ROW CHANGE 
     * 
     * 0|1|2
     * -----
     * 3|4|5
     * -----
     * 6|7|8
     * 
     * col1 = 1 => col2 = 2
     * 
     * 666|777|888       666|888|777
     * 666|777|888       666|888|777
     * 666|777|888       666|888|777
     * -----------       -----------
     * 333|444|555       333|555|444
     * 333|444|555 --->  333|555|444
     * 333|444|555       333|555|444
     * -----------       -----------
     * 000|111|222       000|222|111
     * 000|111|222       000|222|111
     * 000|111|222       000|222|111
     * 
     */
    
    private static void doSubsetColShuffle(int rows, int cols, int[][] sudoku, Random random, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
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

    private static void doColShuffle(int[][] sudoku, Random random, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            int col1 = random.nextInt(9);
            int col2 = col1 + 1;
            if (col2 % 3 == 0) {
                col2 -= 2;
            }
            swapCol(col1, col2, sudoku);
        }
    }
    
    private static void swapCol(int col1, int col2, int[][] sudoku) {
        for (int y = 0; y < 9; y++) {
            swapValues(col1, y, col2, y, sudoku);
        }
    }
    
    private static void doRowShuffle(int[][] sudoku, Random random, int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            int row1 = random.nextInt(9);
            int row2 = row1 + 1;
            if (row2 % 3 == 0) {
                row2 -= 2;
            }
            swapRow(row1, row2, sudoku);
        }
    }
    
    private static void swapRow(int row1, int row2, int[][] sudoku) {
        for (int x = 0; x < 9; x++) {
            swapValues(x, row1, x, row2, sudoku);
        }
    }

    // Swaps 2 values with each other in the given array
    // (x1, y1) => (x2, y2)
    private static void swapValues(int x1, int y1, int x2, int y2, int[][] sd) {
        int temp = sd[findSubsetIndex(x1, y1)][findCellIndex(x1, y1)];
        sd[findSubsetIndex(x1, y1)][findCellIndex(x1, y1)] = sd[findSubsetIndex(x2, y2)][findCellIndex(x2, y2)];
        sd[findSubsetIndex(x2, y2)][findCellIndex(x2, y2)] = temp;
    }
   
    // Only works with standard sudoku size (3x3)
    private static int findSubsetIndex(int x, int y) {
        int rowFactor = ((y + 1) - y % 3) / 3;
        int columnFactor = ((x + 1) - x % 3) / 3;
        return columnFactor + 3 * rowFactor;
    }
    
    private static int findCellIndex(int x, int y) {
        int row = y % 3;
        int col = x % 3;
        return col + 3 * row;
    }
}
