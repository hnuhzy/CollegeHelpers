package com.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private Context mContext;
    public QuestionAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionAdapter.QuestionViewHolder(LayoutInflater.from(mContext).inflate(R.layout.favorites_item,parent,false));
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.QuestionViewHolder holder, int position) {
        holder.tvTime.setText("2020-04-08");
    }

    @Override
    public int getItemCount() {
        return 30;
    }
    class QuestionViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTime;
        private ImageView mPic;
        private TextView tvContent;
        private Button mBtnDel;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.fav_time);
            mPic = itemView.findViewById(R.id.fav_picture);
            tvContent = itemView.findViewById(R.id.fav_content);
            mBtnDel = itemView.findViewById(R.id.fav_del);
        }
    }
}
