/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

    
/**
    * > generates random solved sudoku<br>
    * > sets n cells as zeros<br>
    * > generates all the possible solutions for sudoku<br>
    * > starts adding values to cells till only 1 solution<br>
    * > ...<br>
    * > profit<br>
    * 
    * (current algorithm quite inefficient)
    * @author Henri
    */

public class SudokuGenerator {

    public static Sudoku genNewSudoku(int cols, int rows, int n) {
        // GEN "RANDOM" FILLED SUDOKU AND THEN SET N CELLS TO ZERO
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        
        Sudoku sudoku = SudokuSolver.solve(new Sudoku(cols, rows));
        
        // Switch rows and cols around to make the sudoku more random
        sudoku.shuffleSudoku();
        
        // SET N CELLS TO ZERO
        sudoku = setNtoZero(sudoku, n);        
        
        // Run the main generation algorithm
        ArrayList<Sudoku> solutions = SudokuSolver.solveAll(sudoku);
        Sudoku xDoku = genDatSudoku(sudoku, solutions);
        
        /* Dev notes...
        * Shuffle again ?
        * Does shuffling affect the number of solutions?
        *
        *  xDoku.shuffleSudoku();
        */
        
        return xDoku;
    }
    
    private static Sudoku genDatSudoku(Sudoku sudoku, ArrayList<Sudoku> solutions) {
        if (solutions.size() == 1) {
            return sudoku;
        }
        
        // Create a list that will contain solutions that are still valid after input
        ArrayList<Sudoku> validSolutions = new ArrayList<Sudoku>();
        
        Sudoku uniqueSolutions = countUniques(sudoku.getCols(), sudoku.getRows(), solutions);
        
        // Generate the solution difference histogram
        Coords xy = getMostUnique(uniqueSolutions);
        
        // Get the cell (x,y) where the solutions differ the most
        ArrayList<Integer> uniqueValues = getUniques(solutions, xy);
        // Pick random number from possible solutions that will be inserted
        int number = uniqueValues.get((new Random()).nextInt(uniqueValues.size()));
  
        // copies the solutions that are still valid to validSolutions
        getValidSolutions(number, xy, solutions, validSolutions);
        
        
        Sudoku newSudoku = new Sudoku(sudoku);
        newSudoku.set(xy.x, xy.y, number);
        
        return genDatSudoku(newSudoku, validSolutions);
    }
    
    
    /* DOES NOT RETURN REAL SUDOKU BUT A HISTOGRAM WHERE
    * EACH CELL COINTAINS THE VALUE HOW MUCH THE SOLUTIONS DIFFER
    */
    private static Sudoku countUniques(int cols, int rows, ArrayList<Sudoku> solutions) {
        Sudoku sudoku = new Sudoku(cols, rows);
        ArrayList<Coords> solvedList = genLocs(sudoku);
        
        for (int i = 0; i < sudoku.getSize(); i++) {
            for (int j = 0; j < sudoku.getSize(); j++) {
                Coords xy = new Coords(i, j);
                if (!solvedList.contains(xy)) {
                    ArrayList<Integer> valueList = getUniques(solutions, xy);
                    sudoku.set(xy.x, xy.y, valueList.size());
                }
            }
        }
        return sudoku;
    } 
    
    /*
     * Returns list of unique solutions for a given cell
     */
    
    private static ArrayList<Integer> getUniques(ArrayList<Sudoku> solutions, Coords loc) {
        ArrayList<Integer> valueList = new ArrayList<Integer>();
        for (Sudoku s : solutions) {
            int solutionXYValue = s.getXY(loc.x, loc.y);
            if (!valueList.contains(solutionXYValue)) {
                valueList.add(solutionXYValue);
            }
        }
        return valueList;
    }
    
    /*
     * Returns the coordinates of the cell where most solutions differ
     */
    
    private static Coords getMostUnique(Sudoku uniqueList) {
        Coords target = new Coords(0, 0);
        for (int i = 0; i < uniqueList.getSize(); i++) {
            for (int j = 0; j < uniqueList.getSize(); j++) {
                if (uniqueList.getXY(i, j) > uniqueList.getXY(target.x, target.y)) {
                    target = new Coords(i, j);
                }
            }
        }
        return target;
    }
    
    private static Sudoku setNtoZero(Sudoku sudoku, int n) {
        ArrayList<Coords> coords = genLocs(sudoku);
        Collections.shuffle(coords);
        for (int i = 0; i < n; i++) {
            sudoku.set(coords.get(i).x, coords.get(i).y, 0);
        }
        return sudoku;
    }
    
    private static ArrayList<Coords> genLocs(Sudoku sudoku) {
        ArrayList<Coords> list = new ArrayList<Coords>();
        for (int i = 0; i < sudoku.getSize(); i++) {
            for (int j = 0; j < sudoku.getSize(); j++) {
                if (sudoku.getXY(i, j) != 0) {
                    list.add(new Coords(i, j));
                }
            }
        }
        return list;
    }
    /* 
    private static boolean hasMoreThanOneAnswer(Sudoku sudoku) {
        return SudokuSolver.solveAll(sudoku).size() > 1;
    }
    
    // INEFFICIENT WAY TO CREATE SUDOKUS
    
    public static Sudoku genSudokuWithAtleastNUnsolved(int cols, int rows, int n) {
        Sudoku sudoku = SudokuSolver.solve(new Sudoku(cols,rows));
        while (true) {
            Sudoku newSudoku = removeEntry(sudoku);
            if (newSudoku.countUnsolved() > n) {
                return newSudoku;
            }
        }
    }    
    
    private static Sudoku removeEntry(Sudoku sudoku) {
        Sudoku newSudoku = new Sudoku(sudoku);
        if (hasMoreThanOneAnswer(sudoku)) {
            return null;
        }
        else {
            ArrayList<Coords> locs = genLocs(newSudoku);
            Collections.shuffle(locs);
            newSudoku.set(locs.get(0).x, locs.get(0).y, 0);
            newSudoku = removeEntry(newSudoku);
            if (newSudoku == null) {
                return sudoku;
            }
            else {
                return newSudoku;
            }
        }
    }*/

    private static void getValidSolutions(int number, Coords xy, ArrayList<Sudoku> solutions, ArrayList<Sudoku> validSolutions) {
        for (Sudoku i : solutions) {
            if (i.getXY(xy.x, xy.y) == number) {
                validSolutions.add(i);
            }
        }
    }
    
}
class Coords {
    int x, y;
        
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
        
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coords other = (Coords) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    } 
}
