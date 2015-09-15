/*
This class manages Entity objects. Entities occupy a space in a given maze.
They have an X position (column number) and an Y position (row number). Any
class that extends Entity should implement a 'create' method and set the symbol
attribute.
 */
package com.ucla.hungrysquirrel;

import com.ucla.hungrysquirrelgame.LocationNotAvailableException;

/**
 *
 * @author Roger Carvalho
 */
public abstract class Entity 
{
//------------------------------ Attributes ------------------------------------
    public Maze maze;
    //This points to the game's maze.
        
    private int positionX = -1;
    /*
    This is the row position of the entity in the maze. Initialize to -1 
    to indicate that the position has not yet been set.
    */
    
    private int positionY = -1;
    /*
    This is the column position of the entity in the maze. Initialize to -1 
    to indicate that the position has not yet been set.
    */ 
    private char symbol;
    /*
    This is a character symbol by which an entity is identified on the Maze.  
    For example, a squirrel is represented by ‘@’. Each nut will be represented 
    by the first character of its name (e.g. Almond will be represented by ‘A’).
    */
      
//------------------------------ Constructors-----------------------------------

    public Entity(Maze maze)
    /*
    To create any entity, a maze needs to be provided. This maze will be 
    shared throughout the app.
    */
    {
        this.maze = maze;
    }

//------------------------------- Behaviors ------------------------------------
    
    public abstract void create();
    public abstract int getPoints();

//------------------------------------------------------------------------------
    public Entity put(int newX, int newY) throws LocationNotAvailableException
    /*
    This method puts an entity at location (newX, newY) in the maze. The entity 
    cannot move to a location occupied by a wall (i.e. ‘*’). This method 
    returns a character that was replaced in the new location.
    */
    {
        boolean moved = maze.set(newY, newX, this);
        Entity result = null;
        
       if (!moved)
       {
           //There was a nut or a squirrel in the provided location
           result = maze.get(newY, newX);
           maze.clear(newY, newX);
           maze.set(newY,newX, this);
           return result;
       }
            /*
            If the object was previously somewhere else, clear this position. The
            positions are initialized at -1 to indicate whether they are valid.
             */
            if (getPositionY() >= 0 && getPositionX() >= 0)
            {    
                maze.clear(getPositionY(), getPositionX());
            }
            setPosition(newY, 'y');
            setPosition(newX, 'x');     
       {
           
        return result;  
       }
    }
    
//------------------------------ Getters/Setters--------------------------------
    public int getPositionX()
    {
        return positionX;
    }
//------------------------------------------------------------------------------  
    public int getPositionY()
    {
        return positionY;
    }  
//------------------------------------------------------------------------------

    public void setPosition(int value, char type)
    {
        switch (type)
        {
            case 'x':
                this.positionX = value;
            break;
            case 'y':
                this.positionY = value;
            break;            
        }
    }
//-----------------------------------------------------------------------------    
    public void setSymbol(char symbol)
    {
        this.symbol = symbol;
    }
    
}
