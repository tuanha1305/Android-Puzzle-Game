package com.nstudio.myapplication.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class PuzzleCell extends BaseCell {
    private int color;
    public PuzzleCell(Context context) {
        super(context);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint2 = new Paint();
        paint2.setColor(color);
        paint2.setStyle(Paint.Style.FILL);

        RectF rectF = new RectF(
                5, // left
                5, // top
                canvas.getWidth() - 5, // right
                canvas.getHeight() - 5 // bottom
        );


        int cornersRadius = 25;

        canvas.drawRoundRect(
                rectF, // rect
                cornersRadius, // rx
                cornersRadius, // ry
                paint2 // Paint
        );
    }
}
