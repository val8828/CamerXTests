package com.example.camerxtests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import android.widget.Toast;

import com.example.camerxtests.api.ApiFactory;
import com.example.camerxtests.api.ApiService;
import com.example.camerxtests.base.adapters.PublicationAdapter;
import com.example.camerxtests.base.pojo.Publication;
import com.example.camerxtests.base.pojo.PublicationResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPublications;
    private PublicationAdapter adapter;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    private final int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{
            "android.permission.CAMERA",
            "android.permission.RECORD_AUDIO",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_n);

        if(!allPermissionsGranted()){
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
        afterCreate();
    }
    @Override
    protected void onDestroy() {
        if(compositeDisposable != null){
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }

    private void enableCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    private void enableAudioRecorder() {
        Intent intent = new Intent(this, AudioRecordActivity.class);
        startActivity(intent);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void checkPermission(){

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
               afterCreate();
            } else {
                Toast.makeText(this, R.string.Permission_not_granted, Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }

    private void afterCreate(){

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showToast("I'm busy");
                String path = Environment.getExternalStorageDirectory().toString()+"/folder";
                Log.d("Files", "Path: " + path);
                File directory = new File(path);
                File[] files = directory.listFiles();
                if(files != null) {
                    Log.d("Files", "Size: "+ files.length);
                    for (File file : files) {
                        Log.d("Files", "FileName:" + file.getName());
                    }
                }
            }
        });
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setBackground(null);
//        bottomNavigationView.getMenu().getItem(1).setEnabled(false);

//
//        Button enableCamera = findViewById(R.id.enableCamera);
//        enableCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    enableCamera();
//            }
//        });
//
//        Button enableAudioRecorder = findViewById(R.id.enableSoundRecorder);
//        enableAudioRecorder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enableAudioRecorder();
//            }
//        });
//
//        File internalStorageDir = getFilesDir();
////        Toast.makeText(this, internalStorageDir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//        File alice = new File(internalStorageDir, "alice.csv");
//        // Create file output stream
//        FileOutputStream fos = null;
//        if (alice.exists()){
//            Toast.makeText(this, "alice exists", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, alice.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//        }
//
//        try {
//            fos = new FileOutputStream(alice);
//            // Write a line to the file
//            fos.write("Alice,25,1".getBytes());
//            // Close the file output stream
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        recyclerViewPublications = findViewById(R.id.recyclerViewPublications);
        adapter = new PublicationAdapter();
        adapter.setPublications(new ArrayList<Publication>());
        recyclerViewPublications.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPublications.setAdapter(adapter);
        List<Publication> publications = new ArrayList<>();
        Publication publication1 = new Publication();
        Publication publication2 = new Publication();
        publication1.setCommonDescription("Description1");
        publication2.setCommonDescription("Description2");
        publication1.setName("Title1");
        publication2.setName("Title2");

        publications.add(publication1);
        publications.add(publication2);
        adapter.setPublications(publications);

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        disposable = apiService.getPublications()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PublicationResponse>() {
                    @Override
                    public void accept(PublicationResponse publicationResponse) throws Exception {
                        adapter.setPublications(publicationResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Get data error", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }
}