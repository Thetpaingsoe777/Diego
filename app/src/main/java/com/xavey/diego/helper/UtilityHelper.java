package com.xavey.diego.helper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import com.xavey.diego.R;
import com.xavey.diego.api.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by tinmaungaye on 5/4/15.
 */
public class UtilityHelper {

    public static final int[] itemColors = new int[] { R.color.ans_em_1, R.color.ans_em_2,R.color.ans_em_3,R.color.ans_em_4,R.color.ans_em_5,
                                                            R.color.ans_em_6, R.color.ans_em_7,R.color.ans_em_8,R.color.ans_em_9,R.color.ans_em_10 };
    public static final String PREFS_NAME = "Woody_Settings";
    public static final String PREFS_ZAWGYI = "Woody_ZAWGYI";
    public static final String PREFS_MP = "Woody_MP";
    public static final String PREFS_FB = "Woody_FB";
    public static final String PREFS_P_COMPLETED = "Woody_p_compelted";

    public static void setActionBarTitleColor(Activity act){
        ActionBar actionBar = act.getActionBar();
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
    }

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    /**
     * get datetime
     * */

    public static String getDateTime(Date d, boolean dateOnly) {
        SimpleDateFormat dateFormat = null;

        if (dateOnly) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        }

        return dateFormat.format(d);

    }

    public static Date getDateTime(String d, boolean dateOnly) {
        SimpleDateFormat format = null;
        if (dateOnly) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        else {
            format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        }
        Date formattedDate = null;
        try {
            formattedDate = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }

    public static Boolean getBoolean(Integer b){
        return b==1? true:false;
    }

    public static Boolean isOldEnough(DatePicker datePicker, int checkYear){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        calendar.add(Calendar.YEAR, checkYear);

        Date dt = new Date();
        return dt.before(calendar.getTime());
    }

    public static CharSequence relativeTimespan(Date toCompare){
        Date now = new Date();
        return DateUtils.getRelativeTimeSpanString(toCompare.getTime(), now.getTime(), 0);
    }

    public static void hideSoftKeyboard(Activity act) {
        if(act.getCurrentFocus()!=null) {
            Log.i("hidesoftkey",act.getCurrentFocus().getClass().getName());
            InputMethodManager inputMethodManager = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(act.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(Activity act) {
        if(act.getCurrentFocus()!=null) {
            Log.i("showsoftkey",act.getCurrentFocus().getClass().getName());
            InputMethodManager inputMethodManager = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(act.getCurrentFocus(), 1);
        }
    }

    public static boolean getZawGyiDisplay(Context mContext){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        return settings.getBoolean(PREFS_ZAWGYI, true);
    }

    public static void setZawGyiDisplay(Context mContext, boolean mValue){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PREFS_ZAWGYI, mValue);
        editor.commit();
    }

    public static int getMellPointLocal(Context mContext){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        return settings.getInt(PREFS_MP, 0);
    }

    public static void setMellPointLocal(Context mContext, int mValue){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(PREFS_MP, mValue);
        editor.commit();
    }

    public static String getMyFB(Context mContext){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        return settings.getString(PREFS_FB, "");
    }

    public static void setMyFB(Context mContext, String mValue){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREFS_FB, mValue);
        editor.commit();
    }

    public static Boolean getPCompleted(Context mContext){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        return settings.getBoolean(PREFS_P_COMPLETED, false);
    }

    public static void setPCompleted(Context mContext, Boolean mValue){
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PREFS_P_COMPLETED, mValue);
        editor.commit();
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
