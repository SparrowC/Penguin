package com.kun.panguin.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kun.panguin.Activity.Base.ActivityBase;
import com.kun.panguin.R;


public class ActivityDoor extends ActivityBase {
    private ImageView ivDoorLeft,ivDoorRight;
    private Button doorButton;
    private TextView doorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door);
        InitWidget();
    }

    @Override
    protected void InitWidget() {
        doorButton= (Button) findViewById(R.id.doorButton);
        doorButton.setVisibility(View.INVISIBLE);

        doorText= (TextView) findViewById(R.id.doorText);
        AnimationSet animationSet=new AnimationSet(true);

        AlphaAnimation alphaAnimation=new AlphaAnimation(1f,0.00001f);
        alphaAnimation.setDuration(1500);
        ScaleAnimation scaleAnimation=new ScaleAnimation(0.5f,2f,0.5f,2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.setFillAfter(true);
        doorText.startAnimation(animationSet);

        ivDoorLeft= (ImageView) findViewById(R.id.ivDoorLeft);
        ivDoorRight= (ImageView) findViewById(R.id.ivDoorRight);

        TranslateAnimation leftTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f
                ,Animation.RELATIVE_TO_SELF,-1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        leftTranslateAnimation.setStartOffset(800);
        leftTranslateAnimation.setDuration(2500);
        leftTranslateAnimation.setFillAfter(true);
        ivDoorLeft.startAnimation(leftTranslateAnimation);

        TranslateAnimation rightTranslateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f
                ,Animation.RELATIVE_TO_SELF,+1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
        rightTranslateAnimation.setStartOffset(800);
        rightTranslateAnimation.setDuration(2500);
        rightTranslateAnimation.setFillAfter(true);
        rightTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ActivityDoor.this.OpenActivity(ActivityDoor.this,ActivityMainTab.class);
                ActivityDoor.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivDoorRight.startAnimation(rightTranslateAnimation);
    }

    @Override
    protected void InitListener() {

    }
}
