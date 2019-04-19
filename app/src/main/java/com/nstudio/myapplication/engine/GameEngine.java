package com.nstudio.myapplication.engine;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

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

    private PuzzleCell[] puzzleBlock1=new PuzzleCell[6];
    private PuzzleCell[][] puzzleBlock2=new PuzzleCell[5][5];
    private PuzzleCell[][] puzzleBlock3=new PuzzleCell[5][5];


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
//                PuzzleGrid[x][y].setColor(Color.argb(255,rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));


            }
        }
    }

    public PuzzleCell getCellAt(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return puzzleCellGrid[x][y];
    }
    public void addPuzzle(int position,int blockposition){
        int x = position % WIDTH;
        int y = position / WIDTH;

        if(x+1>0&&y+1>0) {

            switch (blockposition){
                case 0:{
                    puzzleCellGrid[x+1][y].setColor(Color.RED);
                    puzzleCellGrid[x ][y +1].setColor(Color.RED);
                    puzzleCellGrid[x+1][y +1].setColor(Color.RED);
                    puzzleCellGrid[x + 2][y +1].setColor(Color.RED);
                    break;
                }
                case 1:{
                    puzzleCellGrid[x][y].setColor(Color.RED);
                    puzzleCellGrid[x - 1][y +1].setColor(Color.RED);
                    puzzleCellGrid[x][y +1].setColor(Color.RED);
                    puzzleCellGrid[x + 1][y +1].setColor(Color.RED);
                    break;
                }
                case 2:{
                    puzzleCellGrid[x-1][y].setColor(Color.RED);
                    puzzleCellGrid[x][y +1].setColor(Color.RED);
                    puzzleCellGrid[x-1][y +1].setColor(Color.RED);
                    puzzleCellGrid[x -2][y +1].setColor(Color.RED);
                    break;
                }
                case 3:{
                    puzzleCellGrid[x][y].setColor(Color.RED);
                    puzzleCellGrid[x+1][y -1].setColor(Color.RED);
                    puzzleCellGrid[x+1][y].setColor(Color.RED);
                    puzzleCellGrid[x+2][y].setColor(Color.RED);
                    break;
                }
                case 4:{
                    puzzleCellGrid[x][y].setColor(Color.RED);
                    puzzleCellGrid[x][y -1].setColor(Color.RED);
                    puzzleCellGrid[x-1][y].setColor(Color.RED);
                    puzzleCellGrid[x+1][y].setColor(Color.RED);
                    break;
                }
                case 5:{
                    puzzleCellGrid[x][y].setColor(Color.RED);
                    puzzleCellGrid[x-1][y -1].setColor(Color.RED);
                    puzzleCellGrid[x-1][y].setColor(Color.RED);
                    puzzleCellGrid[x-2][y].setColor(Color.RED);
                    break;
                }
            }
            checkdestroy();
//            puzzleCellGrid[x - 1][y - 1].setColor(Color.RED);
//            puzzleCellGrid[x][y - 1].setColor(Color.RED);
//            puzzleCellGrid[x + 1][y - 1].setColor(Color.RED);
        }

    }

    private void checkdestroy() {
        for (int i = 0; i <HEIGHT ; i++) {
                if(puzzleCellGrid[0][i].getValue()==1&&
                   puzzleCellGrid[1][i].getValue()==1&&
                   puzzleCellGrid[2][i].getValue()==1&&
                   puzzleCellGrid[3][i].getValue()==1&&
                   puzzleCellGrid[4][i].getValue()==1&&
                   puzzleCellGrid[5][i].getValue()==1&&
                   puzzleCellGrid[6][i].getValue()==1&&
                   puzzleCellGrid[7][i].getValue()==1&&
                   puzzleCellGrid[8][i].getValue()==1&&
                   puzzleCellGrid[9][i].getValue()==1){
                    puzzleCellGrid[0][i].Destroy();
                    puzzleCellGrid[1][i].Destroy();
                    puzzleCellGrid[2][i].Destroy();
                    puzzleCellGrid[3][i].Destroy();
                    puzzleCellGrid[4][i].Destroy();
                    puzzleCellGrid[5][i].Destroy();
                    puzzleCellGrid[6][i].Destroy();
                    puzzleCellGrid[7][i].Destroy();
                    puzzleCellGrid[8][i].Destroy();
                    puzzleCellGrid[9][i].Destroy();
           }
        }
        for (int i = 0; i <WIDTH ; i++) {
            if(puzzleCellGrid[i][0].getValue()==1&&
                    puzzleCellGrid[i][1].getValue()==1&&
                    puzzleCellGrid[i][2].getValue()==1&&
                    puzzleCellGrid[i][3].getValue()==1&&
                    puzzleCellGrid[i][4].getValue()==1&&
                    puzzleCellGrid[i][5].getValue()==1&&
                    puzzleCellGrid[i][6].getValue()==1&&
                    puzzleCellGrid[i][7].getValue()==1&&
                    puzzleCellGrid[i][8].getValue()==1&&
                    puzzleCellGrid[i][9].getValue()==1){
                puzzleCellGrid[i][0].Destroy();
                puzzleCellGrid[i][1].Destroy();
                puzzleCellGrid[i][2].Destroy();
                puzzleCellGrid[i][3].Destroy();
                puzzleCellGrid[i][4].Destroy();
                puzzleCellGrid[i][5].Destroy();
                puzzleCellGrid[i][6].Destroy();
                puzzleCellGrid[i][7].Destroy();
                puzzleCellGrid[i][8].Destroy();
                puzzleCellGrid[i][9].Destroy();
            }
        }
    }

    //    public void addPuzzle(int x,int y){
//        puzzleCellGrid[x][y].setColor(Color.RED);
//        puzzleCellGrid[x-1][y-1].setColor(Color.RED);
//        puzzleCellGrid[x][y-1].setColor(Color.RED);
//        puzzleCellGrid[x+1][y-1].setColor(Color.RED);
//
//    }
    public PuzzleCell getBlockat(int position) {
        int x = position % 5;
        int y = position / 5;

        return puzzleBlock1[position];
    }


    public void createBlock(Context context) {
        for (int i = 0; i <6 ; i++) {
                if( puzzleBlock1[i] == null ){
                    puzzleBlock1[i]= new PuzzleCell(context);
                }
                if(i==1||i==3||i==4||i==5){
                    puzzleBlock1[i].setColor(Color.RED);
                }else {
                    puzzleBlock1[i].setColor(Color.TRANSPARENT);
                }


            }
    }
}
