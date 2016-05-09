package com.training.todo_list.model.managers;

import com.training.todo_list.model.models.Todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TodoManager {

    private static final Set<Todo> mTodos = new HashSet<>();


    public List<Todo> all() {
        return new ArrayList<>(mTodos);
    }


    public void save(Todo pTodo) {
        if (null != pTodo)
            mTodos.add(pTodo);
    }


    public Todo create(String pSDescription, Date pDateCreation, UUID pIdTodoType, boolean pBIsDone) {
        UUID tIdNew = UUID.randomUUID();
        Todo tTodo = new Todo(pSDescription, pDateCreation, pIdTodoType, pBIsDone, tIdNew);
        mTodos.add(tTodo);
        return tTodo;
    }
}
