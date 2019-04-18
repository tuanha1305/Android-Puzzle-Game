package com.nstudio.myapplication.engine;

import android.content.Context;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by Nhatran241 18/04/2019
 */
public class GameEngine {
    private static GameEngine instance;
    public static int WIDTH=10;
    public static int HEIGHT=10;

    private Context context;

    private Puzzle[][] PuzzleGrid;

    public static GameEngine getInstance() {
        if( instance == null ){
            instance = new GameEngine();
        }

        return instance;
    }

    private GameEngine(){ }

    public void createGrid(Context context){
        this.context = context;
        PuzzleGrid = new Puzzle[WIDTH][HEIGHT];
        setGrid(context);

    }

    private void setGrid( final Context context){
        for( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( PuzzleGrid[x][y] == null ){
                        PuzzleGrid[x][y] = new Puzzle(context);
                }
                Random rnd = new Random();
                PuzzleGrid[x][y].setColor(Color.argb(255,rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                PuzzleGrid[x][y].invalidate();

            }
        }
    }

    public Puzzle getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return PuzzleGrid[x][y];
    }


}
