package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents a case of the game grid
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
     * Default constructor
     * without parameters, default positions (x,y) are 0,0
     */
    public Coordinates()
    {
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Constructor with position values as parameters 
     * @param posx : x value to set
     * @param posy : y value
     */
    public Coordinates(int posx, int posy)
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
    
    // TODO (fixed) override hashCode
    public int hashCode()
    {
        return (this.x *10 + this.y);
    }
    
    // TODO (fixed) write comment (say how coordinates are considered to be the same)
    /**
     * Compare coordinates : if 2 Coordinates have the same X and the same Y, they are equals
     */
    public boolean equals(Object o)
    {
        if (o==this) 
            return true;
        if(o==null)
            return false;
        Coordinates tmp = (Coordinates) o;
        return (this.getX() == tmp.getX()) && (this.getY() == tmp.getY());
    }
}