package com.favorites;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        mRecyclerView = findViewById(R.id.rv_question);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(QuestionActivity.this));
        mRecyclerView.setAdapter(new ExamAdapter(QuestionActivity.this));
    }
}
