package com.example.picture_match;

import static com.example.picture_match.MainActivity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class Level_Show_Activity extends AppCompatActivity {
    ListView listView;
    LevelAdapter levelAdapter;
    int[] Levelno = {1,2,3,4,5,6,7,8,9,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_show);
        listView = findViewById(R.id.activity_level_show_listview);
        levelAdapter = new LevelAdapter(Level_Show_Activity.this,Levelno);
        listView.setAdapter(levelAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editor.putInt("LevelNo",Levelno[i]);
                editor.commit();
                Intent intent = new Intent(Level_Show_Activity.this, Game_Play_Activity.class);
                intent.putExtra("LevelNO",i);
                startActivity(intent);
            }
        });
    }
}