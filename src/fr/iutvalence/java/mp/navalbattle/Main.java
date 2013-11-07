package fr.iutvalence.java.mp.navalbattle;

/**
 * main class for the naval battle game
 * 
 * @author barattoh
 */

public class Main
{
    /**
     * Main method, sets the settings of the players and launches the games
     * 
     * @param args
     *            : none
     */
    public static void main(String[] args)
    {
        Boat[] boatsP1, boatsP2;
        boatsP1 = createRandomBoats();
        boatsP2 = createRandomBoats();
        NavalBattle game = new NavalBattle(boatsP1, boatsP2);
        game.play();
        
    }

    /**
     * method to make a table of random boats
     * 
     * @return table of boats, randomly created
     */
    public static Boat[] createRandomBoats()
    {
        int[] BoatLengths = { 5, 4, 3, 3, 2 };
        int i = 0, newX, newY;
        boolean isBusy;
        BoatCellCoordinates position;

        Boat[] newBoat = new Boat[5];

        while (i < BoatLengths.length)
        {
            newBoat[i] = new Boat(BoatLengths[i]);
            isBusy = false;
            for (int i2 = 0; i2 < BoatLengths[i]; i2++)
            {
                newX = newBoat[i].getPositions()[i2].getX();
                newY = newBoat[i].getPositions()[i2].getY();
                position = new BoatCellCoordinates(newX, newY);
                isBusy = isBusy | checkPosBoat(newBoat, i, position);
            }
            if (!isBusy)
                i++;

        }
        return newBoat;
    }

    /**
     * checks if there is a boat for a specific Player on a given position
     * 
     * @param PlayerBoats
     *            : table containing the boats of a player
     * @param TableLength
     *            : length of the table of boats
     * @param positionCoords
     *            : coordinates of the position to test
     * @return boolean : true if there is a boat, or false
     */
    public static boolean checkPosBoat(Boat[] PlayerBoats, int TableLength, Coordinates positionCoords)
    {
        boolean res = false;
        int i;
        for (i = 0; i < TableLength; i++)
            res = res || PlayerBoats[i].isOnPosition(positionCoords);
        return res;
    }

}