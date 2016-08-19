package com.example.administrator.gridviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linerLayout;
    String[] strings = new String[6];
    private TextView tv1;
    private GridView lv;
    private View view;
    private List<Map<String, Object>> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linerLayout = (LinearLayout) findViewById(R.id.main);
        new Thread(){
            @Override
            public void run() {
                super.run();
                addView();
                handler.sendEmptyMessage(1);
            }
        }.start();
        for (int i = 0; i < strings.length; i++) {
            tv1 = new TextView(this);
            tv1.setText("asd");
            linerLayout.addView(tv1);
            lv = new GridView(this);
            lv.setNumColumns(3);
            lv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            ListAdapter adapter = new SimpleAdapter(this, data, R.layout.item_gridview, new String[]{"title", "Image"},
                    new int[]{R.id.gv_text, R.id.gv_image});
            lv.setAdapter(adapter);
            lv.setBackgroundColor(Color.YELLOW);
            linerLayout.addView(lv);
            view= new View(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    20));
            view.setBackgroundColor(Color.WHITE);
            linerLayout.addView(view);
        }


    }



    private void addView() {
        Message message = new Message();
        for (int j = 0; j < strings.length; j++) {
            data = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < 3; i++) {
                map.put("title", "1"+i);
                map.put("Image", R.mipmap.ic_launcher);
                data.add(map);
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(getApplicationContext(),"数据加载完成",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


}
