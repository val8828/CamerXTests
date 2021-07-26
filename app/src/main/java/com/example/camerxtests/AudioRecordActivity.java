package com.example.camerxtests;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class AudioRecordActivity extends Activity {
    private final int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private Button button_start_recording;
    private Button button_stop_recording;
    private Button button_pause_recording;

    private String output;
    private MediaRecorder mediaRecorder;
    private Boolean state = false;
    private Boolean recordingStopped  = false;

//
//    private boolean isRecording = false;
//    private AudioRecordRecorderService recorderService;
//    private final String outputPath =  Environment.getExternalStorageDirectory().toString() + "/Music/123.pcm";
//    private Timer timer;
//    private int recordingTimeInSecs = 0;
//    private TimerTask displayRecordingTimeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        if(allPermissionsGranted()){
            findView();
            initView();

        } else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    private void findView() {
        button_start_recording = (Button) findViewById(R.id.button_start_recording);
        button_stop_recording = (Button) findViewById(R.id.button_stop_recording);
        button_pause_recording = (Button) findViewById(R.id.button_pause_recording);
    }

    private void initView() {
        output = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music/RecordTests/recording.mp3";
        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();

        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "/Music/RecordTests/");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if(Environment.isExternalStorageManager())
            {
                File internal = new File("/sdcard");
                File[] internalContents = internal.listFiles();

            }
            else
            {
                Intent permissionIntent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(permissionIntent);
            }
        }
        if (!folder.exists()) {
            Log.e("Not Found Dir", "Not Found Dir  ");
            folder.mkdir();
        } else {
            mediaRecorder = new MediaRecorder();

            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(output);

            bindListener();
        }

    }

    private void bindListener() {
        button_start_recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startRecording();
            }
        });

        button_stop_recording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
        button_pause_recording.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pauseRecording();
            }
        });
    }

    private boolean allPermissionsGranted(){

        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                findView();
                initView();
                bindListener();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }
    private void startRecording() {
        try {

            Toast.makeText(this, "StartRecording Button Pressed", Toast.LENGTH_SHORT).show();
            mediaRecorder.prepare();
            mediaRecorder.start();
            state = true;
            Toast.makeText(this, "Recording started!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        Toast.makeText(this, "Stop Recording Button Pressed", Toast.LENGTH_SHORT).show();
        if (state) {
            mediaRecorder.stop();
            mediaRecorder.release();
            state = false;
        } else {
            Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show();
        }
    }

    private void pauseRecording() {
        Toast.makeText(this, "Pause Recording Button Pressed", Toast.LENGTH_SHORT).show();
        if (state) {
            if (!recordingStopped) {
                Toast.makeText(this,"Stopped!", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mediaRecorder.pause();
                    recordingStopped = true;
                    button_pause_recording.setText(R.string.resume);
                }else {
                    Toast.makeText(this, "Wrong SDK version", Toast.LENGTH_SHORT).show();
                }

            } else {
                resumeRecording();
            }
        }
    }


    private void resumeRecording() {
        Toast.makeText(this,"Resume!", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mediaRecorder.resume();
            button_pause_recording.setText(R.string.pause);
            recordingStopped = false;
        }else {
            Toast.makeText(this, "Wrong SDK version", Toast.LENGTH_SHORT).show();
        }

    }
}
