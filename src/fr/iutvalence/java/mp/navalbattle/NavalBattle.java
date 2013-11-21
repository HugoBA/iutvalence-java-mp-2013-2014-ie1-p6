package fr.iutvalence.java.mp.navalbattle;
import java.util.Scanner; 
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
     * Max size for the game grid (X and Y)
     */
    public final static int GRIDSIZE = 10;
    
    /**
     * table containing the names of the 5 boats of the game
     */
    public final static String[] BOATNAMES = {"aircraft carrier","battleship","submarine","destroyer","patrol boat"};

    /**
     * The players
     */
    private Player[] players;

    /**
     * Constructor which initializes the boats of the two players and the grid
     * of what they have done (Operations)
     * Once created, each player owns a Boats table (initialized as untouched) 
     * and an Action table (initialized as unshot)
     * The method play lets the 2 players one after the other
     * It goes on and on until all the boats of a player are sunk
     * 
     * @param player1Boats
     *            : table containing the boats of Player1
     * @param player2Boats
     *            : table containing the boats of Player2
     */
    public NavalBattle(Boat[] player1Boats, Boat[] player2Boats)
    {
        this.players = new Player[2];
        this.players[0] = new Player(player1Boats);
        this.players[1] = new Player(player2Boats);
    }

    /**
     * launches the game 
     * lets the 2 players play their turn alternatively
     * player2 (=bot) plays randomly and automatically the cases of the naval battle game,
     * player1 plays his own case and the updated game grid is displayed
     * the loop continues until a player wins
     */
    public void play()
    {
        int currentPlayer = PLAYER1;
        
        System.out.println("\nLet's play !\n");
        displayPlayerGrid(PLAYER1);
        while (!(this.players[currentPlayer].didPlayerLoose()))
        {
            /*
            displayPlayerGrid(currentPlayer);
            processPlayerShot(currentPlayer, getRandomFreeCellCoordinatesFromPlayerShotGrid(currentPlayer));

            try
            {
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
            */
            if (currentPlayer == PLAYER1)
            {
                processPlayerShot(currentPlayer, getUserFreeCellCoordinatesToShoot(currentPlayer));
                displayPlayerGrid(currentPlayer);
                currentPlayer = PLAYER2;
            }
            else
            {
                processPlayerShot(currentPlayer, getRandomFreeCellCoordinatesFromPlayerShotGrid(currentPlayer));
                currentPlayer = PLAYER1;
            }
        }
        
        
        System.out.println("\nFinal lay-out : \n");
        displayPlayerGrid(PLAYER1);
        displayPlayerGrid(PLAYER2);
        
        if (this.players[PLAYER1].didPlayerLoose())
            System.out.println("Player 2 won!");
        else
            System.out.println("Player 1 won!");

    }

    /**
     * Method to display the grid game of a specified player on console
     * 
     * @param playerNumber
     *            The number of the player
     */
    private void displayPlayerGrid(int playerNumber)
    {
        int i, j;
        Coordinates position;

        System.out.println("   A B C D E F G H I J        A B C D E F G H I J");
        for (j = 0; j < GRIDSIZE; j++)
        {
            if (j + 1 < GRIDSIZE)
                System.out.print(" " + (j + 1) + " ");
            else
                System.out.print((j + 1) + " ");
            for (i = 0; i < GRIDSIZE; i++)
            {
                position = new Coordinates(i, j);
                if (isBoatOnPlayerBoatGridAt(playerNumber, position))
                {
                    if (isBoatHitAt(playerNumber, position))
                        System.out.print("X ");
                    else
                        System.out.print("O ");
                }
                else
                    System.out.print("¤ ");
            }

            System.out.print("    ");
            if (j + 1 < GRIDSIZE)
                System.out.print(" " + (j + 1) + " ");
            else
                System.out.print((j + 1) + " ");
            for (i = 0; i < GRIDSIZE; i++)
            {
                position = new Coordinates(i, j);
                try
                {
                    Action actionPostion = new Action(position, PositionState.INWATER);
                    if (this.players[playerNumber].getActions().indexOf(actionPostion) == -1)
                        System.out.print("¤ ");
                    else if (getCellStateFromPlayerShotGrid(playerNumber, position) == PositionState.INWATER)
                        System.out.print("+ ");
                    else
                        System.out.print("X ");
                }
                catch(UndefinedCaseStateException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    
    /**
     * Lets the player choose the coordinates to play
     * 
     * @param playerNumber
     *            : The number of the player
     * @return : A non-shot case, chosen by the player
     */
    public Coordinates getUserFreeCellCoordinatesToShoot(int playerNumber)
    {
        Scanner coordEntry = new Scanner(System.in);
        Coordinates userXY;
        String xTemp;
        int x, y;
        Action actionPos;
        do
        {
            System.out.println("Coordinates to shoot :");
            System.out.print("X:");
            xTemp = coordEntry.next();
            if(xTemp.charAt(0) - 96 <0)
                x = (int) xTemp.charAt(0) - 64;
            else
                x = (int) xTemp.charAt(0) - 96;
            System.out.print("Y:");
            y = Integer.parseInt(coordEntry.next());
            System.out.println("You just entered : (" + x + ";" + y + ")\n");
            userXY = new Coordinates(x-1, y-1);
            actionPos = new Action(userXY, PositionState.INWATER);
        }
        while (!(this.players[playerNumber].getActions().indexOf(actionPos) == -1));

        coordEntry.close();
        return actionPos.getCoordinates();
    }
    
    
    
    /**
     * Give a random case that the player have not played yet
     * 
     * @param playerNumber
     *            : The number of the player
     * @return : A random non-shot case
     */
    public Coordinates getRandomFreeCellCoordinatesFromPlayerShotGrid(int playerNumber)
    {
        // TODO (fixed) in Java, you can declare local variable where you
        // use it for the first time
        Random rand = new Random();
        Action randomPos;
        do
        {
            randomPos = new Action(
                    new Coordinates(rand.nextInt(GRIDSIZE), 
                            rand.nextInt(GRIDSIZE)), PositionState.INWATER);
        }
        while (!(this.players[playerNumber].getActions().indexOf(randomPos) == -1));

        return randomPos.getCoordinates();
    }

    /**
     * Method to proceed the action of a player : sets the right state to the
     * played case (1 : water, or 2 : touched) and, if there is a boat on this
     * case, sets the case of the boat as touched
     * 
     * @param playerNumber
     *            : The number of the player
     * @param coordinates
     *            : coordinates of the position concerned by the operation
     */
    private void processPlayerShot(int playerNumber, Coordinates coordinates)
    {
        int otherPlayerNumber;
        if (playerNumber == PLAYER1)
            otherPlayerNumber=PLAYER2;
        else
            otherPlayerNumber=PLAYER1;
        
        PositionState actionState;
        if (isBoatOnPlayerBoatGridAt(otherPlayerNumber, coordinates))
        {
            actionState = PositionState.ONBOAT;
            setBoatHitOnPlayerBoatGridAt(otherPlayerNumber, coordinates);
            didBoatSink(otherPlayerNumber);
        }
        else
            actionState = PositionState.INWATER;

        this.players[playerNumber].getActions().add(new Action(coordinates, actionState));
    }


    /**
     * Simple method to easily get the state of a case
     * 
     * @param playerNumber
     *            : The number of the player
     * @param coords
     *            : coordinates of the position to test
     * @return PositionState : the state of the case
     * @throws UndefinedCaseStateException : throw this exception if you try to access 
     *                  to a case which is not in the list of the player's actions
     */
    private PositionState getCellStateFromPlayerShotGrid(int playerNumber, Coordinates coords) throws UndefinedCaseStateException
    {
        Action actionCoords = new Action(coords, PositionState.INWATER);
        int index = this.players[playerNumber].getActions().indexOf(actionCoords);
        
        if (!(index == -1))
            return this.players[playerNumber].getActions().get(index).getState();
        else 
            throw new UndefinedCaseStateException();
    }

    /**
     * checks if there is a boat for a specific Player on a given position
     * 
     * @param playerNumber
     *            : table containing the boats of a player
     * @param position
     *            : coordinates of the position to test
     * @return boolean : true if there is a boat, or false
     */
    private boolean isBoatOnPlayerBoatGridAt(int playerNumber, Coordinates position)
    {
        for (int i = 0; i < this.players[playerNumber].getBoats().length; i++)
        {
            if(this.players[playerNumber].getBoats()[i].isOnPosition(position))
                return true;
        }

        return false;
    }

    /**
     * Returns the state of one case of the boat
     * 
     * @param playerNumber
     *            : The number of the player
     * @param positionCoords
     *            : The coordinates of the case to check
     * @return : True if the case of the boat has been touched, else returns
     *         false
     */
    private boolean isBoatHitAt(int playerNumber, Coordinates positionCoords)
    {
        BoatCellCoordinates currentPosition;
        for (int i = 0; i < this.players[playerNumber].getBoats().length; i++)
        {
            
            for(int j=0; j< this.players[playerNumber].getBoats()[i].getPositions().length; j++)
            {                
                currentPosition = this.players[playerNumber].getBoats()[i].getPositions()[j];
                
                if(currentPosition.getX() == positionCoords.getX() 
                        && currentPosition.getY() == positionCoords.getY()
                        && currentPosition.isBoatCaseTouched() == true)
                    return true;
            }
        }
        return false;
    }

    /**
     * Sets the case of a boat on a specific position as Touched
     * 
     * @param playerNumber
     *            : The number of the player
     * @param coordinates
     *            the position of the case to set as touched
     */
    private void setBoatHitOnPlayerBoatGridAt(int playerNumber, Coordinates coordinates)
    {
        BoatCellCoordinates currentPosition;
        for (int i = 0; i < this.players[playerNumber].getBoats().length; i++)
        {
            
            for(int j=0; j< this.players[playerNumber].getBoats()[i].getPositions().length; j++)
            {                
                currentPosition = this.players[playerNumber].getBoats()[i].getPositions()[j];
                
                if(currentPosition.getX() == coordinates.getX() && currentPosition.getY() == coordinates.getY())
                    currentPosition.setBoatCaseTouched();
            }
        }
    }
    

    /**
     * method to know if the last shot sunk the entire boat
     * if so, a message is displayed to inform the player
     * @param otherPlayerNumber : the number of the adversary
     */
    private void didBoatSink(int otherPlayerNumber)
    {
        for (int i = 0; i < this.players[otherPlayerNumber].getBoats().length; i++)
        {
            if(this.players[otherPlayerNumber].getBoats()[i].didThisBoatJustSank())
                //TODO the following message shouldn't appear when your adversary kills your boats !
                System.out.println("\nYou just sunk the " + BOATNAMES[i] + " !\n");
        }
    }
}