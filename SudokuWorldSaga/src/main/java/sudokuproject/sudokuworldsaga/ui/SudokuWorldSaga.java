/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokuproject.sudokuworldsaga.ui;

import java.awt.Color;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import sudokuproject.sudokuworldsaga.domain.Sudoku;
import sudokuproject.sudokuworldsaga.domain.SudokuGenerator;
import sudokuproject.sudokuworldsaga.domain.SudokuSolver;
import sudokuproject.sudokuworldsaga.fileio.FileManager;

/**
 * SudokuWorldSaga alpha version Graphical User Interface
 *
 * @author Henri
 *  
 */
public class SudokuWorldSaga extends javax.swing.JFrame {

    private Sudoku sudoku = new Sudoku(3, 3);
    private int difficultyValue = 0;
    private JButton[] sudokuButtons;
    private int activeX = 0;
    private int activeY = 0;
    
    /**
     * Creates new form GameGUIManager
     */
    public SudokuWorldSaga() {
        initComponents();
        setFileChooserFilter();
        sudokuButtons = new JButton[]{b0, b1, b2, b3, b4, b5, b6, b7, b8, b9};
        disableSudokuButtons();
    }
    
    private void disableSudokuButtons() {
        for (JButton i : sudokuButtons) {
            i.setEnabled(false);
        }
    }
    
    private void setFileChooserFilter() {
        FileFilter filter = new FileNameExtensionFilter("Sudoku file", "sudoku");
        jFileChooser.addChoosableFileFilter(filter);
        jFileChooser.setFileFilter(filter);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        sudokuBoardBorder = new javax.swing.JPanel();
        sudokuPanel1 = new sudokuproject.sudokuworldsaga.ui.SudokuBoard.SudokuPanel();
        newSudokuButton = new javax.swing.JButton();
        loadSudokuButton = new javax.swing.JButton();
        solveButton = new javax.swing.JButton();
        newUnsolvedSudokuButton = new javax.swing.JButton();
        difficultySlider = new javax.swing.JSlider();
        difficultyLabel = new javax.swing.JLabel();
        difficultyValueText = new javax.swing.JTextField();
        title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textField = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b0 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sudoku Game");
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);

