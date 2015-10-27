package com.kun.panguin.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kun.panguin.Beans.User;
import com.kun.panguin.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class ChatListAdapter extends BaseAdapter {
    private Context mContext;
    private List<User> users;

    public ChatListAdapter(Context mContext, List<User> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        if(convertView==null)
        {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.chat_list_item,null);
            holder.ivChatImage= (ImageView) convertView.findViewById(R.id.ivChatImage);
            holder.tvChatName= (TextView) convertView.findViewById(R.id.tvChatName);
            holder.tvChatSignature= (TextView) convertView.findViewById(R.id.tvChatSignature);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.ivChatImage.setImageBitmap(users.get(position).getPic());
        holder.tvChatName.setText(users.get(position).getName());
        holder.tvChatSignature.setText(users.get(position).getSignature());

        return convertView;
    }
    private class ViewHolder{
        public TextView tvChatName,tvChatSignature;
        public ImageView ivChatImage;
    }

}
