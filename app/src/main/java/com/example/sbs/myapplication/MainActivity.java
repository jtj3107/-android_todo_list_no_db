package com.example.sbs.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText editTextTodo;
    private List<String> todos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todos = new ArrayList<>();

        editTextTodo = findViewById(R.id.activity_main__editTextTodo);
    }

    public void btnAddTodoClicked(View view) {
        String newTodo = editTextTodo.getText().toString().trim();
        editTextTodo.setText(newTodo);

        if(newTodo.length() == 0) {
            Toast.makeText(this, "할일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextTodo.requestFocus();

            return;
        }

        todos.add(newTodo);
        editTextTodo.setText("");
        editTextTodo.requestFocus();

        Log.d(TAG, "todos : " + todos);
    }
}