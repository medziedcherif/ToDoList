package com.training.todo_list.model.models;

import com.training.todo_list.utils.StringUtils;

import java.util.Date;
import java.util.UUID;

public class Todo {

    private String mSDescription;
    private Date mDayCreation;
    private UUID mIdTodoType;
    private boolean mBIsDone;
    private UUID mId;

    public Todo(String pSDescription, Date pDayCreation,
                UUID pIdTodoType, boolean pBIsDone, UUID pId) {
        mSDescription = pSDescription;
        mDayCreation = pDayCreation;
        mIdTodoType = pIdTodoType;
        mBIsDone = pBIsDone;
        mId = pId;
    }

    public String description() {
        return mSDescription;
    }

    public Date dayCreation() {
        return mDayCreation;
    }

    public UUID mIdTodoType() {
        return mIdTodoType;
    }

    public boolean isDone() {
        return mBIsDone;
    }

    public UUID id() {
        return mId;
    }

    @Override
    public int hashCode() {
        String tSName = (null == mSDescription) ? "desc" : mSDescription;
        UUID tId = (null == mId) ? UUID.randomUUID() : mId;
        return tSName.hashCode() * 20 + tId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Todo))
            return false;
        Todo tOther = (Todo) o;
        boolean tBNameEquals = StringUtils.areEquals(mSDescription, tOther.mSDescription);
        boolean tBIdEquals = (null == mId && null == tOther.mId) ||
                (null != mId && null != tOther.mId && mId.equals(tOther.mId));
        return tBNameEquals && tBIdEquals;
    }
}
