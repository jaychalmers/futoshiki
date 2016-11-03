import futoshiki.model.FutoshikiSquare;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Candidate No 132106
 */
public class FutoshikiSquareTest {
    
    int value;
    FutoshikiSquare s;
    
    public FutoshikiSquareTest() {
    }
    
    @Before
    public void setUp() {
        value = 3;
        s = new FutoshikiSquare(value);
    }
    
    @Test
    public void testSquareIsInitialised() {
        assertNotNull(s.getValue());
    }
    
    @Test
    public void testGetValue(){
        assertEquals(value,s.getValue());
    }
    
    @Test
    public void testSetValue(){
        int newValue = 5;
        s.setValue(newValue);
        assertEquals(newValue,s.getValue());
    }
    
    @Test
    public void testToString(){
        s.setValue(0);
        assertEquals(" ",s.toString());
        s.setValue(1);
        assertEquals("1",s.toString());
    }
}
