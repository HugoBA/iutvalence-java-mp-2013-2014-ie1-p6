package fr.iutvalence.java.mp.navalbattle;

// TODO (fix) fix comment
/**
 * A BoatCase is a Coordinates plus the state of the case
 * This class is only made to be used as a case of a Boat
 * @author barattoh
 *
 */
public class BoatCellCoordinates extends Coordinates
{
    /**
     * The state of the case of the Boat
     */
    private boolean isTouched;
    
    /**
     * Default constructor : uses super default constructor and then creates a (0,0) position
     */
    public BoatCellCoordinates ()
    {
        super();
        this.isTouched = false;
    }
    
    /**
     * Create a Boat cell on the given position, as not touched
     * @param x : X position of the cell
     * @param y : Y position of the cell
     */
    public BoatCellCoordinates (int x, int y)
    {
        super(x,y);
        this.isTouched = false;
    }

    /**
     * Gives the state of the cell
     * @return : cell state
     */
    public boolean isBoatCellTouched()
    {
        return this.isTouched;
    }

    /**
     * Sets the cell as touched
     */
    public void setBoatCellTouched()
    {
        this.isTouched = true;
    }   
}
