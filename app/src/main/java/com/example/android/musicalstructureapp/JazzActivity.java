package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class JazzActivity extends AppCompatActivity {

    private ArrayList<Music> arrayList;
    private MusicAdapter adapter;
    private ListView songList;


    private int[] songNumber = {R.raw.chet_baker_my_funny_valentine, R.raw.frank_sinatra_i_love_you_baby, R.raw.john_coltrane_blue_train, R.raw.louis_armstrong_a_kiss_to_build_a_dream_on,
            R.raw.louis_armstrong_la_vie_en_rose, R.raw.louis_armstrong_what_a_wonderful_world, R.raw.louis_armstrong_when_the_saints_go_marchinin, R.raw.nina_simone_feeling_good,
            R.raw.nina_simone_the_look_of_love, R.raw.sonny_rollins_airegin, R.raw.wayne_shorter_footprints, R.raw.wayne_shorter_infant_eyes};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        Resources res = getResources();
        final String[] songName = res.getStringArray(R.array.jazz_song_array);
        final String[] singerName = res.getStringArray(R.array.jazz_singer_array);
        final String[] albumName = res.getStringArray(R.array.jazz_album_array);

        songList = findViewById(R.id.song_list);
        arrayList = new ArrayList<>();

        for (int i = 0; i < songName.length; i++) {

            arrayList.add(new Music(songName[i], "Artist: " + singerName[i], "Album: " + albumName[i], songNumber[i]));

        }


        adapter = new MusicAdapter(this, R.layout.music_item, arrayList);
        songList.setAdapter(adapter);

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(JazzActivity.this, MediaPlayerScreen.class);

                TextView tv_songName = findViewById(R.id.song_name);

                tv_songName.setText(songName[i]);

                ArrayList<Integer> integerArrayList = new ArrayList(Arrays.asList(songName));
                int index = integerArrayList.indexOf(tv_songName.getText().toString());

                intent.putExtra("selectedSong", songNumber[index]);

                intent.putExtra("index", index);

                intent.putExtra("songName", songName);
                intent.putExtra("singerName", singerName);
                intent.putExtra("albumName", albumName);

                intent.putExtra("songNumber", songNumber);

                songList.setAdapter(adapter);

                startActivity(intent);
            }
        });

    }
}
