package com.example.mac.myviewtest;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by Mac on 2017/8/12.
 */

public class MyView extends View {
    private LinkedList<LinkedList<Point>> lines;
    private Paint paint;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.GREEN);
        lines=new LinkedList<>();
        paint=new Paint();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.i("test","onTouch:"+event.getX()+"x"+event.getY());
            touchDown(event.getX(),event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            Log.i("test","Move:"+event.getX()+"x"+event.getY());
            touchMove(event.getX(),event.getY());
        }

        return  true;
    }

    private void touchDown(float x , float y){
        LinkedList<Point> line = new LinkedList<>();
        Point p = new Point();
        p.x=x;p.y=y;
        line.add(p);
        lines.add(line);
    }
    private void touchMove(float x , float y){
        LinkedList<Point> line = lines.getLast();
        Point p = new Point();
        p.x = x; p.y = y;
        line.add(p);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(LinkedList<Point> line : lines){
            for(int i =1 ; i<line.size();i++){
                Point p0 = line.get(i-1);
                Point p1 = line.get(i);
                canvas.drawLine(p0.x,p0.y,p1.x,p1.y,paint);
            }
        }

    }


    private class Point{
        float x,y;
    }


}




