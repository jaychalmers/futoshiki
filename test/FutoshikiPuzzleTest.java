import futoshiki.model.Constraint;
import futoshiki.model.ConstraintType;
import futoshiki.model.FutoshikiPuzzle;
import futoshiki.model.FutoshikiSquare;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Candidate No 132106
 */
public class FutoshikiPuzzleTest {
    
    int boardSize;
    FutoshikiPuzzle puzzle;
    
    public FutoshikiPuzzleTest() {
    }
    
    @Before
    public void setUp() {
        boardSize = 5;
        puzzle = new FutoshikiPuzzle(boardSize);
    }

    @Test
    public void testBoardIsInitialised() {
        assertNotNull(puzzle);
        assertEquals(boardSize,puzzle.getBoardSize());
        assertEquals(0,puzzle.getSquare(0,0).getValue());
    }
    
    @Test
    public void testGetBoardsize() {
        assertEquals(boardSize,puzzle.getBoardSize());
    }
    
    @Test
    public void testSetSquare() {
        puzzle.setSquare(0, 0, 1);
        assertEquals(1, puzzle.getSquare(0, 0).getValue());
    }
    
    @Test
    public void testGetSquare() {
        FutoshikiSquare s = new FutoshikiSquare(3);
        puzzle.setSquare(0, 0, 3);
        assertEquals(s,puzzle.getSquare(0,0));
    }
    
    @Test
    public void testSetRowConstraint() {
        puzzle.setRowConstraint(0,0,ConstraintType.ROWLESSER);
        puzzle.setSquare(0,0,1);
        puzzle.setSquare(0,1,2);
        assertTrue(puzzle.isLegal());
    }
    
    @Test
    public void testSetColumnConstraint() {
        puzzle.setColumnConstraint(0, 0, ConstraintType.COLLESSER);
        puzzle.setSquare(0,0,1);
        puzzle.setSquare(1,0,2);
        assertTrue(puzzle.isLegal());
    }
    
    @Test
    public void testGetRowConstraint() {
        Constraint c = new Constraint(puzzle.getSquare(0, 0),puzzle.getSquare(0,1),ConstraintType.ROWLESSER);
        puzzle.setRowConstraint(0, 0, ConstraintType.ROWLESSER);
        assertEquals(c,puzzle.getRowConstraint(0, 0));
    }
    
    @Test
    public void testGetColumnConstraint() {
        Constraint c = new Constraint(puzzle.getSquare(0, 0),puzzle.getSquare(1,0),ConstraintType.COLLESSER);
        puzzle.setColumnConstraint(0, 0, ConstraintType.COLLESSER);
        assertEquals(c,puzzle.getColumnConstraint(0, 0));
    }
    
    @Test
    public void testFillPuzzle() {
        boolean squareFound = false;
        boolean rowConstraintFound = false;
        boolean columnConstraintFound = false;
        int i = 0;
        int j = 0;
        
        Constraint c = new Constraint(puzzle.getSquare(0, 0),puzzle.getSquare(0,1),ConstraintType.NEUTRAL);
        
        puzzle.fillPuzzle();
        
        while((!squareFound) && (i < boardSize)){
            while((!squareFound) && (j < boardSize)){
                squareFound = (puzzle.getSquare(i, j).getValue() != 0);
                j++;
                }
            j = 0;
            i++;
        }
        i = 0;
        
        while((!rowConstraintFound) && (i < boardSize)){
            while((!rowConstraintFound) && (j < boardSize-1)){
                rowConstraintFound = (puzzle.getRowConstraint(i,j) != c);
                j++;
                }
            j = 0;
            i++;
        }
        i = 0;
        
        while((!columnConstraintFound) && (i < boardSize)){
            while((!columnConstraintFound) && (j < boardSize-1)){
                columnConstraintFound = (puzzle.getColumnConstraint(i,j) != c);
                j++;
                }
            j = 0;
            i++;
        }
        
        assertTrue(squareFound);
        assertTrue(rowConstraintFound);
        assertTrue(columnConstraintFound);
    }
    
    @Test
    public void testToString(){
        puzzle.fillPuzzle();
        assertNotNull(puzzle.toString());
        System.out.print(puzzle);
    }
    
