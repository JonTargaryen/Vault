package com.example.vault;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Password {
    private String Name;
    private String URL;
    private String UserName;
    private String Password;
    private String Email;
    private String ColorHex;
    private static String JSONFileName = "PasswordVault.JSON";

    public Password(){ }

    public Password(String Name, String URL, String UserName, String Password, String Email, String ColorHex){
        this.Name = Name;
        this.URL = URL;
        this.UserName = UserName;
        this.Password = Password;
        this.Email = Email;
        this.ColorHex = ColorHex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getColorHex() {
        return ColorHex;
    }

    public void setColorHex(String colorHex) {
        ColorHex = colorHex;
    }

}
