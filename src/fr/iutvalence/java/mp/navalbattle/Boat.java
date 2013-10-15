package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents each boat
 * @author barattoh
 *
 */
public class Boat
{
    /**
     * Table which contains every positions of the boat on the grid
     */
    private Case[] boatCases;
    
    /**
     * Table which contains the state of each cases of the boat
     * False : not touched yet, True : touched
     */
    public boolean[] touchedCases;

    /**
     * Makes the boat : initializes boat positions, and length
     * @param boatCasesIn : table containing the boat coordinates
     */
    public Boat(Case[] boatCasesIn)
    {
       this.boatCases = boatCasesIn;
       // TODO (fix) the following array is allocated but not initialized properly
       this.touchedCases = new boolean[boatCasesIn.length];
    } 
    
    /**
     * method that simply returns the position of the boat
     * @return table containing all boat positions
     */
    public Case[] getPosition()
    {
        return this.boatCases;
    }
    
    
}
