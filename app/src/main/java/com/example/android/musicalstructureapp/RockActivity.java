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

public class RockActivity extends AppCompatActivity {

    private ArrayList<Music> arrayList;
    private MusicAdapter adapter;
    private ListView songList;


    private int[] songNumber = {R.raw.a_perfect_circle_the_noose, R.raw.anathema_lost_control, R.raw.eagles_of_the_death_metal_already_died, R.raw.elvis_presley_cant_help_falling_in_love,
            R.raw.godsmack_awake, R.raw.guns_n_roses_november_rain, R.raw.guns_n_roses_this_i_love, R.raw.metallica_master_of_puppets, R.raw.metallica_nothing_else_matters, R.raw.muse_unintended,
            R.raw.opeth_harvest, R.raw.pink_floyd_another_brick_in_the_wall, R.raw.rage_against_the_machine_killing_in_the_name, R.raw.rammstein_keine_lust, R.raw.rammstein_mein_teil, R.raw.tool_forty_six_and_two, R.raw.tool_sober};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        Resources res = getResources();
        final String[] songName = res.getStringArray(R.array.rock_song_array);
        final String[] singerName = res.getStringArray(R.array.rock_singer_array);
        final String[] albumName = res.getStringArray(R.array.rock_album_array);

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
                Intent intent = new Intent(RockActivity.this, MediaPlayerScreen.class);

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

