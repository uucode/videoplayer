package com.example.videoplayer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.bzcommon.utils.BZAssetsFileManager;
import com.example.videoplayer.R;
import com.example.videoplayer.widget.BZNativeVideoView;

public class VideoPlayerActivity extends AppCompatActivity {

    private BZNativeVideoView bzNativeVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        SeekBar seek_bar = findViewById(R.id.seek_bar);

        bzNativeVideoView = findViewById(R.id.bz_native_video_view);
        String videoPath = BZAssetsFileManager.getFinalPath(this,"VID_Test.mp4");;
        bzNativeVideoView.setAutoStartPlay(true);
        bzNativeVideoView.setPlayLoop(true);
        bzNativeVideoView.setOnProgressChangedListener(new BZNativeVideoView.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(float progress) {
                seek_bar.setProgress((int) (progress * seek_bar.getMax()));
            }
        });
        bzNativeVideoView.setDataSource(videoPath);


        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    bzNativeVideoView.seek(1.0f * progress / seek_bar.getMax());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                bzNativeVideoView.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                bzNativeVideoView.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        bzNativeVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bzNativeVideoView.release();
    }

    public void start(View view) {
        bzNativeVideoView.start();
    }

    public void pause(View view) {
        bzNativeVideoView.pause();
    }
}