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

public class ElectronicActivity extends AppCompatActivity {

    private ArrayList<Music> arrayList;
    private MusicAdapter adapter;
    private ListView songList;


    private int[] songNumber = {R.raw.armin_van_buuren_jai_envie_de_toi, R.raw.artbat_tabu, R.raw.betoko_raining_again, R.raw.charlotte_gainsbourg_paradisco,
            R.raw.djedjetronic_dirty_and_hard, R.raw.foals_late_night, R.raw.kavinsky_nightcall, R.raw.knife_party_internet_friends,
            R.raw.massive_attack_buterfly_caught, R.raw.oscar_the_wolf_joaquim, R.raw.raving_george_slaves, R.raw.raving_george_you_are_mine,
            R.raw.royksopp_what_else_is_there, R.raw.sofi_tukker_drinkee, R.raw.sofi_tukker_hey_lion, R.raw.unders_syria, R.raw.waxdolls_chips};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        Resources res = getResources();
        final String[] songName = res.getStringArray(R.array.electronic_song_array);
        final String[] singerName = res.getStringArray(R.array.electronic_singer_array);
        final String[] albumName = res.getStringArray(R.array.electronic_album_array);

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
                Intent intent = new Intent(ElectronicActivity.this, MediaPlayerScreen.class);

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
