package com.sta847.stickcycle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.sta847.stickcycle.controller.ManagePermissions;
import com.sta847.stickcycle.controller.ManageSpeedometer;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;
    private ManagePermissions managePermissions;
    private ManageSpeedometer manageSpeedometer;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_stopwatch, R.id.nav_profile, R.id.nav_graphics,
                R.id.nav_maps, R.id.nav_history, R.id.nav_highscore, R.id.nav_social,
                R.id.nav_playground)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //get permissions
        managePermissions = new ManagePermissions();
        managePermissions.getLocationPermission(this);

        //start Speedometer Manager and its requirements
        manageSpeedometer = new ManageSpeedometer(this);
        manageSpeedometer.setupRequirements();
        Log.d("STA847 ", "speed is " + manageSpeedometer.getSpeed());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public AppBarConfiguration getmAppBarConfiguration()
    {
        return mAppBarConfiguration;
    }

    public void setmAppBarConfiguration(AppBarConfiguration mAppBarConfiguration)
    {
        this.mAppBarConfiguration = mAppBarConfiguration;
    }

    public ManagePermissions getManagePermissions()
    {
        return managePermissions;
    }

    public void setManagePermissions(ManagePermissions managePermissions)
    {
        this.managePermissions = managePermissions;
    }

    public ManageSpeedometer getManageSpeedometer()
    {
        return manageSpeedometer;
    }

    public void setManageSpeedometer(ManageSpeedometer manageSpeedometer)
    {
        this.manageSpeedometer = manageSpeedometer;
    }
}
