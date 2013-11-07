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

    // TODO (fixed) you should declare as a 2D array of enumerated values
    // TODO (fixed) this field should be declared as private
    /**
     * Player's actions
     */
    private Action[][] actions;

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
        this.actions =  new Action[NavalBattle.GRIDSIZE][NavalBattle.GRIDSIZE];
        Coordinates position;

        // TODO (fixed) rename local variables (more explicit)
        // TODO (fixed) declare local variable inside loop initializer
        for (int y = 0; y < NavalBattle.GRIDSIZE; y++)
        {
            for (int x = 0; x < NavalBattle.GRIDSIZE; x++)
            {
                position = new Coordinates(x,y);
                this.actions[y][x] = new Action(position);
            }
        }

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
    public Action[][] getActions()
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
            for (int j = 0; j < this.boats[i].getPositions().length; j++)
            {
                lost = lost && this.boats[i].getPositions()[j].isBoatCaseTouched();
            }
        }
        return lost;
    }
    
}
