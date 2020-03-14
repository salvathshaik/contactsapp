package com.pavan.contactsapp;

import android.Manifest;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

import com.pavan.contactsapp.Adapters.ViewPageAdapter;
import com.pavan.contactsapp.fragments.FragmentCalls;
import com.pavan.contactsapp.fragments.FragmentContacts;
import com.pavan.contactsapp.fragments.FragmentFav;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askPermission();


        }
        fragmentCalls=new FragmentCalls();




        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        ViewPageAdapter adapter=new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCalls(),"Calls");
        adapter.addFragment(new FragmentContacts(),"Contacts");
        adapter.addFragment(new FragmentFav(),"Favorites");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

       // tabLayout.setTabTextColors(android.R.color.white,android.R.color.white);

        for(int i=0;i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab=tabLayout.getTabAt(i);
            //tab.setIcon(ICONS[i]);
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
                return;
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
                        &&grantResults[3]==PackageManager.PERMISSION_GRANTED)
                {
                    permissionResult=true;
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
