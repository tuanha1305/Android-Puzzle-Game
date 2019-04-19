package com.nstudio.myapplication.engine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by Nhatran241 on 18/04/2019
 */
public class PuzzleBlockView extends GridView{
    private int pos;
    public PuzzleBlockView(final Context context , AttributeSet attrs){
        super(context,attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                initRation(context);

                GameEngine.getInstance().createBlock(context);
                setNumColumns(3);
                setAdapter(new GridAdapter());

            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pos = pointToPosition((int)event.getX(), (int) event.getY());
        setPos(pos);
        super.onTouchEvent(event);
        return false;
    }

    public int getPosition() {
        return pos;
    }
    public void setPos(int pos){
        this.pos=pos;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 6;
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
            return GameEngine.getInstance().getBlockat(position);
        }
    }
}
