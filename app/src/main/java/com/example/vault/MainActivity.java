package com.example.vault;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Custom Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.homeTitle));
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set Custom Navigation Drawer
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Navigation Button Mmethods
        findViewById(R.id.txtNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navActivity("nav_new");
            }
        });
        findViewById(R.id.txtGenerate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navActivity("nav_gen");
            }
        });
        findViewById(R.id.txtList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navActivity("nav_list");
            }
        });


        //Check for file for 32 byte array


       try {


           File Encryption = new File(getDataDir(), "EncryptionBytes.txt");
           if (Encryption.createNewFile())
           {
               BufferedWriter output = null;
               try
               {
                   byte[] encryption_bytes;

                   output = new BufferedWriter(new FileWriter(Encryption));
                   //output.write(encryption_bytes.toString());
                   return;
               }


               catch (Exception e)
               {
                   e.printStackTrace();
               } finally
               {
                   output.close();
               }
           }

           BufferedReader input = null;
           String EncryptionBytesEnter = "";
           try {
               input = new BufferedReader(new FileReader(Encryption));
               String line;
               while ((line = input.readLine()) != null) {
                   EncryptionBytesEnter += line;
               }


           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               input.close();
           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
           Toast.makeText(getApplicationContext(),"Encryption Failed", Toast.LENGTH_SHORT).show();
       }
        //Check for file for 12 byte array
    }

    //Drawer Navigation
    public void navActivity(String key){
        Intent intent = null;
        switch (key){
            case "nav_home":
                break;
            case "nav_new":
                intent = new Intent(this, newPassword.class);
                startActivity(intent);
                break;
            case "nav_gen":
                intent = new Intent(this, genPassword.class);
                startActivity(intent);
                break;
            case "nav_list":
                intent = new Intent(this, passList.class);
                startActivity(intent);
                break;
        }
    }

    //Drawer Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                navActivity("nav_home");
                break;
            case R.id.nav_new:
                navActivity("nav_new");
                break;
            case R.id.nav_gen:
                navActivity("nav_gen");
                break;
            case R.id.nav_list:
                navActivity("nav_list");
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
