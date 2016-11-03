/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki.view;

import futoshiki.model.FutoshikiPuzzle;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author jc570
 */
public class GUIFutoshiki extends JFrame {
    
    private GUIBoard board;
    private GUIControlPanel controlPanel;
    private FutoshikiPuzzle puzzle;
    private Container contentPane;
    private JFileChooser fileChooser;
    
    public GUIFutoshiki(FutoshikiPuzzle puzzle){
        super("Futoshiki");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.puzzle = puzzle;
        
        fileChooser = new JFileChooser();
        
        //populate
        contentPane = getContentPane();
        makeMenuBar();
        makeControlPanel();
        makeBoard();
        
        //display
        pack();
        setVisible(true);
    }
    
    //------methods for making components
    private void makeMenuBar(){
        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        
        //create and populate the file menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {exit();}
            });
        }
    
    private void makeControlPanel(){
        controlPanel = new GUIControlPanel();
        contentPane.add(controlPanel, BorderLayout.WEST);
    }
    
    public void makeBoard(){
        if (board != null){
            remove(board);
        }
        board = new GUIBoard(puzzle);
        contentPane.add(board, BorderLayout.CENTER);
        pack();
    }    
    
    //----------getters and setters
    public GUIBoard getBoard(){
        return board;
    }
    
    public GUIControlPanel getControlPanel(){
        return controlPanel;
    }
    
    //--------program methods   
        
    public int newGame(){
        Object[] options = {"Easy","Hard","Cancel"};
        int i = JOptionPane.showOptionDialog(this,
                "Please select difficulty:",
                "New Game",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        return i;
    }
    
    public File save(){
        File file = null;
        
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        
        return file;
    }
    
    public File load(){
        File file = null;
        
        int returnVal = fileChooser.showOpenDialog(this);        
        if (returnVal == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
        }
        
        return file;
    }
    
    public void check(){
        if (puzzle.isSolved()){
            JOptionPane.showMessageDialog(this,"The puzzle is solved!\nCongratulations!");
        }
        else {
            JOptionPane.showMessageDialog(this,puzzle.getProblems() + "\nThe puzzle is not yet solved.");
        }
    }
    
    public void solve(Boolean solved){
        if (solved){
            JOptionPane.showMessageDialog(this,"Puzzle has been automatically solved.");
        }
        else {
            JOptionPane.showMessageDialog(this, "Puzzle could not be solved.\nPlease check for any existing illegal values.");
        }
    }
    
    public void exit(){
        int i = JOptionPane.showConfirmDialog(this,
                "Save progress before quitting?"
        );
        if (i == 0){
            controlPanel.getSaveGameButton().doClick();
            System.exit(0);
        }
        else if (i == 1){
            System.exit(0);
        }
    }
}
