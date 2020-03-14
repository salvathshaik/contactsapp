package com.pavan.contactsapp.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pavan.contactsapp.Adapters.CallsRvAdapter;
import com.pavan.contactsapp.Adapters.ContactsRvAdapter;
import com.pavan.contactsapp.R;
import com.pavan.contactsapp.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

public class FragmentContacts extends Fragment {
    private View v;
    private RecyclerView recyclerView;

    public FragmentContacts(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_contacts,container,false);

      /*  recyclerView= container.findViewById(R.id.rv_contacts);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager=linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        ContactsRvAdapter adapter=new ContactsRvAdapter(getContext(),getContacts());
        recyclerView.setAdapter(adapter);*/
//        getContacts();
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_contacts);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new ListingAdapter(mListing);
        ContactsRvAdapter adapter=new ContactsRvAdapter(getContext(),getContacts());

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ContactsRvAdapter adapter=new ContactsRvAdapter(getContext(),getContacts());

            recyclerView.setAdapter(adapter);
            // Refresh your fragment here
        }
    }

    private List<ModelContacts> getContacts(){




        List<ModelContacts> list=  new ArrayList<>();


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 1);


        } else {

            Cursor cursor=getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
                    ,null,null,ContactsContract.Contacts.DISPLAY_NAME+" ASC");
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                list.add(new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )), cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));

            }

        }


        return list;
    }

}
