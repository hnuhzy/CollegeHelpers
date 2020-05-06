package com.favorites;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ElseActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_else);
        mRecyclerView = findViewById(R.id.rv_else);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ElseActivity.this));
        mRecyclerView.setAdapter(new ExamAdapter(ElseActivity.this));
    }
}
