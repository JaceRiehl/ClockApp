package com.example.jaceriehl.clock;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import java.util.Calendar;
import android.widget.*;

/**
 * Created by jaceriehl on 2018-03-03.
 */

public class CanvasView extends View {

    boolean init=false;
    Paint pt;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs)
    {
        super(context,attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    void init()
    {
        pt = new Paint();
        pt.setColor(Color.BLACK);
        pt.setStyle(Paint.Style.FILL_AND_STROKE);
        pt.setTextSize(100);
        pt.setFakeBoldText(true);

    }

    @Override
    protected void onDraw(Canvas canvas){

        if(!init){
            init();
            init=true;
        }

        canvas.drawColor(Color.WHITE);

        //get the time
        Calendar ctime = Calendar.getInstance();

        String hour = Integer.toString(ctime.get(Calendar.HOUR));
        String min = Integer.toString(ctime.get(Calendar.MINUTE));
        String sec = Integer.toString(ctime.get(Calendar.SECOND));

        if(ctime.get(Calendar.MINUTE)>=10&&ctime.get(Calendar.SECOND)>=10) {
            canvas.drawText(hour + ":" + min + ":" + sec, 300, 200, pt);
        } else if(ctime.get(Calendar.MINUTE)>=10){
            canvas.drawText(hour + ":" + min + ":0" + sec, 300, 200, pt);
        } else {
            canvas.drawText(hour + ":0" + min + ":" + sec, 300, 200, pt);
        }

        TextView three = (TextView)findViewById(R.id._three);
        three.setText("JEY");

        //invalidate();
    }

}
