package com.example.helpeachother;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterSend extends RecyclerView.Adapter<RecyclerAdapterSend.myViewHolder>{
    private Context context;
    private ArrayList<GoodsEntity> goodsEntityList;

    class myViewHolder extends RecyclerView.ViewHolder{

        private ImageView mitemState;
        private TextView mitemScore;
        private TextView mitemTime;
        private TextView miteminfo;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            mitemScore = itemView.findViewById(R.id.score);
            mitemState = itemView.findViewById(R.id.image_state);
            mitemTime = itemView.findViewById(R.id.time_request);
            miteminfo = itemView.findViewById(R.id.info);
        }
    }

    public RecyclerAdapterSend(Context context, ArrayList<GoodsEntity> goodsEntityList){
        this.context = context;
        this.goodsEntityList = goodsEntityList;
    }

    public myViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_sendquickly,parent,false);
        final myViewHolder holder = new myViewHolder(view);
        holder.mitemState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"点击了状态",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        GoodsEntity data = goodsEntityList.get(position);
        holder.mitemTime.setText(data.getRequestTime());
        holder.miteminfo.setText(data.getInfomation());
        holder.mitemScore.setText(""+data.getScore());
    }

    @Override
    public int getItemCount() {
        return goodsEntityList.size();
    }




//    public interface onItemClickListener{
//        public void OnItemClick(View view,GoodsEntity data);
//    }

//    private onItemClickListener onItemClickListener;
//    public void setOnItemClickListener(onItemClickListener onItemClickListener){
//        this.onItemClickListener = onItemClickListener;
//    }


}
