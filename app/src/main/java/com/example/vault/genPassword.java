package com.example.vault;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class genPassword extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private static final String Number = "0123456789";
    private static final String Other = "!@#$%&*()_+-=[]?";
    Random ran = new Random();
    private CheckBox ck_capitals;
    private CheckBox ck_Numbers;
    private CheckBox ck_symbols;
    public String Password = "";
    private Button b;
    private EditText output;
    private EditText passwordLength;
    private ClipboardManager myClipboard;
    private Button copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_password);
        myClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.generatePassword));
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ck_capitals = (CheckBox)findViewById(R.id.ckCapitals);
        ck_Numbers = (CheckBox)findViewById(R.id.ckNumber);
        ck_symbols = (CheckBox)findViewById(R.id.ckSymbol);
        output = (EditText)findViewById(R.id.editNewPassword);
        passwordLength = (EditText)findViewById(R.id.editLength);
        b = (Button)findViewById(R.id.btnGenerateWithCriteria);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    generatePassword();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        b = findViewById(R.id.btnGenerateWithCriteria);
        copy = findViewById(R.id.btnCopyNewPassword);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData myClip;
                String text = output.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(),"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CreatePassword(View view){
        Intent intent = new Intent(this, newPassword.class);
        intent.putExtra("com.example.vault.Password", Password);
        startActivity(intent);
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
//                Toast.makeText(getApplicationContext(),"Already on New com.example.vault.Password", Toast.LENGTH_SHORT).show();
                break;
            case "nav_list":
                intent = new Intent(this, passList.class);
                startActivity(intent);
                break;
        }
    }

    private String randomWord(String password) throws IOException, JSONException{
        InputStream is = getAssets().open("words.json");
        int size = is.available();
        byte[]  buffer = new byte[size];
        is.read(buffer);
        is.close();
        String json = new String(buffer,"UTF-8");
        JSONObject o = new JSONObject(json);
        String length = passwordLength.getText().toString();
        int i = Integer.parseInt(length);
        int remainder = 0;
        if(i > 8){
            remainder = i - 8;
        }
        password = "";
        if(remainder > 0){
            JSONArray words = (JSONArray) o.get(Integer.toString(remainder));
            int charIndex = ran.nextInt(words.length());
            password +=  words.get(charIndex).toString() + " ";
        }
        if(i < 8){
            JSONArray words = (JSONArray) o.get(length);
            int charIndex = ran.nextInt(words.length());
            password += words.get(charIndex).toString();
            return password;
        }
        JSONArray words = (JSONArray) o.get("8");
        int charIndex = ran.nextInt(words.length());
        password += words.get(charIndex).toString();
        return password;
    }

    private String updateBase(String password){
        if(ck_capitals.isChecked()){
            int index = ran.nextInt(password.length());
            // com.example.vault.Password.charAt(index) = Character.toUpperCase(com.example.vault.Password.charAt(index));
            char[] letters = password.toCharArray();
            letters[index] = Character.toUpperCase(letters[index]);
            password = new String(letters);

        }
        if(ck_symbols.isChecked()){
            int charIndex = ran.nextInt(Other.length());
            char c = Other.charAt(charIndex);
            password += Character.toString(c);
        }
        if(ck_Numbers.isChecked()){
            int charIndex = ran.nextInt(Number.length());
            char c = Number.charAt(charIndex);
            password += Character.toString(c);
        }
        return password;
    }

    private void generatePassword() throws IOException, JSONException {
        Password = randomWord(Password);
        Password = updateBase(Password);
        output.setText(Password);
    }

}
