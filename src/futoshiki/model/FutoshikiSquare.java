package futoshiki.model;

/**
 * This class contains the logic for the Futoshiki squares, including methods for
 * getting, setting, and displaying the value.
 * 
 * @author Candidate No 132106
 * @version 1.0 (03.Apr.2016)
 */
public class FutoshikiSquare implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int value;
    
    /**
     * Creates a new square.
     * 
     * @param value An integer to set the value of the square.
     */
    public FutoshikiSquare(int value){
        this.value = value;
    }
    
    /**
     * Gets the value of the square.
     * 
     * @return The integer value of the square.
     */
    public int getValue(){
        return value;
    }
    
    /**
     * Sets the value of the square.
     * 
     * @param value The integer value to set the square to.
     */
    public void setValue(int value){
        this.value = value;
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
        final FutoshikiSquare other = (FutoshikiSquare) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }

    /**
     * Gets a string value of the square.
     * 
     * @return A string representation of the square, or ' ' if the value is 0.
     */
    @Override
    public String toString() {
        if(value == 0){return " ";}        
        return Integer.toString(value);
    }
    
    
}
