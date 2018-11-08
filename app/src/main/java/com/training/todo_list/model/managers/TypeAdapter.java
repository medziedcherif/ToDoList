package com.training.todo_list.model.managers;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.training.todo_list.R;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;

import java.util.List;
import java.util.UUID;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHolder> {

    private List<TodoType> dataSet;
    private Context mContext;
    private TodoType tdataModel;
    private mListner listner;
    private UUID mUUID;

    public interface mListner {
        void checktype (UUID uuid);
    }

    public TypeAdapter(List<TodoType> data, Context context, mListner pListner, UUID tUUIDCheckType) {
        this.dataSet = data;
        this.mContext = context;
        this.listner = pListner;
        this.mUUID = tUUIDCheckType;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_type, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, final int i) {
        tdataModel = dataSet.get(i);
        viewHolder.txtType.setText(tdataModel.name());
        if (tdataModel.color().contentEquals("#b94a48")) {
            viewHolder.imgType.setImageResource(R.drawable.ic_emergency);
        } else if (tdataModel.color().contentEquals("#f89406")) {
            viewHolder.imgType.setImageResource(R.drawable.ic_before);
        } else {
            viewHolder.imgType.setImageResource(R.drawable.ic_normal);
        }

        if (tdataModel.id() == mUUID){
            viewHolder.imgCheck.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imgCheck.setVisibility(View.GONE);
        }
        viewHolder.linearLayoutContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.checktype(dataSet.get(i).id());
                mUUID = dataSet.get(i).id();
                   }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgType, imgCheck;
        private TextView txtType;
        private LinearLayout linearLayoutContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCheck = itemView.findViewById(R.id.image_check);
            imgType = itemView.findViewById(R.id.imgType);
            txtType = itemView.findViewById(R.id.txtType);
            linearLayoutContainer = itemView.findViewById(R.id.type_container);
        }
    }


}