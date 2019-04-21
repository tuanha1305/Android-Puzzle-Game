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
public class PuzzleBlockView2 extends GridView{
    private int pos;
    public PuzzleBlockView2(final Context context , AttributeSet attrs){
        super(context,attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                initRation(context);

                GameEngine.getInstance().createBlock2(context);
                setNumColumns(GameEngine.getInstance().getBlockat2(0).getColum());
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

    public void updateAdapter() {
        setNumColumns(GameEngine.getInstance().getBlockat2(0).getColum());
        setAdapter(new GridAdapter());
    }

    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return GameEngine.getInstance().getBlockat2(0).getBlocksize();
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
            return GameEngine.getInstance().getBlockat2(position);
        }
    }
}
