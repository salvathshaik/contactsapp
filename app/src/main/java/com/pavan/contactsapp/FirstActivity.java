package com.pavan.contactsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pavan.contactsapp.Adapters.ViewPageAdapter;
import com.pavan.contactsapp.fragments.FragmentCalls;
import com.pavan.contactsapp.fragments.FragmentContacts;
import com.pavan.contactsapp.fragments.FragmentFav;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class FirstActivity extends AppCompatActivity {

        private TabLayout tabLayout;
        private ViewPager viewPager;
        private final int ICONS[]={R.drawable.ic_launcher_background,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_background};


    private final String[] permissionsRequired=new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CONTACTS

    };
    private static final int PERMISSION_ALL = 1;
    private boolean permissionResult=false;
    static final int REQUEST_LOCATION = 1;
private FragmentCalls fragmentCalls;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        fragmentCalls=new FragmentCalls();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askPermission();

            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }







    }

    /*@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void askPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
            !=PackageManager.PERMISSION_GRANTED ){

            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_CALL_LOG},1);
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_CONTACTS},1);

        }
    }*/

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermission()
    {
        for(String permission:permissionsRequired)
        {
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissionsRequired,PERMISSION_ALL);
                Log.e("permissions3",""+1);
                return;
            }else {
                Log.e("permissions2",""+1);

            }
        }
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1:{
                Log.e("permissions",""+grantResults.length);
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED
                        &&grantResults[1]==PackageManager.PERMISSION_GRANTED
                        &&grantResults[2]==PackageManager.PERMISSION_GRANTED
                        &&grantResults[3]==PackageManager.PERMISSION_GRANTED
                        &&grantResults[4]==PackageManager.PERMISSION_GRANTED)
                {
                    permissionResult=true;


                    Log.e("permissions2",""+grantResults.length);



                   // fragmentCalls.initScreen();



                }
                else
                {
                    permissionResult=false;
                }
            }

            case 99:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    videoFragment.initScreen();


                }else{
//                    Toast.makeText(this, "Please Enable location", Toast.LENGTH_SHORT).show();
//                    videoFragment.initScreen();

                }
            }

        }
    }






}
