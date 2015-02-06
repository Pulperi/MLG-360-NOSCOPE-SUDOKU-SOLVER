/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.fileio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import sudokuproject.sudokuworldsaga.domain.Sudoku;

/** FileManager class handles all the reading from files and writing to files.
 *
 * @author Henri
 */
public class FileManager {

    /**
     * Takes sudoku fileName as String and returns fully loaded sudoku.
     * 
     * @param filename as String
     * @return null if file can't be found or if not valid sudoku file
     */
    public static Sudoku loadSudokuFromFile(String filename) {
        return loadSudokuFromFile(new File(filename));
    }  
    
    /**
     * Load method for GUI
     * 
     * @param file File
     * @return null if file can't be found or if not valid sudoku file
     */
    public static Sudoku loadSudokuFromFile(File file) {
        int[][] sudokuDataArray;
        Sudoku sudoku;
        
        try (Scanner reader = new Scanner(file)) {
            sudokuDataArray = readLoadedFile(reader);
        } catch (FileNotFoundException ex) {

            return null;
        }
        
        if (sudokuDataArray != null) {
            int[][] copy = new int[sudokuDataArray.length-1][];
            for (int i = 0; i < copy.length; i++) {
                copy[i] = Arrays.copyOf(sudokuDataArray[i+1], sudokuDataArray[i+1].length);
            }
            sudoku = new Sudoku(sudokuDataArray[0][0],
                                sudokuDataArray[0][1], 
                                copy);
            return sudoku;
        }
        
        return null;
    }
    
    /** File saving method used to save all the data in a sudoku object data to a file.
     * 
     * @param fileName String fileName
     * @param sudoku Sudoku that is to be saved to file
     * @throws FileNotFoundException if save failed
     */

    public static void saveSudokuToFile(String fileName, Sudoku sudoku) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            int rows = sudoku.getRows();
            int cols = sudoku.getCols();

            writer.println(rows + "," + cols);
            
            int[][] sudokuData = sudoku.getSudokuData();
            
            for (int[] subSet : sudokuData) {
                StringBuilder str = new StringBuilder("");
                str.append(subSet[0]);
                for (int i = 1; i < subSet.length; i++) {
                    str.append(",").append(subSet[i]);
                }
                writer.println(str.toString());
            }
        }
    }
    
    private static int[][] readLoadedFile(Scanner reader) {
        ArrayList<int[]> readFiles = new ArrayList<>();
        
        // FIRST LINE SHOULD CONTAIN SUDOKU SIZE

        int size;
        
        if (reader.hasNext()) {
            String str[] = reader.nextLine().split(",");
            if (str.length == 2) {
                int rows, cols;
                try {
                    rows = Integer.parseInt(str[0]);
                    cols = Integer.parseInt(str[1]);
                } catch (NumberFormatException ex) {
                    System.out.println(ex.getMessage());
                    return null;
                }
                if (rows > 1 && cols > 1) {
                    size = rows*cols;
                    int[] rc = {rows, cols};
                    readFiles.add(rc);
                }
                else {
                    return null;
                }
            }
            else {
                // dimensions wrong
                return null;
            }
        }
        else {
            // empty file
            return null;
        }
        
        while (reader.hasNext()) {
            String[] newLine = reader.nextLine().split(",");
            
            if (newLine.length != size) {
                return null;
            }
            
            int[] newValues;
            
            try {
                newValues = convertToInt(newLine);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
                return null;
            }
            
            if (!checkValues(newValues, size)) {
                return null;
            }
            
            readFiles.add(newValues);
        }
        
        if (readFiles.size() != size + 1) {
            return null;
        }
        
        int[][] array = new int[readFiles.size()][];
        
        return readFiles.toArray(array);
    }

    private static int[] convertToInt(String[] newLine) {
        
        int[] values = new int[newLine.length];
        for (int i = 0; i < newLine.length; i++) {
            values[i] = Integer.parseInt(newLine[i]);
        }
        return values;
    }

    private static boolean checkValues(int[] newValues, int size) {
        for (int i : newValues) {
            if (i < 0 || i > size) {
                return false;
            }
        }
        return true;
    }
        
    /*
    public static Sudoku loadSudokuFromFile(String fileName) {
        int[][] sudokuDataArray;
        Sudoku sudoku;
        
        try (Scanner reader = new Scanner(new File(fileName))) {
            sudokuDataArray = readLoadedFile(reader);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
        if (sudokuDataArray != null) {
            int[][] copy = new int[sudokuDataArray.length-1][];
            for (int i = 0; i < copy.length; i++) {
                copy[i] = Arrays.copyOf(sudokuDataArray[i+1], sudokuDataArray[i+1].length);
            }
            sudoku = new Sudoku(sudokuDataArray[0][0],
                                sudokuDataArray[0][1], 
                                copy);
            return sudoku;
        }
        
        return null;
    }
    */
}
