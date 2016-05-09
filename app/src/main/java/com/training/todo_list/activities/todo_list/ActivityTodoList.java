package com.training.todo_list.activities.todo_list;

import android.app.ListActivity;
import android.os.Bundle;

import com.training.todo_list.R;

public class ActivityTodoList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lt_act_todo_list);
    }
}
