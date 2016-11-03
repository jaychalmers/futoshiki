package futoshiki.model;

import futoshiki.controller.ControllerFutoshiki;
import futoshiki.model.FutoshikiPuzzle;
import futoshiki.view.GUIFutoshiki;

/**
 * Contains main method for running an example Futoshiki puzzle, to be given behaviour later.
 * 
 * @author Candidate No 132106
 * @version 1.0 (03.Apr.2016)
 */
public class Futoshiki {
         
    public static void main(String[] args) {
        FutoshikiPuzzle model = new FutoshikiPuzzle(5);
        GUIFutoshiki view = new GUIFutoshiki(model);
        ControllerFutoshiki controller = new ControllerFutoshiki(model, view);
        System.out.println(model);
        }
}
