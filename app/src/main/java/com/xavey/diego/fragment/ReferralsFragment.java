package com.xavey.diego.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xavey.diego.R;
import com.xavey.diego.adapter.MellerAdapter;
import com.xavey.diego.api.model.Meller;
import com.xavey.diego.helper.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ReferralsFragment extends Fragment {


    private ArrayList<String> strNameList = new ArrayList<String>();
    private ArrayList<String> strMobileList = new ArrayList<String>();
    private ArrayList<String> strGenderList= new ArrayList<String>();
    private ArrayList<Date> dateDOBList= new ArrayList<Date>();

    private Context mContext;

    String login_user;

    public ReferralsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = getActivity().getApplicationContext();

        View rootView = inflater.inflate(R.layout.fragment_referrals, container, false);
        ListView lvMeller = (ListView) rootView.findViewById(R.id.listview_referrals);
        DBHelper dbHelper = new DBHelper(mContext);

        ArrayList<Meller> mellers = null;
        try {
            mellers = dbHelper.getMellers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MellerAdapter mellerAdapter = new MellerAdapter(mellers, mContext);
        lvMeller.setAdapter(mellerAdapter);

        return rootView;
    }

}