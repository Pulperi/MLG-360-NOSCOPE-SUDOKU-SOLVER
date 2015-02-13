package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * SudokuData container class 
 * <p>
 * This class handles all the numeric values inside the sudoku data cells.
 * Mainly used by Sudoku class.
 * 
 * @see Sudoku
 * @see SudokuShuffler
 * 
 * @author Henri
 */
public class SudokuData {
    private int[][] sudoku;
    private final int rows, cols;
    
    /** 
     * All the possible cell values
     * 
     * VALUESET[5] = 5, 
     * VALUESET[12] = c, 
     * ...
     * 
     * (not in use during current phase of the development)
     */
    public static final String[] VALUESET = {"0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "e", "f", "g", "h", "i", 
        "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", 
        "x", "y", "z"}; 
  
    /**
     * Make new empty sudokuData with all cell values set to zero
     * 
     * @param cols Sudoku columns 
     * @param rows Sudoku rows 
     * 
     * @throws IllegalArgumentException When invalid sudoku dimensions given.
    */
    
    public SudokuData(int cols, int rows) {
        this.rows = rows;
        this.cols = cols;
        this.sudoku = new int[cols * rows][];
        for (int i = 0; i < rows * cols; i++) {
            int[] newSubset = new int[rows * cols];
            Arrays.fill(newSubset, 0);
            this.sudoku[i] = newSubset;
        }
    }
    
    /**
     * Make new sudokuData based on values listed on parameter sudokuDataArray
     * 
     * @param cols  Sudoku columns 
     * @param rows  Sudoku rows 
     * @param array Sudoku data
     *
    */ 
    
    public SudokuData(int cols, int rows, int[][] array) {
        this.rows = rows;
        this.cols = cols;
        this.sudoku = matrixCopy(array);
    }
    
    /** Tests that the sudoku is valid sudoku.<p>
     * Valid sudoku doesn't contain invalid numbers and doesn't have duplicates on rows, cols or subsets.
     * 
     * @return true if valid sudoku
     */
    
    public boolean validityTest() {
        return !(validityTestFail() || validityTestSubsetsFail());
    }
    
    /** Tests that sudoku doesn't have invalid numbers and that it doesn't contain duplicates on rows/cols
     * 
     * @return true if validityTest failed
     */
    
    private boolean validityTestFail() {
        int size = cols * rows;
        HashMap<Integer, ArrayList<Integer>> rowList = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> colList = new HashMap<Integer, ArrayList<Integer>>();
        
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int val = getXY(x, y);
                if (val != 0) {
                    if (val > size || val < 0) {
                        return true;
                    }
                    if (!listChecker(val, x, colList)) {
                        return true;
                    }
                    if (!listChecker(val, y, rowList)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /** Checks that the given parameter val doesn't contain value that is already
     * in the ArrayList contained at position n in the hashmap valueList. If the 
     * value is not found it's added in to the list.
     * 
     * @param val
     * @param n
     * @param valueList
     * @return true if value not found
     */
    
    private boolean listChecker(int val, int n, HashMap<Integer, ArrayList<Integer>> valueList) {
        if (!valueList.keySet().contains(n)) {
            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.add(val);
            valueList.put(n, newList);
        } else {
            ArrayList<Integer> currentList = valueList.get(n);
            if (currentList.contains(val)) {
                return false;
            } else {
                currentList.add(val);
            }
        }
        return true;
    }
    
    /** Tests that sudoku doesn't have duplicates in subsets.
     * Subset test is ran separately from other validity tests for simplicity.
     * 
     * @return true if validityTest failed
     */
    
    private boolean validityTestSubsetsFail() {
        int[][] sudokuData = sudoku;
        for (int[] subSet : sudokuData) {
            ArrayList<Integer> subSetValues = new ArrayList<Integer>();
            for (int i : subSet) {
                if (i != 0) {
                    if (!subSetValues.contains(i)) {
                        subSetValues.add(i);
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public int[][] getData() {
        return matrixCopy(sudoku);
    }
    
    public int[] getNumbersInSubset(int x, int y) {
        int[] subSet = Arrays.copyOf(sudoku[findSubsetIndex(x, y)], sudoku[findSubsetIndex(x, y)].length);
        return subSet;
    }
    
    public int getXY(int x, int y) {
        return sudoku[findSubsetIndex(x, y)][findCellIndex(x, y)];
    }
    
    public String getSymbolXY(int x, int y) {
        return VALUESET[getXY(x, y)];
    }
    
    public void set(int x, int y, int value) {
        sudoku[findSubsetIndex(x, y)][findCellIndex(x, y)] = value;
    }
    
    private int findSubsetIndex(int x, int y) {
        int rowFactor = ((y + 1) - y % rows) / rows;
        int columnFactor = ((x + 1) - x % cols) / cols;
        return columnFactor + rows * rowFactor;
    }
    
    private int findCellIndex(int x, int y) {
        int row = y % rows;
        int col = x % cols;
        return col + cols * row;
    }
    
    private int[][] matrixCopy(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = Arrays.copyOf(source[i], source[i].length);
        }
        return copy;
    }
    
    public void doTheShuffle() {
        SudokuShuffler.doTheShuffle(rows, cols, sudoku);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SudokuData other = (SudokuData) obj;
        if (!Arrays.deepEquals(this.sudoku, other.sudoku)) {
            
            return false;
        }
        return true;
    }
}
