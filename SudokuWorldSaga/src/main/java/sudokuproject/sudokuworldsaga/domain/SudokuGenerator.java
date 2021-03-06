/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

    
/**
 * <h5>SudokuGenerator class is used to create new randomly generated unsolved Sudoku objects 
 * with a single solution.</h5>
 * <b>Sudokus are generated with an algorithm that works in several phases:</b><p>
 * First it uses the SudokuSolver class to generate the initial seed for 
 * the generated sudoku.<p>
 * Then SudokuShuffler is used to make the sudoku more random.<p>
 * The algorithm then proceeds to remove n values from the sudoku (n given as method parameter).<p>
 * At this point we are left with a partially solved sudoku that has 1 - m solutions and the goal 
 * is to reduce them to a single one. At this point it uses the SudokuSolver.solveAll() algorithm to
 * get all the possible solutions for the partially solved sudoku.<p>
 * Start loop:<p>
 * If only one solution is found the algorithm finishes.<p>
 * Else the algorithm adds one value from the solutions to the sudoku and removes invalid solutions from list of solutions<p>
 * END.<p>
 * 
 * For more details see project documentation.
 * 
 * @see SudokuSolver
 * @see SudokuShuffler
 * 
 * @author Henri
 */

public class SudokuGenerator {

    /**
     * Algorithm to generate random sudokus with single solution.
     * The generated sudoku will have (nEmpty - n) empty cells depending on 
     * algorithm execution randomness.
     * 
     * @param cols columns
     * @param rows rows
     * @param nEmpty Number of desired empty cells. 
     * @return The generated sudoku
     */
    public static Sudoku genNewSudoku(int cols, int rows, int nEmpty) {
        // GEN "RANDOM" FILLED SUDOKU AND THEN SET N CELLS TO ZERO
        if (nEmpty < 0) {
            throw new IllegalArgumentException("n < 0");
        }
         
        Sudoku sudoku = SudokuSolver.solve(new Sudoku(cols, rows));
        
        // Switch rows and cols around to make the sudoku more random
        sudoku.shuffleSudoku();
        
        // SET N CELLS TO ZERO
        sudoku = setNtoZero(sudoku, nEmpty);        
        
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
        
        // Get the cell (x,y) where the solutions differ the most
        // and the solutions
        
        HashMap<Coords, ArrayList<Integer>> mostUnique = getCellWithMostSolutions(sudoku.getSize(), solutions);
        ArrayList<Integer> uniqueValues = null;
        Coords xy = null;
        
        for (Coords i : mostUnique.keySet()) {
            uniqueValues = mostUnique.get(i);
            xy = i;
        }
        
        // Pick random number from possible solutions that will be inserted
        int number = uniqueValues.get((new Random()).nextInt(uniqueValues.size()));
  
        // copies the solutions that are still valid to validSolutions
        getValidSolutions(number, xy, solutions, validSolutions);
        
        sudoku.set(xy.x, xy.y, number);
        
        return genDatSudoku(sudoku, validSolutions);
    }
    
    /*
     * Returns the coordinates of the cell where most solutions differ and 
     * all the possible solutions for the given cell
     */
    
    private static HashMap<Coords, ArrayList<Integer>> getCellWithMostSolutions(int size, ArrayList<Sudoku> solutions) {
        HashMap<Coords, ArrayList<Integer>> result = new HashMap<Coords, ArrayList<Integer>>();
        
        Coords maxCell = null;
        ArrayList<Integer> maxUniques = null;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Coords xy = new Coords(i, j);
                if (maxCell == null) {
                    maxCell = xy;
                    maxUniques = getUniques(solutions, xy);
                } else {
                    ArrayList<Integer> xyUniques = getUniques(solutions, xy);
                    if (xyUniques.size() > maxUniques.size()) {
                        maxUniques = xyUniques;
                        maxCell = xy;
                    }
                }
            }
        }        
        result.put(maxCell, maxUniques);
        return result;
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

    private static void getValidSolutions(int number, Coords xy, ArrayList<Sudoku> solutions, ArrayList<Sudoku> validSolutions) {
        for (Sudoku i : solutions) {
            if (i.getXY(xy.x, xy.y) == number) {
                validSolutions.add(i);
            }
        }
    }
}
// Helper class
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
