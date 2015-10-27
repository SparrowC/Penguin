package com.kun.panguin.Activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;

import com.kun.panguin.Activity.Base.ActivityBase;
import com.kun.panguin.R;

public class MainActivity extends ActivityBase {

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 1:
                    OpenActivity(MainActivity.this, ActivitySplash.class);
                    MainActivity.this.finish();
                    break;
            }
        }
    };

    @Override
    protected void InitWidget() {

    }

    @Override
    protected void InitListener() {

    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            Looper.prepare();
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=new Message();
            message.what=1;
            mHandler.sendMessage(message);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyThread myThread=new MyThread();
        myThread.start();
    }


}
