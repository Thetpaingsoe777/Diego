package com.xavey.diego.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xavey.diego.R;
import com.xavey.diego.adapter.CallNumberAdapter;
import com.xavey.diego.adapter.MellerAdapter;
import com.xavey.diego.api.model.CallNumber;
import com.xavey.diego.api.model.Meller;
import com.xavey.diego.helper.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProspectsFragment extends Fragment {

    private Context mContext;

    public ProspectsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String login_user;
        login_user = getArguments().getString("login_user");

        mContext = getActivity().getApplicationContext();

        View rootView = inflater.inflate(R.layout.fragment_prospects, container, false);
        ListView lvProspects = (ListView) rootView.findViewById(R.id.listview_prospects);
        DBHelper dbHelper = new DBHelper(mContext);

        ArrayList<CallNumber> callNumbers = null;
        try {
            callNumbers = dbHelper.getNumbers(login_user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CallNumberAdapter callNumberAdapter = new CallNumberAdapter(callNumbers, mContext);
        lvProspects.setAdapter(callNumberAdapter);

        return rootView;
    }
}