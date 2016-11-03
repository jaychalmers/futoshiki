/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki.view;

import futoshiki.model.Constraint;
import javax.swing.JLabel;

/**
 *
 * @author jc570
 */
public class GUIConstraint extends JLabel {
    private final Constraint c;
    
    public GUIConstraint(Constraint c){
        super();
        this.c = c;
        setText(c.getSymbol());
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }
}
