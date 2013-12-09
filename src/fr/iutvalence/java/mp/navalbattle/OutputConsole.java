package fr.iutvalence.java.mp.navalbattle;

/**
 * Method that extends Output to handle the displays in the console
 */
public class OutputConsole implements Output
{
    /**
     * Simple display method on the console
     * @param x : The string to display
     */
    public void display(String x)
    {
        System.out.println(x);
    }
    
    /**
     * Method to display the grid game of a specified player on console
     * @param positions : table containing all the boats of the player (and empty cells)
     * @param actions : table containing all the actions of the players
     */
    public void displayPlayerGrid(String[][] positions, String[][] actions)
    {
        int i, j;
        
        for (i = 0; i < NavalBattle.GRID_SIZE + 1; i++)
        {            
            for (j = 0; j < NavalBattle.GRID_SIZE + 1; j++)
                System.out.print(positions[i][j]+" ");
            System.out.print("     ");
            for (j = 0; j < NavalBattle.GRID_SIZE + 1; j++)
                System.out.print(actions[i][j]+" ");
            System.out.println("");
        }
        System.out.println("");
    } 
}
