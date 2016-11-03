package futoshiki.model;

import java.util.Objects;

/**
 * This class contains the logic for the Futoshiki constraints, including methods
 * for checking and displaying the type of constraint, and for checking if the
 * constraint is violated.
 * 
 * @author Candidate No 132106
 * @version 1.0 (03.Apr.2016)
 */
public class Constraint implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private final FutoshikiSquare source;
    private final FutoshikiSquare comparison;
    private final ConstraintType type;
    
    /**
     * Creates a new constraint.
     * 
     * @param source The first square for comparison.
     * @param comparitor The second square for comparison.
     * @param type The type of the constraint set. Must be one of the types
     * specified in the ConstraintType enum.
     */
    public Constraint(FutoshikiSquare source, FutoshikiSquare comparitor, ConstraintType type){
        this.source = source;
        this.comparison = comparitor;
        this.type = type;
    }
    
    /**
     * Gets the symbol of the constraint.
     * 
     * @return A string symbol of the constraint.
     */
    public String getSymbol(){
        return type.toString();
    }
    
    public ConstraintType getType(){
        return type;
    }
    
    /**
     * Evaluates whether the constraint is correct, that is whether the test "source type comparison"
     * is satisfied.
     * 
     * @return A boolean of whether the constraint is correct.
     */
    public boolean isCorrect(){
        switch (type){
            case ROWGREATER:
            case COLGREATER:
                if (source.getValue() != 0 && comparison.getValue() != 0){
                    return (source.getValue() > comparison.getValue());
                }
            case ROWLESSER:
            case COLLESSER:
                if (source.getValue() != 0 && comparison.getValue() != 0){
                    return (source.getValue() < comparison.getValue());
                }
            case NEUTRAL:
                return true;
            default:
                //type has not been set for some reason
                //EXCEPTION
                return false;
        }
    }

    /**
     * Gets the symbol of the constraint, with correct orientations for rows
     * and columns.
     * 
     * @return A string of the symbol.
     */
    @Override
    public String toString() {
        return type.toString();
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
        final Constraint other = (Constraint) obj;
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        if (!Objects.equals(this.comparison, other.comparison)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
}
