package com.example.sbs.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle("할일 상세보기");

        String todo = getIntent().getStringExtra("todo");

        TextView textViewTodo = findViewById(R.id.activity_detail__textViewTodo);
        textViewTodo.setText(todo);
        textViewTodo.setOnClickListener(view -> {
            finish();
        });
    }
}