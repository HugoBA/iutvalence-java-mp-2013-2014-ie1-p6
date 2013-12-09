package fr.iutvalence.java.mp.navalbattle;

/**
 * 
 * @author barattoh
 * Class that handles all the displays for the game
 */
public interface Output
{    
    /**
     * Simple display method
     * @param x : The string to display
     */
    public abstract void display(String x);
    
    /**
     * Method to display the grid game of a specified player
     * @param positions : table containing all the boats of the player (and empty cells)
     * @param actions : table containing all the actions of the players
     */
    public abstract void displayPlayerGrid(String[][] positions, String[][] actions);
}
