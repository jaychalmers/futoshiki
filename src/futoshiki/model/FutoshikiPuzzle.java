package futoshiki.model;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains the logic for the Futoshiki puzzle, including methods for
 * displaying, checking, and changing the state of the board.
 * 
 * @author Candidate No 132106
 * @version 1.0 (03.Apr.2016)
 */
public class FutoshikiPuzzle implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private final int boardSize;
    
    private FutoshikiSquare[][] board;
    private Constraint[][] rowConstraints;
    private Constraint[][] columnConstraints;
    private ArrayList<FutoshikiProblem> problemList;
    
    String newLine = System.lineSeparator();
    
    /**
     * Creates a new Futoshiki Puzzle.
     * 
     * @param boardSize An integer to set the size of board. Must be single-digit (IE, smaller than 10).
     */
    public FutoshikiPuzzle(int boardSize){
        if (boardSize > 9) throw new IllegalArgumentException("Invalid board size passed. Board size must be less than 10.");
        
        this.boardSize = boardSize;        
        problemList = new ArrayList();
                
        board = new FutoshikiSquare[boardSize][boardSize];
        rowConstraints = new Constraint[boardSize][boardSize - 1];
        columnConstraints = new Constraint[boardSize][boardSize - 1];
        
        resetPuzzle();
    }
        
    /**
     * Gets the size of the board.
     * 
     * @return An integer representing the size of the board.
     */
    public int getBoardSize() {
        return boardSize;
    }
    
    /**
     * Gets the board (Squares, not constraints).
     * 
     * @return A FutoshikiSquare array referring to the squares of the board. 
     */
    public FutoshikiSquare[][] getBoard(){
        return board;
    }
    
    /**
     * Sets a square to a new value by creating a new square object in the specified position.
     * 
     * @param row The row location of the square to set.
     * @param column The column location of the square to set.
     * @param value The value of the new square.
     */
    public void setSquare(int row, int column, int value){
        /*VALIDATION HAS BEEN COMMENTED OUT. THE USER SHOULD BE ABLE TO INPUT
        A BAD VALUE, AND THEN OTHER METHODS WILL POINT IT OUT. IT SHOULDNT JUST
        DO NOTHING*/
        
        //if ((value > 0) && (value < (boardSize + 1))){
            board[row][column].setValue(value);
        //}
    }
    
    /**
     * Gets a specific square object from the board.
     * 
     * @param row The row location of the square to get.
     * @param column The column location of the square to get.
     * @return The square object at the specified location.
     */
    public FutoshikiSquare getSquare(int row, int column){
        if ((row < boardSize+1) && (column < boardSize+1)){
            return board[row][column];
        }
        else {
            throw new IllegalArgumentException("Invalid co-ordinates passed.");
        }
    }
    
    /**
     * Sets a new row constraint at the specified location.
     * 
     * @param row The row location of the constraint to set.
     * @param column The column location of the constraint to set.
     * @param type The type of the constraint set. Must be one of the types
     * specified in the ConstraintType enum.
     */
    public void setRowConstraint(int row, int column, ConstraintType type){
        rowConstraints[row][column] = new Constraint(board[row][column], board[row][column+1], type);
    }
    
    /**
     * Sets a new column constraint at the specified location.
     * 
     * @param column The column location of the constraint to set.
     * @param row The row location of the constraint to set.
     * @param type The type of the constraint set. Must be one of the types
     * specified in the ConstraintType enum.
     */
    public void setColumnConstraint(int column, int row, ConstraintType type){
        columnConstraints[column][row] = new Constraint(board[row][column], board[row+1][column], type);
    }
    
    /**
     * Gets the row constraint object at the specified location.
     * 
     * @param row The row location of the constraint to get.
     * @param col The column location of the constraint to get.
     * @return A constraint object.
     */
    public Constraint getRowConstraint(int row, int col){
        return rowConstraints[row][col];
    }

    /**
     * Gets the column constraint object at the specified location.
     * 
     * @param col The column location of the constraint to get.
     * @param row The row location of the constraint to get.
     * @return A constraint object.
     */    
    public Constraint getColumnConstraint(int col, int row){
        return columnConstraints[col][row];
    }
    
    /**
     * Resets all squares to 0 and all constraints to neutral.
     */
    public void resetPuzzle(){
        //Set all squares to empty
        for(int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                board[i][j] = new FutoshikiSquare(0);
            }
        }
        
        //Set all constraints to neutral
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize-1; j++){
                setRowConstraint(i,j,ConstraintType.NEUTRAL);
                setColumnConstraint(i,j,ConstraintType.NEUTRAL);
            }
        }
    }
    
    /**
     * Fills the puzzle with a number of values, which do not represent a legal 
     * configuration of the board, but include a range of square values, 
     * including 0, and all types of constraints, to assist with testing correct
     * display of all values.
     */
    public void fillPuzzle(){
        ConstraintType[] cTypes = {
            ConstraintType.ROWGREATER,
            ConstraintType.COLGREATER,
            ConstraintType.ROWLESSER,
            ConstraintType.COLLESSER};
        
        for(int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                board[i][j] = new FutoshikiSquare(j);
            }
        }
        
        for(int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize-1; j++){
                if((j % 2) == 0){
                    setRowConstraint(i,j,cTypes[j]);
                }
                else{
                    setColumnConstraint(i,j,cTypes[j]);
                }
            }
        }
    }
    
    /**
     * Fills the puzzle with the preset 'easy' values to create a solveable game.
     */
    public void fillPuzzleEasy5(){
        setSquare(0,0,4);
        setSquare(1,1,2);
        setSquare(4,2,3);
        setColumnConstraint(0,0,ConstraintType.COLGREATER);
        setColumnConstraint(0,2,ConstraintType.COLGREATER);
        setColumnConstraint(3,3,ConstraintType.COLGREATER);
        setColumnConstraint(4,3,ConstraintType.COLLESSER);
        setRowConstraint(2,0,ConstraintType.ROWLESSER);
        setRowConstraint(2,2,ConstraintType.ROWLESSER);
        setRowConstraint(4,2,ConstraintType.ROWLESSER);
    }
    
    /**
     * Fills the puzzle with the preset 'hard' values to create a solveable game.
     */
    public void fillPuzzleHard5(){
        setSquare(4,4,1);
        setColumnConstraint(0,1,ConstraintType.COLGREATER);
        setColumnConstraint(2,1,ConstraintType.COLGREATER);
        setColumnConstraint(4,2,ConstraintType.COLLESSER);
        setColumnConstraint(0,3,ConstraintType.COLGREATER);
        setColumnConstraint(1,3,ConstraintType.COLGREATER);
        setRowConstraint(1,1,ConstraintType.ROWGREATER);
        setRowConstraint(4,0,ConstraintType.ROWLESSER);
    }

    /**
     * Solves the game by filling all squares with legal values, if possible.
     * 
     * @return A boolean value 'true' if the game was solved, 'false' if it could not be solved. 
     */
    public boolean solve(){
        int i = 0;
        int j = 0;
                
        //Is the board state legal? if so, look for the next empty square
        while(isLegal()){
            if (i >= boardSize){
                //we've gone passed the end of all the rows. Can't have found an empty square.
                //Since the board state is definitely legal at this point, we've found a solution!
                return true;
            }
            else if (j >= boardSize){
                //we've gone passed the end of the current row. Reset j to 0, increment i and keep looking.
                j = 0;
                i++;
            }
            else if (getSquare(i,j).getValue() == 0){
                //we have found an empty square! Hooray.
                //lets attempt to put some values in it.
                int value = 1;
                while (value <= boardSize) {
                    //pop a value in there
                    setSquare(i,j,value);
                    
                    //and then recursively call solve. if it returns true, we are solved baby.
                    if (solve()) {
                        return true;
                    }
                    else {
                        //otherwise, increment value and try again
                        value++;
                    }
                }
                //if we went through all that and didn't find a solution, then there's no solution in the current state.
                //set the value back to empty, and go back to the previous square.
                setSquare(i,j,0);
                return false;
            }
            else {
                //Ok, so we haven't solved the puzzle, but the current square is already filled.
                //Increment j++ and keep looking.
                j++;
            }
        }
        return false;
    }
    
    /**
     * Gets a string representation of the current state of the board.
     * 
     * @return A string visually representing the current state of the board.
     */
    @Override
    public String toString(){
        
        String myString = "";
        
        for(int i = 0; i < boardSize; i++){
            
            //Seperator lines
            for(int j = 0; j < boardSize; j++){
                myString = myString + "---";
                if(j != boardSize-1){myString = myString + " ";}
                else{myString = myString + newLine;}
            }
            
            //row
            for(int j = 0; j < boardSize; j++){
                myString = myString + "|" + board[i][j].toString() + "|";
                if(j != boardSize-1){myString = myString + rowConstraints[i][j].toString();}
                else{myString = myString + newLine;}
            }
            
            //another seperator
            for(int j = 0; j < boardSize; j++){
                myString = myString + "---";
                if(j != boardSize-1){myString = myString + " ";}
                else{myString = myString + newLine;}
            }
            
            //column constraints row
            if (i != boardSize-1){
                for(int j = 0; j < boardSize; j++){
                    myString = myString + " " + columnConstraints[j][i].toString() + " ";
                    if (j != boardSize-1){myString = myString + " ";}
                    else{myString = myString + newLine;}
                }
            }
        }
        return myString;
    }
    
    /**
     * Checks whether the board is currently in a legal state, by checking whether 
     * any squares have illegal or repeated values or any constraints are violated, 
     * and if any problems are found, adds them to the problem list.
     * 
     * @return A boolean representing whether or the not the board is currently legal.
     */
    public boolean isLegal(){
        boolean result = true;
        
        //reset problemList - get rid of any outdated values in there
        problemList.clear();
        
        //check for any illegal values
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                if (board[i][j].getValue() < 0 || board[i][j].getValue() > boardSize){
                    problemList.add(new FutoshikiProblem(i+1,j+1,"Illegal value"));
                    result = false;
                }
            }
        }
        
        //check for any repeated values in rows and columns
        for (int i = 0; i < boardSize; i++){
            if (rowHasDuplicates(i)){
                problemList.add(new FutoshikiProblem(i+1,"Duplicate values in row"));
                result = false;
            }
            if(columnHasDuplicates(i)){
                problemList.add(new FutoshikiProblem(i+1,"Duplicate values in column"));
                result = false;
            }
        }
        
        //check for any violated constraints
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize-1; j++){
                if (!(rowConstraints[i][j].isCorrect()) || !(columnConstraints[i][j].isCorrect())){
                    problemList.add(new FutoshikiProblem(i+1,j+1,"Violated constraint"));
                    result = false;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Gets a string description of any problems currently putting the board into
     * an illegal state, by using isLegal() to check.
     * 
     * @return A string description of all problems.
     */
    public String getProblems(){
        String description = "";
        
        //calling isLegal will ensure problemList is up-to-date
        if(!isLegal()){
            for (FutoshikiProblem instance : problemList){
                description = description + instance.toString() + newLine;
            }
        }
        else{description = "No problems found. Board state is legal.";}
        
        return description;
    }
    
    /**
     * Checks whether game is solved by checking if the board state is legal and if all squares are filled.
     * 
     * @return True if the game is already solved, false if not.
     */
    public boolean isSolved(){
        boolean result = isLegal();
        int i = 0;
        while (result && (i < boardSize)) {
            for (int j = 0; j < boardSize-1; j++){
                if (getSquare(i,j).getValue() == 0){
                    result = false;
                }                
            }
            i++;
        }
        return result;
    }
    
    private boolean rowHasDuplicates(int row){     
        for (int i = 0; i < boardSize-1; i++){
            for(int j = i+1; j < boardSize; j++){
                if((board[row][i].getValue() > 0) && (board[row][i].equals(board[row][j]))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean columnHasDuplicates(int column){
        for (int i = 0; i < boardSize-1; i++){
            for(int j = i+1; j < boardSize; j++){
                if((board[i][column].getValue() > 0) && (board[i][column].equals(board[j][column]))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FutoshikiPuzzle other = (FutoshikiPuzzle) obj;
        if (!Arrays.deepEquals(this.board, other.board)) {
            return false;
        }
        if (!Arrays.deepEquals(this.rowConstraints, other.rowConstraints)) {
            return false;
        }
        if (!Arrays.deepEquals(this.columnConstraints, other.columnConstraints)) {
            return false;
        }
        return true;
    }
   
    class FutoshikiProblem {
        private final int row;
        private final int column;
        private final String description;

        public FutoshikiProblem(int row, int column, String type){
            this.row = row;
            this.column = column;
            description = type + " at " + row + "," + column;
        }
        
        public FutoshikiProblem(int location, String type){
            this.row = location;
            column = 0;
            description = type + location;
        }

        @Override
        public String toString(){
            return description;
        }
    }
}
