package com.example.picture_match;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LevelAdapter extends BaseAdapter {
    Level_Show_Activity level_show_activity;
    int[] levelno;
    int leveln=0;

    public LevelAdapter(Level_Show_Activity level_show_activity, int[] levelno) {
        this.level_show_activity = level_show_activity;
        this.levelno = levelno;
    }

    @Override
    public int getCount() {
        return levelno.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(level_show_activity).inflate(R.layout.activity_level_show_item_file,viewGroup,false);
        TextView textView = view.findViewById(R.id.activity_level_show_item_file_textview);
        textView.setText("LEVEL "+levelno[i]);
        /*if(levelno.length<=2)
        {
            MainActivity.editor.putInt("LevelNo",levelno[i]);
            MainActivity.editor.commit();
        }
        if(levelno.length>3||levelno.length<=6)
        {
            MainActivity.editor.putInt("LevelNo",levelno[i]);
            MainActivity.editor.commit();
        }*/
        return view;
    }
}
