package com.example.search;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ScrollView;

public class Scrollerview extends ScrollView {

    private OnScrollListener onScrollListener;
    private int lastScrollY;

    public Scrollerview(Context context) {
        super(context);
    }

    public Scrollerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Scrollerview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        this.onScrollListener = onScrollListener;
    }
    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            int scrollY = Scrollerview.this.getScrollY();

            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if(lastScrollY != scrollY){
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if(onScrollListener != null){
                onScrollListener.onScroll(scrollY);
            }

        };

    };

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(onScrollListener != null){
            onScrollListener.onScroll(lastScrollY = this.getScrollY());
        }
        switch(ev.getAction()){
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 20);
                break;
        }
        return super.onTouchEvent(ev);
    }
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
        public void onScroll(int scrollY);
    }
}
