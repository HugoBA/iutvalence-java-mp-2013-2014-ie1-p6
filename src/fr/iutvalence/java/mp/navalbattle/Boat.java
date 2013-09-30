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
    private int[] boatCases; // TODO créer un type coordonnées -> (x,y)
    
    // TODO (think about it) this field is useless because this information
    // can be retrieved from another field
    /**
     * Length of the boat
     */
    private int length;
    
    /**
     * State of the boat, to know if it is sunk or not
     */
    public boolean sunk; 
    
    /**
     * Makes the boat : initializes boat positions, and length
     * @param extrem1 : first extremity of the boat
     * @param extrem2 : second extremity of the boat
     */
    public Boat (int extrem1, int extrem2)
    {
        int xa, xb, ya, yb;
        xa = extrem1 / 10; ya = extrem1 % 10; //TODO revoir calcul avec nouveau type coordonnees
        xb = extrem2 / 10; yb = extrem2 % 10;
        
        this.length = java.lang.Math.abs((xa - xb) + (ya - yb));
        this.sunk = false;
    }
}
