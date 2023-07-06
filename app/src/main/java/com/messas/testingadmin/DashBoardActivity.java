package com.messas.testingadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DashBoardActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar mainToolbar;
    private String current_user_id;
    private BottomNavigationView mainBottomNav;
    private DrawerLayout mainDrawer;
    private ActionBarDrawerToggle mainToggle;
    private NavigationView mainNav;
    HomeFragment homeFragment;
    MessageFragment messageFragment;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        mainDrawer=findViewById(R.id.main_activity);
        mainNav = findViewById(R.id.main_nav);
        mainNav.setNavigationItemSelectedListener(this);
        frameLayout=findViewById(R.id.main_container);
        mainToggle = new ActionBarDrawerToggle(this,mainDrawer,toolbar,R.string.open,R.string.close);
        mainDrawer.addDrawerListener(mainToggle);
        mainToggle.setDrawerIndicatorEnabled(true);
        mainToggle.syncState();
        homeFragment=new HomeFragment();
        messageFragment=new MessageFragment();

        mainBottomNav = findViewById(R.id.mainBottomNav);
        //mainBottomNav.setAnimation(bottomAnimation);
        mainBottomNav.setOnNavigationItemSelectedListener(selectlistner);

        //
        /*
        int a=12;
        if (a>10&&a<200) {
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
         */

        //
        initializeFragment();
        mainBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        flag=1;
                        replaceFragment(homeFragment);

                        return true;

                    case R.id.navigation_chat:
                        flag=2;
                        replaceFragment(messageFragment);

                        return true;
                   /*
                    case R.id.withlist:
                        flag=2;
                        replaceFragment(wishlistFragment);
                        searchView.setVisibility(View.GONE);
                        return true;
                    */





                    default:
                        return false;
                }
            }
        });
    }
    int flag=1;
    private BottomNavigationView.OnNavigationItemSelectedListener selectlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.bottom_home:
                            HomeFragment fragment2 = new HomeFragment();
                            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                            ft2.replace(R.id.content, fragment2, "");
                            ft2.commit();
                            break;



                    }
                    return false;
                }
            };
    private void replaceFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (fragment == homeFragment){
            fragmentTransaction.hide(messageFragment);

            // fragmentTransaction.hide(historyFragment);

        } else if (fragment == messageFragment){

            fragmentTransaction.hide(homeFragment);

            //   fragmentTransaction.hide(historyFragment);

        }

    }




    public void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container,homeFragment);
        fragmentTransaction.add(R.id.main_container,messageFragment);


        // fragmentTransaction.add(R.id.main_container,historyFragment);


        fragmentTransaction.hide(messageFragment);



        fragmentTransaction.commit();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id) {
            case R.id.logout:
                AlertDialog.Builder warning = new AlertDialog.Builder(DashBoardActivity.this)
                        .setTitle("Logout")
                        .setMessage("Are you want to logout?")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();



                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // ToDO: delete all the notes created by the Anon user



                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();


                            }
                        });

                warning.show();
                break;
            case R.id.addNote:
                Toast.makeText(this, "You are now in home", Toast.LENGTH_SHORT).show();

                break;

            case R.id.porf:
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                break;

            case R.id.cartliost:
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
                break;

            case R.id.shareapp1:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                break;



        }

        return false;
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(DashBoardActivity.this);
        builder.setTitle("Exit")
                .setCancelable(false)
                .setMessage("Are you want to exit")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAffinity();
            }
        }).create();
        builder.show();



    }


}