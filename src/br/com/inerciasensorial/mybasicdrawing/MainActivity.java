package br.com.inerciasensorial.mybasicdrawing;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private DrawingView mDraw;
    private Integer[] strokeWidths = { 5, 10, 15 };
    private int currentStrokeWidthIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDraw = (DrawingView) findViewById(R.id.drawingView);
    }

    public void newPaint(View v) {
        mDraw.resetDrawing();
    }

    public void setDrawingColor(View v) {
        PaletteColorView paletteView = (PaletteColorView) v;

        mDraw.setPaintColor(paletteView.getColor());
    }

    public void setDrawingColorWhite(View v) {
        mDraw.setPaintColor(Color.WHITE);
    }

    public void changeStrokeWidth(View v) {
        int newStrokeWidth = strokeWidths[currentStrokeWidthIndex];

        mDraw.setStrokeWidth(newStrokeWidth);

        Toast.makeText(getApplicationContext(),
                "Stroke width changed to " + newStrokeWidth, Toast.LENGTH_SHORT)
                .show();

        currentStrokeWidthIndex = currentStrokeWidthIndex + 1;
        if (currentStrokeWidthIndex >= strokeWidths.length) {
            currentStrokeWidthIndex = 0;
        }
    }
}
