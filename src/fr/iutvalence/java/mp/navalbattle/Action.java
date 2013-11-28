package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents an operation made by the player
 * 
 * @author Hugo
 */

public class Action
{
    /**
     * Cell concerned by the operation Is specified by its XY positions
     */
    private Coordinates coordinates;

    /**
     * State of the operation 
     */
    private PositionState state;

    /**
     * Operation constructor
     * 
     * @param position
     *            : X and Y positions of the played cell
     * @param inState : the state to set
     */
    public Action(Coordinates position, PositionState inState)
    {
        this.coordinates = position;
        this.state = inState;
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
    public PositionState getState()
    {
        return this.state;
    }

    /**
     * method to change the state of the operation
     * 
     * @param State
     *            : the new state of the operation
     */
    public void setState(PositionState State)
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
        Action tmp = (Action) o;
        return this.coordinates.equals(tmp.coordinates);
    }

}