package sudokuproject.sudokuworldsaga;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sudokuproject.sudokuworldsaga.domain.Sudoku;
import sudokuproject.sudokuworldsaga.domain.SudokuSolver;
import sudokuproject.sudokuworldsaga.fileio.FileManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void runTests() {
        
        Sudoku sudoku = FileManager.loadSudokuFromFile(new File("testFiles/testSudokuFile.sudoku"));
        
        System.out.println(sudoku.toString());
        
        
        /*
        int[][] sudokuDataString = {{0,0,5,0,7,8,0,1,0}, 
                                    {1,0,0,0,0,6,0,2,0}, 
                                    {0,0,0,2,3,0,0,0,8},
                                    {0,0,0,0,5,9,2,0,0}, 
                                    {0,3,0,0,0,0,0,4,0}, 
                                    {0,0,2,8,4,0,0,0,0},
                                    {1,0,0,0,6,3,0,0,0}, 
                                    {0,9,0,7,0,0,0,0,3}, 
                                    {0,8,0,9,5,0,1,0,0}};
        Sudoku sudoku;
        
        try {
            sudoku = new Sudoku(3,3, sudokuDataString);
            String string = "Henri's super sudoku solver app - #SUDOKUWORLDSAGA";
            System.out.println(underLine(string) + "\n\n" + underLine("Unsolved sudoku:") + "\n" + sudoku.toString());
            System.out.print("Running");
            runLoading();
            System.out.println(underLine("Solved sudoku:") + "\n" + SudokuSolver.solve(sudoku).toString());
            
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }*/
    }
    
    private static String underLine(String length) {
        StringBuilder sb = new StringBuilder(length + "\n");
        for (int i = 0; i < length.length(); i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    private static void runLoading() {
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(500 + i * 20);
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.print(".");
        }
        System.out.println("\nDone.\n");
    }
}
