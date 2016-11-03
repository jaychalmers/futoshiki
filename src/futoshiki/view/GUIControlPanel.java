/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jamie
 */
public class GUIControlPanel extends JPanel {
    private JButton newGame;
    private JButton saveGame;
    private JButton loadGame;
    private JButton check;
    private JButton solve;
    private JButton exit;
    
    private Container contentPane;
    
    public GUIControlPanel(){
        //temp values
        setPreferredSize(new Dimension(200,200));
        
        setLayout(new BoxLayout(this, Y_AXIS));
        
        add(Box.createVerticalGlue());
        makeFileControl();
        
        add(Box.createVerticalGlue());
        makeGameControl();
        
        add(Box.createVerticalGlue());
        makeExitButton();
        
        add(Box.createVerticalGlue());
        
    }
    
    //---------the littlest getter
    public JButton getSaveGameButton(){
        return saveGame;
    }    
    
    //---------creation of grouped panel elements
    private void makeFileControl(){
        newGame = createButton("New Game");
        add(newGame);
        
        saveGame = createButton("Save Game");
        add(saveGame);
        
        loadGame = createButton("Load Game");
        add(loadGame);
    }
    
    private void makeGameControl(){
        check = createButton("Check");
        add(check);
        
        solve = createButton("Solve");
        add(solve);
    }
    
    private void makeExitButton(){
        exit = createButton("Exit");
        add(exit);
    }
    
    //---------generic methods for creating panel elements
    private JButton createButton(String text){
        JButton button = new JButton(text);
        button.setAlignmentX(CENTER_ALIGNMENT);
        return button;
    }
        
    //----------listeners
    public void addNewGameListener(ActionListener listenForNewGameButton) {
        newGame.addActionListener(listenForNewGameButton);
    }
    
    public void addSaveGameListener(ActionListener listenForSaveGameButton) {
        saveGame.addActionListener(listenForSaveGameButton);
    }
    
    public void addLoadGameListener(ActionListener listenForLoadGameButton) {
        loadGame.addActionListener(listenForLoadGameButton);
    }
    
    public void addCheckListener(ActionListener listenForCheckButton) {
        check.addActionListener(listenForCheckButton);
    }
    
    public void addSolveListener(ActionListener listenForSolveButton) {
        solve.addActionListener(listenForSolveButton);
    }
    
    public void addExitListener(ActionListener listenForExitButton){
        exit.addActionListener(listenForExitButton);
    }
}
