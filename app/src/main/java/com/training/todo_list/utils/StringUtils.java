package com.training.todo_list.utils;

import android.content.res.Resources;
import android.graphics.Color;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StringUtils {

    public static boolean isNullOrEmpty(String pString) {
        return null == pString || "".equals(pString);
    }

    public static boolean areEquals(String pSFirst, String pSSecond) {
        if (null == pSFirst)
            return null == pSSecond;
        if (null == pSSecond)
            return false;
        return pSFirst.equals(pSSecond);
    }


    private WeakReference<Resources> mRes;


    public StringUtils(Resources pRes) {
        mRes = new WeakReference<>(pRes);
    }


    public String stringFor(int pIResId) {
        Resources tRes = mRes.get();
        if (null != tRes)
            return tRes.getString(pIResId);
        return "";
    }

    public String dayFor(Date pDate) {
        DateFormat tDateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ENGLISH);
        tDateFormat.setTimeZone(TimeZone.getDefault());
        return tDateFormat.format(pDate);
    }

    public String timeFor(Date pDate) {
        DateFormat tDateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.ENGLISH);
        tDateFormat.setTimeZone(TimeZone.getDefault());
        return tDateFormat.format(pDate);
    }

    public int colorFor(String pSColor, int pIDefault) {
        if (isNullOrEmpty(pSColor))
            return pIDefault;
        try {
            return Color.parseColor(pSColor);
        } catch (IllegalArgumentException e) {
            return pIDefault;
        }
    }
}
