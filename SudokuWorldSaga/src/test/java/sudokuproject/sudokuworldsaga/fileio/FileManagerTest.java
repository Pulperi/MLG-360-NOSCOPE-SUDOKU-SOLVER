/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.fileio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import sudokuproject.sudokuworldsaga.domain.Sudoku;

/**
 *
 * @author Henri
 */
public class FileManagerTest {
    
    public FileManagerTest() {
    }

    @Test
    public void testWrongFileNameLoadSudokuFromFile() {
        Sudoku sudoku = FileManager.loadSudokuFromFile("testFileNameThatDoesNotExist");
        assertTrue("Should return null when loading invalid file", sudoku == null);
    }
    
    @Test
    public void testSudokuFileLoadSudokuFromFile() {
        Sudoku sudoku = FileManager.loadSudokuFromFile("testFiles/testSudokuFile.sudoku");
        assertTrue("Should return non empty String[][]", sudoku != null);
    }

    @Test
    public void testInvalidSudokuFileLoadSudokuFromFile() {
        // SUDOKU FILE HAS TOO MANY NUMBERS ON ROW 6
        Sudoku sudoku = FileManager.loadSudokuFromFile("testFiles/testInvalidSudokuFile.sudoku");
        assertTrue("Should return null when loading invalid file", sudoku == null);
        
        // SUDOKU FILE HAS INVALID NUMBER ON ROWS 2 and 8
        sudoku = FileManager.loadSudokuFromFile("testFiles/testInvalidSudokuFile2.sudoku");
        assertTrue("Should return null when loading invalid file", sudoku == null);
        
        sudoku = FileManager.loadSudokuFromFile("testFiles/emptyFile.sudoku");
        assertTrue("Should return null when loading empty file", sudoku == null);
    }
    
    //LOAD AND SAVE TEST
    
    @Test
    public void testLoadingAndSaving() {
        // Create empty 3x3 sudoku
        Sudoku sudoku = new Sudoku(3,3);
        
        // Create tested numbers [1,9] that are inserted to sudoku
        
        Random random = new Random();
        int[] randomNumbers = new int[10];
        for (int i = 0; i < 10; i++) {
            randomNumbers[i] = random.nextInt(9) + 1;
        }
        
        System.out.println("Random numbers:");
        for (int i : randomNumbers) {
            System.out.print(i + ", ");
        }
        System.out.println("");
            
        // Create locations x,y: [0,9] where numbers are insterted
        ArrayList<Coordinates> locations = new ArrayList<>();
        
        while (locations.size() < 10) {
            Coordinates xy = new Coordinates(random.nextInt(9),random.nextInt(9));
            if (!locations.contains(xy)) {
                locations.add(xy);
            }
        }
        
        System.out.println("Locations:");
        for (Coordinates i : locations) {
            System.out.print(i.toString() + " <> ");
        }
        System.out.println("");
        
        // insert random numbers to sudoku
        // will not be solvable
        
        for (int i = 0; i < randomNumbers.length; i++) {
            sudoku.set(locations.get(i).x, locations.get(i).y, randomNumbers[i]);
        }

        // save sudoku to file
        try {
            FileManager.saveSudokuToFile("testFiles/testFileNameTestTestTest", sudoku);
        } catch (FileNotFoundException ex) {
            assertTrue("Failed to save sudoku.", false);
        }
        
        // clear old sudoku
        String testString = sudoku.toString();
        sudoku = null;
        
        // load sudoku from file
        sudoku = FileManager.loadSudokuFromFile("testFiles/testFileNameTestTestTest");
        assertTrue("Failed to load from file. Returned null sudoku", sudoku != null);
        assertTrue("File loaded incorrectly.", sudoku.toString().equals(testString));
        
        
        
    }

    private class Coordinates {
        
        public int x, y;
        
        public Coordinates (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            int hash = 3;
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
            final Coordinates other = (Coordinates) obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            return "X: " + x + ", Y: " + y;
        }
        
    }
    
}