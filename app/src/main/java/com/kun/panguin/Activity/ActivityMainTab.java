package com.kun.panguin.Activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.kun.panguin.Activity.Base.ActivityBase;
import com.kun.panguin.Fragment.FragmentChat;
import com.kun.panguin.Fragment.FragmentContact;
import com.kun.panguin.Fragment.FragmentTrend;
import com.kun.panguin.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityMainTab extends FragmentActivity {
    private ImageView ivBottomLine,ivMainChat,ivMainContact,ivMainTrend;
    private ViewPager mainViewPager;
    private int bottomLineWidth;
    private List<Fragment> fragmentList;
    private int curPagerItem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scroll_layout);
        InitWidget();
        InitListener();
    }

    private void InitWidth() {
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        bottomLineWidth=metrics.widthPixels/3;

        ViewGroup.LayoutParams params=ivBottomLine.getLayoutParams();
        params.width=bottomLineWidth;
        ivBottomLine.setLayoutParams(params);
    }

    protected void InitWidget() {
        ivBottomLine= (ImageView) findViewById(R.id.ivBottomLine);
        InitWidth();

        ivMainChat= (ImageView) findViewById(R.id.ivMainChat);
        ivMainContact= (ImageView) findViewById(R.id.ivMainContact);
        ivMainTrend= (ImageView) findViewById(R.id.ivMainTrend);

        mainViewPager= (ViewPager) findViewById(R.id.mainViewPager);
        fragmentList=new ArrayList<>();

        FragmentChat fragmentChat=new FragmentChat();
        FragmentContact fragmentContact=new FragmentContact();
        FragmentTrend fragmentTrend=new FragmentTrend();
        fragmentList.add(fragmentChat);
        fragmentList.add(fragmentContact);
        fragmentList.add(fragmentTrend);
        mainViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mainViewPager.setCurrentItem(0);
    }


    protected void InitListener() {
        ivMainChat.setOnClickListener(new MyOnclickListener(0));
        ivMainContact.setOnClickListener(new MyOnclickListener(1));
        ivMainTrend.setOnClickListener(new MyOnclickListener(2));
        mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int translate=position-curPagerItem;
                TranslateAnimation animation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,curPagerItem,
                        Animation.RELATIVE_TO_SELF,position,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
                curPagerItem=position;
                animation.setFillAfter(true);
                animation.setDuration(300);
                ivBottomLine.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private class MyOnclickListener implements View.OnClickListener{

        int type;
        public MyOnclickListener(int type) {
            this.type = type;
        }
        @Override
        public void onClick(View v) {
            mainViewPager.setCurrentItem(type);
        }
    }
}
