package com.umn.week06_31273;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

public class ViewAnimasi extends View{

    private static final int Durasi =4000;
    private static final long Delay =1000;
    private static final int PENGATURAN_WARNA = 5;
    private float mx;
    private float my;
    private float mRadius;
    private final Paint mPaint = new Paint();
    private AnimatorSet mAnimatorSet = new AnimatorSet();


    public ViewAnimasi(Context context){
        super(context);
    }

    public ViewAnimasi(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mx,my,mRadius,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked()==MotionEvent.ACTION_DOWN){
            mx = event.getX();
            my = event.getY();

            if(mAnimatorSet!=null&&mAnimatorSet.isRunning()){
                mAnimatorSet.cancel();
            }
            mAnimatorSet.start();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        ObjectAnimator membesar = ObjectAnimator.ofFloat(this,"radius",0,getWidth());
        membesar.setDuration(Durasi);
        membesar.setInterpolator(new LinearInterpolator());

        ObjectAnimator mengecil = ObjectAnimator.ofFloat(this,"radius",getWidth(),0);
        mengecil.setDuration(Durasi);
        mengecil.setInterpolator(new LinearOutSlowInInterpolator());
        mengecil.setStartDelay(Delay);

        ObjectAnimator ulang = ObjectAnimator.ofFloat(this,"radius",0,getWidth());
        ulang.setStartDelay(Delay);
        ulang.setDuration(Durasi);
        ulang.setRepeatCount(1);
        ulang.setRepeatMode(ValueAnimator.REVERSE);
        mAnimatorSet.play(membesar).before(mengecil);
        mAnimatorSet.play(ulang).after(mengecil);
    }

    public void setRadius(float Radius) {
        this.mRadius = mRadius;
        mPaint.setColor(Color.GREEN+(int)mRadius/PENGATURAN_WARNA);
        invalidate();
    }
}
