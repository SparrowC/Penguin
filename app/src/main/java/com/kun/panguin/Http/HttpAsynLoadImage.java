package com.kun.panguin.Http;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.kun.panguin.Adapter.ChatListAdapter;
import com.kun.panguin.Beans.User;
import com.kun.panguin.Utils.ParseJSONUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HttpAsynLoadImage extends AsyncTask<String,Integer,List<User>> {
    private Context mContext;
    private ListView mListView;
    private HttpURLConnection connection;
    private ProgressDialog mProgressDialog;

    public HttpAsynLoadImage(Context mContext, ListView mListView) {
        this.mContext = mContext;
        this.mListView = mListView;
        mProgressDialog=new ProgressDialog(mContext);
    }

    @Override
    protected List<User> doInBackground(String... params) {
        String result="";
        BufferedReader bufferedReader = null;
        try {
            //替换成自己的一个json数据所放的URL地址，可以放在阿里云服务器上，或者搭个本地服务器
            /*
            {
                "userInfo":
                [
                    {"url":"127.0.0.1:8080/image1.jpg","name":"aaa","signature":"vvvvvvvv"},
                    {"url":"1127.0.0.1:8080/image1.jpg","name":"aaa","signature":"vvvvvvvv"},
                    {"url":"1127.0.0.1:8080/image1.jpg","name":"aaa","signature":"vvvvvvvv"},
                    {"url":"1127.0.0.1:8080/image1.jpg","name":"aaa","signature":"vvvvvvvv"},
                    {"url":"1127.0.0.1:8080/image1.jpg","name":"aaa","signature":"vvvvvvvv"}
                ]
            }
             */
            //把上面的json数据写入userInfo.txt里
            URL url=new URL("http://127.0.0.1:8080/userInfo.txt");
            connection= (HttpURLConnection) url.openConnection();

            bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str=new String();
            while ((str=bufferedReader.readLine())!=null)
                result+=str;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<User> users= ParseJSONUtils.ParseJSONtoUesrs(result,mContext);
        return users;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog.setMessage("正在努力加载中。。。");
        mProgressDialog.setTitle("请稍等");
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        mListView.setAdapter(new ChatListAdapter(mContext,users));
        if(mProgressDialog!=null)
            mProgressDialog.dismiss();
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
