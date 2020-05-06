package com.favorites;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //private Button mBtnAdd;
    private Button mBtnBack;
   // private Button mBtnSearch;
    private Button mBtnText;
    private Button mBtnExam;
    private Button mBtnQuestion;
    private Button mBtnElse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mBtnAdd = findViewById(R.id.add);
        mBtnBack = findViewById(R.id.back);
        //mBtnSearch = findViewById(R.id.search);
        mBtnText = findViewById(R.id.text);
        mBtnExam = findViewById(R.id.exam);
        mBtnQuestion = findViewById(R.id.question);
        mBtnElse = findViewById(R.id.els);
        OnClick onClick = new OnClick();
        mBtnBack.setOnClickListener(onClick);
        mBtnQuestion.setOnClickListener(onClick);
        mBtnText.setOnClickListener(onClick);
        mBtnElse.setOnClickListener(onClick);
        mBtnExam.setOnClickListener(onClick);


    }
    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent;
            switch(v.getId()){
                case R.id.back:
                    Toast.makeText(MainActivity.this,"Return to the home page",Toast.LENGTH_LONG).show();
                    break;
                case R.id.text:
                    intent = new Intent(MainActivity.this, TextActivity.class);
                    startActivity(intent);
                    break;
                case R.id.exam:
                    intent = new Intent(MainActivity.this, ExamActivity.class);
                    startActivity(intent);
                    break;
                case R.id.question:
                    intent = new Intent(MainActivity.this, QuestionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.els:
                    intent = new Intent(MainActivity.this, ElseActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
