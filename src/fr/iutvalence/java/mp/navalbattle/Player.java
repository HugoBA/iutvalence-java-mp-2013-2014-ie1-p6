package fr.iutvalence.java.mp.navalbattle;

/**
 * Represent a player of the Naval Battle
 * @author Quentin
 *
 */
public class Player
{
    /**
     * Player's boats
     */
    private Boat[] PBoats;
    
    /**
     * Player's actions
     */
    public Action[] PActions;
    
    /**
     * Constructor for a player
     * @param inBoats : The boats of the player
     * Initialize the table of the Player's actions (empty)
     */
    public Player(Boat[] inBoats)
    {
        Action[] operTable = new Action[100];
        
        int i, j;
        for (j=0; j<10; j++)
        {
            for (i=0; i<10; i++)
            {
                operTable[(j*10+i)] = new Action(i,j);
            }
        }
        
        this.PActions = operTable;
        this.PBoats = inBoats;
    }
    
    /**
     * A method to get the positions of the boat of the player
     * @return : Player's boat table
     */
    public Boat[] getBoats()
    {
        return this.PBoats;
    }
    
    /**
     * Check if the player has lost the game
     * @return : True if all the boat of the player are sunk
     */
    public boolean didPlayerLoose()
    {
        boolean lost = true;
        for(int i = 0; i < this.PBoats.length; i++)
        {
            for(int j = 0; j < this.PBoats[i].getPositions().length; j++)
            {
                lost = lost && this.PBoats[i].isHit(j);
            }
        }
        return lost;
    }
}
