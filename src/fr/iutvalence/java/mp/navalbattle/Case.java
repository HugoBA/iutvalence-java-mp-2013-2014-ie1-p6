package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents a case of the game grid
 * @author barattoh
 *
 */
public class Case
{
    /**
     * Position x of the case
     */
    private int x;
    
    /**
     * Position y of the case
     */
    private int y;
    
    /**
     * Default constructor
     */
    public Case()
    {
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Case constructor with position values as parameters
     * @param posx : x value to set
     * @param posy : y value
     */
    public Case(int posx, int posy)
    {
        this.x = posx;
        this.y = posy;
    }
    
    /**
     * Method to get x
     * @return : x value
     */
    public int getX()
    {
        return this.x;
    }
    
    /**
     * Method to get y
     * @return : y value
     */
    public int getY()
    {
        return this.y;
    }
}
