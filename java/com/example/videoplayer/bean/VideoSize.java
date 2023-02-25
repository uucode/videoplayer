package com.example.videoplayer.bean;

/**
 * Description:
 */
public class VideoSize {
    private int width;
    private int height;

    public VideoSize() {
    }

    public VideoSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getVideoWidth() {
        return width;
    }

    public void setVideoWidth(int videoHidth) {
        this.width = videoHidth;
    }

    public int getVideoHeight() {
        return height;
    }

    public void setVideoHeight(int videoHeight) {
        this.height = videoHeight;
    }
}
