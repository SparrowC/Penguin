package com.kun.panguin.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kun.panguin.Adapter.ChatListAdapter;
import com.kun.panguin.Beans.User;
import com.kun.panguin.Http.HttpAsynLoadImage;
import com.kun.panguin.R;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class FragmentChat extends Fragment{
    private ListView chatListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.chat_fragment,container,false);
        chatListView= (ListView) root.findViewById(R.id.chatListView);
        HttpAsynLoadImage asynLoadImage=new HttpAsynLoadImage(getActivity(),chatListView);
        asynLoadImage.execute("12");
        return root;
    }
}
