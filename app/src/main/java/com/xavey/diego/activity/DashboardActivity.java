package com.xavey.diego.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.xavey.diego.R;
import com.xavey.diego.fragment.ProspectsFragment;
import com.xavey.diego.fragment.ReferralsFragment;
import com.xavey.diego.helper.AppValues;
import com.xavey.diego.helper.DBHelper;
import com.xavey.diego.helper.UtilityHelper;

import java.util.Date;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context mContext;
    String AuthToken="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        try {
//            AuthToken= TokenHelper.genAPIToken(mContext, DashboardActivity.this);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (AuthToken != "") {
//            final DBHelper dbH = new DBHelper(mContext);
//            try {
//                User dbUser = dbH.getUser();
//                TextView tvMID = (TextView) findViewById(R.id.txtDrawerMellerID);
//                tvMID.setText(dbUser.getFull_name());
//                TextView tvMobile = (TextView) findViewById(R.id.txtDrawerMobile);
//                tvMobile.setText(dbUser.getPhone());
//
//            } catch (Exception e) {
//                Log.d("AuthAPI", e.getStackTrace().toString());
//            }
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final DBHelper dbH = new DBHelper(mContext);
        try {
            View v = (View) navigationView.getHeaderView(0);
            TextView tvMID = (TextView)v.findViewById(R.id.txtDrawerMellerID);
            tvMID.setText(AppValues.getInstance().loginUser.getFull_name());
            TextView tvMobile = (TextView)v.findViewById(R.id.txtDrawerMobile);
            Date d=new Date();
            tvMobile.setText(UtilityHelper.getDateTime(d,false));

        } catch (Exception e) {
            Log.d("AuthAPI", e.getStackTrace().toString());
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ReferralsFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new_meller) {
            Intent intent = new Intent(mContext,CreateActivity.class);
            mContext.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_referrals) {
            if (findViewById(R.id.container) != null) {
                Fragment newFragment = new ReferralsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } else if (id == R.id.nav_prospects) {
            if (findViewById(R.id.container) != null) {
                Fragment newFragment = new ProspectsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            finish();
            Intent intent = new Intent(mContext,LoginActivity.class);
            mContext.startActivity(intent);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
