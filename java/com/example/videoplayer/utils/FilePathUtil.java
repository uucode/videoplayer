package com.example.videoplayer.utils;

import android.os.Environment;

/**
 * description:
 */
public class FilePathUtil {
    public static String getWorkDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/bzmedia";
    }

    public static String getAVideoPath() {
        return getWorkDir() + "/VID_" + System.currentTimeMillis() + ".mp4";
    }

    public static String getAAudioPath() {
        return getWorkDir() + "/audio_" + System.currentTimeMillis() + ".m4a";
    }
}
