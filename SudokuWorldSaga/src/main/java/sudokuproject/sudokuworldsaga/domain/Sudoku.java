package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import sudokuproject.sudokuworldsaga.fileio.FileManager;

/**
 * Main Sudoku class 
 * <p>
 * This class acts as a interface between Sudoku related operations 
 * and SudokuData class.
 * 
 * @see SudokuData
 * @see SudokuGenerator
 * @see SudokuSolver
 * 
 * @author Henri
 */
public class Sudoku {

    private SudokuData sudoku;
    
    /*
     * cols, rows indicate sudoku subset size
     * 
     */
    
    private int rows, cols;

    
    // CONSTRUCTORS
    
    /**
     * Make new sudoku based on values listed on parameter sudokuDataArray
     * 
     * @param cols Sudoku columns (>1)
     * @param rows Sudoku rows (>1)
     * @param sudokuDataArray Sudoku data
     *
     * @throws IllegalArgumentException When invalid sudoku dimensions or sudokuData given.
    */
    
    public Sudoku(int cols, int rows, int[][] sudokuDataArray) throws IllegalArgumentException {
        if (cols <= 1 || rows <= 1) {
            throw (new IllegalArgumentException("Too small"));
        }
        if (sudokuDataArray == null) {
            throw (new IllegalArgumentException("Datastring null"));
        }
        this.rows = rows;
        this.cols = cols;
        sudoku = new SudokuData(cols, rows, sudokuDataArray);
    }

    /**
     * Make new empty sudoku with all cell values set to zero
     * 
     * @param cols Sudoku columns (>1)
     * @param rows Sudoku rows (>1)
     * 
     * @throws IllegalArgumentException When invalid sudoku dimensions given.
    */
    
    public Sudoku(int cols, int rows) throws IllegalArgumentException {
        if (cols <= 1 || rows <= 1) {
            throw (new IllegalArgumentException("Too small"));
        }
        this.rows = rows;
        this.cols = cols;
        this.sudoku = new SudokuData(cols, rows);
    }
    
    /**
     * Clones a sudoku from the values of the sudoku given as parameter
    */
    public Sudoku(Sudoku sudoku) throws IllegalArgumentException {
        if (sudoku == null) {
            throw new IllegalArgumentException("Source sudoku is null");
        }
        this.cols = sudoku.cols;
        this.rows = sudoku.rows;
        this.sudoku = new SudokuData(cols, rows, sudoku.getSudokuData());
    }
    /**
     * Method goes through whole sudoku and checks if there are any unsolved sudoku cells.
     * 
     * @return true if solved, false if not solved
     */
    public boolean isSolved() {
        return countUnsolved() == 0;
    }
    
    /**
     * Method goes through whole sudoku and counts the unsolved sudoku cells.
     * 
     * @return number of unsolved sudoku cells
     */
    public int countUnsolved() {
        int n = 0;
        int sudokuSize = cols * rows;
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                if (sudoku.getXY(i, j) == 0) {
                    n++;
                }
            }
        }
        return n;
    }
    
    /**
     * Uses the sudoku shuffler algorithm of the SudokuShuffler class to scramble the sudoku.
     * Shuffled sudokus are still valid.
     */
    
    public void shuffleSudoku() {
        sudoku.doTheShuffle();
    }
    // SETTERS
    
    public void set(int x, int y, int value) {
        sudoku.set(x, y, value);
    }

    // GETTERS
  
    
    /**
     * Takes sudoku cell location (x, y) as parameteres and finds all the possible entry values to that cell.
     * 
     * @param x column
     * @param y row
     * @return Returns an ArrayList of possible entries in the cell (x, y)
     * @throws IllegalArgumentException if x or y out of bounds
     */
    
    public ArrayList<Integer> getEntries(int x, int y) throws IllegalArgumentException {
        if (!isInRange(x, y)) {
            throw new IllegalArgumentException("x and/or y not in range");
        }
        ArrayList<Integer> validEntries = new ArrayList<Integer>();
        ArrayList<Integer> falseEntries = new ArrayList<Integer>();
        // find all the numbers already in the subset
        
        // FIXED VALUE 10 SHOULD BE DYNAMIC DEPENDING ON SUDOKU SIZE
        // [1,...,9,a,...z] (scandics?) 
        
        for (int i = 1; i < 10; i++) {
            validEntries.add(i);
        }
        for (int i : sudoku.getNumbersInSubset(x, y)) {
            if (!falseEntries.contains(i) && i != 0) {
                falseEntries.add(i);
            }
        }
        // find all the numbers already on the row
        for (int i = 0; i < rows * cols; i++) {
            if (!falseEntries.contains(getXY(i, y)) && getXY(i, y) != 0) {
                falseEntries.add(getXY(i, y));
            }
        }
        // find all the numbers already on the column
        for (int i = 0; i < rows * cols; i++) {
            if (!falseEntries.contains(getXY(x, i)) && getXY(x, i) != 0) {
                falseEntries.add(getXY(x, i));
            }
        }
        validEntries.removeAll(falseEntries);
        return validEntries;
    }

    /** 
     * Checks if the sudoku data is valid
     * 
     * @see SudokuData
     * 
     * @return true if valid sudoku
     */
    
    public boolean isValid() {
        return sudoku.validityTest();
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    /**
     * 
     * @return size = cols * rows
     */
    public int getSize() {
        return rows * cols;
    }
    
    /** 
     * Takes the sudoku cell location (x, y) as parameter and returns it's current value
     * 
     * @param x column
     * @param y row
     * @return cell value
     * @throws IllegalArgumentException if x or y out of bounds
     */
    public int getXY(int x, int y) throws IllegalArgumentException {
        if (!isInRange(x, y)) {
            throw new IllegalArgumentException("x and/or y not in range");
        }
        return sudoku.getXY(x, y);
    }
    
    /**
     * Returns a 2d int array representation of the sudoku, mainly used by the FileManager class.
     * 
     * @see FileManager
     * 
     * @return int[][] with sudoku values
     */
    public int[][] getSudokuData() {
        return sudoku.getData();
    }

    
    // ****************
    
    @Override
    public String toString() {
        StringBuilder sudokuString = new StringBuilder("");
        String column = "|";
        for (int i = 0; i < rows * cols; i++) {
            if (i % rows == 0 && i != 0) {
                sudokuString.append(lineRow());
            }
            for (int j = 0; j < rows * cols; j++) {
                if (j % cols == 0 && j != 0) {
                    sudokuString.append(column);
                }
                sudokuString.append(sudoku.getSymbolXY(j, i));
            }
            sudokuString.append("\n");
        }
        sudokuString.append("Unsolved: " + countUnsolved() + "\n");
        return sudokuString.toString();
    }    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }        
        final Sudoku other = (Sudoku) obj;
        if (!this.sudoku.equals(other.sudoku)) {
            return false;
        }
        return true;
    }

    // PRIVATE HELPER METHODS
    
    /**
     * Returns true/false if (x,y) in sudoku range 
     */

    private boolean isInRange(int x, int y) {
        int size = this.getSize();
        return (((y < 0 || y > size) || (x < 0 || x > size)) ? false : true);
    }
    
    /**
     * Prints a row of '-' for toString() method
     */
    
    private String lineRow() {
        StringBuilder string = new StringBuilder("");
        String c = "-";
        for (int i = 0; i < (cols + 1) * rows - 1; i++) {
            string.append(c);
        }
        string.append("\n");
        return string.toString();
    }
}
