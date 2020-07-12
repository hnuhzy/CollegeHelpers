package com.example.helpeachother;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapterLose extends RecyclerView.Adapter<RecyclerAdapterLose.LoseViewHolder>{
    private Context context;
    private ArrayList<LoseEntity> loseEntityList;

    class LoseViewHolder extends RecyclerView.ViewHolder{

        private ImageView mloseState;
        private TextView mloseScore;
        private TextView mlosePhone;
        private TextView mlosePosition;
        private ImageView mlosething;
        private TextView mloseInfo;

        public LoseViewHolder(@NonNull View itemView) {
            super(itemView);
            mlosePhone = itemView.findViewById(R.id.connect_ways);
            mloseScore = itemView.findViewById(R.id.money);
            mloseState = itemView.findViewById(R.id.things_state);
            mlosePosition = itemView.findViewById(R.id.lose_position);
            mlosething = itemView.findViewById(R.id.lose_things);
            mloseInfo = itemView.findViewById(R.id.lose_info);
        }
    }

    public RecyclerAdapterLose(Context context,ArrayList<LoseEntity> loseEntityList){
        this.context = context;
        this.loseEntityList = loseEntityList;
    }

    @NonNull
    @Override
    public LoseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context = parent.getContext();
        }
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_losethings,parent,false);
        final LoseViewHolder holder = new LoseViewHolder(view);
        holder.mloseState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"点击了状态",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoseViewHolder holder, int position) {
        LoseEntity entity = loseEntityList.get(position);
        holder.mloseState.setImageResource(entity.getState());
        holder.mloseScore.setText(entity.getScore());
        holder.mlosePhone.setText(entity.getPhoneNum());
        holder.mlosePosition.setText(entity.getPosition());
        holder.mloseInfo.setText(entity.getInformation());
        Glide.with(context).load(entity.getLoseImageId()).into(holder.mlosething);
    }

    @Override
    public int getItemCount() {
        return loseEntityList.size();
    }

}
