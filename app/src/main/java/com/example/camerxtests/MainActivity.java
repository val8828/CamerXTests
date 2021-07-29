package com.example.camerxtests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.exifinterface.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    private final int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE","android.permission.RECORD_AUDIO"};

    RecyclerView recyclerViewPublications;
    RecyclerViewAdapter recyclerViewAdapter;
    MainActivity context;
    MainViewModel viewModel;
    int counter = 0;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_n);

        if(!allPermissionsGranted()){
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
        initial();

    }

    private void initial(){

        context = this;
        recyclerViewPublications = findViewById(R.id.recyclerViewPublications);

        recyclerViewPublications.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerViewPublications ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
//                        Toast.makeText(MainActivity.this, , Toast.LENGTH_SHORT).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        viewModel = new ViewModelProvider(context).get(MainViewModel.class);

        viewModel.getPublicationMutableLiveData().observe(context, userListUpdateObserver);

        HashMap<Integer, Publication> publicationHashMap = readFilesInDirectory(new File(getBatchDirectoryName()));
        viewModel.addNewPublications( publicationHashMap.values());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enableCamera();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void enableCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    Observer<ArrayList<Publication>> userListUpdateObserver = new Observer<ArrayList<Publication>>() {
        @Override
        public void onChanged(ArrayList<Publication> publicationArrayList) {
            recyclerViewAdapter = new RecyclerViewAdapter(context, publicationArrayList);
            recyclerViewPublications.setLayoutManager(new LinearLayoutManager(context));
            recyclerViewPublications.setAdapter(recyclerViewAdapter);
        }
    };

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
                initial();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }

    private HashMap<Integer, Publication> readFilesInDirectory(File file){
        HashMap<Integer, Publication> result = new HashMap<>();
        File[] list = file.listFiles();

        if(list != null) {
            for (File currentFile : list) {
                String fileName = currentFile.getAbsolutePath();
                if (fileName.contains("/preview_id")) {
                    Publication publication;// = new Publication();
                    int id = Integer.parseInt(fileName.substring((fileName.indexOf("/preview_id") + 11), fileName.lastIndexOf("_")));
                    if(result.containsKey(id)){
                        publication = result.get(id);
                        if(publication == null) publication = new Publication();
                    }else {
                        publication = new Publication();
                        publication.setId(id);
                    }

                    if(fileName.endsWith(".jpg")) {
                        publication.setImageFile(currentFile);
                        try {
                            ExifInterface exifInterface = new ExifInterface(currentFile);
//                            exifInterface.setAttribute("UserComment", mString);
//                            exifInterface.saveAttributes();
                            String description = exifInterface.getAttribute("UserComment");
                            if(description != null){
                                publication.setDescription(description);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(fileName.endsWith(".mp3")) {
                        publication.setAudioFile(currentFile);
                    }
                    result.put(id,publication);
                }
            }
        }
        return result;
    }

    static public String getBatchDirectoryName() {
        String app_folder_path = "";
        app_folder_path = Environment.getExternalStorageDirectory().toString() + "/Pictures/MyApp/";
        File dir = new File(app_folder_path);
        if (!dir.exists() && !dir.mkdirs()) {

        }

        return app_folder_path;
    }

}