        sudokuBoardBorder.setBackground(new java.awt.Color(255, 255, 255));
        sudokuBoardBorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 5));
        sudokuBoardBorder.setPreferredSize(new java.awt.Dimension(450, 450));
        sudokuBoardBorder.setLayout(new java.awt.GridLayout(1, 0));

        sudokuPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sudokuPanel1MouseClicked(evt);
            }
        });
        sudokuBoardBorder.add(sudokuPanel1);

        newSudokuButton.setBackground(new java.awt.Color(0, 0, 0));
        newSudokuButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        newSudokuButton.setForeground(new java.awt.Color(255, 255, 255));
        newSudokuButton.setText("New Empty");
        newSudokuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSudokuButtonActionPerformed(evt);
            }
        });

        loadSudokuButton.setBackground(new java.awt.Color(0, 0, 0));
        loadSudokuButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loadSudokuButton.setForeground(new java.awt.Color(255, 255, 255));
        loadSudokuButton.setText("Load Sudoku");
        loadSudokuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadSudokuButtonActionPerformed(evt);
            }
        });

        solveButton.setBackground(new java.awt.Color(0, 0, 0));
        solveButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        solveButton.setForeground(new java.awt.Color(255, 255, 255));
        solveButton.setText("Solve Sudoku");
        solveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveButtonActionPerformed(evt);
            }
        });

        newUnsolvedSudokuButton.setBackground(new java.awt.Color(0, 0, 0));
        newUnsolvedSudokuButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        newUnsolvedSudokuButton.setForeground(new java.awt.Color(255, 255, 255));
        newUnsolvedSudokuButton.setText("New Unsolved");
        newUnsolvedSudokuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUnsolvedSudokuButtonActionPerformed(evt);
            }
        });

        difficultySlider.setMaximum(60);
        difficultySlider.setValue(0);
        difficultySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                difficultySliderStateChanged(evt);
            }
        });

        difficultyLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        difficultyLabel.setLabelFor(difficultySlider);
        difficultyLabel.setText("Sudoku difficulty [0, 60]");

        difficultyValueText.setText("0");
        difficultyValueText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                difficultyValueTextKeyPressed(evt);
            }
        });

        title.setFont(new java.awt.Font("Rod", 1, 18)); // NOI18N
        title.setText("<html><center><font color='red'><b>Sudoku World Saga</b></font></center></html>");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/kissa.jpeg"))); // NOI18N

        jScrollPane2.setAutoscrolls(true);

        textField.setColumns(20);
        textField.setRows(5);
        jScrollPane2.setViewportView(textField);

        buttonPanel.setLayout(new java.awt.GridLayout(3, 3));

        b1.setText("1");
        b1.setToolTipText("");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b1);
        b1.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b2.setText("2");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b2);
        b2.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b3.setText("3");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b3);
        b3.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b4.setText("4");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b4);
        b4.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b5.setText("5");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b5);
        b5.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b6.setText("6");
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b6);
        b6.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b7.setText("7");
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b7);
        b7.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b8.setText("8");
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b8);
        b8.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b9.setText("9");
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b9);
        b9.getAccessibleContext().setAccessibleDescription("sudokuButton");

        b0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sudokuButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(b0);
        b0.getAccessibleContext().setAccessibleDescription("sudokuButton");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sudokuBoardBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(difficultyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(newSudokuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loadSudokuButton)
                                        .addComponent(solveButton)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(difficultySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(difficultyValueText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(newUnsolvedSudokuButton))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {loadSudokuButton, newSudokuButton, solveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sudokuBoardBorder, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(solveButton)
                        .addGap(18, 18, 18)
                        .addComponent(loadSudokuButton)
                        .addGap(18, 18, 18)
                        .addComponent(newSudokuButton)
                        .addGap(18, 18, 18)
                        .addComponent(newUnsolvedSudokuButton)
                        .addGap(11, 11, 11)
                        .addComponent(difficultyLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(difficultySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(difficultyValueText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveButtonActionPerformed
        // Action when solve Sudoku button is clicked
        Sudoku solvedSudoku = SudokuSolver.solve(sudoku);
        if (solvedSudoku != null) {
            sudokuPanel1.updateSolvedValues(sudoku, solvedSudoku);
            solveButton.setEnabled(false);
        }
        else {
            textField.append("Sudoku is not solvable. Mistakes were made.\n");
        }
        disableSudokuButtons();
    }//GEN-LAST:event_solveButtonActionPerformed

    private void newSudokuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSudokuButtonActionPerformed
        // Action when new empty Sudoku button is clicked
        sudoku = new Sudoku(3, 3);
        sudokuPanel1.updateValues(sudoku);
        solveButton.setEnabled(true);
        disableSudokuButtons();
    }//GEN-LAST:event_newSudokuButtonActionPerformed

    private void loadSudokuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadSudokuButtonActionPerformed
        // Action when load Sudoku button is clicked
        // sudokuArea.setText("Opening");
        int returnVal = jFileChooser.showOpenDialog(SudokuWorldSaga.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            Sudoku loadedSudoku = FileManager.loadSudokuFromFile(file);
            if (loadedSudoku != null) {
                sudoku = loadedSudoku;
                sudokuPanel1.updateValues(sudoku);
                solveButton.setEnabled(true);
            }
        }
        disableSudokuButtons();
    }//GEN-LAST:event_loadSudokuButtonActionPerformed

    private void newUnsolvedSudokuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUnsolvedSudokuButtonActionPerformed
        // Action when new unsolved Sudoku button is clicked
        sudoku = SudokuGenerator.genNewSudoku(3, 3, difficultyValue);
        sudokuPanel1.updateValues(sudoku);
        solveButton.setEnabled(true);
        disableSudokuButtons();        
    }//GEN-LAST:event_newUnsolvedSudokuButtonActionPerformed

    private void difficultySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_difficultySliderStateChanged
        difficultyValueText.setText(difficultySlider.getValue() + "");
        difficultyValue = difficultySlider.getValue();
    }//GEN-LAST:event_difficultySliderStateChanged

    private void difficultyValueTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_difficultyValueTextKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            int value = 0;
            try {
                value = Integer.parseInt(difficultyValueText.getText());
                if (value < 0) {
                    value = 0;
                    difficultyValueText.setText(value + "");
                } else if (value > 60) {
                    value = 60;
                    difficultyValueText.setText(value + "");
                }
            } catch (Exception ex) {
                value = 0;
                difficultyValueText.setText("0");
            }
            difficultySlider.setValue(value);
            difficultyValue = value;
        }
    }//GEN-LAST:event_difficultyValueTextKeyPressed

    private void sudokuPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sudokuPanel1MouseClicked
        disableSudokuButtons();
        int[] xy = coordsToXY(evt.getX(), evt.getY());  
        if (xy[0] < 9 && xy[0] >= 0 && xy[1] < 9 && xy[1] >= 0) {
            activeX = xy[0];
            activeY = xy[1];
            textField.append("Selected cell x: " + activeX + ", y: " + activeY + " with value: " + sudoku.getXY(activeX, activeY) + "\n");
            for (int i : sudoku.getEntries(activeX, activeY)) {
                sudokuButtons[i].setEnabled(true);
            }
            if (sudoku.getXY(activeX, activeY) != 0) {
                sudokuButtons[0].setEnabled(true);
            }
        }
    }//GEN-LAST:event_sudokuPanel1MouseClicked

    private void sudokuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sudokuButtonActionPerformed
        // TODO add your handling code here:
        Object o = evt.getSource();
        if (o.getClass() != b0.getClass()) {
            return;
        }
        JButton bSource = (JButton)o;
        if (bSource.getAccessibleContext().getAccessibleDescription().equals("sudokuButton")) {
            
            String bText = bSource.getText();
            sudokuPanel1.setXY(activeX, activeY, bText);
            int val = 0;
            sudokuPanel1.setXYColor(activeX, activeY, Color.WHITE);
            if (!bText.equals("")) {
                val = Integer.parseInt(bText);
                sudokuPanel1.setXYColor(activeX, activeY, Color.YELLOW);
            }
            sudoku.set(activeX, activeY, val);
            textField.append("Changed cell x: " + activeX + ", y: " + activeY + " value to: " + sudoku.getXY(activeX, activeY) + "\n");
        }
        disableSudokuButtons();
    }//GEN-LAST:event_sudokuButtonActionPerformed

    // Calculates the sudoku row/col from mouse click coords
    private int[] coordsToXY(int clickX, int clickY) {
        // constants that depend on UI construction
        int borderWidth = 5;
        int cellWidth = 45;
        
        int absX = clickX - borderWidth;
        int absY = clickY - borderWidth;
        
        //textField.append("location: x = " + absX + ", y = " + absY + "\n");
        
        // Borders on sudoku subsets have to be removed from coords
        absX = removeBorders(absX, borderWidth, cellWidth);
        absY = removeBorders(absY, borderWidth, cellWidth);
        
        // Calculate row/col index
        int x = (absX - (absX % cellWidth))/cellWidth;
        int y = (absY - (absY % cellWidth))/cellWidth;
        
        return new int[]{x, y};
    }
    
    private int removeBorders(int coord, int borderWidth, int cellWidth) {
        if (coord - (2 * borderWidth) >= cellWidth * 3 && coord - (2 * borderWidth) < cellWidth * 6) {
            // if click is on cols 3-5, remove 1 extra border from x coords
            coord -= (2 * borderWidth);
        } else if (coord - (4 * borderWidth) > cellWidth * 6 && coord - (4 * borderWidth) < cellWidth * 9) {
            // if click is on cols 6-8, remove 2 extra border from x coords
            coord -= 4 * borderWidth;
        }
        return coord;
    }
    
    
    /**
     * Launches the GUI
     */
    
    
    public static void launch() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SudokuWorldSaga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SudokuWorldSaga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SudokuWorldSaga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SudokuWorldSaga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SudokuWorldSaga().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b0;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel difficultyLabel;
    private javax.swing.JSlider difficultySlider;
    private javax.swing.JTextField difficultyValueText;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadSudokuButton;
    private javax.swing.JButton newSudokuButton;
    private javax.swing.JButton newUnsolvedSudokuButton;
    private javax.swing.JButton solveButton;
    private javax.swing.JPanel sudokuBoardBorder;
    private sudokuproject.sudokuworldsaga.ui.SudokuBoard.SudokuPanel sudokuPanel1;
    private javax.swing.JTextArea textField;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
