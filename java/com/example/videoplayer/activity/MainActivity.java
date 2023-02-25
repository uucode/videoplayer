package com.example.videoplayer.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bzcommon.glutils.BZOpenGlUtils;
import com.bzcommon.utils.BZAssetsFileManager;
import com.bzcommon.utils.BZLogUtil;
import com.bzcommon.utils.BZPermissionUtil;
import com.example.videoplayer.BuildConfig;
import com.example.videoplayer.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "bz_MainActivity ";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("bzffmpeg");
        System.loadLibrary("native-lib");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BZAssetsFileManager.getFinalPath(this,"VID_Test.mp4");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BZOpenGlUtils.detectOpenGLES30(this);
        initNative(this, BuildConfig.DEBUG, Build.VERSION.SDK_INT);
//        requestPermission();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        File fileDir = new File(FilePathUtil.getWorkDir());
//        if (!fileDir.exists()) {
//            fileDir.mkdirs();
//        }
//    }

    private boolean requestPermission() {
        ArrayList<String> permissionList = new ArrayList<>();
        if (!BZPermissionUtil.isPermissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!BZPermissionUtil.isPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        String[] permissionStrings = new String[permissionList.size()];
        permissionList.toArray(permissionStrings);

        if (permissionList.size() > 0) {
            BZPermissionUtil.requestPermission(this, permissionStrings, BZPermissionUtil.CODE_REQ_PERMISSION);
            return false;
        } else {
            BZLogUtil.d(TAG, "Have all permissions");
            return true;
        }
    }

    public void VideoPlayerActivity(View view) {
        startActivity(new Intent(this, VideoPlayerActivity.class));
    }

    private native int initNative(Context context, boolean isDebug, int sdkInt);
}