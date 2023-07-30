package com.example.picture_match;

import static com.example.picture_match.MainActivity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game_Play_Activity extends AppCompatActivity {
    GridView gridView;
    ImageView imageView;
    TextView countdown;
    GamePlayAdapter gamePlayAdapter;
    ProgressBar progressBar;
    int levelno=0;
    private ArrayList<String> imgArr=new ArrayList<>();
    private List<String> arrayList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        gridView = findViewById(R.id.activity_game_play_gridview);
        countdown = findViewById(R.id.activity_game_play_countdown);
        progressBar = findViewById(R.id.activity_game_play_progess_bar);
        levelno = getIntent().getIntExtra("LevelNO",0);

        String[] images = new String[0];
        try {
            images = getAssets().list("");
            imgArr = new ArrayList<String>(Arrays.asList(images));
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
        Collections.sort(imgArr);

        Log.d("TTT", "getImage: All Images= "+imgArr);

        imgArr.remove("device_features");
        imgArr.remove("hybrid");
        imgArr.remove("images");
        imgArr.remove("keys");
        imgArr.remove("license");
        imgArr.remove("sounds");
        imgArr.remove("tel_uniqid_len8.dat");
        imgArr.remove("xiaomi_mobile.dat");
        imgArr.remove("webkit");
        levelno++;
        if(levelno<=3) {
            editor.putInt("LevelNo",levelno);
            editor.commit();
            arrayList = imgArr.subList(0, 6);
            arrayList.addAll(arrayList);
            Collections.shuffle(arrayList);
            Collections.shuffle(arrayList);
            Log.d("TTT", "getImage: Selected Images= " + arrayList);

            gamePlayAdapter = new GamePlayAdapter(Game_Play_Activity.this, arrayList, countdown, progressBar);
            gridView.setAdapter(gamePlayAdapter);
        }
        else if(levelno>4||levelno<=7)
        {
            arrayList = imgArr.subList(0, 9);
            arrayList.addAll(arrayList);
            Collections.shuffle(arrayList);
            Collections.shuffle(arrayList);
            Log.d("TTT", "getImage: Selected Images= " + arrayList);

            gamePlayAdapter = new GamePlayAdapter(Game_Play_Activity.this, arrayList, countdown, progressBar);
            gridView.setAdapter(gamePlayAdapter);
        }
        else if(levelno>8||levelno<10)
        {
            arrayList = imgArr.subList(0, 21);
            arrayList.addAll(arrayList);
            Collections.shuffle(arrayList);
            Collections.shuffle(arrayList);
            Log.d("TTT", "getImage: Selected Images= " + arrayList);

            gamePlayAdapter = new GamePlayAdapter(Game_Play_Activity.this, arrayList, countdown, progressBar);
            gridView.setAdapter(gamePlayAdapter);
        }
    }
}