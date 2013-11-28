package fr.iutvalence.java.mp.navalbattle;

import java.util.List;
import java.util.LinkedList;

/**
 * Represent a player of the Naval Battle
 * 
 * @author Quentin
 * 
 */
public class Player
{
    /**
     * Player's boats
     */
    private Boat[] boats;

    // TODO (fixed) detail comment
    /**
     * Player's actions
     * Represents the cell where the player has shot
     */
    private List<Action> actions;

    /**
     * Constructor for a player
     * 
     * @param playerBoats
     *            : The boats of the player Initialize the table of the Player's
     *            actions (empty)
     */
    public Player(Boat[] playerBoats)
    {
        this.actions =  new LinkedList<Action>();
        this.boats = playerBoats;
    }

    /**
     * A method to get the positions of the boat of the player
     * 
     * @return : Player's boat table
     */
    public Boat[] getBoats()
    {
        return this.boats;
    }

    /**
     * returns the Action table of the player
     * @return the action table
     */
    public List<Action> getActions()
    {
        return this.actions;
    }

    /**
     * Check if the player has lost the game
     * 
     * @return : True if all the boat of the player are sunk
     */
    public boolean didPlayerLoose()
    {
        // TODO (fixed) simplify and optimize
        for (int i = 0; i < this.boats.length; i++)
        {
            if (!this.boats[i].isSunk())
                return false;
        }
        return true;
    }
    
}
