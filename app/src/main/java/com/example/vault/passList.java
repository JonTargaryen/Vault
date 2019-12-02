package com.example.vault;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class passList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ListView lvPasswords;
    private ArrayList<String> passNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_list);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.passwords));
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        loadPasswords();
    }

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

    private void navActivity(String key){
        Intent intent = null;
        switch (key){
            case "nav_home":
                finish();
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
//                Toast.makeText(getApplicationContext(),"Already on Passwords", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void loadPasswords(){
        try {
            File file = new File(getDataDir(), getString(R.string.json));
            int objectIndex = -1;
            if (file.createNewFile()) {
                BufferedWriter output = null;
                try {
                    JSONObject root = new JSONObject();
                    JSONArray passwords = new JSONArray();
                    root.put("passwords", passwords);
                    output = new BufferedWriter(new FileWriter(file));
                    output.write(root.toString());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    output.close();
                }
            }
            BufferedReader input = null;
            String json = "";
            try{
                input = new BufferedReader(new FileReader(file));
                String line;
                while ((line = input.readLine()) != null) {
                    json += line;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                input.close();
            }

            passNames = new ArrayList<String>();

            JSONObject root = new JSONObject(json);
            JSONArray passwords = root.getJSONArray("passwords");

            for(int i =0; i<passwords.length();i++){
                JSONObject iteratorJSON = passwords.getJSONObject(i);
                passNames.add(iteratorJSON.getString("Name"));
            }

            lvPasswords = (ListView)findViewById(R.id.lvPasswords);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, passNames);
            lvPasswords.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            lvPasswords.setAdapter(adapter);

            lvPasswords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> arg0, View arg1,
                                                int arg2, long arg3) {
                            Intent intent = new Intent(getApplicationContext(), editPassword.class);
                            intent.putExtra("index",arg2);
                            startActivity(intent);
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Load Failed", Toast.LENGTH_SHORT).show();
        }
    }

}
