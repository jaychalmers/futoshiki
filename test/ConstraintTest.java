import futoshiki.model.Constraint;
import futoshiki.model.ConstraintType;
import futoshiki.model.FutoshikiSquare;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Candidate No 132106
 */
public class ConstraintTest {
    
    Constraint c;
    
    public ConstraintTest() {
    }
    
    @Before
    public void setUp() {
        c = new Constraint(new FutoshikiSquare(0),new FutoshikiSquare(1),ConstraintType.ROWLESSER);
    }
    
    @Test
    public void testConstraintIsInitialised() {
        assertNotNull(c.getSymbol());
    }
    
    @Test
    public void testGetSymbol(){
        assertEquals("<",c.getSymbol());
    }
    
    @Test
    public void testIsCorrect(){
        assertTrue(c.isCorrect());
        
        Constraint c2 = new Constraint(new FutoshikiSquare(0),new FutoshikiSquare(1),ConstraintType.ROWGREATER);
        assertFalse(c2.isCorrect());
        
        Constraint c3 = new Constraint(new FutoshikiSquare(0),new FutoshikiSquare(1),ConstraintType.COLLESSER);
        assertTrue(c3.isCorrect());
        
        Constraint c4 = new Constraint(new FutoshikiSquare(0),new FutoshikiSquare(1),ConstraintType.COLGREATER);
        assertFalse(c4.isCorrect());
        
        Constraint c5 = new Constraint(new FutoshikiSquare(0),new FutoshikiSquare(1),ConstraintType.NEUTRAL);
        assertTrue(c5.isCorrect());
    }
    
    @Test
    public void testToString(){
        assertEquals("<",c.toString());
    }
}
