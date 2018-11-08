package com.training.todo_list.model.managers;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.training.todo_list.R;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Todo> {

    private List<Todo> dataSet;
    Context mContext;


    // View lookup cache
    private static class ViewHolder {
        TextView txtDescription, txtType;
        CardView rltContainer, doneContainer;
    }

    public CustomAdapter(List<Todo> data, Context context) {
        super(context, R.layout.item_todo, data);
        this.dataSet = data;
        this.mContext = context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Todo dataModel = getItem(position);
        ViewHolder viewHolder;

        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.item_todo, parent, false);
        viewHolder.txtDescription = convertView.findViewById(R.id.txtDescription);
        viewHolder.rltContainer = convertView.findViewById(R.id.todo_container);
        viewHolder.txtType =  convertView.findViewById(R.id.typeTxt);
        viewHolder.doneContainer = convertView.findViewById(R.id.done_container);
        TodoTypeManager mTodoTypeManager = new TodoTypeManager();
        TodoType tTodoType = mTodoTypeManager.todoTypeFor(dataModel.idTodoType());
        viewHolder.rltContainer.setCardBackgroundColor(Color.parseColor(tTodoType.color()));
        viewHolder.txtDescription.setText(dataModel.description());
        viewHolder.txtType.setText(tTodoType.name());
        if (dataModel.isDone()) {
            viewHolder.rltContainer.setVisibility(View.GONE);
            viewHolder.doneContainer.setCardBackgroundColor(Color.parseColor("#BDE2B4"));
        } else {
            viewHolder.doneContainer.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            viewHolder.rltContainer.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}