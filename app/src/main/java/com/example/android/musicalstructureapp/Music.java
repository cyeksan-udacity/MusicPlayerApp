package com.example.android.musicalstructureapp;

/**
 * Created by Cansu on 21.03.2018.
 */

public class Music {

    private String mSongName;
    private String mSingerName;
    private String mAlbumName;
    private int mSongNumber;

    public Music(String songName, String singerName, String albumName, int songNumber) {

        mSongName = songName;
        mSingerName = singerName;
        mAlbumName = albumName;
        mSongNumber = songNumber;
    }

    public String getSongName() {
        return mSongName;
    }

    public void setSongName(String songName) {
        this.mSongName = songName;
    }

    public String getSingerName() {
        return mSingerName;
    }

    public void setSingerName(String singerName) {
        this.mSingerName = singerName;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        this.mAlbumName = albumName;
    }

    public int getSongNumber() {
        return mSongNumber;
    }

    public void setSongNumber(int songNumber) {
        this.mSongNumber = songNumber;
    }


}
