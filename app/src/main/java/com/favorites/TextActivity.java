package com.favorites;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TextActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mRecyclerView = findViewById(R.id.rv_text);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(TextActivity.this));
        mRecyclerView.setAdapter(new ExamAdapter(TextActivity.this));
    }
}
