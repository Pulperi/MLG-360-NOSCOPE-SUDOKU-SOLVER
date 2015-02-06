package sudokuproject.sudokuworldsaga.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/** SudokuSolver class
 * <p>
 * Produces solved sudokus from unfinished ones
 * 
 * @author Henri
 */
public class SudokuSolver {
    
    /**
     * Generates a solution for a sudoku.
     * If multiple solutions found
     * - one is picked randomly.
     * 
     * @param sudoku Unsolved sudoku
     * @return Solved Sudoku
     */
    public static Sudoku solve(Sudoku sudoku) throws IllegalArgumentException {
        // Sudoku is null, abort mission
        if (sudoku == null) {
            throw new IllegalArgumentException("Sudoku you are trying to solve is null");
        }
        // Sudoku is already solved
        if (sudoku.isSolved()) {
            return sudoku;
        }
        ArrayList<Sudoku> answers = solveAll(sudoku);
        // Sudoku is unsolvable
        if (answers.isEmpty()) {
            return null;
        }
        // Return random answer for sudoku
        else {
            Random random = new Random();
            return answers.get(random.nextInt(answers.size()));
        }
    }
    
    /**
     * Generates up to x (currently 1000) solutions for a given sudoku
     * <p>
     * NOTE: curently generated answers for sudokus are systematically very 
     * similiar to each other. Only problematic when using solver to solve 
     * sudokus with large amount of possible solutions.
     * 
     * @param sudoku Unsolved sudoku
     * @return ArrayList of solved sudokus
     */
    
    public static ArrayList<Sudoku> solveAll(Sudoku sudoku) {
        ArrayList<Sudoku> solvedSudokus = nextTry(sudoku);
        if (solvedSudokus == null) {
            return null;
        }
        else {
            return solvedSudokus;
        }
    }
    
    private static ArrayList<Sudoku> nextTry(Sudoku sudoku) {
        ArrayList<Sudoku> solvedSudokus = new ArrayList<Sudoku>();
        if (sudoku.isSolved()) {
            solvedSudokus.add(sudoku);
            return solvedSudokus;
        }
        else {
            // find next unsolved
            int[] xy = findNextUnsolved(sudoku);
            // get list of possible entries
            ArrayList<Integer> validEntries = sudoku.getEntries(xy[0], xy[1]);
            // run nextTry on all new branches
            for (int i : validEntries) {
                Sudoku newSudoku = new Sudoku(sudoku);
                newSudoku.set(xy[0], xy[1], i);
                ArrayList<Sudoku> results = nextTry(newSudoku);
                if (!results.isEmpty()) {
                    for (Sudoku j : results) {
                        solvedSudokus.add(j);
                        // Quit if over 1000 solutions found
                        if (solvedSudokus.size() >= 1000) {
                            return solvedSudokus;
                        }
                    }
                }
            }
        }
        return solvedSudokus;
    }

    private static int[] findNextUnsolved(Sudoku sudoku) {
        for (int y = 0; y < sudoku.getCols()*sudoku.getRows(); y++) {
            for (int x = 0; x < sudoku.getCols()*sudoku.getRows(); x++) {
                if (sudoku.getXY(x, y) == 0) {
                    int[] xy = new int[2];
                    xy[0] = x;
                    xy[1] = y;
                    return xy;
                }
            }
        }
        return null;
    }

    
}
