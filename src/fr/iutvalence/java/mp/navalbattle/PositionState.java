package fr.iutvalence.java.mp.navalbattle;

/**
 * Enum type for the different states of a position on the Action table
 * @author barattoh
 */
public enum PositionState
{
    /**
     * The player shot on a boat and sunk this one
     */
    SUNK, 
    /**
     * The player shot on a boat, but the latter hasn't been sunk yet
     */
    ONBOAT, 
    /**
     * The player shot in water
     */
    INWATER;

    @Override
    public String toString()
    {
        switch (this)
        {
        case SUNK:
            
            return "S";
        case ONBOAT:
            
            return "T";
        case INWATER:
        default:
            return " ";
         }
    }
    
    
};