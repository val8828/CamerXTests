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
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

        File internalStorageDir = getFilesDir();
//        Toast.makeText(this, internalStorageDir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        File alice = new File(internalStorageDir, "alice.csv");
        // Create file output stream
        FileOutputStream fos = null;
        if (alice.exists()){
            Toast.makeText(this, "alice exists", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, alice.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }

        try {
            fos = new FileOutputStream(alice);
            // Write a line to the file
            fos.write("Alice,25,1".getBytes());
            // Close the file output stream
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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