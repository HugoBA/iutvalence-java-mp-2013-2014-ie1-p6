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
     * list of positions occupied by the boat
     */
    private BoatCellCoordinates[] positions;

    /**
     * Create a boat by giving its coordinates
     * 
     * @param positions
     *            : table containing the boat coordinates
     */
    public Boat(BoatCellCoordinates[] positions)
    {
        this.positions = positions;
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
        // TODO (fixed) rename fields i and d
        int direction = rand.nextInt(4);
        // TODO (fixed) declare hard-coded values as constants
        int x,y;
        boolean able = false;
        
        // TODO (fixed) avoid using a temp variable
        this.positions = new BoatCellCoordinates[length];
        
        do
        {
            x =  rand.nextInt(NavalBattle.GRIDSIZE);
            y =  rand.nextInt(NavalBattle.GRIDSIZE);
            switch (direction)
            {
            case 0:
                able = ((x - length) >= 0);
                break;
            case 1:
                able = ((x + length) < NavalBattle.GRIDSIZE);
                break;
            case 2:
                able = ((y - length) >= 0);
                break;
            case 3:
                able = ((y + length) < NavalBattle.GRIDSIZE);
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
     *            : the coordinates of the case to check
     * @return : true if the boat is present on these coordinates, false if it
     *         isn't
     */
    public boolean isOnPosition(Coordinates position)
    {
        for (int i = 0; i < (this.positions.length); i++)
            if (this.positions[i].equals(position)) return true;
        return false;
    }
}
