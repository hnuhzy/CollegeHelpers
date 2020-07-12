package com.example.helpeachother;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentsOfSend extends Fragment {
    private View view;
    public RecyclerView mRecyclerView;
    private ArrayList<GoodsEntity> goodsEntities = new ArrayList<>();
    private RecyclerAdapterSend mRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        System.out.println("在构造适配器了！");
        view = inflater.inflate(R.layout.fragment_recycler,container,false);
        initData();//模拟数据
        initRecyclerView();
        return view;
    }

    private void initData(){
//        System.out.println("开始新数据模拟");
        for (int i=0;i<10;i++){
            GoodsEntity goodsEntity = new GoodsEntity();
            goodsEntity.setScore(i*10);
            goodsEntity.setRequestTime(i+"小时");
            goodsEntity.setInfomation("非常好吃的零食");
            goodsEntities.add(goodsEntity);
        }
    }
    private void initRecyclerView(){
//        System.out.println("开始构造适配器");
        mRecyclerView = view.findViewById(R.id.my_recyclerView);
        mRecyclerAdapter = new RecyclerAdapterSend(getActivity(),goodsEntities);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
//        mRecyclerAdapter.setOnItemClickListener(new RecyclerAdapter.onItemClickListener() {
//            @Override
//            public void OnItemClick(View view, GoodsEntity data) {
//                Toast.makeText(getActivity(),"item", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
