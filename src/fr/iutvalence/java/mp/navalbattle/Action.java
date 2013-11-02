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
     * State of the operation 0 : not played yet 1 : touched in the water 2 :
     * touched a boat
     */
    private int state;

    /**
     * Operation constructor
     * 
     * @param posX
     *            : X position of the played Case
     * @param posY
     *            : Y position of the played Case
     */
    // TODO (fix) replace the two parameters by a single Coordinates
    public Action(int posX, int posY)
    {
        this.coordinates = new Coordinates(posX, posY);
        this.state = 0;
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
    public int getState()
    {
        return this.state;
    }

    /**
     * method to change the state of the operation
     * 
     * @param State
     *            : the new state of the operation
     */
    public void setState(int State)
    {
        this.state = State;
    }

}