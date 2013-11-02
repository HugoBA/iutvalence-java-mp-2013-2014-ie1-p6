package fr.iutvalence.java.mp.navalbattle;

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

    // TODO (fix) you should declare as a 2D array of enumerated values
    // TODO (fix) this field should be declared as private
    /**
     * Player's actions
     */
    public Action[] actions;

    /**
     * Constructor for a player
     * 
     * @param playerBoats
     *            : The boats of the player Initialize the table of the Player's
     *            actions (empty)
     */
    public Player(Boat[] playerBoats)
    {
        // TODO (fix) declare hard-coded values as constants
        // TODO (fix) avoid using a temp variable
        Action[] operTable = new Action[100];

        // TODO (fix) rename local variables (more explicit)
        // TODO (fix) declare local variable inside loop initializer
        int i, j;
        // TODO (fix) declare hard-coded values as constants
        for (j = 0; j < 10; j++)
        {
            for (i = 0; i < 10; i++)
            {
                operTable[(j * 10 + i)] = new Action(i, j);
            }
        }

        this.actions = operTable;
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
     * Check if the player has lost the game
     * 
     * @return : True if all the boat of the player are sunk
     */
    public boolean didPlayerLoose()
    {
        boolean lost = true;
        for (int i = 0; i < this.boats.length; i++)
        {
            for (int j = 0; j < this.boats[i].getPositions().length; j++)
            {
                lost = lost && this.boats[i].isHit(j);
            }
        }
        return lost;
    }
}
