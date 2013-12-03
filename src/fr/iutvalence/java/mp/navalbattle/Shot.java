package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents a shot (for history management)
 * 
 * @author Hugo
 */

public class Shot
{
    /**
     * Position of the shot
     */
    private Coordinates coordinates;

    /**
     * Result of the shot
     */
    private ShotResult state;

    /**
     * Operation constructor
     * 
     * @param coordinates
     *            position of the shot
     * @param shotResult result of the shot
     */
    public Shot(Coordinates coordinates, ShotResult shotResult)
    {
        this.coordinates = coordinates;
        this.state = shotResult;
    }

    /**
     * method to get the cell of the operation
     * 
     * @return the cell of the operation
     */
    public Coordinates getCoordinates()
    {
        return this.coordinates;
    }

    /**
     * method to get the state of the operation
     * 
     * @return integer, the state of the operation
     */
    public ShotResult getState()
    {
        return this.state;
    }

    /**
     * method to change the state of the operation
     * 
     * @param State
     *            : the new state of the operation
     */
    public void setState(ShotResult State)
    {
        this.state = State;
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return this.coordinates.hashCode();
    }
    
    /**
     * Two Actions are considerate as equals if their Coordinates are the same
     */
    public boolean equals(Object o)
    {
        if (o == this)
            return true;
        if (o == null)
            return false;
        Shot tmp = (Shot) o;
        return this.coordinates.equals(tmp.coordinates);
    }

}