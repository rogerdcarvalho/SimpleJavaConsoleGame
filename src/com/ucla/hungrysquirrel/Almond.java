/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucla.hungrysquirrel;

import com.ucla.hungrysquirrelgame.Nuts;

/**
 *
 * @author Roger
 */
public class Almond extends Nuts 
{
    private final int NutritionPoints = 5;
    private final char Symbol = 'A';
    
    public Almond(Maze maze)
    {
        super(maze);
        setSymbol(Symbol);
    }
    
    @Override 
    public int getNutritionPoints()
    {
        return NutritionPoints;
    }
    
    @Override 
    public char getSymbol()
    {
        return Symbol;
    }
}
