/*
This class manages the maze used by this game. It can read an input file and 
copy the maze design to a local multidimensional array. It also communicates 
with other classes to allow or forbid entities to be placed inside.
 */
package com.ucla.hungrysquirrel;

//------------------------------ Imports ---------------------------------------

import com.ucla.hungrysquirrelgame.Wall;
import com.ucla.hungrysquirrelgame.LocationNotAvailableException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Roger Carvalho
 */
public class Maze 
{
//------------------------------ Attributes ------------------------------------
    /*
    Static constants, will not change throughout game and can be reused by other
    classes. 
    */
    public static final int MaxMazeRow = 20;
    public static final int MaxMazeColumn = 50;
    public final static char WallSymbol = '*';
    
    //public boolean, any class can end the game by setting this to true
    public boolean gameOver = false;

    private Entity [][] maze = new Entity[MaxMazeRow][MaxMazeColumn];

//------------------------------ Constructors-----------------------------------
 
    public Maze(String filename)
    {
        //initialize array
        create(filename);
       }
    
//------------------------------ Behaviors ------------------------------------

    public boolean available(int row, int col)
    /*
    This method will take a row and a column as arguments and determine if 
    the space in the row and the column is empty (i.e. blank space ‘ ‘). If it 
    is empty, it returns true; otherwise, it returns false.
    */
    {
        return maze[row][col] == null;
    }
//------------------------------------------------------------------------------
    public void create(String fileName)
    /*
    This method will read the Maze.txt file and initialize the 2-dimentional 
    array with the maze structure defined in the file.
    */
    {
        //Initialize variables
        String row;
        int rowNum = 0;
        FileReader fileReader;
        BufferedReader bufferedReader;

        try 
        {
            //Open the file and buffered stream
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            
            /*
            For each row of text in the document (up to the maximum amount
            of rows), copy contents to the array 
            */
            while((row = bufferedReader.readLine()) != null && rowNum < 
                    this.MaxMazeRow) 
            {
                //Fill the array with the contents of the document
                for (int i = 0; i < MaxMazeColumn; i++)
                {               
                    //only read up to the max column in the text document.
                    if (i <= row.length())
                    {
                        if (row.charAt(i) == WallSymbol)
                        {
                            Entity wall = new Wall(this);
                            wall.create();
                            maze[rowNum][i] = wall;
                        }
                    }
                }
                rowNum++;
            } 
            
            //Close the file.
            bufferedReader.close(); 
          }
        
        catch(FileNotFoundException ex) 
        {
            System.out.println("Unable to open file '" + fileName + "'");
            System.exit(-1);
        }
        
        catch(IOException ex) 
        {
            System.out.println("Error reading file '" + fileName + "'"); 
            System.exit(-1);
        }
    }
 //-----------------------------------------------------------------------------
    public void display()
    /*
    This method will display the maze structure and the containing entities on 
    the console.
    */
    {   
        //For each row, print all columns of characters
        for (int i=0; i < this.MaxMazeRow; i++)
        {
            for (Entity wall : maze[i])
            {
                System.out.print(wall);
            }
            //Move on to the next line
            System.out.print("\n");
        }
    }
    
    public void clear(int row, int col)
    {
        maze[row][col] = null;
    }
    
//------------------------------ Getters/Setters--------------------------------
        
    public Entity get(int row, int col)
    /*
    This method will display the character stored in a given position of the 
    maze.
    */
              
    {
        return maze[row][col];
    }
//-----------------------------------------------------------------------------  
    public boolean set (int row, int col, Entity entity)  
            throws LocationNotAvailableException
    /*
    This method sets the character in a given position of the maze. Setting the
    character is only permitted when there is no wall currently occupying the
    position.
    */
   
    {
        if (row < this.MaxMazeRow && col < this.MaxMazeColumn)
            if (available(row, col))
            {
                maze[row][col] = entity;
                return true;
            }
            else
            {
                if (!(this.get(row, col) instanceof Wall))
                {
                    return false;
                }
                else
                {
                    throw new LocationNotAvailableException(row, col);
                }
            }
        else {
            throw new LocationNotAvailableException(row, col);
        }
    }  
}