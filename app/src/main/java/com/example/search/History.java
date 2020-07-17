package com.example.search;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class History extends AppCompatActivity {

    private  List<Question> list = new ArrayList<>();//listview中要显示的item实例
    private  List<View> listview = new ArrayList<>();//viewpage中要显示的listview实例
    private RadioGroup radioGroup ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        final ViewPager viewPager = findViewById(R.id.history_viewpager);
        Toolbar toolbar = findViewById(R.id.history_toolbar);
        radioGroup = findViewById(R.id.radiogroup_history);
        //设置状态栏的颜色为亮色，字体为深色
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
           //设置状态栏的颜色为亮色，字体为深色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //改变状态栏颜色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置为白色
            getWindow().setStatusBarColor(Color.WHITE);
        }
        //默认显示左上角返回键
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //为listview初始化item实例
        init();
        //获取listview的布局文件，创建实例
        ListView listView0 =  getLayoutInflater().from(this).inflate(R.layout.listview_all,null).findViewById(R.id.listview);
        ListView listView1 =  getLayoutInflater().from(this).inflate(R.layout.listview_all,null).findViewById(R.id.listview);
        ListView listView2 =  getLayoutInflater().from(this).inflate(R.layout.listview_all,null).findViewById(R.id.listview);
        //加入容器
        listview.add(listView0);
        listview.add(listView1);
        listview.add(listView2);
        //设置listview的适配器
        final QuestionAdapter questionAdapter0 = new QuestionAdapter(History.this,R.layout.historyitem,list);
        listView0.setAdapter(questionAdapter0);
        listView1.setAdapter(questionAdapter0);
        listView2.setAdapter(questionAdapter0);
        //设置listview的item点击事件监听,跳转到题目详细界面
        listView0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id>=0){
                    Intent intent = new Intent(History.this,Message.class);
                    intent.putExtra("activity_name","History");
                    startActivity(intent);
                    finish();
                }
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id>=0){
                    Intent intent = new Intent(History.this,Message.class);
                    intent.putExtra("activity_name","History");
                    startActivity(intent);
                    finish();
                }
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id>=0){
                    Intent intent = new Intent(History.this,Message.class);
                    intent.putExtra("activity_name","History");
                    startActivity(intent);
                    finish();
                }
            }
        });
        //设置listview的item长按事件监听,删除操作
        listView0.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(History.this);
                builder.setMessage("确定删除?");
                builder.setTitle("提示");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (list.remove(position)!=null){
                            Toast.makeText(History.this,"删除成功",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(History.this,"删除失败",Toast.LENGTH_SHORT).show();
                        }
                        questionAdapter0.notifyDataSetChanged();
                    }
                });

                builder.create().show();


                return true;
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.button1_history:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.button2_history:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.button3_history:
                        viewPager.setCurrentItem(2);
                        break;
                    default:break;
                }
            }
        });
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listview.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                try {
                    if (listview.get(position).getParent()==null){
                        View view = listview.get(position);
                        container.addView(view);
                    }else{
                        //因为viewpager只能存放一个直接子布局，切换时要将本页的listview删掉，才能切换下一页
                        ((ViewGroup)listview.get(position).getParent()).removeView(listview.get(position));
                        container.addView(listview.get(position));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return listview.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(listview.get(position));
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    switch (position){
                        case 0:
                            radioGroup.check(R.id.button1);
                            break;
                        case 1:
                            radioGroup.check(R.id.button2);
                            break;
                        case 2:
                            radioGroup.check(R.id.button3);
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

        getMenuInflater().inflate(R.menu.historybar,menu);
        return true;
    }
    //菜单监听事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                Intent intent = new Intent(History.this,MainActivity.class);
               startActivity(intent);
               finish();
        }
        return super.onOptionsItemSelected(item);
    }
   //初始化listview
    private void init() {
        for (int i = 0; i < 10; i++) {
            Question question = new Question("今天",R.drawable.question);
            list.add(question);
        }
    }
}
