package com.umn.week05_31273;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomView extends View {
    private static String bentuk = "Path";
    private static long warna = Color.rgb(0,0,0);
    private Paint paintku;
    private Path pathku;
    private static Canvas kanvasku;
    private Bitmap bitmapku;
    private float pathX,pathY,awalX,awalY,antarX,antarY;
    private static final float TOLERANSI_SENTUH=4;
    private boolean clear;


    public void gantiBentuk(String bentukbaru){
        bentuk = bentukbaru;
    }

    public void gantiWarna(int red,int green,int blue){
        warna = Color.rgb(red,green,blue);
    }


    public CustomView(Context context) {
        super(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintku = new Paint();
        paintku.setColor(warna);
        paintku.setAntiAlias(true);
        paintku.setDither(true);
        paintku.setStyle(Paint.Style.STROKE);
        paintku.setStrokeJoin(Paint.Join.ROUND);
        paintku.setStrokeCap(Paint.Cap.ROUND);
        paintku.setStrokeWidth(12);
        pathku = new Path();

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldwidth, int oldheight) {
        super.onSizeChanged(width, height, oldwidth, oldheight);
        bitmapku = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        kanvasku = new Canvas(bitmapku);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapku,0,0,null);
        Paint localpaint = new Paint();
        localpaint.setStyle(Paint.Style.FILL);
        localpaint.setColor(Color.RED);
        canvas.drawCircle(50,50,50,localpaint);
        localpaint.setStyle(Paint.Style.STROKE);
        localpaint.setStrokeWidth(10);
        localpaint.setColor(Color.WHITE);
        canvas.drawLine(20,20,80,80,localpaint);
        canvas.drawLine(20,80,80,20,localpaint);


        if(!clear){
        //menggambar bentuk antara (Ract,Oval dan Line)
        if(bentuk.equalsIgnoreCase("Rect")){
            canvas.drawRect(awalX,awalY,antarX,antarY,paintku);
        }else if (bentuk.equalsIgnoreCase("Oval")){
            canvas.drawLine(awalX,awalY,antarX,antarY,paintku);
        }else if (bentuk.equalsIgnoreCase("Line")){
            canvas.drawLine(awalX,awalY,antarX,antarY,paintku);
        }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
          float x = event.getX();
          float y =event.getY();

          switch (event.getAction()){
              case MotionEvent.ACTION_DOWN:
                  mulaiSentuh(x,y);
                  break;
              case MotionEvent.ACTION_MOVE:
                  geser(x,y);
                  break;
              case MotionEvent.ACTION_UP:
                  lepas();
                  break;
              default:
          }
          return true;
    }

    private void lepas() {
        if(!clear){
        if (bentuk.equalsIgnoreCase("Path")){
            pathku.reset();
        }else if(bentuk.equalsIgnoreCase("Rect")){
            kanvasku.drawRect(awalX,awalY,antarX,antarY,paintku);
        }else if(bentuk.equalsIgnoreCase("Oval")){
            kanvasku.drawOval(awalX,awalY,antarX,antarY,paintku);
        }else if(bentuk.equalsIgnoreCase("Line")){
            kanvasku.drawLine(awalX,awalY,antarX,antarY,paintku);
        }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void geser(float x, float y) {
        float dx = Math.abs(x-pathX);
        float dy = Math.abs(y-pathY);

        if (dx>=TOLERANSI_SENTUH||dy>=TOLERANSI_SENTUH){
            paintku.setColor(warna);
            if(bentuk.equalsIgnoreCase("Path")){
                pathku.quadTo(pathX,pathY,(x+pathX)/2,(y+pathY)/2);
                kanvasku.drawPath(pathku,paintku);
                pathY = y;
                pathX = x;
            }else {
                antarX = x;
                antarY = y;
            }
        }
            invalidate();
    }

    private void mulaiSentuh(float x, float y) {
       if (x<=100&&y<=100){
           kanvasku.drawColor(Color.WHITE);
           clear = true;
           invalidate();
       }else {

        if(bentuk.equalsIgnoreCase("Path")){
            pathku.moveTo(x,y);
            pathX = x;
            pathY = y;
        }else {
            awalX = x;
            awalY = y;
        }
        clear = false;
       }
    }
}
