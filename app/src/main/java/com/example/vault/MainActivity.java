package com.example.vault;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    @Override
    protected void onResume() {
        updateFragment("nav_home");
        super.onResume();
    }

    public void updateFragment(String key){
        switch (key){
            case "nav_home":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new home()).commit();
                toolbar.setTitle("Vault Home");
                break;
            case "nav_new":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new New_Password()).commit();
                toolbar.setTitle("New Password");
                break;
            case "nav_gen":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new generate()).commit();
                toolbar.setTitle("Generate");
                break;
            case "nav_list":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new passwords()).commit();
                toolbar.setTitle("Passwords");
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                updateFragment("nav_home");
                break;
            case R.id.nav_new:
                updateFragment("nav_new");
                break;
            case R.id.nav_gen:
                updateFragment("nav_gen");
                break;
            case R.id.nav_list:
                updateFragment("nav_list");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //TODO - make undo fragment instead of close app
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
