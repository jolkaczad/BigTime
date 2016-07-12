package com.example.w.bigtime;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WL on 10-lip-2016
 * <p/>
 * Version 1.0
 */
public class RectProgressView extends View {
    private Paint paint;
    private int counts;
    private int active;

    public RectProgressView(Context context) {
        super(context);
    }

    public RectProgressView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(null, 0);
    }

    public RectProgressView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init (AttributeSet attrs, int defStyle){
        paint = new Paint();
        paint.setColor(Color.CYAN);

        counts = 1;
    }

    public void setCounts(int counts){
        this.counts = counts;
    }

    public void setActive(int active){
        this.active = active;
        invalidate();
    }

    // TODO: make progress bar blink the most recent element
    @Override
    public void onDraw(Canvas canvas){
        int w;
        int h;

        final int margin = 10;

        int x0, y0, x1, y1;

        w = (getWidth()/counts);
        h = getHeight();

        x0 = 0 + margin;
        y0 = 0 + margin;

        x1 = w - margin;
        y1 = h - margin;

        // left top right bottom
        for (int i = 0; i < counts; i++){
            if (i < active){
                paint.setColor(Color.CYAN);
            }
            else {
                paint.setColor(Color.GREEN);
            }
            canvas.drawRect(x0, y0, x1, y1, paint);
            x0 += w;
            x1 += w;
        }
    }
}
