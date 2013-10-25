package fr.iutvalence.java.mp.navalbattle;

/**
 * Operation class
 * Represents an operation made by the player
 * @author Hugo
 */
public class Operation
{
    /**
     * Case concerned by the operation
     * Is specified by its XY positions
     */
    private Coordinates operCase;
    
    /**
     * State of the operation
     * 0 : not played yet
     * 1 : touched in the water
     * 2 : touched a boat
     */
    private int operState;
    
    /**
     * Operation constructor
     * @param posX : X position of the played Case
     * @param posY : Y position of the played Case
     */
    public Operation (int posX, int posY)
    {
        this.operCase = new Coordinates(posX, posY);
        this.operState = 0;
    }
    
    /**
     * method to get the case of the operation
     * @return the case of the operation
     */
    public Coordinates getOperCase()
    {
        return this.operCase;
    }

    /**
     * method to get the state of the operation
     * @return integer, the state of the operation
     */
    public int getOperState()
    {
        return this.operState;
    }
    
    /**
     * method to change the state of the operation
     * @param State : the new state of the operation
     */
    public void setOperState(int State)
    {
        this.operState = State;
    }
    
    
}