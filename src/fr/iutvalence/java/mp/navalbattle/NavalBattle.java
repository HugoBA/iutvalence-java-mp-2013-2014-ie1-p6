package fr.iutvalence.java.mp.navalbattle;

import java.util.Random;

/**
 * Class which represents a naval battle game
 */
public class NavalBattle
{
    /**
     * Value for Player1
     */
    public final static int PLAYER1 = 0;
    
    /**
     * Value for Player2
     */
    public final static int PLAYER2 = 1;
    
    /**
     * Table containing the players
     */
    private Player[] partyPlayers;

    // TODO (fixed) detail comment
    /**
     * Constructor which initializes the boats of the two players and the grid of what they have done (Operations)
     * @param inBoatsP1 : table containing the boats of Player1
     * @param inBoatsP2 : table containing the boats of Player2
     */
    public NavalBattle(Boat[] inBoatsP1, Boat[] inBoatsP2)
    {
        Player[] tempPlayers = new Player[2];
        tempPlayers[0] = new Player(inBoatsP1);
        tempPlayers[1] = new Player(inBoatsP2);
        this.partyPlayers = tempPlayers;
    }
       
    /** 
     * launches the game
     * plays randomly the cases of the naval battle game, until a player wins
     */
    public void play()
    {
       
        // TODO (fix) consider that the loop allows only the current player to play
        // here, it is a player1+player2 loop
        while(!(this.partyPlayers[0].didPlayerLoose() || this.partyPlayers[1].didPlayerLoose()))
        {
            
            playRandom(PLAYER1);
            playRandom(PLAYER2);
            
            displayGrid(PLAYER1);
            displayGrid(PLAYER2);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
        if(this.partyPlayers[1].didPlayerLoose())
            System.out.println("Player 2 won!");
        else
            System.out.println("Player 1 won!");
       
    }
    
    
    // TODO (fixed) this method should be private
    /**
     * Method to display the grid game of a specified player on console
     * @param numP The number of the player
     */
    private void displayGrid(int numP)
    {
        int i, j;
        Coordinates position;
        
        System.out.println("   A B C D E F G H I J        A B C D E F G H I J");
        for (j=0; j<10; j++)
        {
            if(j+1<10)
                System.out.print(" "+(j+1)+" ");
            else
                System.out.print((j+1)+" ");
            for(i=0; i<10;i++)
            {
                position = new Coordinates(i,j);
                if(isBoatAt(numP, position))
                {
                    if(isBoatHitAt(numP, position))
                        System.out.print("X ");
                    else
                        System.out.print("O ");
                }
                else
                   System.out.print("¤ ");
            }
            
            
            System.out.print("    ");
            if(j+1<10)
                System.out.print(" "+(j+1)+" ");
            else
                System.out.print((j+1)+" ");
            for(i=0; i<10;i++)
            {
                position = new Coordinates(i,j);
                if(getActionResult(numP,position) == 0)
                    System.out.print("¤ ");
                else if(getActionResult(numP,position) == 1)
                   System.out.print("+ ");
                else
                    System.out.print("X ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    // TODO (fixed) this method should be private
    // TODO (fixed) consider passing a playerNumber instead of the whole list of boats
    // TODO (fixed) second parameter is useless
    /**
     * plays a random case which hasn't been played yet
     * @param numP : The number of the player
     */
    // TODO (fixed) separate (in two methods) the choice of the position where to shoot from the 
    // processing of the shot itself 
    private void playRandom(int numP)
    {
        int otherP;
        
        if (numP == PLAYER1)
            otherP = PLAYER2;
        else
            otherP = PLAYER1;
        
        processAction(numP, otherP, randomFreeCase(numP));
    }
    
    /**
     * Give a random case that the player have not played yet
     * @param numP : The number of the player
     * @return : A random non-shot case
     */
    public Coordinates randomFreeCase(int numP)
    {
        Random rand = new Random();
        int x, y;
        Coordinates randomPos;
        do
        {

            x = rand.nextInt(10);
            y = rand.nextInt(10);
            randomPos = new Coordinates(x,y);
        }while(!(getActionResult(numP,randomPos) ==0));
        
        return randomPos;
    }
    
    // TODO (fixed) rewrite comment
    /**
     * Method to proceed the action of a player : sets the right state to the played case (1 : water, or 2 : touched)
     * and, if there is a boat on this case, sets the case of the boat as touched
     * @param numP : The number of the player
     * @param otherP : the number of his adversary
     * @param OperationCoords : coordinates of the position concerned by the operation
     */
    private void processAction(int numP, int otherP, Coordinates OperationCoords)
    {
        int operState = 0;
        if(isBoatAt(otherP, OperationCoords))
        {
            operState = 2;
            if(!isBoatHitAt(otherP, OperationCoords));
                setBoatHitAt(otherP, OperationCoords);
        }
        else
            operState = 1;
        
        this.partyPlayers[numP].PActions[OperationCoords.getY()*10+OperationCoords.getX()].setOperState(operState);
    }

    /**
     * simple method to easily get the state of a case
     * @param numP : The number of the player
     * @param coords : coordinates of the position to test
     * @return  integer : 0 this position hasn't been played yet
     *                    1 the position has been played but water
     *                    2 the position has been played and touched a boat
     */
    // TODO (fixed) this method should be private
    // TODO (fixed) consider passing a playerNumber instead of the whole list of boats
    private int getActionResult(int numP, Coordinates coords)
    {
        // TODO (fix) this is not error-proof
        return this.partyPlayers[numP].PActions[coords.getY()*10+coords.getX()].getOperState();
    }
    
    
    // TODO (fixed) the second parameter is useless (redundant)
    // TODO (fixed) this method should be private
    /**
     * checks if there is a boat for a specific Player on a given position
     * @param numP : table containing the boats of a player
     * @param position : coordinates of the position to test
     * @return boolean : true if there is a boat, or false
     */
    private boolean isBoatAt(int numP, Coordinates position)
    {
        // TODO (fix) you can make it more readable
        boolean res = false;
        
        for(int i = 0; i < this.partyPlayers[numP].getBoats().length; i++)
            res = res || this.partyPlayers[numP].getBoats()[i].isOnPosition(position);
        
        return res;
    }
    
    /**
     * Returns the state of one case of the boat
     * @param numP : The number of the player
     * @param positionCoords : The coordinates of the case to check
     * @return : True if the case of the boat has been touched, else returns false
     */
    // TODO (fixed) this method should be private
    // TODO (fixed) consider passing a playerNumber instead of the whole list of boats
    private boolean isBoatHitAt(int numP, Coordinates positionCoords)
    {
        boolean res = false;
        int i, index;
        for(i = 0; i < this.partyPlayers[numP].getBoats().length; i++)
        {
            index = this.partyPlayers[numP].getBoats()[i].getIndex(positionCoords);
            if(index != -1)
                res = this.partyPlayers[numP].getBoats()[i].isHit(index);
                
        }
        return res;
    }
    
    /**
     * Sets the case of a boat on a specific position as Touched
     * @param numP : The number of the player
     * @param positionCoords the position of the case to set as touched
     */
    // TODO (fixed) this method should be private
    // TODO (fix) it is not very clever to pass all boat if you know chat boat has
    // been touched
    private void setBoatHitAt(int numP, Coordinates positionCoords)
    {
        int i, index;
        for(i = 0; i < this.partyPlayers[numP].getBoats().length; i++)
        {
            index = this.partyPlayers[numP].getBoats()[i].getIndex(positionCoords);
            if(index != -1)
                this.partyPlayers[numP].getBoats()[i].setHit(index);
        }
    }
}