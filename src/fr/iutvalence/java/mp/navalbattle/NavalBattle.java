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
    public final static int PLAYER_1 = 0;

    /**
     * Value for Player2
     */
    public final static int PLAYER_2 = 1;
    
    /**
     * Max size for the game grid (X and Y)
     */
    public final static int GRID_SIZE = 10;
    
    /**
     * table containing the names of the 5 boats of the game
     */
    public final static String[] BOAT_NAMES = {"aircraft carrier",
        "battleship","submarine","destroyer","patrol boat"};

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
    public NavalBattle(Boat[] player1Boats, Boat[] player2Boats, String displayMode)
    {
        this.players = new Player[2];
        this.players[0] = new Player(player1Boats, displayMode);
        this.players[1] = new Player(player2Boats, displayMode);
    }

    /**Output
     * launches the game 
     * lets the 2 players play their turn alternatively
     * player2 (=bot) plays randomly and automatically the cells of the naval battle game,
     * player1 plays his own cell and the updated game grid is displayed
     * the loop continues until a player wins
     */
    public void play()
    {
        int currentPlayer = PLAYER_1;
        
        Output.display("\nLet's play !\n");
        displayPlayerGrid(PLAYER_1);
        while (!(this.players[currentPlayer].didPlayerLoose()))
        {
            processPlayerShot(currentPlayer, getRandomFreeCellCoordinatesFromPlayerShotGrid(currentPlayer));
            if (currentPlayer == PLAYER_1)
                displayPlayerGrid(currentPlayer);
            currentPlayer = changePlayer(currentPlayer);
        }
        
        
        Output.display("\nFinal lay-out : \n");
        displayPlayerGrid(PLAYER_1);
        displayPlayerGrid(PLAYER_2);
        
        if (this.players[PLAYER_1].didPlayerLoose())
            Output.display("Player 2 won!");
        else
            Output.display("Player 1 won!");

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
        String[][] boatsPositions = new String[GRID_SIZE+1][GRID_SIZE+1];
        String[][] actionsTable = new String[GRID_SIZE+1][GRID_SIZE+1];
        Coordinates position;
        
        boatsPositions[0][0] = "  ";
        actionsTable[0][0] = "  ";
        
        for (i=0;i< GRID_SIZE; i++)
        {
            boatsPositions[0][i+1] = (char) (i+65)+"";
            actionsTable[0][i+1] = (char) (i+65)+"";
        }
        
        for (j = 0; j < GRID_SIZE; j++)
        {
            if (j < 9)
            {
                boatsPositions[j+1][0] = String.valueOf(j+1) + " ";
                actionsTable[j+1][0] = String.valueOf(j+1) + " ";
            }
            else
            {
                boatsPositions[j+1][0] = String.valueOf(j+1);
                actionsTable[j+1][0] = String.valueOf(j+1);
            }
        }
        
        for (j = 1; j < GRID_SIZE+1; j++)
        {
            for (i = 1; i < GRID_SIZE+1; i++)
            {
                position = new Coordinates(i - 1, j - 1);
                if (isBoatOnPlayerBoatGridAt(playerNumber, position))
                {
                    if (isBoatHitAt(playerNumber, position))
                        boatsPositions[i][j] = "X";
                    else
                        boatsPositions[i][j] = "O";
                }
                else
                    boatsPositions[i][j] = "¤";
                
                try
                {
                    Shot actionPostion = new Shot(position, ShotResult.IN_WATER);
                    if (this.players[playerNumber].getShots().indexOf(actionPostion) == -1)
                        actionsTable[i][j] = "¤";
                    else if (getCellStateFromPlayerShotGrid(playerNumber, position) == ShotResult.IN_WATER)
                        actionsTable[i][j] = "+";
                    else
                        actionsTable[i][j] = "X";
                }
                catch(InvalidCoordinatesException e)
                {
                    e.printStackTrace();
                }
            }
        }
        Output.displayPlayerGrid(boatsPositions, actionsTable);
    }

    
    /**
     * Lets the player choose the coordinates to play
     * 
     * @param playerNumber
     *            : The number of the player
     * @return : A non-shot cell, chosen by the player
     */
    // TODO (fixed) this should not be public
    private Coordinates getUserFreeCellCoordinatesToShoot(int playerNumber)
    {
        Scanner coordEntry = new Scanner(System.in);
        Coordinates userXY;
        String xTemp;
        int x, y;
        Shot actionPos;
        do
        {
            Output.display("Coordinates to shoot :");
            System.out.print("X:");
            xTemp = coordEntry.next();
            if(xTemp.charAt(0) - 96 <0)
                x = (int) xTemp.charAt(0) - 64;
            else
                x = (int) xTemp.charAt(0) - 96;
            System.out.print("Y:");
            y = Integer.parseInt(coordEntry.next());
            Output.display("You just entered : (" + x + ";" + y + ")\n");
            userXY = new Coordinates(x-1, y-1);
            actionPos = new Shot(userXY, ShotResult.IN_WATER);
        }
        while (!(this.players[playerNumber].getShots().indexOf(actionPos) == -1));

        coordEntry.close();
        return actionPos.getCoordinates();
    }
    
    
    
    /**
     * Give a random cell that the player have not played yet
     * 
     * @param playerNumber
     *            : The number of the player
     * @return : A random non-shot cell
     */
    // TODO (fixed) this should not be public
    private Coordinates getRandomFreeCellCoordinatesFromPlayerShotGrid(int playerNumber)
    {
        Random rand = new Random();
        Shot randomPos;
        do
        {
            randomPos = new Shot(
                    new Coordinates(rand.nextInt(GRID_SIZE), 
                            rand.nextInt(GRID_SIZE)), ShotResult.IN_WATER);
        }
        while (!(this.players[playerNumber].getShots().indexOf(randomPos) == -1));

        return randomPos.getCoordinates();
    }

    /**
     * Method to proceed the action of a player : sets the right state to the
     * played cell (1 : water, or 2 : touched) and, if there is a boat on this
     * cell, sets the cell of the boat as touched
     * 
     * @param playerNumber
     *            : The number of the player
     * @param coordinates
     *            : coordinates of the position concerned by the operation
     */
    private void processPlayerShot(int playerNumber, Coordinates coordinates)
    {
        int otherPlayerNumber;
        if (playerNumber == PLAYER_1)
            otherPlayerNumber=PLAYER_2;
        else
            otherPlayerNumber=PLAYER_1;
        
        ShotResult actionState;
        if (isBoatOnPlayerBoatGridAt(otherPlayerNumber, coordinates))
        {
            actionState = ShotResult.TOUCHED;
            setBoatHitOnPlayerBoatGridAt(otherPlayerNumber, coordinates);
            didBoatSink(otherPlayerNumber);
        }
        else
            actionState = ShotResult.IN_WATER;

        this.players[playerNumber].getShots().add(new Shot(coordinates, actionState));
    }


    /**
     * Simple method to easily get the state of a cell
     * 
     * @param playerNumber
     *            : The number of the player
     * @param coords
     *            : coordinates of the position to test
     * @return PositionState : the state of the cell
     * @throws InvalidCoordinatesException : throw this exception if you try to access 
     *                  to a cell which is not in the list of the player's actions
     */
    private ShotResult getCellStateFromPlayerShotGrid(int playerNumber, Coordinates coords) throws InvalidCoordinatesException
    {
        Shot actionCoords = new Shot(coords, ShotResult.IN_WATER);
        int index = this.players[playerNumber].getShots().indexOf(actionCoords);
        
        if (!(index == -1))
            return this.players[playerNumber].getShots().get(index).getState();
        else 
            throw new InvalidCoordinatesException();
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
     * Returns the state of one cell of the boat
     * 
     * @param playerNumber
     *            : The number of the player
     * @param positionCoords
     *            : The coordinates of the cell to check
     * @return : True if the cell of the boat has been touched, else returns
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
                        && currentPosition.isBoatCellTouched() == true)
                    return true;
            }
        }
        return false;
    }

    /**
     * Sets the cell of a boat on a specific position as Touched
     * 
     * @param playerNumber
     *            : The number of the player
     * @param coordinates
     *            the position of the cell to set as touched
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
                    currentPosition.setBoatCellTouched();
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
                //TODO (fix) the following message shouldn't appear when your adversary kills your boats !
                Output.display("\nYou just sunk the " + BOAT_NAMES[i] + " !\n");
        }
    }
    
    /**
     * A method to change the current player 
     * @param player : The current player
     * @return : The other player ID
     */
    // TODO (fixed) this should not be public
    private int changePlayer(int player)
    {
        if (player == PLAYER_1)
            return PLAYER_2;
        else
            return PLAYER_1;
    }
}