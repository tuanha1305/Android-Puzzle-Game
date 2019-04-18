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

    private PuzzleCell[][] puzzleCellGrid;

    public static GameEngine getInstance() {
        if( instance == null ){
            instance = new GameEngine();
        }

        return instance;
    }

    private GameEngine(){ }

    public void createGrid(Context context){
        this.context = context;
        puzzleCellGrid = new PuzzleCell[WIDTH][HEIGHT];
        setGrid(context);

    }

    private void setGrid( final Context context){
        for( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( puzzleCellGrid[x][y] == null ){
                        puzzleCellGrid[x][y] = new PuzzleCell(context);
                }
                Random rnd = new Random();
                puzzleCellGrid[x][y].setColor(Color.WHITE);
//                PuzzleGrid[x][y].setColor(Color.argb(255,rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));


            }
        }
    }

    public PuzzleCell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return puzzleCellGrid[x][y];
    }
    public void addPuzzle(int position){
        int x = position % WIDTH;
        int y = position / WIDTH;
            puzzleCellGrid[x][y].setColor(Color.RED);
            puzzleCellGrid[x-1][y-1].setColor(Color.RED);
            puzzleCellGrid[x][y-1].setColor(Color.RED);
            puzzleCellGrid[x+1][y-1].setColor(Color.RED);

    }
    public void addPuzzle(int x,int y){
        puzzleCellGrid[x][y].setColor(Color.RED);
        puzzleCellGrid[x-1][y-1].setColor(Color.RED);
        puzzleCellGrid[x][y-1].setColor(Color.RED);
        puzzleCellGrid[x+1][y-1].setColor(Color.RED);

    }


}
