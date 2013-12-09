package fr.iutvalence.java.mp.navalbattle;

import java.util.Random;

/**
 * Represents each boat
 * 
 * @author barattoh
 * 
 */
public class Boat
{
    /**
     * The number of directions a boat can take
     *      North, South
     *      East, West
     */
    public final static int NB_DIRECTIONS = 4;
    
    /**
     * Left direction value
     */
    public final static int LEFT = 0;
    
    /**
     * Right direction value
     */
    public final static int RIGHT = 1;
    
    /**
     * Up direction value
     */
    public final static int UP = 2;
    
    /**
     * Down direction value
     */
    public final static int DOWN = 3;
    
    /**
     * List of positions occupied by the boat
     */
    private BoatCellCoordinates[] positions;
    
    /**
     * Boolean to know if the boat sank or not
     */
    private boolean sunk;

    /**
     * Create a boat by giving its coordinates
     * 
     * @param positions
     *            : table containing the boat coordinates
     */
    public Boat(BoatCellCoordinates[] positions)
    {
        this.positions = positions;
        this.sunk = false;
    }

    /**
     * Creates a random boat from the length given in parameter Takes random x
     * and y positions, verifies if the boat is out of bounds or not, according
     * to its size
     * 
     * @param length
     *            : length of the boat
     */
    public Boat(int length)
    {
        Random rand = new Random();
        
        int direction = rand.nextInt(NB_DIRECTIONS);
        boolean able = false;
        
        this.positions = new BoatCellCoordinates[length];
        
        int x,y;
        do
        {
            x =  rand.nextInt(NavalBattle.GRID_SIZE);
            y =  rand.nextInt(NavalBattle.GRID_SIZE);
            switch (direction)
            {
            case LEFT:
                able = ((x - length) >= 0);
                break;
            case RIGHT:
                able = ((x + length) < NavalBattle.GRID_SIZE);
                break;
            case UP:
                able = ((y - length) >= 0);
                break;
            case DOWN:
                able = ((y + length) < NavalBattle.GRID_SIZE);
                break;
            }
        }
        while (!able);
        this.positions[0] = new BoatCellCoordinates(x, y);

        int i;
        for (i = 1; i < length; i++)
        {
            switch (direction)
            {
            case 0:
                this.positions[i] = new BoatCellCoordinates(x - i, y);
                break;
            case 1:
                this.positions[i] = new BoatCellCoordinates(x + i, y);
                break;
            case 2:
                this.positions[i] = new BoatCellCoordinates(x, y - i);
                break;
            case 3:
                this.positions[i] = new BoatCellCoordinates(x, y + i);
                break;
            }
        }
    }

    
    /**
     * Creates a boat from the extremity given in parameters, to a given direction, for a specified length 
     * @param extremity :  the coordinates of one extremity of the boat
     * @param direction : integer : the direction the boat takes from its first extremity
     *                           0: to the left
     *                           1: to the right
     *                           2: to the top
     *                           3: to the bottom
     * 
     * @param length : length of the boat
     //* @throws InvalidCoordinateException : Invalid cell coordinates
     */
    public Boat(Coordinates extremity, int direction, int length) //throws InvalidCoordinateException
    {
        int x = extremity.getX();
        int y = extremity.getY();
        
        /*if (x < 0 || x > NavalBattle.GRID_SIZE 
                || y < 0 || y > NavalBattle.GRID_SIZE)
            throw new InvalidCoordinateException();*/
        
        this.positions = new BoatCellCoordinates[length];

        int i;
        for (i = 0; i < length; i++)
        {
            switch (direction)
            {
            case LEFT:
                this.positions[i] = new BoatCellCoordinates(x - i, y);
                break;
            case RIGHT:
                this.positions[i] = new BoatCellCoordinates(x + i, y);
                break;
            case UP:
                this.positions[i] = new BoatCellCoordinates(x, y - i);
                break;
            case DOWN:
                this.positions[i] = new BoatCellCoordinates(x, y + i);
                break;
            }
        }
    }
    
    
    /**
     * Method that simply returns the position of the boat
     * 
     * @return : Table containing all boat positions
     */
    public BoatCellCoordinates[] getPositions()
    {
        return this.positions;
    }

    /**
     * method to check if the boat is concerned by a specific coordinate
     * 
     * @param position
     *            : the coordinates of the cell to check
     * @return : true if the boat is present on these coordinates, false if it
     *         isn't
     */
    public boolean isOnPosition(Coordinates position)
    {
        for (int i = 0; i < (this.positions.length); i++)
            if (this.positions[i].equals(position)) return true;
        return false;
    }

    /**
     * method to know if the boat is sunk or not
     * @return boolean : true if the boat is sunk
     */
    public boolean isSunk()
    {
        return this.sunk;
    }
    
    /**
     * Method to know if the boat has been sunk after the action
     * if yes, sets the boat as sunk
     * @return boolean : true if the boat has just been sunk
     *                   false if the boat isn't or was already sunk
     */
    public boolean didThisBoatJustSank()
    {
        for (int i = 0; i < this.positions.length; i++)
        {
            if(!this.positions[i].isBoatCellTouched() || this.sunk == true)
                return false;
        }
        this.sunk = true;
        return true;
    }
}
