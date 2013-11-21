package fr.iutvalence.java.mp.navalbattle;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner; 

/**
 * main class for the naval battle game
 * 
 * @author barattoh
 */

public class Main
{
    /**
     * Main method, sets the settings of the players and launches the games
     * Asks the user if he wants to create his boats himself, or if he lets the computer handle it for him
     * @param args
     *            : none
     */
    public static void main(String[] args)
    {
        Boat[] boatsP1, boatsP2;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Do you want to create your own boats ? [yes]");
        if(!scanner.next().equals("yes"))
            boatsP1 = createRandomBoats();
        else
            boatsP1 = createUserBoats();
        
        boatsP2 = createRandomBoats();
        NavalBattle game = new NavalBattle(boatsP1, boatsP2);
        game.play();
        scanner.close();
        
    }


    
    /**
     * method to make a table of boats, created manually by the user
     * 
     * @return the table of boats just created
     */
    public static Boat[] createUserBoats()
    {
        int[] BoatLengths = { 5, 4, 3, 3, 2 };
        Scanner coordEntry = new Scanner(System.in);
        int x, y;
        String xTemp, yTemp, directionTemp;
        Coordinates extremityPosition;
        Boat[] newBoat = new Boat[5];

        for(int i=0; i < BoatLengths.length; i++)
        {
            System.out.println("\nFirst extremity of the "+NavalBattle.BOATNAMES[i]+" (length "+BoatLengths[i]+") :");
            do
            {
                System.out.print("X:");
                xTemp = coordEntry.next();
            
                if(xTemp.charAt(0) - 96 <0)
                    x = (int) xTemp.charAt(0) - 64;
                else
                    x = (int) xTemp.charAt(0) - 96;
                if (x < 1 || x > NavalBattle.GRIDSIZE)
                    System.out.println("Invalid X. Try again !");
            }while (x < 1 || x > NavalBattle.GRIDSIZE);
            
            do
            {
                System.out.print("Y:");
                yTemp = coordEntry.next();
                y = (int) yTemp.charAt(0)-48;
                if (y < 1 || y > NavalBattle.GRIDSIZE)
                    System.out.println("Invalid Y. Try again !");
            }
            while (y < 1 || y > NavalBattle.GRIDSIZE);
            
            System.out.println("You just entered : (" + x + ";" + y + ")");
            
            System.out.println("Now choose a direction:");
            System.out.println(Boat.LEFT+": to the left");
            System.out.println(Boat.RIGHT+": to the right");
            System.out.println(Boat.UP+": to the top");
            System.out.println(Boat.DOWN+": to the bottom");

            //TODO (fix) not error-proof
            int direction = coordEntry.nextInt();
                
            
            extremityPosition = new Coordinates(x-1, y-1);
            newBoat[i] = new Boat(extremityPosition, direction, BoatLengths[i]);
        }
        coordEntry.close();
        return newBoat;
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