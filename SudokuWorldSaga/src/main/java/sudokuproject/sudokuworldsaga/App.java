package sudokuproject.sudokuworldsaga;

import sudokuproject.sudokuworldsaga.domain.Sudoku;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Sudoku sudoku = null;
        Sudoku sudoku2 = null;
        try {
            sudoku = new Sudoku(5,2);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
        }
        System.out.println(sudoku);
    }
}
