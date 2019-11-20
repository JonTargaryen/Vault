package com.example.vault;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


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
    private ClipboardManager myClipboard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_new_password,container,false);
        myClipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
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

        ClipData clipData = myClipboard.getPrimaryClip();
        ClipData.Item item = clipData.getItemAt(0);
        editPassword.setText(item.getText().toString());

        return rootView;
    }

    @Override
    public void onClick(View view) {
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
        switch (view.getId()){
            //Row 1
            case R.id.btnGrey:
                btnGrey.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.grey));
                editHex.setTextColor(Color.parseColor(getString(R.string.grey)));
                editName.setTextColor(Color.parseColor(getString(R.string.grey)));
                editURL.setTextColor(Color.parseColor(getString(R.string.grey)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.grey)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.grey)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.grey)));
                break;
            case R.id.btnDarkGrey:
                btnDarkGrey.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.darkGrey));
                editHex.setTextColor(Color.parseColor(getString(R.string.darkGrey)));
                editName.setTextColor(Color.parseColor(getString(R.string.darkGrey)));
                editURL.setTextColor(Color.parseColor(getString(R.string.darkGrey)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.darkGrey)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.darkGrey)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.darkGrey)));
                break;
            case R.id.btnBlack:
                btnBlack.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.black));
                editHex.setTextColor(Color.parseColor(getString(R.string.black)));
                editName.setTextColor(Color.parseColor(getString(R.string.black)));
                editURL.setTextColor(Color.parseColor(getString(R.string.black)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.black)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.black)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.black)));
                break;
            case R.id.btnDarkRed:
                btnDarkRed.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.darkRed));
                editHex.setTextColor(Color.parseColor(getString(R.string.darkRed)));
                editName.setTextColor(Color.parseColor(getString(R.string.darkRed)));
                editURL.setTextColor(Color.parseColor(getString(R.string.darkRed)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.darkRed)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.darkRed)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.darkRed)));
                break;
            case R.id.btnRed:
                btnRed.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.red));
                editHex.setTextColor(Color.parseColor(getString(R.string.red)));
                editName.setTextColor(Color.parseColor(getString(R.string.red)));
                editURL.setTextColor(Color.parseColor(getString(R.string.red)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.red)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.red)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.red)));
                break;

            // Row 2
            case R.id.btnDarkOrange:
                btnDarkOrange.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.darkOrange));
                editHex.setTextColor(Color.parseColor(getString(R.string.darkOrange)));
                editName.setTextColor(Color.parseColor(getString(R.string.darkOrange)));
                editURL.setTextColor(Color.parseColor(getString(R.string.darkOrange)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.darkOrange)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.darkOrange)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.darkOrange)));
                break;
            case R.id.btnOrange:
                btnOrange.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.orange));
                editHex.setTextColor(Color.parseColor(getString(R.string.orange)));
                editName.setTextColor(Color.parseColor(getString(R.string.orange)));
                editURL.setTextColor(Color.parseColor(getString(R.string.orange)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.orange)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.orange)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.orange)));
                break;
            case R.id.btnGold:
                btnYellow.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.gold));
                editHex.setTextColor(Color.parseColor(getString(R.string.gold)));
                editName.setTextColor(Color.parseColor(getString(R.string.gold)));
                editURL.setTextColor(Color.parseColor(getString(R.string.gold)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.gold)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.gold)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.gold)));
                break;
            case R.id.btnGreen:
                btnGreen.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.green));
                editHex.setTextColor(Color.parseColor(getString(R.string.green)));
                editName.setTextColor(Color.parseColor(getString(R.string.green)));
                editURL.setTextColor(Color.parseColor(getString(R.string.green)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.green)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.green)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.green)));
                break;
            case R.id.btnDarkGreen:
                btnDarkGreen.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.darkGreen));
                editHex.setTextColor(Color.parseColor(getString(R.string.darkGreen)));
                editName.setTextColor(Color.parseColor(getString(R.string.darkGreen)));
                editURL.setTextColor(Color.parseColor(getString(R.string.darkGreen)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.darkGreen)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.darkGreen)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.darkGreen)));
                break;

            // Row 3
            case R.id.btnBlue:
                btnBlue.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.blue));
                editHex.setTextColor(Color.parseColor(getString(R.string.blue)));
                editName.setTextColor(Color.parseColor(getString(R.string.blue)));
                editURL.setTextColor(Color.parseColor(getString(R.string.blue)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.blue)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.blue)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.blue)));
                break;
            case R.id.btnDarkBlue:
                btnDarkBlue.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.darkBlue));
                editHex.setTextColor(Color.parseColor(getString(R.string.darkBlue)));
                editName.setTextColor(Color.parseColor(getString(R.string.darkBlue)));
                editURL.setTextColor(Color.parseColor(getString(R.string.darkBlue)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.darkBlue)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.darkBlue)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.darkBlue)));
                break;
            case R.id.btnPurple:
                btnPurple.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.purple));
                editHex.setTextColor(Color.parseColor(getString(R.string.purple)));
                editName.setTextColor(Color.parseColor(getString(R.string.purple)));
                editURL.setTextColor(Color.parseColor(getString(R.string.purple)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.purple)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.purple)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.purple)));
                break;
            case R.id.btnDarkPurple:
                btnDarkPurple.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.darkPurple));
                editHex.setTextColor(Color.parseColor(getString(R.string.darkPurple)));
                editName.setTextColor(Color.parseColor(getString(R.string.darkPurple)));
                editURL.setTextColor(Color.parseColor(getString(R.string.darkPurple)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.darkPurple)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.darkPurple)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.darkPurple)));
                break;
            case R.id.btnBrown:
                btnBrown.setText(getString(R.string.selectedColor));
                editHex.setText(getString(R.string.brown));
                editHex.setTextColor(Color.parseColor(getString(R.string.brown)));
                editName.setTextColor(Color.parseColor(getString(R.string.brown)));
                editURL.setTextColor(Color.parseColor(getString(R.string.brown)));
                editUserName.setTextColor(Color.parseColor(getString(R.string.brown)));
                editPassword.setTextColor(Color.parseColor(getString(R.string.brown)));
                editEmail.setTextColor(Color.parseColor(getString(R.string.brown)));
                break;

        }
    }
}

//https://stackoverflow.com/questions/35533905/how-to-handle-onclick-in-fragments?rq=1
