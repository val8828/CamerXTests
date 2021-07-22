package com.example.camerxtests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enableCamera = findViewById(R.id.enableCamera);
        enableCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    enableCamera();
            }
        });

        Button enableAudioRecorder = findViewById(R.id.enableSoundRecorder);
        enableAudioRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableAudioRecorder();
            }
        });
    }

    private void enableCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    private void enableAudioRecorder() {
        Intent intent = new Intent(this, AudioRecordActivity.class);
        startActivity(intent);
    }
}