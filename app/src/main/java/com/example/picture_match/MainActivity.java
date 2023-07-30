package com.example.picture_match;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button notimelimit,normal,hard;
    int levelNo=0;
    public  static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notimelimit = findViewById(R.id.activity_main_no_time_limit_button);
        normal = findViewById(R.id.activity_main_normal_button);
        hard = findViewById(R.id.activity_main_hard_button);

        sharedPreferences = getSharedPreferences("MyDownload",MODE_PRIVATE);

        notimelimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Level_Show_Activity.class);

                editor = sharedPreferences.edit();
                levelNo = sharedPreferences.getInt("LevelNo",0);
                startActivity(intent);
            }
        });
    }
}