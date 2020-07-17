package com.example.search;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Question {

    private  String time;
    private  int imageid;

    public Question(String time, int imageid){
        this.time = time;
        this.imageid = imageid;
    }

    public String getTime() {
        return time;
    }

    public int getimageid() {
        return imageid;
    }
}
