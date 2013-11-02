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
     * list of positions occupied by the boat
     */
    private Coordinates[] positions;
    
    /** 
     * Table which contains the state of each position occupied by the boat
     * False : not touched yet, True : touched
     */
    private boolean[] touchedPositions;

    // TODO (fixed) rewrite comment
    /**
     * Create a boat by giving its coordinates
     * @param positions : table containing the boat coordinates
     */
    public Boat(Coordinates[] positions)
    {
       this.positions = positions;
       this.touchedPositions = new boolean[positions.length];
       for (int i = 0; i < positions.length; i++)
           this.touchedPositions[i] = false;
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
        this.positions = tCases;        
        
        this.touchedPositions = new boolean[length];
        for (i = 0; i < length; i++)
            this.touchedPositions[i] = false;
    }
    
    /**
     * Method that simply returns the position of the boat
     * @return : Table containing all boat positions
     */
    public Coordinates[] getPositions()
    {
        return this.positions;
    }
    
    /**
     * gives the index of the case matching with the position
     * @param casePosition : coordinates of the position
     * @return the index if this boat is concerned by the position
     *         else returns -1
     */
    public int getIndex(Coordinates casePosition)
    {
        // TODO (fix) simplify this (you can)
        int j = 0;
        boolean equal = false;
        while (!equal && j < this.positions.length)
        {
            equal = this.positions[j].equals(casePosition);
            j++;
        }
        
        j--; //We decrement to prevent the j++ at the end of the while loop
        if(this.positions[j].equals(casePosition))
            return j;
        else
            return -1;
    }
    
    
    /**
     * method to check if the boat is concerned by a specific coordinate
     * @param position : the coordinates of the case to check
     * @return : true if the boat is present on these coordinates, false if it isn't 
     */  
    public boolean isOnPosition(Coordinates position)
    {
        boolean res = false;
        // TODO (fixed) simplify by using Coordinates#equals !
        for(int i = 0; i < ( this.positions.length); i++)
            res = res || (this.positions[i].equals(position));
        return res;
    }
    
    /**
     * method to test if a case of the boat has been touched
     * @param index : the index of the case to check
     * @return true if this case has been touched, else false
     */
    public boolean isHit(int index)
    {
        return this.touchedPositions[index];
    }
    
    /**
     * method to set a case of the boat as touched
     * @param index the index of the case to set
     */
    public void setHit(int index)
    {
        this.touchedPositions[index] = true;
    }
}