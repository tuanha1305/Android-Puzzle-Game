package com.nstudio.myapplication.engine;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
            }
        });

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
