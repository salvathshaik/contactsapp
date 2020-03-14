package com.pavan.contactsapp.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pavan.contactsapp.Adapters.CallsRvAdapter;
import com.pavan.contactsapp.Adapters.ContactsRvAdapter;
import com.pavan.contactsapp.R;
import com.pavan.contactsapp.models.ModelCalls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FragmentCalls extends Fragment {
    private View v;
    private RecyclerView recyclerView;


    public FragmentCalls() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_calls, container, false);

        /*recyclerView= findViewById(R.id.rv_calls);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        CallsRvAdapter adapter=new CallsRvAdapter(getContext(),getCallLogs());

        recyclerView.setAdapter(adapter);*/

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_calls);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new ListingAdapter(mListing);


        CallsRvAdapter adapter = new CallsRvAdapter(getContext(), getCallLogs());

        recyclerView.setAdapter(adapter);
    }

    public void initScreen() {


    }


  /*  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            CallsRvAdapter adapter = new CallsRvAdapter(getContext(), getCallLogs());

            recyclerView.setAdapter(adapter);
            // Refresh your fragment here
        }
    }*/

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private List<ModelCalls> getCallLogs() {

        List<ModelCalls> list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.READ_CALL_LOG}, 1);


        } else {

            Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null
                    , null, CallLog.Calls.DATE + " DESC");
       /* Cursor cursor=getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null
                ,null ,CallLog.Calls.DATE+" ASC");*/
            int number = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
            Log.e("number: ", "" + number);
            String num = null;

            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);

            cursor.moveToFirst();
            while (cursor.moveToNext()) {

                Date date1 = new Date(Long.valueOf(cursor.getString(date)));
                String mnth_date, week_day, year, month;

                mnth_date = (String) DateFormat.format("dd", date1);
                week_day = (String) DateFormat.format("EEEE", date1);
                month = (String) DateFormat.format("MMM", date1);

                //list.add(new ModelCalls(cursor.getString(number),cursor.getString(duration),date1.toString()));

                if (cursor.getString(number) == null) {
                    num = "No name";
                }

                if (cursor.getString(number) == null) {
                    if (cursor.getString(duration).equals("0")) {
                        list.add(new ModelCalls(num, "-",
                                week_day + " " + mnth_date + " " + month));
                    } else {
                        list.add(new ModelCalls(num, cursor.getString(duration),
                                week_day + " " + mnth_date + " " + month));
                    }
                } else {
                    if (cursor.getString(duration).equals("0")) {
                        list.add(new ModelCalls(cursor.getString(number), "-",
                                week_day + " " + mnth_date + " " + month));
                    } else {
                        list.add(new ModelCalls(cursor.getString(number), cursor.getString(duration),
                                week_day + " " + mnth_date + " " + month));
                    }
                }


                Log.e("MiC:: ", " " + cursor.getString(number) +
                        cursor.getString(duration) + " " +
                        week_day + " " + mnth_date + " " + month);



            }


        }
        return list;
    }

}
