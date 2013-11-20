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

    // TODO (fixed) you should declare as a 2D array of enumerated values
    // TODO (fixed) this field should be declared as private
    /**
     * Player's actions
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
        // TODO (fixed) declare hard-coded values as constants
        // TODO (fixed) avoid using a temp variable
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
        boolean lost = true;
        for (int i = 0; i < this.boats.length; i++)
        {
            lost = lost && this.boats[i].isSunk();
        }
        return lost;
    }
    
}
