package fr.iutvalence.java.mp.navalbattle;

/**
 * Enum type for the different states of a position on the Action table
 * @author barattoh
 */
public enum PositionState
{
    /**
     * The player didn't shoot the position yet
     */
    UNSHOT, 
    /**
     * The player shot on a boat
     */
    ONBOAT, 
    /**
     * The player shot in water
     */
    INWATER;
};