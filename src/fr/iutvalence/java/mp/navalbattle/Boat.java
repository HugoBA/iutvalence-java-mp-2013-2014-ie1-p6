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
    private Case[] boatCases;
    
    /** 
     * Table which contains the state of each cases of the boat
     * False : not touched yet, True : touched
     */
    public boolean[] touchedCases;

    /**
     * Makes the boat : initializes boat positions, and length
     * @param boatCasesIn : table containing the boat coordinates
     */
    public Boat(Case[] boatCasesIn)
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
        Case[] tCases = new Case[length];
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
        
        tCases[0] = new Case(x,y);
        
        for(i=1; i< length; i++)
        {
            switch(d)
            {
                case 0 :
                    tCases[i] = new Case(x-i,y);
                    break;
                case 1 :
                    tCases[i] = new Case(x+i,y);
                    break;
                case 2 :
                    tCases[i] = new Case(x,y-i);
                    break;
                case 3 :
                    tCases[i] = new Case(x,y+i);
                    break;
            }
        }
        this.boatCases = tCases;        
    }
    
    /**
     * Method that simply returns the position of the boat
     * @return : Table containing all boat positions
     */
    public Case[] getPositions()
    {
        return this.boatCases;
    }
    
    /**
     * method to check if the boat is concerned by a specific coordinate
     * @param Xin : X coord
     * @param Yin : Y coord
     * @return : true if the boat is present on these coordinates, false if it isn't 
     */
    // TODO (fix) this method should take a coordinates (Case) object as parameter
    // TODO (fix) rename method (more explicit)
    public boolean isFull(int Xin,int Yin)
    {
        boolean res = false;
        int i;
        for(i = 0; i < ( this.boatCases.length); i++)
            res = res || (this.boatCases[i].getX() == Xin && this.boatCases[i].getY() == Yin);
        return res;
    }
}
