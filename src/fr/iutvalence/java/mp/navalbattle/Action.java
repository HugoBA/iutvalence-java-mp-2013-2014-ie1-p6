package fr.iutvalence.java.mp.navalbattle;

/**
 * Represents an operation made by the player
 * 
 * @author Hugo
 */

public class Action
{
    /**
     * Case concerned by the operation Is specified by its XY positions
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
     *            : X and Y positions of the played Case
     */
    // TODO (fixed) replace the two parameters by a single Coordinates
    public Action(Coordinates position)
    {
        this.coordinates = position;
        this.state = PositionState.UNSHOT;
    }

    /**
     * method to get the case of the operation
     * 
     * @return the case of the operation
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

}