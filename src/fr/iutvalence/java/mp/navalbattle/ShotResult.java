package fr.iutvalence.java.mp.navalbattle;

/**
 * Enum type for the different states of a position on the Action table
 * @author barattoh
 */
public enum ShotResult
{
    /**
     * The player shot on a boat and sunk this one
     */
    SUNK, 
    /**
     * The player shot on a boat, but the latter hasn't been sunk yet
     */
    TOUCHED, 
    /**
     * The player shot in water
     */
    IN_WATER;

    @Override
    public String toString()
    {
        switch (this)
        {
        case SUNK:
            
            return "S";
        case TOUCHED:
            
            return "T";
        case IN_WATER:
        default:
            return " ";
         }
    }
    
    
};