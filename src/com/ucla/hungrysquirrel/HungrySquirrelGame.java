/*
This is the main method. 
 */
package com.ucla.hungrysquirrel;

//import java.io.BufferedReader;
import com.ucla.hungrysquirrelgame.Squirrel;
import com.ucla.hungrysquirrelgame.Peanut;
import com.ucla.hungrysquirrelgame.Nuts;
import com.ucla.hungrysquirrelgame.LocationNotAvailableException;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Roger
 */
public class HungrySquirrelGame 
{
    public static void main(String[] args) throws IOException
    {
        Maze game = new Maze("maze.txt");
        Squirrel sq = new Squirrel(game);
        sq.create();
        
        Nuts [] nuts = new Nuts[Nuts.totalNuts];
        
        for (Nuts nut : nuts)
        {
            Random r = new Random(); 
            int choice = r.nextInt(2);
            
            if(choice==0)
            {
                System.out.println("Creating Almond");
                nut = new Almond(game);
                nut.create();
            }
            else
            {
                System.out.println("Creating Peanut");
                nut = new Peanut(game);
                nut.create();
            }
        }
        game.display();
        
         //initialize Scanner.
        Scanner in = new Scanner(System.in);
        String movement;
        
            //As long as no valid position has been provided, keep requesting.
            while (!game.gameOver)
            {
                //Ask user for input
                System.out.println("Enter commands u,d,l,r to move Up, Down, "
                        + "Left, and Right respectively: ");
           if(in.hasNextLine())
           {
                movement = in.nextLine();
            try
            { 
                switch (movement)
                {
                    case "u":
                        sq.moveUp();
                    break;
                    case "d":
                        sq.moveDown();
                    break;
                    case "l":
                        sq.moveLeft();
                    break;
                    case "r":
                        sq.moveRight();
                    break;
                    default:
                    System.out.println("Incorrect command entered");
                }
                game.display();
            }
            catch(LocationNotAvailableException e)
            {
            System.err.println("Position not available. Try again!");

            }    
                          
        }  
            }
    }
        
}
