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
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class newPassword extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    //Initialize Encryption
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String SAMPLE_ALIAS = "MYALIAS";
    private EnCryptor encryptor;
    private DeCryptor decryptor;
    // private String TextToEncrypt;
    private String EncryptedText;
    private String DecryptedText;



    private DrawerLayout drawer;
    private Toolbar toolbar;
    private EditText editHex;
    private EditText editName;
    private EditText editURL;
    private EditText editUserName;
    private EditText editPassword;
    private EditText editEmail;
    private static final int request_code = 5;
    private ClipboardManager myClipboard;
    private Button btnCopy;
    private Button btnLaunch;
    private Button btnSave;

    //Color Buttons
    //Row 1
    private Button btnGrey;
    private Button btnDarkGrey;
    private Button btnBlack;
    private Button btnDarkRed;
    private Button btnRed;

    //Row 2
    private Button btnDarkOrange;
    private Button btnOrange;
    private Button btnYellow;
    private Button btnGreen;
    private Button btnDarkGreen;

    //Row 3
    private Button btnBlue;
    private Button btnDarkBlue;
    private Button btnPurple;
    private Button btnDarkPurple;
    private Button btnBrown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);


        myClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.NewPassword));
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
//                uncheckColors();
                applyColor(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {            }
        });

        btnCopy = (Button)findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData myClip;
                String text = editPassword.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(),"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            }
        });

        btnLaunch = (Button)findViewById(R.id.btnLaunch);
        btnLaunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String URL = editURL.getText().toString();
                    if (URL.equals("") || URL.equals(null)) {
                        Toast.makeText(getApplicationContext(), "No URL to Launch", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(mIntent);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Not a valid URL",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = editName.getText().toString();
                String URL = editURL.getText().toString();
                String UserName = editUserName.getText().toString();
                String Password = editPassword.getText().toString();
                String Email = editEmail.getText().toString();
                String ColorHex = editHex.getText().toString();

                Password pass = new Password(
                        Name, URL, UserName, Password, Email, ColorHex);
                try {
                    File file = new File(getDataDir(),getString(R.string.json));
                    int objectIndex = -1;
                    if (file.createNewFile()) {
                        BufferedWriter output = null;
                        try {
                            JSONObject root = new JSONObject();
                            JSONArray passwords = new JSONArray();
                            root.put("passwords", passwords);
                            output = new BufferedWriter(new FileWriter(file));
                            //Encrypt here
                            output.write(root.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            output.close();
                        }
                    }

                    BufferedReader input = null;
                    String json = "";
                    try{

                        //Decrypt here
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

                    JSONObject root = new JSONObject(json);
                    JSONArray passwords = root.getJSONArray("passwords");

                    //search for this entry already in the json file
                    for (int i = 0; i < passwords.length(); i++) {
                        JSONObject iteratorJSON = passwords.getJSONObject(i);
                        if (iteratorJSON.get("Name").equals(pass.getName())) {
                            passwords.remove(i);
                            objectIndex = i;
                            break;
                        }
                    }

                    JSONObject thisPassword = new JSONObject();
                    thisPassword.put("Name", pass.getName());
                    thisPassword.put("URL", pass.getURL());
                    thisPassword.put("UserName", pass.getUserName());
                    thisPassword.put("Password", pass.getPassword());
                    thisPassword.put("Email", pass.getEmail());
                    thisPassword.put("ColorHex", pass.getColorHex());

                    passwords.put(thisPassword);
                    root.put("passwords",passwords);
                    BufferedWriter output = null;
                    try {
                        output = new BufferedWriter(new FileWriter(file));
                        //Encrypt Here
                        //output.write(encryptText(root.toString()));
                        output.write(root.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        output.close();
                    }

                    Toast.makeText(getApplicationContext(),"Saved Successfully.",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Save Failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Encryption / Decryption onCreate
        encryptor = new EnCryptor();

        try
        {
            decryptor = new DeCryptor();
        }
        catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException e)
        {
            e.printStackTrace();
        }





    }

    //Method for the encryption of text
    private String encryptText(String TextToEncrypt)
    {
        try
        {
            final byte[] encryptedText = encryptor.encryptText(SAMPLE_ALIAS, TextToEncrypt);
            EncryptedText = Base64.encodeToString(encryptedText,Base64.DEFAULT);


        }

        catch (UnrecoverableEntryException | NoSuchAlgorithmException | NoSuchProviderException |
                KeyStoreException | IOException | NoSuchPaddingException | InvalidKeyException e)

        {
            Log.e(TAG, "onClick() called with: " + e.getMessage(), e);
        }

        catch (InvalidAlgorithmParameterException | SignatureException |
                IllegalBlockSizeException | BadPaddingException e)
        {
            e.printStackTrace();
        }

        return EncryptedText;
    }

    //Method for the decryption of text
    private String decryptText()
    {
        try
        {
            DecryptedText = (decryptor.decryptData(SAMPLE_ALIAS, encryptor.getEncryption(), encryptor.getIv()));

        }

        catch (UnrecoverableEntryException | NoSuchAlgorithmException |
                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
                IOException | InvalidKeyException e)
        {
            Log.e(TAG, "decryptData() called with: " + e.getMessage(), e);
        }

        catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }

        return DecryptedText;
    }

    public void moveToGenerateActivity(View view)
    {
        Intent intent = new Intent(this, genPassword_New.class);
        startActivityForResult(intent,request_code);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == request_code)&&(resultCode ==RESULT_OK)){
            String generatedPassword = data.getStringExtra("genPassword_New");
            editPassword.setText(generatedPassword);
        }
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
//                Toast.makeText(getApplicationContext(),"Already on New com.example.vault.Password", Toast.LENGTH_SHORT).show();
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
