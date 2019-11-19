package com.example.vault;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class generate extends Fragment {

    private static final String Number = "0123456789";
    private static final String Other = "!@#$%&*()_+-=[]?";
    Random ran = new Random();
    private CheckBox ck_capitals;
    private CheckBox ck_Numbers;
    private CheckBox ck_symbols;
    private String Password = "";
    private Button b;
    private EditText output;
    private EditText passwordLength;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_generate,container,false);
        ck_capitals = rootView.findViewById(R.id.ckCapitals);
        ck_Numbers = rootView.findViewById(R.id.ckNumber);
        ck_symbols = rootView.findViewById(R.id.ckSymbol);
        output = rootView.findViewById(R.id.editNewPassword);
        passwordLength = rootView.findViewById(R.id.editLength);
        b = rootView.findViewById(R.id.btnGenerateWithCriteria);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   generatePassword();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }

    private String randomWord(String password) throws IOException, JSONException{
        InputStream is = getActivity().getAssets().open("words.json");
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
            // password.charAt(index) = Character.toUpperCase(password.charAt(index));
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
