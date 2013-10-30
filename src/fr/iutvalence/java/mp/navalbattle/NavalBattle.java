package fr.iutvalence.java.mp.navalbattle;

import java.util.Random;

/**
 * Class which represents a naval battle game
 */
public class NavalBattle
{    
    /**
     * table containing the boats of Player1
     */
    private Boat[] player1Boats; 
    
    /**
     * table containing Player1 actions
     */
    private Action[] player1Actions; 
    
    /**
     * table containing the boats of Player2
     */
    private Boat[] player2Boats; 
    
    /**
     * table containing player2 actions
     */
    private Action[] player2Actions; 

    // TODO (fix) detail comment
    /**
     * Constructor which initializes the boats of the two players
     * @param inBoatsP1 table of the boats of Player1
     * @param inBoatsP2 table of the boats of Player2
     * @param P1Operations the operation table of Player1
     * @param P2operations the operation table of Player2 
     */
    public NavalBattle(Boat[] inBoatsP1, Boat[] inBoatsP2, Action[] P1Operations, Action[] P2operations)
    {
        this.player1Boats = inBoatsP1;
        this.player1Actions = P1Operations;
        
        this.player2Boats = inBoatsP2;
        this.player2Actions = P2operations;
    }
       
    /** 
     * launches the game
     * plays randomly the cases of the naval battle game, until a player wins
     */
    public void play()
    {
        boolean hasPlayer1lost = false;
        boolean hasPlayer2lost = false;
       
        // TODO (fix) consider that the loop allows only the current player to play
        // here, it is a player1+player2 loop
        while(!(hasPlayer1lost || hasPlayer2lost))
        {
            
            playRandom(this.player1Actions, this.player2Boats);
            playRandom(this.player2Actions, this.player1Boats);
            
            displayGrid(this.player1Boats, this.player1Actions);
            displayGrid(this.player2Boats, this.player2Actions);
            
            hasPlayer1lost = didPlayerLoose(this.player1Boats);
            hasPlayer2lost = didPlayerLoose(this.player2Boats);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
        if(hasPlayer1lost)
            System.out.println("Player 2 won!");
        else
            System.out.println("Player 1 won!");
       
    }
    
    
    // TODO (fix) this method should be private
    /**
     * method to display the grid game of a specified player on console
     * @param BoatPlayer the boat table of the player
     * @param PlayerOperations the operation table of the player
     */
    public void displayGrid(Boat[] BoatPlayer, Action[] PlayerOperations)
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
                if(isBoatAt(BoatPlayer, BoatPlayer.length, position))
                {
                    if(isBoatHitAt(BoatPlayer, position))
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
                if(getActionResult(PlayerOperations,position) == 0)
                    System.out.print("¤ ");
                else if(getActionResult(PlayerOperations,position) == 1)
                   System.out.print("+ ");
                else
                    System.out.print("X ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    // TODO (fix) this method should be private
    // TODO (fix) consider passing a playerNumber instead of the whole list of boats
    // TODO (fix) second parameter is useless
    /**
     * plays a random case which hasn't been played yet
     * @param PlayerOperations : the operation of the Player WHO IS PLAYING
     * @param BoatAdvers : the boat table of HIS ADVERSARY
     */
    // TODO (fix) separate (in two methods) the choice of the position where to shoot from the 
    // processing of the shot itself 
    public void playRandom(Action[] PlayerOperations, Boat[] BoatAdvers)
    {
        Random rand = new Random();
        int x, y;
        Coordinates randomPos;
        boolean operOK = false;
        while(!operOK)
        {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            randomPos = new Coordinates(x,y);
            if(getActionResult(PlayerOperations,randomPos) ==0)
            {
                processAction(PlayerOperations, BoatAdvers, randomPos);
                operOK = true;
            }
        }
        
    }
    
    // TODO (fix) rewrite comment
    /**
     * method to "launch" the operation
     * -> sets the chosen case as 1 (=played but water) or 2 (=played and touched a boat)
     * -> if a boat is touched, sets its case as touched
     * @param PlayerOperations : the operation table of the player
     * @param AdversaryBoats : the boat table of his adversary
     * @param OperationCoords : coordinates of the position concerned by the operation
     */
    private void processAction(Action[] PlayerOperations, Boat[] AdversaryBoats, Coordinates OperationCoords)
    {
        int operState =0;
        if(isBoatAt(AdversaryBoats, AdversaryBoats.length, OperationCoords))
        {
            operState = 2;
            if(!isBoatHitAt(AdversaryBoats, OperationCoords));
                setBoatHitAt(AdversaryBoats, OperationCoords);
        }
        else
            operState = 1;
        
        PlayerOperations[OperationCoords.getY()*10+OperationCoords.getX()].setOperState(operState);
    }

    /**
     * simple method to easily get the state of a case
     * @param PlayerOperations : the operation table of the player
     * @param coords : coordinates of the position to test
     * @return  integer : 0 this position hasn't been played yet
     *                    1 the position has been played but water
     *                    2 the position has been played and touched a boat
     */
    // TODO (fix) this method should be private
    // TODO (fix) consider passing a playerNumber instead of the whole list of boats
    public int getActionResult(Action[] PlayerOperations, Coordinates coords)
    {
        // TODO (fix) this is not error-proof
        return PlayerOperations[coords.getY()*10+coords.getX()].getOperState();
    }
    
    
    // TODO (fix) the second parameter is useless (redundant)
    // TODO (fix) this method should be private
    /**
     * checks if there is a boat for a specific Player on a given position
     * @param PlayerBoats : table containing the boats of a player
     * @param TableLength : length of the table of boats
     * @param position : coordinates of the position to test
     * @return boolean : true if there is a boat, or false
     */
    public boolean isBoatAt(Boat[] PlayerBoats, int TableLength, Coordinates position)
    {
        // TODO (fix) you can make it more readable
        boolean res = false;
        for(int i = 0; i < TableLength; i++)
            res = res || PlayerBoats[i].isOnPosition(position);
        return res;
    }
    
    /**
     * Returns the state of one case of the boat
     * @param PlayerBoats : Player's boats
     * @param positionCoords : The coordinates of the case to check
     * @return : True if the case of the boat has been touched, else returns false
     */
    // TODO (fix) this method should be private
    // TODO (fix) consider passing a playerNumber instead of the whole list of boats
    public boolean isBoatHitAt(Boat[] PlayerBoats, Coordinates positionCoords)
    {
        boolean res = false;
        int i, index;
        for(i = 0; i < PlayerBoats.length; i++)
        {
            index = PlayerBoats[i].getIndex(positionCoords);
            if(index != -1)
                res = PlayerBoats[i].isHit(index);
                
        }
        return res;
    }
    
    /**
     * Sets the case of a boat on a specific position as Touched
     * @param PlayerBoats the boats table of the Player
     * @param positionCoords the position of the case to set as touched
     */
    // TODO (fix) this method should be private
    // TODO (fix) it is not very clever to pass all boat if you know chat boat has
    // been touched
    public void setBoatHitAt(Boat[] PlayerBoats, Coordinates positionCoords)
    {
        int i, index;
        for(i = 0; i < PlayerBoats.length; i++)
        {
            index = PlayerBoats[i].getIndex(positionCoords);
            if(index != -1)
                PlayerBoats[i].setHit(index);
                
        }
    }
    
    // TODO (fix) this method should be private
    // TODO (fix) consider passing a playerNumber instead of the whole list of boats
    /**
     * returns if all the boats of a player have been sunk, or not
     * @param playerBoats : the boats table of a player
     * @return boolean, true if all the boats have been sunk, else returns false
     */    
    public boolean didPlayerLoose(Boat[] playerBoats)
    {
        boolean lost = true;
        for(int i=0; i< playerBoats.length; i++)
        {
            for(int j=0; j< playerBoats[i].getPositions().length; j++)
            {
                lost = lost && playerBoats[i].isHit(j);
            }
        }
        return lost;
    }
}