package com.kun.panguin.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.kun.panguin.Beans.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ParseJSONUtils {
    public static List<User> ParseJSONtoUesrs(String jsonText,Context mContext)
    {
        List<User> users=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(jsonText);
            JSONArray usersInfo=jsonObject.getJSONArray("userInfo");
            for (int i=0;i<usersInfo.length();i++)
            {
                User user=new User();
                JSONObject userInfo=usersInfo.getJSONObject(i);
                user.setName(userInfo.getString("name"));
                user.setSignature(userInfo.getString("signature"));
                user.setPic(GetBitmapByURL(userInfo.getString("url"),mContext));

                users.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return users;
        }
    }
    private static Bitmap GetBitmapByURL(String urlStr,Context mContext) {
        Bitmap bitmap=null;
        InputStream is = null;
        HttpURLConnection connection;
        try {
            URL url=new URL(urlStr);
            connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            int responseCode=connection.getResponseCode();
            if (responseCode!=200)
            {
                switch (responseCode)
                {
                    case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                        Toast.makeText(mContext, "网络连接超时", Toast.LENGTH_SHORT);
                        break;
                }
            }
            is=connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                return bitmap;
            }
        }
    }

}
