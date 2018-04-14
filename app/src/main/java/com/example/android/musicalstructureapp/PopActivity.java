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

public class PopActivity extends AppCompatActivity {

    private ArrayList<Music> arrayList;
    private MusicAdapter adapter;
    private ListView songList;


    private int[] songNumber = {R.raw.bebe_rexha_meant_to_be, R.raw.bruno_mars_finesse, R.raw.camila_cabello_havana, R.raw.camila_cabello_never_be_the_same,
            R.raw.charlie_puth_attention, R.raw.dua_lipa_new_rules, R.raw.ed_sheeran_perfect, R.raw.ed_sheeran_photograph, R.raw.ed_sheeran_shape_of_you, R.raw.maroon_5_wait,
            R.raw.pharrell_williams_happy, R.raw.rihanna_diamonds, R.raw.selena_gomez_marshmellow, R.raw.taylor_swift_delicate};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        Resources res = getResources();
        final String[] songName = res.getStringArray(R.array.pop_song_array);
        final String[] singerName = res.getStringArray(R.array.pop_singer_array);
        final String[] albumName = res.getStringArray(R.array.pop_album_array);

        songList = findViewById(R.id.song_list);
        arrayList = new ArrayList<>();

        for (int i = 0; i < songName.length; i++) {

            arrayList.add(new Music(songName[i], "Artist: " + singerName[i], "Album: " + albumName[i], songNumber[i]));

        }
        ;

        adapter = new MusicAdapter(this, R.layout.music_item, arrayList);
        songList.setAdapter(adapter);

        songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PopActivity.this, MediaPlayerScreen.class);

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
