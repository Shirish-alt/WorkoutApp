package com.infostackresearch.homefit.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.infostackresearch.homefit.R;
import com.infostackresearch.homefit.sessions.UserSessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new UserSessionManager(MainActivity.this);
        HashMap<String, String> userData = session.getUserDetails();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setItemIconTintList(null);
        navigationView.setItemIconTintList(null);
        Menu sideMenu = navigationView.getMenu();
        Menu bottomMenu = navView.getMenu();

        if (session.checkLogin()) {
            MenuItem logout = sideMenu.findItem(R.id.navigation_logout);
            logout.setVisible(false);
            MenuItem bottomProfile = bottomMenu.findItem(R.id.navigation_profile);
            bottomProfile.setVisible(false);
            MenuItem sideProfile = sideMenu.findItem(R.id.navigation_profile);
            sideProfile.setVisible(false);

        } else {
            MenuItem login = sideMenu.findItem(R.id.navigation_login);
            login.setVisible(false);
//            MenuItem profile = menu.findItem(R.id.navigation_profile);
//            profile.setVisible(false);
        }

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_plans,
                R.id.navigation_home,
                R.id.navigation_subscription,
                R.id.navigation_workout,
                R.id.navigation_myactivity,
                R.id.navigation_diet,
                R.id.navigation_pt,
                R.id.navigation_Live,
                R.id.navigation_BMI,
                R.id.navigation_profile,
                R.id.navigation_logout,
                R.id.navigation_login)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}