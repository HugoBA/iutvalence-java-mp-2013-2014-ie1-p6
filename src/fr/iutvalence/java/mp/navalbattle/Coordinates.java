package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents a cell of the game grid
 * 
 * @author barattoh
 * 
 */
public class Coordinates
{
    /**
     * X-axis coordinate
     */
    private int x;

    /**
     * Y-axis coordinate
     */
    private int y;

    /**
     * Default constructor without parameters, default positions (x,y) are 0,0
     */
    public Coordinates()
    {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor with position values as parameters
     * 
     * @param posX
     *            : x value to set
     * @param posY
     *            : y value
     */
    public Coordinates(int posX, int posY)
    {
        this.x = posX;
        this.y = posY;
    }

    /**
     * Method to get x
     * 
     * @return : x value
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * Method to get y
     * 
     * @return : y value
     */
    public int getY()
    {
        return this.y;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return (this.x * 10 + this.y);
    }

    /**
     * Two coordinates are equals if they share the same values for both x and y
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
        if (o == this)
            return true;
        if (o == null)
            return false;
        Coordinates tmp = (Coordinates) o;
        return (this.getX() == tmp.getX()) && (this.getY() == tmp.getY());
    }
}