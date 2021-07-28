package com.example.camerxtests;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class EditPhotoActivity extends AppCompatActivity {

    TextInputLayout textField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initial();
    }

    private void initial(){
        textField.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textField.getEditText() != null && textField.getEditText().getText() != null) {
                    System.out.println(textField.getEditText().getText().toString());
                }
            }
        });
    }
}
