/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshiki.controller;

import futoshiki.model.ConstraintType;
import futoshiki.model.FutoshikiPuzzle;
import futoshiki.view.GUIFutoshiki;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author jamie
 */
public class ControllerFutoshiki {
    private FutoshikiPuzzle model;
    private GUIFutoshiki view;
           
    public ControllerFutoshiki(FutoshikiPuzzle model, GUIFutoshiki view){
        this.view = view;
        this.model = model;
        
        for (int i = 0; i < model.getBoardSize(); i++){
            for (int j = 0; j < model.getBoardSize(); j++){
                //this.view.getBoard().getGrid()[i][j].addSquareListener(new SquareListener(i,j));
                }
            }
        
        this.view.getControlPanel().addNewGameListener(new NewGameListener());
        this.view.getControlPanel().addSaveGameListener(new SaveGameListener());
        this.view.getControlPanel().addLoadGameListener(new LoadGameListener());
        
        this.view.getControlPanel().addCheckListener(new CheckListener());
        this.view.getControlPanel().addSolveListener(new SolveListener());
        
        this.view.getControlPanel().addExitListener(new ExitListener());
        }
    
    private void updateModel(){
        for (int i = 0; i < model.getBoardSize(); i++){
            for (int j = 0; j < model.getBoardSize(); j++){
                int value;
                    
                String entry = view.getBoard().getGrid()[i][j].getText();
                entry = entry.replaceAll("\\s+","");
                    
                if (entry.equals("")){
                    value = 0;
                }
                else try {
                    value = Integer.parseInt(entry);
                } catch (NumberFormatException ex) {
                    System.out.println("Bad value in square " + i + "," + j);
                    System.out.println("Setting to -1 in Model.");
                    value = -1;
                }
                model.setSquare(i, j, value);
            }
        }
    } 
    
    class NewGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            int option = view.newGame();
            
            if (option == 0){
                model.resetPuzzle();
                model.fillPuzzleEasy5();
                view.makeBoard();
            }
            else if (option == 1){
                model.resetPuzzle();
                model.fillPuzzleHard5();
                view.makeBoard();
            }
        }
    }
    
    class SaveGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            updateModel();
            
            File file = view.save();
            try {
                FileOutputStream saveFile = new FileOutputStream(file);
                ObjectOutputStream save = new ObjectOutputStream(saveFile);
                save.writeObject(model);
                save.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class LoadGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            FutoshikiPuzzle loadModel = null;
            
            File file = view.load();
            if (file != null){
                try {
                    FileInputStream openFile = new FileInputStream(file);
                   ObjectInputStream open = new ObjectInputStream(openFile);
                  loadModel = (FutoshikiPuzzle) open.readObject();
                   open.close();
                   System.out.println("model.getBoardSize() is " + model.getBoardSize());
                  //Copy contents of loaded object into model
                    for (int i = 0; i < model.getBoardSize(); i++){
                        for (int j = 0; j < model.getBoardSize(); j++){
                            System.out.println("We're happy at: " + i + "," + j);
                            int value = loadModel.getSquare(i,j).getValue();
                            model.setSquare(i,j,value);
                            if (j < model.getBoardSize()-1){
                                ConstraintType rowType = loadModel.getRowConstraint(i,j).getType();
                                ConstraintType colType = loadModel.getColumnConstraint(i,j).getType();
                                model.setRowConstraint(i,j,rowType);
                                model.setColumnConstraint(i,j,colType);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            System.out.println("We're out of the loop baby");
            System.out.println(model);
            view.makeBoard();
        }
    }
}
    
    class CheckListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            updateModel();
            view.check();
        }
    }
    
    class SolveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            view.solve(model.solve());
            view.makeBoard();
        }
    }
    
    class ExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.exit();
        }
    }
}