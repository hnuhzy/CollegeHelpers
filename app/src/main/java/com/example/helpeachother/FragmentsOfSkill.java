package com.example.helpeachother;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class FragmentsOfSkill extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private ArrayList<SkillEntity> skillEntityList = new ArrayList<>();
    private RecyclerAdapterSkill mrecyclerAdapterSkill;
    private SkillEntity[] Skills={new SkillEntity("修钢笔",R.drawable.pen),new SkillEntity("水杯完全无痕拼接",R.drawable.cup),
    new SkillEntity("重做雨伞骨架",R.drawable.umbrella),new SkillEntity("手表拆开重装",R.drawable.watch)};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler,container,false);
        initData();
        initAdapter();
        return view;
    }

    private void initData(){
        skillEntityList.clear();
        for (int i=0;i<20;i++){
            if (i%3==0){
                SkillEntity skillEntity = new SkillEntity();
                skillEntity.setSkillPhone(""+i*23112);
                skillEntity.setSkillPosition("德智"+i+"号楼");
                skillEntity.setSkillWorth(i*100);
                skillEntityList.add(skillEntity);
            }else{
                Random random = new Random();
                int index = random.nextInt(Skills.length);
                skillEntityList.add(Skills[index]);
            }
        }
    }
    private void initAdapter(){
        mRecyclerView = view.findViewById(R.id.my_recyclerView);
        mrecyclerAdapterSkill = new RecyclerAdapterSkill(getContext(),skillEntityList);
        mRecyclerView.setAdapter(mrecyclerAdapterSkill);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

    }
}
