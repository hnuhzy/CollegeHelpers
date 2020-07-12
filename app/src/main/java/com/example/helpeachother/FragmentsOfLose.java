package com.example.helpeachother;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Random;

public class FragmentsOfLose extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private ArrayList<LoseEntity> loseEntities = new ArrayList<>();
    private RecyclerAdapterLose mrecyclerAdapterLose;
    private LoseEntity[] Lost= {new LoseEntity("综合楼",R.drawable.umbrella),new LoseEntity("研楼",R.drawable.pen),
            new LoseEntity("图书馆",R.drawable.cup),new LoseEntity("院楼一个角落",R.drawable.watch)};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler,container,false);
        initData();
        initAdapter();
        return view;
    }

    private void initData(){
        loseEntities.clear();
        System.out.println("******创建新数据******");
        for (int i=0;i<50;i++){
            if (i%3 ==0 || i%5==0){
                LoseEntity loseEntity = new LoseEntity();
                loseEntity.setPosition("天马"+i+"号楼");
                loseEntity.setPhoneNum("xxx-xxxx-xxxx");
                loseEntity.setState(0);
                loseEntity.setInformation("非常可爱的充电宝");
                loseEntities.add(loseEntity);
            }else {
                Random random = new Random();
                int index = random.nextInt(Lost.length);
                loseEntities.add(Lost[index]);
            }
        }
    }

    private void initAdapter(){
        mRecyclerView = view.findViewById(R.id.my_recyclerView);
        mrecyclerAdapterLose = new RecyclerAdapterLose(getActivity(),loseEntities);
        mRecyclerView.setAdapter(mrecyclerAdapterLose);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

    }
}
