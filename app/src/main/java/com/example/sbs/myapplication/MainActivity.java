package com.example.sbs.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText editTextTodo;
    private List<String> todos;
    ArrayAdapter<String> listViewTodoAdapter;

    private void addTodo(String newTodo) {
        todos.add(0, newTodo);
        listViewTodoAdapter.notifyDataSetChanged();
    }

    private void deleteTodo(int index) {
        todos.remove(index);
        listViewTodoAdapter.notifyDataSetChanged();
    }

    private void deleteAllTodos() {
        todos.clear();
        listViewTodoAdapter.notifyDataSetChanged();
    }

    private void makeTestData() {
        addTodo("안녕하세요.");
        addTodo("반갑습니다.");
        addTodo("또만나요.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("할일 리스트");

        todos = new ArrayList<>();
        editTextTodo = findViewById(R.id.activity_main__editTextTodo);

        ListView listViewTodo = findViewById(R.id.main_activity__listViewTodo);

        listViewTodo.setOnItemClickListener((adapterView, view, position, id) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            String todo = todos.get(position);
            intent.putExtra("todo", todo);
            startActivity(intent);
        });

        listViewTodo.setOnItemLongClickListener((adapterView, view, position, id) -> {
            final int indexToDelete = position;

            DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        deleteTodo(indexToDelete);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            };

            new AlertDialog.Builder(this)
                    .setMessage("삭제하시겠습니까?")
                    .setPositiveButton("예", onClickListener)
                    .setNegativeButton("아니오", onClickListener)
                    .show();

            return false;
        });

        listViewTodoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todos);
        listViewTodo.setAdapter(listViewTodoAdapter);

        makeTestData();
    }

    public void btnAddTodoClicked(View view) {
        String newTodo = editTextTodo.getText().toString().trim();
        editTextTodo.setText(newTodo);

        if (newTodo.length() == 0) {
            Toast.makeText(this, "할일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            editTextTodo.requestFocus();

            return;
        }

        addTodo(newTodo);

        editTextTodo.setText("");
        editTextTodo.requestFocus();

    }

    public void btnDeleteAllTodosClicked(View view) {
        if (todos.size() == 0) {
            Toast.makeText(this, "할일이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        DialogInterface.OnClickListener onClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    deleteAllTodos();
                    Toast.makeText(this, "모든 할일이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        new AlertDialog.Builder(this)
                .setMessage("정말 전부 삭제하시겠습니까?")
                .setPositiveButton("예", onClickListener)
                .setNegativeButton("아니오", onClickListener)
                .show();
    }

    public void btnFinishAppClicked(View view) {
        finish();
    }
}