    @Test
    public void testIsLegal(){
        fillPuzzleLegally();
        assertTrue(puzzle.isLegal());
        fillPuzzleIllegally();
        assertFalse(puzzle.isLegal());
    }
    
    @Test
    public void testGetProblems(){
        String legal = "No problems found. Board state is legal.";
        fillPuzzleLegally();
        assertEquals(legal,puzzle.getProblems());
        fillPuzzleIllegally();
        assertTrue(!legal.equals(puzzle.getProblems()));
    }
    
    public void fillPuzzleLegally(){
        puzzle.setSquare(0,0,1);
        puzzle.setSquare(0,1,2);
        puzzle.setSquare(0,2,3);
        puzzle.setSquare(0,3,4);
        puzzle.setSquare(0,4,5);
        puzzle.setSquare(1,0,3);
        puzzle.setSquare(1,1,1);
        puzzle.setSquare(1,2,2);
        puzzle.setSquare(1,3,5);
        puzzle.setSquare(1,4,4);
        puzzle.setSquare(2,0,2);
        puzzle.setSquare(2,1,4);
        puzzle.setSquare(2,2,5);
        puzzle.setSquare(2,3,3);
        puzzle.setSquare(2,4,1);
        puzzle.setSquare(3,0,5);
        puzzle.setSquare(3,1,3);
        puzzle.setSquare(3,2,4);
        puzzle.setSquare(3,3,1);
        puzzle.setSquare(3,4,2);
        puzzle.setSquare(4,0,4);
        puzzle.setSquare(4,1,5);
        puzzle.setSquare(4,2,1);
        puzzle.setSquare(4,3,2);
        puzzle.setSquare(4,4,3);
        
        puzzle.setRowConstraint(1,3,ConstraintType.ROWGREATER);
        puzzle.setColumnConstraint(0,1,ConstraintType.COLGREATER);
        puzzle.setColumnConstraint(4,2,ConstraintType.COLLESSER);
        puzzle.setRowConstraint(3,1,ConstraintType.ROWLESSER);
        puzzle.setColumnConstraint(4,3,ConstraintType.COLLESSER);
        puzzle.setRowConstraint(4,0,ConstraintType.ROWLESSER);
        puzzle.setRowConstraint(4,2,ConstraintType.ROWLESSER);
    }
    
    public void fillPuzzleIllegally(){
        puzzle.setSquare(0,0,1);
        puzzle.setSquare(0,1,2);
        puzzle.setSquare(0,2,3);
        puzzle.setSquare(0,3,4);
        puzzle.setSquare(0,4,5);
        puzzle.setSquare(1,0,3);
        puzzle.setSquare(1,1,1);
        puzzle.setSquare(1,2,3);
        puzzle.setSquare(1,3,5);
        puzzle.setSquare(1,4,4);
        puzzle.setSquare(2,0,2);
        puzzle.setSquare(2,1,4);
        puzzle.setSquare(2,2,5);
        puzzle.setSquare(2,3,3);
        puzzle.setSquare(2,4,1);
        puzzle.setSquare(3,0,5);
        puzzle.setSquare(3,1,3);
        puzzle.setSquare(3,2,4);
        puzzle.setSquare(3,3,4);
        puzzle.setSquare(3,4,2);
        puzzle.setSquare(4,0,4);
        puzzle.setSquare(4,1,5);
        puzzle.setSquare(4,2,1);
        puzzle.setSquare(4,3,2);
        puzzle.setSquare(4,4,3);
        
        puzzle.setRowConstraint(1,3,ConstraintType.ROWLESSER);
        puzzle.setColumnConstraint(0,1,ConstraintType.COLGREATER);
        puzzle.setColumnConstraint(4,2,ConstraintType.COLGREATER);
        puzzle.setRowConstraint(3,1,ConstraintType.ROWLESSER);
        puzzle.setColumnConstraint(4,3,ConstraintType.COLLESSER);
        puzzle.setRowConstraint(4,0,ConstraintType.ROWLESSER);
        puzzle.setRowConstraint(4,2,ConstraintType.ROWLESSER);
    }
}
