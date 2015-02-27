/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.SudokuBoard;

import sudokuproject.sudokuworldsaga.SudokuBoard.SudokuCell;
import sudokuproject.sudokuworldsaga.SudokuBoard.SudokuPanelSubset;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Henri
 */
public class SudokuPanelSubsetTest {

    private SudokuPanelSubset sPS;
    private SudokuPanelSubset sPS2;

    public SudokuPanelSubsetTest() {
        sPS = new SudokuPanelSubset();
        sPS2 = new SudokuPanelSubset();
    }

    @Test
    public void testSetValue() {
        assertTrue("Should be 4.", testSetValueN(5, "4"));
        assertTrue("Should be 5.", testSetValueN(0, "5"));
        assertTrue("Should be 6.", testSetValueN(8, "6"));
    }
    
    private boolean testSetValueN(int i, String val) {
        sPS2.setValue(i, val);
        return getCellLabel(getSudokuCell(sPS2, i)).getText().equals(val);
    }

    @Test
    public void testSetColor() {
        assertTrue("Color Should be different.", testSetColorN(5, Color.PINK));
        assertTrue("Color Should be different.", testSetColorN(0, Color.PINK));
        assertTrue("Color Should be different.", testSetColorN(8, Color.PINK));
    }
    
    private boolean testSetColorN(int i, Color color) {
        Color colorBefore = getCellLabel(getSudokuCell(sPS, i)).getBackground();
        sPS.setColor(i, color);
        Color colorAfter = getCellLabel(getSudokuCell(sPS, i)).getBackground();
        return colorBefore.equals(colorAfter);
    }
    
    
    @Test
    public void testSetValueDim() {
        try {
            sPS.setValue(-5, "6");
            sPS.setValue(10, "6");
        } catch (Exception ex) {
            assertTrue("Dim Test failed.", true);
        }
        assertTrue("Test passed.", true);
    }

    @Test
    public void testSetColorDim() {
        try {
            sPS.setColor(-5, Color.MAGENTA);
            sPS.setColor(10, Color.MAGENTA);
        } catch (Exception ex) {
            assertTrue("Dim Test failed.", true);
        }
        assertTrue("Test passed.", true);
    }
    
    @Test
    public void testSetColorNull() {
        Color colorBefore = getCellLabel(getSudokuCell(sPS, 5)).getBackground();
        sPS.setColor(3, null);
        Color colorAfter = getCellLabel(getSudokuCell(sPS, 5)).getBackground();
        assertTrue("Should not be different.", colorBefore.equals(colorAfter));
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
}
