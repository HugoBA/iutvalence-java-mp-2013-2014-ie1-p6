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
     * the players
     */
    private Player[] players;

    // TODO (fix) detail comment (how is the game once created?)
    /**
     * Constructor which initializes the boats of the two players and the grid
     * of what they have done (Operations)
     * 
     * @param player1Boats
     *            : table containing the boats of Player1
     * @param player2Boats
     *            : table containing the boats of Player2
     */
    public NavalBattle(Boat[] player1Boats, Boat[] player2Boats)
    {
        // TODO (fix) avoird using a temp variable
        Player[] tempPlayers = new Player[2];
        tempPlayers[0] = new Player(player1Boats);
        tempPlayers[1] = new Player(player2Boats);
        this.players = tempPlayers;
    }

    /**
     * launches the game plays randomly the cases of the naval battle game,
     * until a player wins
     */
    public void play()
    {

        // TODO (fix) consider that the loop allows only the current player to
        // play
        // here, it is a player1+player2 loop
        while (!(this.players[0].didPlayerLoose() || this.players[1].didPlayerLoose()))
        {

            playRandom(PLAYER1);
            playRandom(PLAYER2);

            displayPlayerGrid(PLAYER1);
            displayPlayerGrid(PLAYER2);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
        if (this.players[1].didPlayerLoose())
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
        for (j = 0; j < 10; j++)
        {
            if (j + 1 < 10)
                System.out.print(" " + (j + 1) + " ");
            else
                System.out.print((j + 1) + " ");
            for (i = 0; i < 10; i++)
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
            if (j + 1 < 10)
                System.out.print(" " + (j + 1) + " ");
            else
                System.out.print((j + 1) + " ");
            for (i = 0; i < 10; i++)
            {
                position = new Coordinates(i, j);
                if (getCellStateFromPlayerShotGrid(playerNumber, position) == 0)
                    System.out.print("¤ ");
                else if (getCellStateFromPlayerShotGrid(playerNumber, position) == 1)
                    System.out.print("+ ");
                else
                    System.out.print("X ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * plays a random case which hasn't been played yet
     * 
     * @param playerNumber
     *            : The number of the player
     */
    // TODO (fix) call processAction from the play method
    private void playRandom(int playerNumber)
    {
        int otherPlayerNumber;

        if (playerNumber == PLAYER1)
            otherPlayerNumber = PLAYER2;
        else
            otherPlayerNumber = PLAYER1;

        processPlayerShot(playerNumber, otherPlayerNumber, getRandomFreeCellCoordinatesFromPlayerShotGrid(playerNumber));
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
        // TODO (fix) in Java, you can declare local variable where you
        // use it for the first time
        Random rand = new Random();
        int x, y;
        Coordinates randomPos;
        do
        {
            x = rand.nextInt(10);
            y = rand.nextInt(10);
            randomPos = new Coordinates(x, y);
        }
        // TODO (fix) declare hard-coded values as constant (0)
        while (!(getCellStateFromPlayerShotGrid(playerNumber, randomPos) == 0));

        return randomPos;
    }

    /**
     * Method to proceed the action of a player : sets the right state to the
     * played case (1 : water, or 2 : touched) and, if there is a boat on this
     * case, sets the case of the boat as touched
     * 
     * @param playerNumber
     *            : The number of the player
     * @param otherPlayerNumber
     *            : the number of his adversary
     * @param coordinates
     *            : coordinates of the position concerned by the operation
     */
    private void processPlayerShot(int playerNumber, int otherPlayerNumber, Coordinates coordinates)
    {
        // TODO (fix) rename variable (more explicit)
        int operState = 0;
        if (isBoatOnPlayerBoatGridAt(otherPlayerNumber, coordinates))
        {
            // TODO (fix) declare hard-coded values as constants
            operState = 2;
            
            // TODO (fix) fix the following test, it is dirty
            if (!isBoatHitAt(otherPlayerNumber, coordinates))
                ;
            setBoatHitOnPlayerBoatGridAt(otherPlayerNumber, coordinates);
        }
        else
            // TODO (fix) declare hard-coded values as constants
            operState = 1;

        // TODO (fix) declare hard-coded values as constants
        this.players[playerNumber].actions[coordinates.getY() * 10 + coordinates.getX()].setState(operState);
    }

    /**
     * simple method to easily get the state of a case
     * 
     * @param playerNumber
     *            : The number of the player
     * @param coords
     *            : coordinates of the position to test
     * @return integer : 0 this position hasn't been played yet 1 the position
     *         has been played but water 2 the position has been played and
     *         touched a boat
     */
    private int getCellStateFromPlayerShotGrid(int playerNumber, Coordinates coords)
    {
        // TODO (fix) this is not error-proof
        return this.players[playerNumber].actions[coords.getY() * 10 + coords.getX()].getState();
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
        // TODO (fix) you can make it more readable
        boolean res = false;

        for (int i = 0; i < this.players[playerNumber].getBoats().length; i++)
            res = res || this.players[playerNumber].getBoats()[i].isOnPosition(position);

        return res;
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
        boolean res = false;
        int i, index;
        for (i = 0; i < this.players[playerNumber].getBoats().length; i++)
        {
            index = this.players[playerNumber].getBoats()[i].getIndex(positionCoords);
            if (index != -1)
                res = this.players[playerNumber].getBoats()[i].isHit(index);

        }
        return res;
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
        int i, index;
        for (i = 0; i < this.players[playerNumber].getBoats().length; i++)
        {
            index = this.players[playerNumber].getBoats()[i].getIndex(coordinates);
            if (index != -1)
                this.players[playerNumber].getBoats()[i].setHit(index);
        }
    }
}