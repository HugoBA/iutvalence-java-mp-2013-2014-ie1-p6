package fr.iutvalence.java.mp.navalbattle;

import java.util.Random;

/**
 * Represents each boat
 * @author barattoh
 *
 */
public class Boat
{ 
    /**
     * Table which contains every positions of the boat on the grid
     */
    private Coordinates[] boatCases;
    
    /** 
     * Table which contains the state of each cases of the boat
     * False : not touched yet, True : touched
     */
    private boolean[] touchedCases;

    /**
     * Makes the boat : initializes boat positions, and length
     * @param boatCasesIn : table containing the boat coordinates
     */
    public Boat(Coordinates[] boatCasesIn)
    {
       this.boatCases = boatCasesIn;
       this.touchedCases = new boolean[boatCasesIn.length];
       for (int i = 0; i < boatCasesIn.length; i++)
           this.touchedCases[i] = false;
    } 
   
    /**
     * Creates a random boat from the length given in parameter
     * Takes random x and y positions, verifies if the boat is out of bounds or not, according to its size
     * @param length : length of the boat
     */
    public Boat(int length)
    {
        Random rand = new Random();
        int x, y, d, i;
        boolean able = false;
        x = rand.nextInt(10);
        y = rand.nextInt(10);
        Coordinates[] tCases = new Coordinates[length];
        do
        {
            d = rand.nextInt(4);
            switch(d)
            {
                case 0 :
                    able = (x-length >=0);
                    break;
                case 1 :
                    able = (x+length <10);
                    break;
                case 2 :
                    able = (y-length >=0);
                    break;
                case 3 :
                    able = (y+length <10);
                    break;
            }
        }while (!able);
        
        tCases[0] = new Coordinates(x,y);
        
        for(i=1; i< length; i++)
        {
            switch(d)
            {
                case 0 :
                    tCases[i] = new Coordinates(x-i,y);
                    break;
                case 1 :
                    tCases[i] = new Coordinates(x+i,y);
                    break;
                case 2 :
                    tCases[i] = new Coordinates(x,y-i);
                    break;
                case 3 :
                    tCases[i] = new Coordinates(x,y+i);
                    break;
            }
        }
        this.boatCases = tCases;        
        
        this.touchedCases = new boolean[length];
        for (i = 0; i < length; i++)
            this.touchedCases[i] = false;
    }
    
    /**
     * Method that simply returns the position of the boat
     * @return : Table containing all boat positions
     */
    public Coordinates[] getPositions()
    {
        return this.boatCases;
    }
    
    /**
     * gives the index of the case matching with the position
     * @param casePosition : coordinates of the position
     * @return the index if this boat is concerned by the position
     *         else returns -1
     */
    public int getIndex(Coordinates casePosition)
    {
        int j = 0;
        boolean equal = false;
        while (!equal && j < this.boatCases.length)
        {
            equal = this.boatCases[j].equals(casePosition);
            j++;
        }
        
        j--; //We decrement to prevent the j++ at the end of the while loop
        if(equal)
            return j;
        else
            return -1;
    }
    
    
    /**
     * method to check if the boat is concerned by a specific coordinate
     * @param casePos : the coordinates of the case to check
     * @return : true if the boat is present on these coordinates, false if it isn't 
     */  
    public boolean isTaken(Coordinates casePos)
    {
        boolean res = false;
        int i;
        for(i = 0; i < ( this.boatCases.length); i++)
            res = res || (this.boatCases[i].getX() == casePos.getX() && this.boatCases[i].getY() == casePos.getY());
        return res;
    }
    
    /**
     * method to test if a case of the boat has been touched
     * @param caseIndex : the index of the case to check
     * @return true if this case has been touched, else false
     */
    public boolean isTouched(int caseIndex)
    {
        return this.touchedCases[caseIndex];
    }
    
    /**
     * method to set a case of the boat as touched
     * @param indexCase the index of the case to set
     */
    public void setCaseTouched(int indexCase)
    {
        this.touchedCases[indexCase] = true;
    }
}