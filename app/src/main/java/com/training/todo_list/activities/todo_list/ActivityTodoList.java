package com.training.todo_list.activities.todo_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.model.managers.CustomAdapter;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.models.Todo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActivityTodoList extends Activity {

    private List<Todo> todos = new ArrayList<>();
    public static int RESULT_FOR = 100;
    private ListView mList;
    private CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);
        mList = (ListView) findViewById(R.id.list);
        TodoManager tTodoManager = new TodoManager();
        todos = tTodoManager.all();
        mCustomAdapter = new CustomAdapter(todos, this);
        mList.setAdapter(mCustomAdapter);
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!todos.get(position).isDone()) {
                    Intent intent = new Intent(ActivityTodoList.this, TodoModificationActivity.class);
                    intent.putExtra("isModification", true);
                    intent.putExtra("uid", todos.get(position).id().toString());
                    startActivityForResult(intent, RESULT_FOR);
                } else {
                    Toast.makeText(ActivityTodoList.this, "Sorry , this task is already done! ", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoManager tTodoManager = new TodoManager();
                tTodoManager.todoDone(todos.get(position));
                todos.clear();
                todos.addAll(tTodoManager.all());
                mCustomAdapter.notifyDataSetChanged();
            }
        });

    }


    public void askAddTodo(View pView) {
//        Toast.makeText(this, "Ask add todo", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TodoModificationActivity.class);
        intent.putExtra("isModification", false);
        startActivityForResult(intent, RESULT_FOR);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED) {
            TodoManager tTodoManager = new TodoManager();
            todos.clear();
            todos.addAll(tTodoManager.all());
            mCustomAdapter.notifyDataSetChanged();

        }

    }
}
