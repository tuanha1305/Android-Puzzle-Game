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

/**
 * Created by Nhatran241 on 18/04/2019
 */
public class GameView extends GridView{
    float dX, dY;
    float odX, odY;
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
                 child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                     @Override
                     public void onGlobalLayout() {
                         getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        odX=child.getX();
                        odY=child.getY();
                     }
                 });
                paren.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                        final int x = (int) event.getRawX();
//                        final int y = (int) event.getRawY();


                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_DOWN:
//
                                dX = child.getX() - event.getRawX();
                                dY = child.getY() - event.getRawY();

//                                Log.d("tesssdsd", "dxdy: "+getWidth()+"/"+getHeight());
//                                Log.d("tesssdsd", "dxdy: "+child.getWidth()+"/"+child.getHeight());
//                                Log.d("tesssdsd", "dxdy: "+child.getX()+"/"+child.getY());
                                Log.d("tesssdsd", "dxdy: "+event.getX()+"/"+event.getY());
                                Log.d("tesssdsd", "dxdy: "+event.getRawX()+"/"+event.getRawY());
                                child.animate()
                                        .x(0)
                                        .y(getHeight()-child.getHeight())
                                        .setDuration(0)
                                        .start();
                                break;
                            case MotionEvent.ACTION_UP:

                                int pos = pointToPosition((int)child.getX()+child.getWidth()/2, (int) child.getY()+child.getHeight()/2);
                                Toast.makeText(context, ""+pos, Toast.LENGTH_SHORT).show();
//                                GameEngine.getInstance().addPuzzle(pos,child.getPosition());
                                GameEngine.getInstance().addPuzzle(pos);
                                child.animate()
                                        .x(odX)
                                        .y(odY)
                                        .setDuration(0)
                                        .start();
                                    break;
                            case MotionEvent.ACTION_POINTER_DOWN:
                                break;
                            case MotionEvent.ACTION_POINTER_UP:
                                break;
                            case MotionEvent.ACTION_MOVE:
                                Log.d("tesssdsd", "dxdy: "+dX+"/"+dY);
                                Log.d("tesssdsd", "ev: "+event.getX()+"/"+event.getY());
                                Log.d("tesssdsd", "ed: "+event.getRawX()+"/"+event.getRawY());
//                                child.animate()
//                                        .x(event.getRawX()+child.getX())
//                                        .y(event.getRawY()+child.getY())
//                                        .setDuration(0)
//                                        .start();
                                child.animate()
                                        .x(event.getRawX() + dX)
                                        .y(event.getRawY() + dY-child.getHeight())
                                        .setDuration(0)
                                        .start();
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
