package com.nstudio.myapplication.engine;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nstudio.myapplication.R;
import com.nstudio.myapplication.Utils.ScreenUtils;

/**
 * Created by Nhatran241 on 18/04/2019
 */
public class GameView extends GridView{
    float dX, dY;
    float dX2, dY2;
    float odX, odY;
    float odX2, odY2;

    int style=0;
    public GameView(final Context context , AttributeSet attrs){
        super(context,attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                GameEngine.getInstance().createGrid(context);
                setNumColumns(GameEngine.WIDTH);
                setAdapter(new GridAdapter());
                 final View paren = (View) getParent();
                 final View gameview = paren.findViewById(R.id.gameview);
                 final PuzzleBlockView child = paren.findViewById(R.id.puzzle1);
                final PuzzleBlockView2 child2 = paren.findViewById(R.id.puzzle2);
                 child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                     @Override
                     public void onGlobalLayout() {
                         getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        odX=child.getX();
                        odY=child.getY();
                     }
                 });
                child2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        odX2=child2.getX();
                        odY2=child2.getY();
                    }
                });
                paren.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                        final int x = (int) event.getRawX();
//                        final int y = (int) event.getRawY();

                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_DOWN:
                                if(event.getRawX()>getWidth()/2){
                                    style=1;
                                }else {
                                    style=0;
                                }
                                if(style==0){

                                    dX = child.getX() - event.getRawX();
                                    dY = child.getY() - event.getRawY();
                                    child.animate()
                                            .x(0)
                                            .y(getHeight() -child.getHeight())
                                            .setDuration(0)
                                            .start();
                                }else {
                                    dX2 = child2.getX() - event.getRawX();
                                    dY2 = child2.getY() - event.getRawY();
                                    child2.animate()
                                            .x(getWidth()-child2.getWidth())
                                            .y(getHeight()-child2.getHeight())
                                            .setDuration(0)
                                            .start();
                                }


                                break;
                            case MotionEvent.ACTION_UP:
                                if(style==1){
                                    int pos = pointToPosition((int)child2.getX()+child2.getWidth()/2, (int) (child2.getY()+child2.getHeight()*4/5));
                                    GameEngine.getInstance().addPuzzle2(pos);
                                    GameEngine.getInstance().createBlock2(context);
                                    child2.updateAdapter();
                                    child2.animate()
                                            .x(odX2)
                                            .y(odY2)
                                            .setDuration(0)
                                            .start();

                                }else {
                                    int pos = pointToPosition((int)child.getX()+child.getWidth()/2, (int) (child.getY()+child.getHeight()*4/5));
                                    GameEngine.getInstance().addPuzzle(pos);
                                    GameEngine.getInstance().createBlock(context);
                                    child.updateAdapter();
                                    child.animate()
                                            .x(odX)
                                            .y(odY)
                                            .setDuration(0)
                                            .start();
                                }

                                    break;
                            case MotionEvent.ACTION_POINTER_DOWN:
                                break;
                            case MotionEvent.ACTION_POINTER_UP:
                                break;
                            case MotionEvent.ACTION_MOVE:
                                if(style==0){
                                    child.animate()
                                            .x(event.getRawX() + dX)
                                            .y(event.getRawY() + dY-child.getHeight())
                                            .setDuration(0)
                                            .start();
                                }else {
                                    child2.animate()
                                            .x(event.getRawX() + dX2)
                                            .y(event.getRawY() + dY2-child2.getHeight())
                                            .setDuration(0)
                                            .start();
                                }

                                break;
                        }
                        return true;
                    }
                });
//                setOnItemClickListener(new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        int x= (int) (view.getX()/view.getWidth());
//                        int y= (int) (view.getY()/view.getHeight());
//
//                        Toast.makeText(context, ""+x+"/"+y, Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return GameEngine.WIDTH*GameEngine.HEIGHT;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return GameEngine.getInstance().getCellAt(position);
        }
    }
}
