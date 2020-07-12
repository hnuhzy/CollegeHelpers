package com.example.helpeachother;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.PipedReader;
import java.util.ArrayList;

public class RecyclerAdapterSkill extends RecyclerView.Adapter<RecyclerAdapterSkill.SkillViewHolder>{
    private Context context;
    private ArrayList<SkillEntity> skillEntityList;

    class SkillViewHolder extends RecyclerView.ViewHolder{
        private ImageView mSkillImage;
        private TextView mSkillPosition;
        private TextView mSkillWorth;
        private TextView mSkillPhone;
        private TextView mSkillinfo;
        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            mSkillImage = itemView.findViewById(R.id.Skill_img);
            mSkillPosition = itemView.findViewById(R.id.SkillTime);
            mSkillWorth = itemView.findViewById(R.id.SkillScore);
            mSkillPhone = itemView.findViewById(R.id.SkillerPhone);
            mSkillinfo = itemView.findViewById(R.id.Skill_info);
        }
    }

    public RecyclerAdapterSkill(Context context,ArrayList<SkillEntity> skillEntityList){
        this.context = context;
        this.skillEntityList = skillEntityList;
    }


    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_skillhelp,parent,false);
        SkillViewHolder holder = new SkillViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        SkillEntity skills = skillEntityList.get(position);
        holder.mSkillPosition.setText(skills.getSkillPosition());
        holder.mSkillWorth.setText(""+skills.getSkillWorth());
        holder.mSkillPhone.setText(skills.getSkillPhone());
        holder.mSkillinfo.setText(skills.getSkillinfo());
        Glide.with(context).load(skills.getSkillPic()).into(holder.mSkillImage);
    }

    @Override
    public int getItemCount() {
        return skillEntityList.size();
    }
}
