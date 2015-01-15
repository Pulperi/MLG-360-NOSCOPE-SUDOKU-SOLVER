/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Henri
 */
public class Sudoku {

    private int[][] sudoku;
    private int rows, cols;
    
    public Sudoku(int cols, int rows, String[] dataString) {
        this.rows = rows;
        this.cols = cols;
        sudoku = new int[cols*rows][];
        parseData(dataString);
    }

    
    public Sudoku(Sudoku sudoku) {
        this.cols = sudoku.cols;
        this.rows = sudoku.rows;
        this.sudoku = new int[cols*rows][];
        for (int i = 0; i < rows*cols; i++) {
            int[] newSubset = new int[rows*cols];
            System.arraycopy(sudoku.sudoku[i], 0, newSubset, 0, rows*cols);
            this.sudoku[i] = newSubset;
        }
    }
    
    private void parseData(String[] dataString) {
        for (int i = 0; i < rows*cols; i++) {
            int[] newSubset = new int[rows*cols];
            String[] subData = dataString[i].split(",");
            for (int j = 0; j < rows*cols; j++) {
                newSubset[j] = Integer.parseInt(subData[j]);
            }
            sudoku[i] = newSubset;
        }
    }
    
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    
    public int getXY(int x, int y) {
        return sudoku[findSubsetIndex(x,y)][findCellIndex(x,y)];
    }
    
    public int findSubsetIndex(int x, int y) {
        int rowFactor = ((y+1)-y%rows)/rows;
        int columnFactor = ((x+1)-x%cols)/cols;
        return columnFactor + rows*rowFactor;
    }
    
    public int findCellIndex(int x, int y) {
        int row = y%rows;
        int col = x%cols;
        return col+cols*row;
    }
    
    public boolean isSolved() {
        for (int[] i : sudoku) {
            for (int j : i) {
                if (j == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        String sudokuString = "";
        for (int i = 0; i < rows*cols; i++) {
            if (i%rows == 0 && i != 0) {
                sudokuString+="-----------\n";
            }
            for (int j = 0; j < rows*cols; j++) {
                if (j%cols == 0 && j != 0) {
                    sudokuString+="|";
                }
                if (getXY(j,i) == 10) {
                    sudokuString+=" ";
                }
                else {
                    sudokuString+=getXY(j,i);
                }
            }
            sudokuString+="\n";
        }
        
        return sudokuString;
    }

    public ArrayList<Integer> getEntries(int x, int y) {
        ArrayList<Integer> validEntries = new ArrayList<Integer>();
        ArrayList<Integer> falseEntries = new ArrayList<Integer>();
        // find all the numbers already in the subset
        for (int i = 1; i < 10; i++) {
            validEntries.add(i);
        }
        for (int i : sudoku[findSubsetIndex(x,y)]) {
            if (!falseEntries.contains(i) && i != 0) {
                falseEntries.add(i);
            }
        }
        // find all the numbers already on the row
        for (int i = 0; i < rows*cols; i++) {
            if (!falseEntries.contains(getXY(i,y)) && getXY(i,y) != 0) {
                falseEntries.add(getXY(i,y));
            }
        }
        // find all the numbers already on the column
        for (int i = 0; i < rows*cols; i++) {
            if (!falseEntries.contains(getXY(x,i)) && getXY(x,i) != 0) {
                falseEntries.add(getXY(x,i));
            }
        }
        validEntries.removeAll(falseEntries);
        return validEntries;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.deepHashCode(this.sudoku);
        return hash;
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
        if (!Arrays.deepEquals(this.sudoku, other.sudoku)) {
            return false;
        }
        return true;
    }
    
    

    public void set(int x, int y, int value) {
        sudoku[findSubsetIndex(x,y)][findCellIndex(x,y)] = value;
    }

    
}
