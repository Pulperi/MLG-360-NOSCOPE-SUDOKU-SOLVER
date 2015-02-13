package sudokuproject.sudokuworldsaga;

import sudokuproject.sudokuworldsaga.domain.Sudoku;
import sudokuproject.sudokuworldsaga.domain.SudokuGenerator;
import sudokuproject.sudokuworldsaga.domain.SudokuSolver;
import sudokuproject.sudokuworldsaga.ui.SudokuWorldSaga;

/**
 * Main launcher class for SudokuWorldSaga -game
 *
 */
public class App {
    public static void main(String[] args) {
        SudokuWorldSaga swg = new SudokuWorldSaga();
        swg.launch();
    }
}   
    
    
    