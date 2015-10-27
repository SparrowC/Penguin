package com.kun.panguin.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.kun.panguin.Activity.Base.ActivityBase;
import com.kun.panguin.R;


public class ActivityMainInfo extends ActivityBase {

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        InitWidget();
        InitListener();
    }


    protected void InitWidget() {
        mListView= (ListView) findViewById(R.id.left_drawer);
        mListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, getResources().getStringArray(R.array.lists)));
    }


    protected void InitListener() {

    }
}
