/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki.view;

import futoshiki.model.FutoshikiPuzzle;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author jc570
 */
public class GUIBoard extends JPanel {
    
    private FutoshikiPuzzle puzzle;
    private GUISquare[][] grid;
    private int gridSize;
    
    public GUIBoard(FutoshikiPuzzle puzzle){
        super();
        this.puzzle = puzzle;
        
        setPreferredSize(new Dimension(600,600));
        gridSize = ((puzzle.getBoardSize()*2)-1);
        setLayout(new GridLayout(gridSize,gridSize));
        grid = new GUISquare[gridSize][gridSize];
        
        //populate grid
        for(int i = 0; i < puzzle.getBoardSize(); i++){
            
            //add squares and column constraints per row
            for(int j = 0; j < puzzle.getBoardSize(); j++){
                GUISquare s = new GUISquare(puzzle.getSquare(i, j));
                grid[i][j] = s;
                add(s);
                if(j < puzzle.getBoardSize()-1){
                    add(new GUIConstraint(puzzle.getRowConstraint(i,j)));
                }
            }
            
            //add rowconstraints and blank spaces per row
            if(i < puzzle.getBoardSize()-1){
                for(int j = 0; j < puzzle.getBoardSize(); j++){
                    add(new GUIConstraint(puzzle.getColumnConstraint(j,i)));
                    if(j < puzzle.getBoardSize()-1){
                        add(new JPanel());
                        }
                }   
            }
            
        }
    }
    
    public void setPuzzle(FutoshikiPuzzle puzzle){
        this.puzzle = puzzle;
    }
    
    public GUISquare[][] getGrid(){
        return grid;
    }
}
