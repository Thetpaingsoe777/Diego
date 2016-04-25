package com.xavey.diego.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xavey.diego.R;
import com.xavey.diego.activity.CreateActivity;
import com.xavey.diego.adapter.CallNumberAdapter;
import com.xavey.diego.api.model.CallNumber;
import com.xavey.diego.helper.DBHelper;

import java.util.ArrayList;


public class ProspectsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private Context mContext;
    private ArrayList<CallNumber> mNumbers = null;

    public ProspectsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mContext = getActivity().getApplicationContext();

        View rootView = inflater.inflate(R.layout.fragment_prospects, container, false);
        ListView lvProspects = (ListView) rootView.findViewById(R.id.listview_prospects);
        DBHelper dbHelper = new DBHelper(mContext);


        try {
            mNumbers = dbHelper.getNumbers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CallNumberAdapter callNumberAdapter = new CallNumberAdapter(mNumbers, mContext);
        lvProspects.setAdapter(callNumberAdapter);
        lvProspects.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CallNumber n = mNumbers.get(position);
        Intent intent = new Intent(getActivity(), CreateActivity.class);
        intent.putExtra(CreateActivity.P_NUMBER, n.getNumber());
        startActivity(intent);

        Intent dial= new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + n.getNumber()));

        startActivity(dial);
    }
}