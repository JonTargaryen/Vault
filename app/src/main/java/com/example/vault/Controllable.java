package com.example.vault;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public interface Controllable {
    static final String Number = "0123456789";
    static final String Other = "!@#$%&*()_+-=[]?";
    Random ran = new Random();

    default void savePassword(Password pass, View view, Context context, File dir, String filename){
        try {
            File file = new File(dir, filename);
            JSONObject root = new JSONObject();
            JSONArray passwords = readPasswords(dir, file);

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
            BufferedOutputStream output = null;
            try {
                output = new BufferedOutputStream(new FileOutputStream(file));
                //Encrypt JSON
                String json = root.toString();
                byte[] yourKey = generateKey("password");
                byte[] fileBytes = encodeFile(yourKey, json.getBytes("UTF-8"));
                output.write(fileBytes);
                output.flush();
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

    default JSONArray readPasswords(File dir, File file){
        JSONObject root = null;
        JSONArray passwords = null;

        try{
            //Create new FILE if missing
            if (file.createNewFile()) {
                BufferedOutputStream output = null;
                try {
                    root = new JSONObject();
                    passwords = new JSONArray();
                    root.put("passwords", passwords);
                    String baseJSON = root.toString();
                    output = new BufferedOutputStream(new FileOutputStream(file));
                    //Encrypt JSON
                    byte[] yourKey = generateKey("password");
                    byte[] fileBytes = encodeFile(yourKey, baseJSON.getBytes("UTF-8"));
                    output.write(fileBytes);
                    output.flush();
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
                //get file bytes
                byte[] encryptedBytes;
                byte[] yourKey = generateKey("password");
                byte[] decodedData = decodeFile(yourKey, encryptedBytes);
            }
            catch (Exception e){
                e.printStackTrace();
            }finally {
//                input.close();
             input.close();
            }

            root = new JSONObject(json);
            passwords = root.getJSONArray("passwords");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return passwords;
        }
    }

    default public byte[] generateKey(String password) throws Exception
    {
        byte[] keyStart = password.getBytes("UTF-8");

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(keyStart);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();
        return skey.getEncoded();
    }

    default public byte[] encodeFile(byte[] key, byte[] fileData) throws Exception
    {

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(fileData);

        return encrypted;
    }

    default public byte[] decodeFile(byte[] key, byte[] fileData) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] decrypted = cipher.doFinal(fileData);

        return decrypted;
    }
}
