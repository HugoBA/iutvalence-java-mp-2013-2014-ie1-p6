package fr.iutvalence.java.mp.navalbattle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * main class for the naval battle game
 * 
 * @author barattoh
 */

public class Main
{
    static OutputConsole Output = new OutputConsole();
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
        
        Output.display("Do you want to create your own boats ? [yes]");
        if(!inputDataFromKeyboard().equals("yes"))
            boatsP1 = createRandomBoats();
        else
            boatsP1 = createUserBoats();
        
        boatsP2 = createRandomBoats();
        NavalBattle game = new NavalBattle(boatsP1, boatsP2, "console");
        game.play();     
    }


    /* 
     * TODO (fixed) Replace Scanner by using a Stream
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

        int x, y=0, direction = 0;
        String coordTemp;
        Coordinates extremityPosition;
        Boat[] newBoat = new Boat[5];


        for(int i=0; i < BoatLengths.length; i++)
        {
            Output.display("\nFirst extremity of the "+NavalBattle.BOAT_NAMES[i]+" (length "+BoatLengths[i]+") :");
            do
            {
                coordTemp = inputDataFromKeyboard();
                y = x = -1; 
            
                if(coordTemp.split(" ")[0].charAt(0) - 96 <0)
                    x = (int) coordTemp.split(" ")[0].charAt(0) - 64;
                else
                    x = (int) coordTemp.split(" ")[0].charAt(0) - 96;
                if (x < 1 || x > NavalBattle.GRID_SIZE)
                    Output.display("Invalid X. Try again !");
                
                try 
                {
                    y = Integer.parseInt(coordTemp.split(" ")[1]);
                }
                catch (NumberFormatException e)
                {
                    Output.display("Don't try to enter a number instead of a letter");
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    Output.display("You should separate the coordinates by a space");
                }
                
            }while (x < 1 || x > NavalBattle.GRID_SIZE || y < 1 || y > NavalBattle.GRID_SIZE);
            
            
            Output.display("You just entered : (" + x + ";" + y + ")");
            
            Output.display("Now choose a direction:");
            Output.display(Boat.LEFT+": to the left");
            Output.display(Boat.RIGHT+": to the right");
            Output.display(Boat.UP+": to the top");
            Output.display(Boat.DOWN+": to the bottom");

            boolean invalid;
            do
            {
                invalid = false;
                Output.display("Direction : ");
                try 
                {
                    direction = Integer.parseInt(inputDataFromKeyboard());
                }
                catch (NumberFormatException e)
                {
                    Output.display("Invalid direction. Try again !");
                    invalid = true;
                }
                
                if (direction < 0 || direction > Boat.NB_DIRECTIONS-1)
                {
                    Output.display("Invalid direction. Try again !");
                    invalid = true;
                }
            } while(invalid);
                
            
            extremityPosition = new Coordinates(x-1, y-1);
            newBoat[i] = new Boat(extremityPosition, direction, BoatLengths[i]);
        }
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
    
    /**
     * Method to get a string from keyboard entry
     * @return String : what the user entered
     */
    public static String inputDataFromKeyboard()
    {
        InputStreamReader keyboardIn = null;
        try
        {
            keyboardIn = new InputStreamReader(System.in, "UTF-8");
        }
        catch (UnsupportedEncodingException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        BufferedReader br = new BufferedReader(keyboardIn);
        String returnString = null;
        try
        {
            returnString = br.readLine();
        }
        catch (IOException e) 
        {
            Output.display("Input error...");
        }
        return returnString;
     }

}