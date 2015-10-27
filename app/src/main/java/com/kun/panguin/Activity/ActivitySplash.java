package com.kun.panguin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.kun.panguin.Activity.Base.ActivityBase;
import com.kun.panguin.Adapter.StartViewPagerAdapter;
import com.kun.panguin.R;

import java.util.ArrayList;
import java.util.List;


public class ActivitySplash extends ActivityBase {
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        InitWidget();
    }

    @Override
    protected void InitWidget() {
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        LayoutInflater inflater=LayoutInflater.from(this);

        List<View> views=new ArrayList<>();
        views.add(inflater.inflate(R.layout.viewpager_item1,null));
        views.add(inflater.inflate(R.layout.viewpager_item2,null));
        views.add(inflater.inflate(R.layout.viewpager_item3, null));
        views.add(inflater.inflate(R.layout.door, null));

        viewPager.setAdapter(new StartViewPagerAdapter(views));
        viewPager.setCurrentItem(0);




    }

    @Override
    protected void InitListener() {

    }

    public void ButtonClicked(View view)
    {
        Intent intent=new Intent();
        intent.setClass(ActivitySplash.this, ActivityDoor.class);
        startActivity(intent);
        this.finish();
    }
}
