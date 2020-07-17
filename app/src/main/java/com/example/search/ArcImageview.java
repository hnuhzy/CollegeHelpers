package com.example.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.Nullable;


public class ArcImageview extends androidx.appcompat.widget.AppCompatImageView {

    private int mArcHeight;
    private static final String TAG = "ArcImageView";


    public ArcImageview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ArcImageView);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.ArcImageView_arcHeight,0);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //贝塞尔曲线画弧
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0,getHeight());
        path.quadTo(getWidth() / 2, getBottom()-200, getWidth(), getHeight());
        path.lineTo(getWidth(), 0);
        path.close();
        canvas.clipPath(path);
        super.onDraw(canvas);

    }
}
