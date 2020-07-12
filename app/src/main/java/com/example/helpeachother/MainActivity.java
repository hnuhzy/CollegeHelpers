package com.example.helpeachother;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mTopImage;
    private List<String> mTabTitle = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetConnection();//与组件对应
        SetTabAndVP();
        setSupportActionBar(mToolbar);

    }

    private void SetConnection(){
        mToolbar = findViewById(R.id.tool_bar);
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mTopImage = findViewById(R.id.Top_image);
    }
    private void SetTabAndVP(){
        mTabTitle.add("快递跑腿");
        mTabTitle.add("技能互助");
        mTabTitle.add("失物招领");

        mFragments.add(new FragmentsOfSend());
        mFragments.add(new FragmentsOfSkill());
        mFragments.add(new FragmentsOfLose());

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //super.destroyItem(container, position, object);
            }
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitle.get(position);
            }
        });
       mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //此方法在滑动ViewPager的时候一直被调用，页面在滑动过程中不停触发该方法：“position”按照api的解释是“目前显示在屏幕上的第一个页面，只要positionOffset不为0，那么他后面的页面同样是可见的”
            //position 返回滑动结束后页面的下标
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        Glide.with(MainActivity.this).load(R.drawable.bg_send).into(mTopImage);
                        break;
                    case 1:
                        Glide.with(MainActivity.this).load(R.drawable.bg_skill).into(mTopImage);
                        break;
                    case 2:
                        Glide.with(MainActivity.this).load(R.drawable.bg_lose).into(mTopImage);
                        break;
                        default:break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Toast.makeText(this,"添加",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


}
