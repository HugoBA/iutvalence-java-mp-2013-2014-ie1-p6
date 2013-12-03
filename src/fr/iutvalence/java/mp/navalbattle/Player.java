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

    /**
     * Player's actions
     * Represents the cells where the player has shot
     */
    // TODO (fix) once a boat is sunk, the position states of already played
    // shots, marked as touched, must be updated to "sunk"
    private List<Shot> shots;

    /**
     * Constructor for a player
     * 
     * @param playerBoats
     *            : The boats of the player Initialize the table of the Player's
     *            actions (empty)
     */
    public Player(Boat[] playerBoats)
    {
        this.shots =  new LinkedList<Shot>();
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
     * returns the list of shots already performed by the player
     * @return the list of shots already peformed by player
     */
    public List<Shot> getShots()
    {
        return this.shots;
    }

    /**
     * Check if the player has lost the game
     * 
     * @return : True if all the boat of the player are sunk
     */
    public boolean didPlayerLoose()
    {
        for (int i = 0; i < this.boats.length; i++)
        {
            if (!this.boats[i].isSunk())
                return false;
        }
        return true;
    }
    
}
