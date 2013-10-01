package fr.iutvalence.java.mp.navalbattle;
import java.util.Scanner;

/**
 * Class which represents a naval battle game
 */
public class NavalBattle
{
    // TODO (fixed) declare something in this class, else all other classes will be removed
    /**
     * Main method of the naval battle game, used as a testing method at the moment
     * @param args : unused
     */
    public static void main(String[] args)
    {  
        /* INPUT TEST
        Scanner sc = new Scanner(System.in);
        System.out.print("First boat coordinates :");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        System.out.println("Entered coords : " + x1 + "," + y1);
        */
        
        Case[] boatCases = new Case[3];
        boatCases[0] = new Case(2,3);
        boatCases[1] = new Case(2,4);
        boatCases[2] = new Case(2,5);
        Boat myBoat = new Boat(boatCases);
        int i;
        for(i=0; i < 3; i++)
        {
            System.out.println("X : "+myBoat.getPosition()[i].getX()+" Y : "+myBoat.getPosition()[i].getY()+" Etat : "+myBoat.touchedCases[i]);
        }
      
    }
    
}
