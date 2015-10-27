package com.kun.panguin.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.kun.panguin.R;
import com.nineoldandroids.view.ViewHelper;

public class SlideMenu extends HorizontalScrollView {
    private int rightPadding;
    private LinearLayout mWapper;
    private ViewGroup mLeftMenu;
    private ViewGroup mRightContext;
    private int screenWidth;
    boolean isMeasured=false;

    public SlideMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenu(Context context) {
        super(context);
    }

    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        screenWidth=metrics.widthPixels;

        TypedArray array=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlideMenu,defStyleAttr,0);
        for (int i=0;i<array.getIndexCount();i++)
        {
            int attr=array.getIndex(i);
            switch (attr)
            {
                case R.styleable.SlideMenu_slideMenuRightPadding:
                    rightPadding=array.getDimensionPixelSize(attr,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,metrics));
                    break;
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWapper= (LinearLayout) getChildAt(0);
        mRightContext= (ViewGroup) mWapper.getChildAt(1);
        mLeftMenu= (ViewGroup) mWapper.getChildAt(0);

        mRightContext.getLayoutParams().width=screenWidth;
        mLeftMenu.getLayoutParams().width=screenWidth-rightPadding;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(screenWidth-rightPadding,0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction())
        {
            case MotionEvent.ACTION_UP:
                int scrollX=this.getScrollX();
                if(scrollX>=(screenWidth-rightPadding)/2)
                {
                    smoothScrollTo(screenWidth-rightPadding,0);
                }else {
                    smoothScrollTo(0,0);
                }
                return true;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale=l*1.0f/(screenWidth-rightPadding);
        scale=scale/4+0.75f;
        //ViewHelper.setScaleX(mRightContext, scale);
        ViewHelper.setScaleY(mRightContext,scale);
    }

//    public SlideMenu(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        WindowManager wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics metrics=new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(metrics);
//        screenWidth=metrics.widthPixels;
//        //context.getResources().getDisplayMetrics() 与metrics有什么区别
//        rightPadding= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,metrics);
//
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if(!isMeasured)
//        {
//            mWapper= (LinearLayout) getChildAt(0);
//            mLeftMenu= (ViewGroup) mWapper.getChildAt(0);
//            mRightContext= (ViewGroup) mWapper.getChildAt(1);
//
//            mLeftMenu.getLayoutParams().width=screenWidth-rightPadding;
//            mRightContext.getLayoutParams().width=screenWidth;
//            mWapper.getLayoutParams().width=screenWidth*2-rightPadding;
//            isMeasured=true;
//        }
//
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        if(changed)
//        {
//            this.scrollTo(screenWidth-rightPadding,0);
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction())
//        {
//            case MotionEvent.ACTION_UP:
//                int scrollX=this.getScrollX();
//                if(scrollX>=(screenWidth-rightPadding)/2)
//                {
//                    ScaleAnimation scaleAnimation=new ScaleAnimation(0,0,1,0.8f, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,1);
//                    scaleAnimation.setFillAfter(true);
//                    scaleAnimation.setDuration(1000);
//                    mRightContext.startAnimation(scaleAnimation);
//                    this.smoothScrollTo(screenWidth-rightPadding,0);
//                }else {
//                    ScaleAnimation scaleAnimation=new ScaleAnimation(0,0,0.8f,1, Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0.8f);
//                    scaleAnimation.setFillAfter(true);
//                    scaleAnimation.setDuration(1000);
//                    mRightContext.startAnimation(scaleAnimation);
//                    this.smoothScrollTo(0, 0);
//                }
//                return true;//如果不返回true，将调用基类的onTouchEvent，那么这里的操作就无效了
//        }
//        return super.onTouchEvent(ev);
//    }
}
