package com.example.android.musicalstructureapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cansu on 21.03.2018.
 */

public class MusicAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Music> arrayList;

    public MusicAdapter(Context context, int layout, ArrayList<Music> arrayList) {

        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{

        TextView txtSongName, txtSingerName, txtAlbumName;
        ImageView ivAlbum, ivPlay;
        ListView songList;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (view == null) {

            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(layout, null);

            viewHolder.txtSongName = view.findViewById(R.id.song_name);
            viewHolder.txtSingerName = view.findViewById(R.id.singer_name);
            viewHolder.txtAlbumName = view.findViewById(R.id.album_name);
            viewHolder.songList = view.findViewById(R.id.song_list);
            viewHolder.ivAlbum = view.findViewById(R.id.music_icon);


            view.setTag(viewHolder);
        }
        else {

            viewHolder = (ViewHolder) view.getTag();
        }

        final Music music = arrayList.get(i);
        viewHolder.txtSongName.setText(music.getSongName());
        viewHolder.txtSingerName.setText(music.getSingerName());
        viewHolder.txtAlbumName.setText(music.getAlbumName());

        return view;

    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
