package com.nstudio.myapplication.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.nstudio.myapplication.R;

public class PuzzleCell extends BaseCell {
    private int color=Color.parseColor("#FF474747");
    private float rectsize;
    private float rectRadious;
    private int blocksize;
    private int colum;
    public PuzzleCell(Context context) {
        super(context);
        rectsize=getContext().getResources().getDimension(R.dimen.dp5);
        rectRadious=getContext().getResources().getDimension(R.dimen.dp25);
    }

    public int getColum() {
        return colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public int getColor() {
        return color;
    }

    public int getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(int blocksize) {
        this.blocksize = blocksize;
    }

    public void setColor(int color) {
        this.color = color;
        setValue(1);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
    public void Destroy(){
        setValue(0);
        color= Color.parseColor("#FF474747");
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint2 = new Paint();
        paint2.setColor(color);
        paint2.setStyle(Paint.Style.FILL);

        RectF rectF = new RectF(
                rectsize, // left
                rectsize, // top
                canvas.getWidth() - rectsize, // right
                canvas.getHeight() -rectsize // bottom
        );



        canvas.drawRoundRect(
                rectF, // rect
                rectsize*2, // rx
                rectsize*2, // ry
                paint2 // Paint
        );
    }
}
