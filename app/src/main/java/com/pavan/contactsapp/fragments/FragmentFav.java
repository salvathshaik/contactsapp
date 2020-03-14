package com.pavan.contactsapp.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pavan.contactsapp.Adapters.ContactsRvAdapter;
import com.pavan.contactsapp.Adapters.FvtContactsRvAdapter;
import com.pavan.contactsapp.R;
import com.pavan.contactsapp.models.ModelContacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentFav extends Fragment {
    private View v;

    RecyclerView recyclerView;

    public FragmentFav(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.frag_fav,container,false);
        return v;


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Map temp=getFavoriteContacts();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fav);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new ListingAdapter(mListing);
        FvtContactsRvAdapter adapter=new FvtContactsRvAdapter(getContext(),getFavoriteContacts());

        //getFavoriteContacts();
        recyclerView.setAdapter(adapter);
    }

    List<ModelContacts> getFavoriteContacts(){

        Map contactMap = new HashMap();
        List<ModelContacts> list=  new ArrayList<>();
        Uri queryUri = ContactsContract.Contacts.CONTENT_URI;

        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.STARRED};

        /*for(int i=0;i<projection.length;i++){
            Log.e("fvt contacts: ",projection[i]);
        }*/

        String selection =ContactsContract.Contacts.STARRED + "='1'";

        Cursor cursor = getContext().getContentResolver().query(queryUri, projection, selection, null, null);

        while (cursor.moveToNext()) {
            String contactID = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.Contacts._ID));

            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.withAppendedPath(
                    ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactID));
            intent.setData(uri);
            String intentUriString = intent.toUri(0);

            String title = (cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

            list.add(new ModelContacts(title,intentUriString));
//            String number = (cursor.getString(
//                    cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

            Log.e("fvt contacts: AND NUMB",title+" "+"hdu");

            contactMap.put(title,intentUriString);

        }

        cursor.close();
        return list;
    }

    public List<ModelContacts> getFavoriteContacts2(){

        Uri queryUri = ContactsContract.Contacts.CONTENT_URI;

        //List<ModelContacts> list=  new ArrayList<>();

        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.STARRED};

        /*for(int i=0;i<projection.length;i++){
            Log.e("fvt contacts: ",projection[i]);
        }*/

        String selection =ContactsContract.Contacts.STARRED + "='1'";

     /*   Cursor cursor = getContext().getContentResolver().query(queryUri, projection, selection, null, null);


        while (cursor.moveToNext()) {

            String name = (cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
            String number = (cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));


            list.add(new ModelContacts(name,number));


          //Log.e("fvt contacts: ",title);
           // contactMap.put(title,intentUriString);



        }*/


        List<ModelContacts> list=  new ArrayList<>();
        Cursor cursor=getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
                ,selection,null,null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            list.add(new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )), cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));

        }



        cursor.close();
        return list;
    }


}

