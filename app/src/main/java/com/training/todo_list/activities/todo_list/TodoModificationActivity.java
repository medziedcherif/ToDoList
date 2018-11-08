package com.training.todo_list.activities.todo_list;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.managers.TodoTypeManager;
import com.training.todo_list.model.managers.TypeAdapter;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class TodoModificationActivity extends AppCompatActivity implements TypeAdapter.mListner {

    private TextView mDescription;
    private List<TodoType> mTypes = new ArrayList<>();
    private TypeAdapter mTypeAdapter;
    private Button mValider;
    private UUID tUUID;
    private UUID tTodoUUID;
    private boolean isModification = false;
    private Todo mTodo;
    private UUID mUUIDCheckType = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_modification);
        TodoManager tTodoManager = new TodoManager();
        Todo tTodo = tTodoManager.todoFor(tTodoUUID);
        mDescription = findViewById(R.id.edtDescription);
        mValider = findViewById(R.id.btnvalider);
        if (getIntent().getBooleanExtra("isModification", false)) {
            tTodoUUID = UUID.fromString(getIntent().getStringExtra("uid"));
            isModification = true;
            FillEdit();
        }
        if (isModification) {
            mUUIDCheckType = mTodo.idTodoType();
        }
        RecyclerView recyclerView = findViewById(R.id.list_type);
        TodoTypeManager tTodoTypeManager = new TodoTypeManager();
        mTypes = tTodoTypeManager.all();
        mTypeAdapter = new TypeAdapter(mTypes, this, this, mUUIDCheckType);
        RecyclerView.LayoutManager tLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(tLayoutManager);
        recyclerView.setAdapter(mTypeAdapter);
        mDescription.getText().toString();
        mValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mDescription.getText().toString().contentEquals("")) {
                    AddTodo();
                } else {
                    mDescription.setHint("Please fill the description");
                }
            }
        });

    }

    private void FillEdit() {
        TodoManager tTodomanager = new TodoManager();
        mTodo = tTodomanager.todoFor(tTodoUUID);
        mDescription.setText(mTodo.description());
        tUUID = mTodo.idTodoType();
    }

    private void AddTodo() {
        TodoManager tTodomanager = new TodoManager();

        if (isModification) {
            Todo tTodo = new Todo(mDescription.getText().toString(), new Date(System.currentTimeMillis()), tUUID, false, tTodoUUID);
            tTodomanager.todoEdit(tTodo, mTodo);
        } else {
            tTodomanager.create(mDescription.getText().toString(), new Date(System.currentTimeMillis()), tUUID, false);
        }

        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void checktype(UUID uuid) {
        tUUID = uuid;
        mUUIDCheckType = uuid;
        mTypeAdapter.notifyDataSetChanged();
    }
}
