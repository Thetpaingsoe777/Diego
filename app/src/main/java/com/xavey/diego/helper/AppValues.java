package com.xavey.diego.helper;

import android.os.Environment;

import com.xavey.diego.adapter.CallNumberAdapter;
import com.xavey.diego.adapter.MellerAdapter;
import com.xavey.diego.api.model.CallNumber;
import com.xavey.diego.api.model.Meller;
import com.xavey.diego.api.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinmaungaye on 5/19/15.
 */
public class AppValues{
    private static AppValues mInstance = null;

    private static String root = Environment.getExternalStorageDirectory().toString();
    public static File XAVEY_DIRECTORY = new File(root,"/MellOffline");

    public static User loginUser = new User();

    public static String GCM_ID="";

    public static MellerAdapter mellerAdapter;
    public ArrayList<Meller> mellers = null;
    public static CallNumberAdapter callNumberAdapter;
    public ArrayList<CallNumber> mNumbers = null;

    private AppValues(){
    }

    public static AppValues getInstance(){
        if(mInstance == null)
        {
            mInstance = new AppValues();
        }
        return mInstance;
    }

    public static String getGcmId() {
        return GCM_ID;
    }

    public static void setGcmId(String gcmId) {
        GCM_ID = gcmId;
    }

}
