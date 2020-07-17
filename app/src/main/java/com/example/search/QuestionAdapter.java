package com.example.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {


    private  int resourceid;
    public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
        resourceid = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Question question = getItem(position);//获取当前listbiew的item实例
        View view = LayoutInflater.from(getContext()).inflate(resourceid,parent,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.question_picture);
        TextView textView = (TextView) view.findViewById(R.id.time);
        imageView.setImageResource(question.getimageid());
        textView.setText(question.getTime());
        return  view;
    }
}
