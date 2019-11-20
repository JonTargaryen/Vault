package com.example.vault;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class New_Password extends Fragment implements View.OnClickListener {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_new_password,container,false);

        editHex = (EditText)rootView.findViewById(R.id.editHex);
        editName = (EditText)rootView.findViewById(R.id.editName);
        editURL = (EditText)rootView.findViewById(R.id.editURL);
        editUserName = (EditText)rootView.findViewById(R.id.editUserName);
        editPassword = (EditText)rootView.findViewById(R.id.editPassword);
        editEmail = (EditText)rootView.findViewById(R.id.editEmail);

        //Color Picker OnClickListener
        //Row 1
        btnGrey = rootView.findViewById(R.id.btnGrey);
        btnGrey.setOnClickListener(this);
        btnDarkGrey = rootView.findViewById(R.id.btnDarkGrey);
        btnDarkGrey.setOnClickListener(this);
        btnBlack = rootView.findViewById(R.id.btnBlack);
        btnBlack.setOnClickListener(this);
        btnDarkRed = rootView.findViewById(R.id.btnDarkRed);
        btnDarkRed.setOnClickListener(this);
        btnRed = rootView.findViewById(R.id.btnRed);
        btnRed.setOnClickListener(this);

        //Row 2
        btnDarkOrange = rootView.findViewById(R.id.btnDarkOrange);
        btnDarkOrange.setOnClickListener(this);
        btnOrange = rootView.findViewById(R.id.btnOrange);
        btnOrange.setOnClickListener(this);
        btnYellow = rootView.findViewById(R.id.btnGold);
        btnYellow.setOnClickListener(this);
        btnGreen= rootView.findViewById(R.id.btnGreen);
        btnGreen.setOnClickListener(this);
        btnDarkGreen = rootView.findViewById(R.id.btnDarkGreen);
        btnDarkGreen.setOnClickListener(this);

        //Row 3
        btnBlue = rootView.findViewById(R.id.btnBlue);
        btnBlue.setOnClickListener(this);
        btnDarkBlue = rootView.findViewById(R.id.btnDarkBlue);
        btnDarkBlue.setOnClickListener(this);
        btnPurple = rootView.findViewById(R.id.btnPurple);
        btnPurple.setOnClickListener(this);
        btnDarkPurple = rootView.findViewById(R.id.btnDarkPurple);
        btnDarkPurple.setOnClickListener(this);
        btnBrown = rootView.findViewById(R.id.btnBrown);
        btnBrown.setOnClickListener(this);

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

        return rootView;
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
            Toast.makeText(getContext(),"Color: " + hex + " is not valid.",Toast.LENGTH_SHORT).show();
        }
    }
}

//https://stackoverflow.com/questions/35533905/how-to-handle-onclick-in-fragments?rq=1
