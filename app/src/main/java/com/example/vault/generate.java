package com.example.vault;

import android.content.Context;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class generate extends Fragment {

    private static final String Lowercase = "abcdefghijklmnopqrstuvwxyz";
    private static final String Uppercase = Lowercase.toUpperCase();
    private static final String Number = "0123456789";
    private static final String Other = "!@#$%&*()_+-=[]?";
    private String password_Base;
    Random ran = new Random();
    private CheckBox ck_capitals;
    private CheckBox ck_Numbers;
    private CheckBox ck_symbols;
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
                String length = passwordLength.getText().toString();
                int i = 0;
                try{
                    i =  Integer.parseInt(length);
                }
                catch(Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT);
                }
                output.setText(generatePassword(i));
            }
        });
        return rootView;
    }
    private void updateBase(){
        password_Base = Lowercase;
        if(ck_capitals.isChecked()){
            password_Base += Uppercase;
        }
        if(ck_symbols.isChecked()){
            password_Base += Other;
        }
        if(ck_Numbers.isChecked()){
            password_Base += Number;
        }
        List<String> letters = Arrays.asList(password_Base.split(""));
        Collections.shuffle(letters);
        password_Base = letters.stream().collect(Collectors.joining());

    }

    private String generatePassword(int i){
        if (i <= 1){
            Toast.makeText(getContext(), "Password must be Longer than 1 char length", Toast.LENGTH_LONG);
        }
        updateBase();
        StringBuilder sb = new StringBuilder(i);
        for(int k = 0; k < i; k++  ) {

            int charIndex = ran.nextInt(password_Base.length());
            char c = password_Base.charAt(charIndex);
            sb.append(c);
        }
        return sb.toString();
    }

}
