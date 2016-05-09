package com.training.todo_list.model.managers;

import com.training.todo_list.model.models.TodoType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TodoTypeManager {

    private static final Set<TodoType> mTodoTypes = new HashSet<>();


    public List<TodoType> all() {
        return new ArrayList<>(mTodoTypes);
    }


    public void save(TodoType pTodoType) {
        if (null != pTodoType)
            mTodoTypes.add(pTodoType);
    }

    public TodoType create(String pSName, String pSColor) {
        UUID tIdNew = UUID.randomUUID();
        TodoType tTodoType = new TodoType(pSName, pSColor, tIdNew);
        mTodoTypes.add(tTodoType);
        return tTodoType;
    }
}
