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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class newPassword extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{
    private DrawerLayout drawer;
    private Toolbar toolbar;
    EditText editHex;
    EditText editName;
    EditText editURL;
    EditText editUserName;
    EditText editPassword;
    EditText editEmail;

    //Color Buttons
    //Row 1
    Button btnGrey;
    Button btnDarkGrey;
    Button btnBlack;
    Button btnDarkRed;
    Button btnRed;

    //Row 2
    Button btnDarkOrange;
    Button btnOrange;
    Button btnYellow;
    Button btnGreen;
    Button btnDarkGreen;

    //Row 3
    Button btnBlue;
    Button btnDarkBlue;
    Button btnPurple;
    Button btnDarkPurple;
    Button btnBrown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("New Password");
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        editHex = (EditText)findViewById(R.id.editHex);
        editName = (EditText)findViewById(R.id.editName);
        editURL = (EditText)findViewById(R.id.editURL);
        editUserName = (EditText)findViewById(R.id.editUserName);
        editPassword = (EditText)findViewById(R.id.editPassword);
        editEmail = (EditText)findViewById(R.id.editEmail);

        //Color Picker OnClickListener
        //Row 1
        btnGrey = (Button)findViewById(R.id.btnGrey);
        btnGrey.setOnClickListener(this);
        btnDarkGrey = (Button)findViewById(R.id.btnDarkGrey);
        btnDarkGrey.setOnClickListener(this);
        btnBlack = (Button)findViewById(R.id.btnBlack);
        btnBlack.setOnClickListener(this);
        btnDarkRed = (Button)findViewById(R.id.btnDarkRed);
        btnDarkRed.setOnClickListener(this);
        btnRed = (Button)findViewById(R.id.btnRed);
        btnRed.setOnClickListener(this);

        //Row 2
        btnDarkOrange = (Button)findViewById(R.id.btnDarkOrange);
        btnDarkOrange.setOnClickListener(this);
        btnOrange = (Button)findViewById(R.id.btnOrange);
        btnOrange.setOnClickListener(this);
        btnYellow = (Button)findViewById(R.id.btnGold);
        btnYellow.setOnClickListener(this);
        btnGreen= (Button)findViewById(R.id.btnGreen);
        btnGreen.setOnClickListener(this);
        btnDarkGreen = (Button)findViewById(R.id.btnDarkGreen);
        btnDarkGreen.setOnClickListener(this);

        //Row 3
        btnBlue = (Button)findViewById(R.id.btnBlue);
        btnBlue.setOnClickListener(this);
        btnDarkBlue = (Button)findViewById(R.id.btnDarkBlue);
        btnDarkBlue.setOnClickListener(this);
        btnPurple = (Button)findViewById(R.id.btnPurple);
        btnPurple.setOnClickListener(this);
        btnDarkPurple = (Button)findViewById(R.id.btnDarkPurple);
        btnDarkPurple.setOnClickListener(this);
        btnBrown = (Button)findViewById(R.id.btnBrown);
        btnBrown.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String password = getIntent().getStringExtra("Password");
            editPassword.setText(password);
        }



        editHex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                uncheckColors();
                applyColor(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {            }
        });

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
//                Toast.makeText(getApplicationContext(),"Already on New Password", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onClick(View view) {
        uncheckColors();
        String newColor = "";
        switch (view.getId()){
            //Row 1
            case R.id.btnGrey:
                btnGrey.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.grey);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnDarkGrey:
                btnDarkGrey.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.darkGrey);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnBlack:
                btnBlack.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.black);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnDarkRed:
                btnDarkRed.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.darkRed);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnRed:
                btnRed.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.red);
                editHex.setText(newColor);
                applyColor(newColor);
                break;

            // Row 2
            case R.id.btnDarkOrange:
                btnDarkOrange.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.darkOrange);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnOrange:
                btnOrange.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.orange);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnGold:
                btnYellow.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.gold);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnGreen:
                btnGreen.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.green);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnDarkGreen:
                btnDarkGreen.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.darkGreen);
                editHex.setText(newColor);
                applyColor(newColor);
                break;

            // Row 3
            case R.id.btnBlue:
                btnBlue.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.blue);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnDarkBlue:
                btnDarkBlue.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.darkBlue);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnPurple:
                btnPurple.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.purple);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnDarkPurple:
                btnDarkPurple.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.darkPurple);
                editHex.setText(newColor);
                applyColor(newColor);
                break;
            case R.id.btnBrown:
                btnBrown.setText(getString(R.string.selectedColor));
                newColor = getString(R.string.brown);
                editHex.setText(newColor);
                applyColor(newColor);
                break;

        }
    }

    private void uncheckColors(){
        //Row 1
        btnGrey.setText(null);
        btnDarkGrey.setText(null);
        btnBlack.setText(null);
        btnDarkRed.setText(null);
        btnRed.setText(null);

        //Row 2
        btnDarkOrange.setText(null);
        btnOrange.setText(null);
        btnYellow.setText(null);
        btnGreen.setText(null);
        btnDarkGreen.setText(null);

        //Row 3
        btnBlue.setText(null);
        btnDarkBlue.setText(null);
        btnPurple.setText(null);
        btnDarkPurple.setText(null);
        btnBrown.setText(null);
    }

    private void applyColor(String hex){
        try {
            if(hex.charAt(0)!= '#') {
                hex = "#" + hex;
            }
            editHex.setTextColor(Color.parseColor(hex));
            editName.setTextColor(Color.parseColor(hex));
            editURL.setTextColor(Color.parseColor(hex));
            editUserName.setTextColor(Color.parseColor(hex));
            editPassword.setTextColor(Color.parseColor(hex));
            editEmail.setTextColor(Color.parseColor(hex));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Color: " + hex + " is not valid.",Toast.LENGTH_SHORT).show();
        }
    }
}
