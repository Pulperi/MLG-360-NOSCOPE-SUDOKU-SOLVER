/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.guicomponents;

import java.awt.Component;
import javax.swing.JLabel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henri
 */
public class SudokuCellTest {
    
    private SudokuCell sudokuCell1, sudokuCell2, sudokuCell3;
    
    public SudokuCellTest() {
        sudokuCell1 = new SudokuCell();
        sudokuCell2 = new SudokuCell();
        sudokuCell3 = new SudokuCell();
    }

    @Test
    public void testSetValueWithNonNumeral() {
        try {
            sudokuCell1.setValue("fail");
            assertTrue("Value must not be changed.", getCellLabel(sudokuCell1).getText().isEmpty());
        } catch (Exception ex) {
            assertTrue("Insert test failed: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testSetValueDimRanges() {
        try {
            sudokuCell1.setValue("-1");
            sudokuCell1.setValue("10");
            assertTrue("Value must not be changed.", getCellLabel(sudokuCell1).getText().isEmpty());
        } catch (Exception ex) {
            assertTrue("Insert test failed: " + ex.getMessage(), false);
        }
    }

    @Test
    public void testSetValueNull() {
        sudokuCell1.setValue(null);
        assertTrue("Value must not be changed.", getCellLabel(sudokuCell1).getText().isEmpty());
    }
    
    @Test
    public void testValidInsert() {
        sudokuCell2.setValue("4");
        assertTrue("Value should be 4.", getCellLabel(sudokuCell2).getText().equals("4"));
        sudokuCell2.setValue("0");
        assertTrue("Value should be empty.", getCellLabel(sudokuCell2).getText().isEmpty());
        sudokuCell2.setValue("9");
        assertTrue("Value should be 9.", getCellLabel(sudokuCell2).getText().equals("9"));
    }
    
    private JLabel getCellLabel(SudokuCell cell) {
        for (Component i : cell.getComponents()) {
            if (i.getAccessibleContext().getAccessibleName().equals("sudokuCellValue")) {
                return (JLabel)i;
            }
        }
        return null;
    }
}
