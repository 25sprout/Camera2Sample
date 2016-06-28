package com.crazyma.camerasample;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private File file;
    private AutoFitTextureView textureView;
    private CameraUtil cameraUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textureView = (AutoFitTextureView) findViewById(R.id.texture_view);

        file = getOutputMediaFile();
        cameraUtil = new CameraUtil(this,file);
        cameraUtil.setTextureView(textureView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraUtil.resumeCamera();
    }

    @Override
    protected void onPause() {
        cameraUtil.pauseCamera();
        super.onPause();
    }


    public void takePictureButtonClicked(View view) {
        cameraUtil.takePicture();
    }

    private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "image.jpg");

        return mediaFile;
    }
}
