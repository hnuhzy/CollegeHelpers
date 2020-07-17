package com.example.search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {

    private ViewPager viewPager;
    private List<View> list ; //用来装viewpager要显示的view
    private RadioGroup tab;   //三个选择按钮
    private ImageButton camera;
    private  ImageButton return1;
    private int toolbar;
    private FloatingActionButton floatingActionButton;
    private NestedScrollView nestedScrollView;
    private  String activity_name;
    private SwipeRefreshLayout swipeRefreshLayout;
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        //顶部状态栏设置
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            //全屏、状态栏为高亮，则字体问深色（灰色/黑色）
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //选择要设置颜色的部分，为背景
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //背景一开始为透明，这里设置没有明显效果，因为之前的图片已经是沉浸模式，相当于透明效果
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        nestedScrollView = findViewById(R.id.nestedscorllerview);
        swipeRefreshLayout = findViewById(R.id.swipefreshlayout);
        floatingActionButton = findViewById(R.id.floatbutton);
        list = new ArrayList<View>();
        list.add(LayoutInflater.from(this).inflate(R.layout.page01,null));
        list.add(LayoutInflater.from(this).inflate(R.layout.page02,null));
        list.add(LayoutInflater.from(this).inflate(R.layout.page03,null));
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tab = (RadioGroup)findViewById(R.id.radiogroup);
        //显示默认左上角的返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //不显示标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //刷新界面时转圈的颜色
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimaryDark);
        //刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshQuestion();
            }
        });
        //悬浮按钮的监听事件，置顶功能
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Message.this,"置顶",Toast.LENGTH_SHORT).show();
            }
        });

        //viewpager设置适配器PagerAdapter
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = list.get(position);
                container.addView(view);
                return  view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(list.get(position));
            }
        });
        //三个按钮设置选项监听
        tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case  R.id.button1:
                        viewPager.setCurrentItem(0);
                        break;
                    case  R.id.button2:
                        viewPager.setCurrentItem(1);
                        break;
                    case  R.id.button3:
                        viewPager.setCurrentItem(2);
                        break;

                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //默认滑动监听事件
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //设置选项监听,viewpager随着按钮的选择进行滑页
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tab.check(R.id.button1);
                        break;
                    case 1:
                        tab.check(R.id.button2);
                        break;
                    case 2:
                        tab.check(R.id.button3);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //创建标题栏菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return  true;
    }
    //为菜单的选项添加监听事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //调用摄像头
            case R.id.camera1:
                //存储地址为内部存储/Android/data/相应的包名/cache
                File outputImage = new File(getExternalCacheDir(),"ouputimage.jpg");
                try{
                    if (outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }

                Uri uri;
                if (Build.VERSION.SDK_INT>=24){
                    uri = FileProvider.getUriForFile(Message.this,"com.example.search.fileprovider",outputImage);
                }else {
                    uri = Uri.fromFile(outputImage);
                }
                //调起摄像头
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent,1);
                break;
                //左上角返回键监听事件,根据上一个跳转过来的activity进行相应的返回
            case android.R.id.home:
                //activity_name为上一个activity传过来的类名
                activity_name = this.getIntent().getStringExtra("activity_name");
                if (activity_name.equals("Mainactivity")){
                    Intent intent1 = new Intent(Message.this,MainActivity.class);
                    startActivity(intent1);
                }else if (activity_name.equals("History")){
                    Intent intent2 = new Intent(Message.this,History.class);
                    startActivity(intent2);
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //设置系统返回键的监听事件，返回到搜题界面
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Message.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }


    //下拉刷新逻辑功能实现
    private void refreshQuestion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //可以用来更新数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();
    }
}
