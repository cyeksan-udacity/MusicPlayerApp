package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MediaPlayerScreen extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private int currentSong;
    private int[] songNumber;
    private int index;
    private String[] songName;
    private String[] singerName;
    private String[] albumName;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("position", mediaPlayer.getCurrentPosition());
        mediaPlayer.pause();

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_screen);

        Intent intent = getIntent();
        currentSong = intent.getIntExtra("selectedSong", 0);
        songNumber = intent.getIntArrayExtra("songNumber");
        index = intent.getIntExtra("index", 0);
        songName = intent.getStringArrayExtra("songName");
        singerName = intent.getStringArrayExtra("singerName");
        albumName = intent.getStringArrayExtra("albumName");

        TextView song = findViewById(R.id.song);
        TextView singer = findViewById(R.id.singer);
        TextView album = findViewById(R.id.album);

        song.setText(songName[index]);
        singer.setText("Artist: " + singerName[index]);
        album.setText("Album: " + albumName[index]);

        handler = new Handler();

        seekBar = findViewById(R.id.song_seekbar);

        mediaPlayer = MediaPlayer.create(this, currentSong);

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.start();


        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mediaPlayer.getDuration());
                playCycle();
                mediaPlayer.start();

            }

        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mediaPlayer.seekTo(i);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        final ImageView playPause = findViewById(R.id.play_pause);
        final ImageView nextIcon = findViewById(R.id.next_icon);
        final ImageView previousIcon = findViewById(R.id.previous_icon);


        playPause.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playPause.setImageResource(R.drawable.play_icon);

                } else {

                    mediaPlayer.start();
                    playCycle();
                    playPause.setImageResource(R.drawable.pause_icon);
                }
            }

        });

        previousIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                if (--index >= 0) {

                    mediaPlayer = MediaPlayer.create(view.getContext(), songNumber[index]);
                    seekBar.setMax(mediaPlayer.getDuration());
                    mediaPlayer.start();

                    playPause.setImageResource(R.drawable.pause_icon);

                    TextView song = findViewById(R.id.song);
                    TextView singer = findViewById(R.id.singer);
                    TextView album = findViewById(R.id.album);

                    song.setText(songName[index]);
                    singer.setText("Artist: " + singerName[index]);
                    album.setText("Album: " + albumName[index]);


                } else {

                    index = 0;
                    mediaPlayer = MediaPlayer.create(view.getContext(), songNumber[index]);
                    seekBar.setMax(mediaPlayer.getDuration());
                    mediaPlayer.start();
                    playPause.setImageResource(R.drawable.pause_icon);

                    TextView song = findViewById(R.id.song);
                    TextView singer = findViewById(R.id.singer);
                    TextView album = findViewById(R.id.album);

                    song.setText(songName[index]);
                    singer.setText("Artist: " + singerName[index]);
                    album.setText("Album: " + albumName[index]);

                }
            }

        });

        nextIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                mediaPlayer.reset();

                if (++index < songNumber.length) {

                    mediaPlayer = MediaPlayer.create(view.getContext(), songNumber[index]);
                    seekBar.setMax(mediaPlayer.getDuration());
                    mediaPlayer.start();

                    playPause.setImageResource(R.drawable.pause_icon);

                    TextView song = findViewById(R.id.song);
                    TextView singer = findViewById(R.id.singer);
                    TextView album = findViewById(R.id.album);

                    song.setText(songName[index]);
                    singer.setText("Artist: " + singerName[index]);
                    album.setText("Album: " + albumName[index]);


                } else {

                    index = songNumber.length - 1;
                    mediaPlayer = MediaPlayer.create(view.getContext(), songNumber[index]);
                    seekBar.setMax(mediaPlayer.getDuration());
                    mediaPlayer.start();
                    playPause.setImageResource(R.drawable.pause_icon);

                    TextView song = findViewById(R.id.song);
                    TextView singer = findViewById(R.id.singer);
                    TextView album = findViewById(R.id.album);

                    song.setText(songName[index]);
                    singer.setText("Artist: " + singerName[index]);
                    album.setText("Album: " + albumName[index]);

                }


            }

        });
        mediaPlayer.setLooping(true);
    }


    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        int pos = outState.getInt("position");

        mediaPlayer.seekTo(pos);

        super.onRestoreInstanceState(outState);
    }


    public void playCycle() {

        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()) {

            runnable = new Runnable() {
                @Override
                public void run() {

                    playCycle();

                }

            };

            handler.postDelayed(runnable, 1000);
        }

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }

}

