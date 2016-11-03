package futoshiki.model;

/**
 * This class contains the enum values for the various constraint types in the
 * Futoshiki package, including methods to handle display.
 * 
 * @author Candidate No 132106
 * @version 1.0 (03.Apr.2016)
 */
public enum ConstraintType {
    ROWGREATER(">"), ROWLESSER("<"), COLGREATER("V"), COLLESSER("^"), NEUTRAL(" ");

    private final String symbol;

    ConstraintType(String symbol){
        this.symbol = symbol;
    }

    /**
     * Gets the correct symbol associated with the type of the instance.
     * 
     * @return A string of the symbol.
     */
    @Override
    public String toString(){
        return symbol;
    }
}