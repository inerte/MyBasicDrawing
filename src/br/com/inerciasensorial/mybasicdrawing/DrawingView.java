package br.com.inerciasensorial.mybasicdrawing;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
    private int paintColor = Color.BLACK;
    private ArrayList<Pair<Path, Paint>> paths = new ArrayList<Pair<Path, Paint>>();
    private Path path;
    private int strokeWidth = 5;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Pair<Path, Paint> p : paths) {
            canvas.drawPath(p.first, p.second);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        Paint drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(strokeWidth);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        // Checks for the event that occurs
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            path = new Path();
            paths.add(new Pair<Path, Paint>(path, drawPaint));
            path.moveTo(pointX, pointY);
            return true;
        case MotionEvent.ACTION_MOVE:
            paths.get(paths.size() - 1).first.lineTo(pointX, pointY);
            break;
        case MotionEvent.ACTION_UP:
            break;
        default:
            return false;
        }

        // Force a view to draw again
        postInvalidate();
        return true;
    }

    public int getPaintColor() {
        return this.paintColor;
    }

    public void setPaintColor(int color) {
        this.paintColor = color;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void resetDrawing() {
        paths.clear();
        postInvalidate();
    }
}