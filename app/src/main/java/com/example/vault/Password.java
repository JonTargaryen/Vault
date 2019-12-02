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

    public void savePasswordToFile(File Directory) throws Exception{
        try {
            File file = new File(Directory,JSONFileName);
            int objectIndex = -1;
            if (file.createNewFile()) {
                BufferedWriter output = null;
                try {
                    JSONObject root = new JSONObject();
                    JSONArray passwords = new JSONArray();
                    root.put("passwords", passwords);
                    output = new BufferedWriter(new FileWriter(file));
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
                if (iteratorJSON.get("Name").equals(this.getName())) {
                    passwords.remove(i);
                    objectIndex = i;
                    break;
                }
            }

            JSONObject thisPassword = new JSONObject();
            thisPassword.put("Name", getName());
            thisPassword.put("URL", getURL());
            thisPassword.put("UserName", getUserName());
            thisPassword.put("Password", getPassword());
            thisPassword.put("Email", getEmail());
            thisPassword.put("ColorHex", getColorHex());

            passwords.put(thisPassword);
            root.put("passwords",passwords);
            BufferedWriter output = null;
            try {
                output = new BufferedWriter(new FileWriter(file));
                output.write(root.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                output.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
