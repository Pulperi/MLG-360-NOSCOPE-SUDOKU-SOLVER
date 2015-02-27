/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.SudokuBoard;

import sudokuproject.sudokuworldsaga.SudokuBoard.SudokuCell;
import sudokuproject.sudokuworldsaga.SudokuBoard.SudokuPanel;
import sudokuproject.sudokuworldsaga.SudokuBoard.SudokuPanelSubset;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import static org.junit.Assert.*;
import org.junit.Test;
import sudokuproject.sudokuworldsaga.domain.Sudoku;
import sudokuproject.sudokuworldsaga.domain.SudokuSolver;
import sudokuproject.sudokuworldsaga.fileio.FileManager;

/**
 *
 * @author Henri
 */
public class SudokuPanelTest {
    
    private SudokuPanel sp, sp1;
    private Sudoku sudoku, solvedSudoku;
    
    public SudokuPanelTest() {
        sp = new SudokuPanel();
        sp1 = new SudokuPanel();
        sudoku = FileManager.loadSudokuFromFile("testFiles/testSudokuFile.sudoku");
        solvedSudoku = SudokuSolver.solve(sudoku);
        
    }

    @Test
    public void testUpdating() {
        testUpdateValues();
        testUpdateSolvedValues();
        testUpdateValues();
    }
    
    
    
    private void testUpdateValues() {
        sp1.updateValues(sudoku);
        
        SudokuPanelSubset sps1 = getSudokuPanelSubset(sp1, 0);
        SudokuCell c = getSudokuCell(sps1, 2);
        
        JLabel controlLabel = new JLabel();
        controlLabel.setBackground(Color.WHITE);
        
        assertTrue("updateValues() should change cell (2, 0) to value 5.", getCellLabel(c).getText().equals("5"));
        assertTrue("All cells color should be white.(omg racist)", controlLabel.getBackground().equals(getSudokuCell(sps1, 0).getBackground()));
    }
  
    private void testUpdateSolvedValues() {
        SudokuPanelSubset sps1 = getSudokuPanelSubset(sp1, 0);
        SudokuCell c = getSudokuCell(sps1, 0);
        JLabel controlLabel = new JLabel();
        controlLabel.setBackground(Color.GREEN);
        
        sp1.updateSolvedValues(sudoku, solvedSudoku);
        
        assertTrue("After updateSolvedValues() cell (0, 0) should not be empty.", !getCellLabel(c).getText().isEmpty());
        assertTrue("After updateSolvedValues() cell (0, 0) should be different color.", c.getBackground().equals(controlLabel.getBackground()));
    }

    @Test
    public void testSetXYvalid() {
        for (int x = 0; x < 2; x++) {
                for (int y = 0; y < 2; y++) {
                    assertTrue("Cell (" + (x * 8) + ", " + (y * 8) + ") value should be: 5", testSetXY(x * 8, y * 8, "5"));
                }
        }
    }

    @Test
    public void testSetXYinValid() {
        for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 2; j++) {
                    int x = (i * 10) - 1;
                    int y = (j * 10) - 1;
                    try {
                        sp.setXY(x, y, "5");
                    } catch (Exception ex) {
                        assertTrue("setXY() dim test failed (" + x + ", " + y + "): " + ex.getMessage(), false);
                    }
                }
        }
        assertTrue("setXY() dim test passed", true);
    }
    
    @Test
    public void testSetXYColorvalid() {
        for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    int x = i * 8;
                    int y = j * 8;
                    assertTrue("Cell (" + x + ", " + y + ") color should be pink.", testSetXYColor(x, y, Color.PINK));
                }
        }
    }
    
    @Test
    public void testSetXYColorinValid() {
        for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 2; j++) {
                    int x = (i * 10) - 1;
                    int y = (j * 10) - 1;
                    try {
                        sp.setXYColor(x, y, Color.PINK);
                    } catch (Exception ex) {
                        assertTrue("setXYColor() dim test failed (" + x + ", " + y + "): " + ex.getMessage(), false);
                    }
                }
        }
        assertTrue("setXYColor() dim test passed", true);
    }
    
    // Tests if the color is changed in cell (x, y)
    private boolean testSetXYColor(int x, int y, Color color) {
        sp.setXYColor(x, y, color);
        SudokuPanelSubset sps1 = getSudokuPanelSubset(sp, findSubsetIndex(x, y));
        SudokuCell c = getSudokuCell(sps1, findCellIndex(x, y));
        JLabel controlLabel = new JLabel();
        controlLabel.setBackground(color);
        return c.getBackground().equals(controlLabel.getBackground());
    }
    
    
    
    
    private boolean testSetXY(int x, int y, String string) {
        sp.setXY(x, y, string);
        SudokuPanelSubset sps1 = getSudokuPanelSubset(sp, findSubsetIndex(x, y));
        SudokuCell c = getSudokuCell(sps1, findCellIndex(x, y));
        return getCellLabel(c).getText().equals(string);
    }
    
    private int findSubsetIndex(int x, int y) {
        int rowFactor = ((y + 1) - y % 3) / 3;
        int columnFactor = ((x + 1) - x % 3) / 3;
        return columnFactor + 3 * rowFactor;
    }
    
    private int findCellIndex(int x, int y) {
        int row = y % 3;
        int col = x % 3;
        return col + 3 * row;
    }
    
    private JLabel getCellLabel(SudokuCell cell) {
    for (Component i : cell.getComponents()) {
        if (i.getAccessibleContext().getAccessibleName().equals("sudokuCellValue")) {
            return (JLabel) i;
        }
    }
    return null;
    }

    private SudokuCell getSudokuCell(SudokuPanelSubset subset, int i) {
        for (Component c : subset.getComponents()) {
            if (c.getAccessibleContext().getAccessibleName().equals("cell" + i)) {
                return (SudokuCell) c;
            }
        }
        return null;
    }

    private SudokuPanelSubset getSudokuPanelSubset(SudokuPanel panel, int i) {
        for (Component c : panel.getComponents()) {
            if (c.getAccessibleContext().getAccessibleName().equals("subset" + i)) {
                return (SudokuPanelSubset) c;
            }
        }
        return null;
    }
}
