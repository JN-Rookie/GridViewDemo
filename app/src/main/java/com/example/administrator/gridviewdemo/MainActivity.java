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
    String[] strings = new String[3];
    private TextView tv1;
    private MyGridView lv;
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
            RelativeLayout layout=new RelativeLayout(this);
            layout.setBackgroundColor(Color.rgb(249,248,248));
            tv1 = new TextView(this);
            tv1.setText("asd");
            tv1.setTextSize(20);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);//与父容器的左侧对齐
            lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);//与父容器的上侧对齐
            lp.leftMargin=30;
            lp.topMargin=30;
            tv1.setId(R.id.my_text);//设置这个View 的id
            tv1.setLayoutParams(lp);//设置布局参数
            layout.addView(tv1);
            lv = new MyGridView(this);
            lv.setNumColumns(3);
            lv.setId(R.id.my_gridview);
            RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp1.addRule(RelativeLayout.BELOW, R.id.my_text);
            lp1.topMargin=20;
            lp1.bottomMargin=20;
            lv.setLayoutParams(lp1);
            ListAdapter adapter = new SimpleAdapter(this, data, R.layout.item_gridview, new String[]{"title", "Image"},
                    new int[]{R.id.gv_text, R.id.gv_image});
            lv.setAdapter(adapter);
            layout.addView(lv);
            view= new View(this);
            RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, 20);
            view.setBackgroundColor(Color.WHITE);
            lp2.addRule(RelativeLayout.BELOW,R.id.my_gridview);
            view.setId(R.id.my_view);
            view.setLayoutParams(lp2);
            if(i<strings.length-1){
                layout.addView(view);
            }

            linerLayout.addView(layout);
        }


    }



    private void addView() {
        Message message = new Message();
        for (int j = 0; j < strings.length; j++) {
            data = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < 7; i++) {
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
