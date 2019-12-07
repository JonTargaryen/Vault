package com.example.vault;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Random;

public interface Controllable {
    static final String Number = "0123456789";
    static final String Other = "!@#$%&*()_+-=[]?";
    Random ran = new Random();

    default void savePassword(Password pass, View view, Context context, File dir, String filename){
        try {
            File file = new File(dir, filename);
            JSONObject root = new JSONObject();
            JSONArray passwords = readPasswords(file);

            //search for this entry already in the json file
            for (int i = 0; i < passwords.length(); i++) {
                JSONObject iteratorJSON = passwords.getJSONObject(i);
                if (iteratorJSON.get("Name").equals(pass.getName())) {
                    passwords.remove(i);
                    break;
                }
            }

            //create JSON object for this password
            JSONObject thisPassword = new JSONObject();
            thisPassword.put("Name", pass.getName());
            thisPassword.put("URL", pass.getURL());
            thisPassword.put("UserName", pass.getUserName());
            thisPassword.put("Password", pass.getPassword());
            thisPassword.put("Email", pass.getEmail());
            thisPassword.put("ColorHex", pass.getColorHex());

            //add JSON object to Array
            passwords.put(thisPassword);
            root.put("passwords",passwords);

            //Write JSON array to file
            BufferedWriter output = null;
            try {
                output = new BufferedWriter(new FileWriter(file));
                //Encrypt JSON
                output.write(AESUtils.encrypt(root.toString()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                output.close();
            }

            Toast.makeText(context,"Saved Successfully.",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context,"Save Failed.",Toast.LENGTH_SHORT).show();
        }
    }

    default String randomWord(InputStream is, String length ){
        String password = "";
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject o = new JSONObject(json);
            int i = Integer.parseInt(length);
            int remainder = 0;
            if (i > 8) {
                remainder = i - 8;
            }
            password = "";
            if (remainder > 0) {
                JSONArray words = (JSONArray) o.get(Integer.toString(remainder));
                int charIndex = ran.nextInt(words.length());
                password += words.get(charIndex).toString() + " ";
            }
            if (i < 8) {
                JSONArray words = (JSONArray) o.get(length);
                int charIndex = ran.nextInt(words.length());
                password += words.get(charIndex).toString();
                return password;
            }
            JSONArray words = (JSONArray) o.get("8");
            int charIndex = ran.nextInt(words.length());
            password += words.get(charIndex).toString();
            return password;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return password;
        }
    }

    default JSONArray readPasswords(File file){
        JSONObject root = null;
        JSONArray passwords = null;

        try{
            //Create new FILE if missing
            if (file.createNewFile()) {
                BufferedWriter output = null;
                try {
                    root = new JSONObject();
                    passwords = new JSONArray();
                    root.put("passwords", passwords);
                    output = new BufferedWriter(new FileWriter(file));
                    //Encrypt string and write to file
                    output.write(AESUtils.encrypt(root.toString()));
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
                while ((line = input.readLine())!= null){
                    json +=line;
                }
                //decrypt string
                json = AESUtils.decrypt(json);
            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
                input.close();
            }
            //parse decrypted string to JSONObject
            root = new JSONObject(json);
            passwords = root.getJSONArray("passwords");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return passwords;
        }
    }
}