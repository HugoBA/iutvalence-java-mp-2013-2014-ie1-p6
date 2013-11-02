package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents a case of the game grid
 * 
 * @author barattoh
 * 
 */
public class Coordinates
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
     * @param posx
     *            : x value to set
     * @param posy
     *            : y value
     */
    public Coordinates(int posx, int posy)
    {
        this.x = posx;
        this.y = posy;
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

    public int hashCode()
    {
        return (this.x * 10 + this.y);
    }

    // TODO (refixed) write comment (say how coordinates are considered to be the
    // same)
    /**
     * (two coordinates are eauls if they share the same values for both x and y)
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