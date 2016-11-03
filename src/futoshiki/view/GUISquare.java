/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki.view;

import futoshiki.model.FutoshikiSquare;
import javax.swing.JTextField;

/**
 *
 * @author jc570
 */
public class GUISquare extends JTextField {
    private FutoshikiSquare square;
    
    public GUISquare(FutoshikiSquare square){
        super(square.toString());
        this.square = square;
        setHorizontalAlignment(CENTER);
    }
    /*
    public void addSquareListener(ActionListener listenForSquare){
        addActionListener(listenForSquare);
    }
      */
    /*public void commit(){
        setSquare(new FutoshikiSquare(Integer.parseInt(getText())));
    }*/
    
    public FutoshikiSquare getSquare(){
        return square;
    }
    
    public void setSquare(final FutoshikiSquare square){
        this.square = square;
    }
}
