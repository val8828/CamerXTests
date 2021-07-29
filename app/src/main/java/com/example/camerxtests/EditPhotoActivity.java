package com.example.camerxtests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.exifinterface.media.ExifInterface;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditPhotoActivity extends AppCompatActivity {

    TextInputLayout textField;
    EditPhotoActivity context;
    File file;
    private int lastPublicationId = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        readPreferences();
        initial();
        context = this;
    }

    private void initial(){
        ImageView imageView3 = findViewById(R.id.imageView3);
        textField = findViewById(R.id.textField);
        file = new File(MainActivity.getBatchDirectoryName(), "Preview.jpg");
        if(file.exists()){
            imageView3.setImageURI(Uri.parse(file.getAbsolutePath()));

        }else {
            Toast.makeText(EditPhotoActivity.this, "Error opening preview image", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, CameraActivity.class);
            startActivity(intent);
        }
        textField.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AudioRecordActivity.class);
                startActivity(intent);
            }
        });
        textField.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(file != null){

                    try {

                        if(textField.getEditText()!= null && textField.getEditText().getText() != null){
                            String description = String.valueOf(textField.getEditText().getText());
                            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);

                            File previewFile = new File(MainActivity.getBatchDirectoryName(),"preview_id" + (++lastPublicationId) + "_" + mDateFormat.format(new Date())+ ".jpg");
//                            if (!previewFile.exists()) {
//                                try {
//                                    previewFile.mkdirs();
//
//                                    FileWriter writer = new FileWriter(previewFile);
////                                    writer.append(sBody);
//                                    writer.flush();
//                                    writer.close();
//
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }

                            copy(file, previewFile);
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                            Bitmap bitmap = BitmapFactory.decodeFile(previewFile.getAbsolutePath(), options);
                            Bitmap resized = Bitmap.createScaledBitmap(bitmap, 640, 480, true);
                            try (FileOutputStream out = new FileOutputStream(previewFile)) {
                                resized.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            setExifInfo(previewFile,description);

                            File dest = new File(MainActivity.getBatchDirectoryName(), "id" + (++lastPublicationId) + "_" + mDateFormat.format(new Date())+ ".jpg");
                            file.renameTo(dest);

                            setExifInfo(file,description);

                            writePreferences();
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void setExifInfo(File file, String info){
        try {
            ExifInterface exifInterface = new ExifInterface(file);
            exifInterface.setAttribute("UserComment", info);
            exifInterface.saveAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readPreferences(){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(EditPhotoActivity.this);
        lastPublicationId = myPreferences.getInt("LAST_PUBLICATION_ID", 0);
    }

    private void writePreferences(){
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(EditPhotoActivity.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("LAST_PUBLICATION_ID", lastPublicationId);
        myEditor.apply();

    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}
