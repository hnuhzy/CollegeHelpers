package com.favorites;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExamActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        mRecyclerView = findViewById(R.id.rv_exam);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ExamActivity.this));
        mRecyclerView.setAdapter(new ExamAdapter(ExamActivity.this));
    }
}
