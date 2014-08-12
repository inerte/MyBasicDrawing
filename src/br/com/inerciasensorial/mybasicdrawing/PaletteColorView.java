package br.com.inerciasensorial.mybasicdrawing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PaletteColorView extends View {
    private int color;
    private Paint drawPaint = new Paint();

    public PaletteColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        // Obtain a typed array of attributes
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.PaletteColorView, 0, 0);
        // Extract custom attributes into member variables
        try {
            color = a.getColor(R.styleable.PaletteColorView_color, Color.BLACK);
        } finally {
            // TypedArray objects are shared and must be recycled.
            a.recycle();
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPaint.setAntiAlias(true);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setColor(this.color);
        canvas.drawCircle(20, 20, 12, drawPaint);
    }
}
