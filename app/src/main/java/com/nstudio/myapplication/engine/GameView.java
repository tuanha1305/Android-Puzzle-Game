package com.nstudio.myapplication.engine;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Nhatran241 on 18/04/2019
 */
public class GameView extends GridView{
    public GameView(final Context context , AttributeSet attrs){
        super(context,attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                initRation(context);
                GameEngine.getInstance().createGrid(context);
                setNumColumns(GameEngine.WIDTH);
                setAdapter(new GridAdapter());
                setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                        final int x = (int) event.getRawX();
//                        final int y = (int) event.getRawY();
                        int pos = pointToPosition((int) event.getX(), (int) event.getY());

                        switch (event.getAction() & MotionEvent.ACTION_MASK) {
                            case MotionEvent.ACTION_DOWN:

                                break;
                            case MotionEvent.ACTION_UP:
                                GameEngine.getInstance().addPuzzle(pos);
                                    break;
                            case MotionEvent.ACTION_POINTER_DOWN:
                                break;
                            case MotionEvent.ACTION_POINTER_UP:
                                break;
                            case MotionEvent.ACTION_MOVE:
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
