package fr.iutvalence.java.mp.navalbattle;
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
    // TODO (fix) you should extract the code related to the positioning of boats
    // to another class
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


    /* 
     * TODO (fix) Replace Scanner by using a Stream
     * (WARNING) Do not close System.in stream 
     * (NOTE) Use the split method of a string (cut on a space)
     *        Cut the String into two strings
     *        For example : "A 10".split(' ') 
     *          --> Return an array containing "A" & "10"
     *        Check the length
     */
    //TODO (fix) Check directions : a boat can be out of the grid
    //TODO (fix) Check collisions : a boat can be on an other
    /**
     * method to make a table of boats, created manually by the user
     * 
     * @return the table of boats just created
     */
    public static Boat[] createUserBoats()
    {
        int[] BoatLengths = { 5, 4, 3, 3, 2 };
        Scanner coordEntry = new Scanner(System.in);
        int x, direction = 0;
        String xTemp, yTemp, directionTemp;
        Coordinates extremityPosition;
        Boat[] newBoat = new Boat[5];

        for(int i=0; i < BoatLengths.length; i++)
        {
            System.out.println("\nFirst extremity of the "+NavalBattle.BOAT_NAMES[i]+" (length "+BoatLengths[i]+") :");
            do
            {
                System.out.print("X:");
                xTemp = coordEntry.next();
            
                if(xTemp.charAt(0) - 96 <0)
                    x = (int) xTemp.charAt(0) - 64;
                else
                    x = (int) xTemp.charAt(0) - 96;
                if (x < 1 || x > NavalBattle.GRID_SIZE)
                    System.out.println("Invalid X. Try again !");
            }while (x < 1 || x > NavalBattle.GRID_SIZE);
            
            int y = 0;
            
            do
            {
                y = 0;
                System.out.print("Y:");
                yTemp = coordEntry.next();
                for (int i1 = 0; i1 < yTemp.length(); i1++)
                {
                    y += (int) (yTemp.toCharArray()[i1]-48)*Math.pow(10, yTemp.length()-i1-1);
                }
                if (y < 1 || y > NavalBattle.GRID_SIZE)
                    System.out.println("Invalid Y. Try again !");
            }
            while (y < 1 || y > NavalBattle.GRID_SIZE);
            
            System.out.println("You just entered : (" + x + ";" + y + ")");
            
            System.out.println("Now choose a direction:");
            System.out.println(Boat.LEFT+": to the left");
            System.out.println(Boat.RIGHT+": to the right");
            System.out.println(Boat.UP+": to the top");
            System.out.println(Boat.DOWN+": to the bottom");

            boolean invalid;
            do
            {
                invalid = false;
                System.out.println("Direction : ");
                directionTemp = coordEntry.next();
                if (directionTemp.length() > 1)
                {
                    System.out.println("Invalid direction. Try again (string size) !");
                    invalid = true;
                }
                else
                {
                    direction = (int) directionTemp.charAt(0)-48;
                    if (direction < 0 || direction > Boat.NB_DIRECTIONS)
                    {
                        System.out.println("Invalid direction. Try again !");
                        invalid = true;
                    }
                }
            }while(invalid);
                
            
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