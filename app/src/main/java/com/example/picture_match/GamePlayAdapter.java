package com.example.picture_match;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GamePlayAdapter extends BaseAdapter {
    Game_Play_Activity game_play_activity;
    List<String> arrayList;
    TextView countdown;
    private long delaytime = 5000;
    private Runnable runnable;
    int click = 1, pos1 = 0, pos2 = 0;
    private View firstview;
    ProgressBar progressBar;

    public GamePlayAdapter(Game_Play_Activity game_play_activity, List<String> arrayList, TextView countdown, ProgressBar progressBar) {
        this.game_play_activity = game_play_activity;
        this.arrayList = arrayList;
        this.countdown = countdown;
        this.progressBar = progressBar;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        view = LayoutInflater.from(game_play_activity).inflate(R.layout.activity_game_play_item_file,viewGroup,false);
        ImageView imageView=view.findViewById(R.id.activity_game_play_item_file_grid_imageview);
        InputStream inputStream=null;
        try {
            try {
                inputStream = game_play_activity.getAssets().open(""+ arrayList.get(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Drawable drawable =Drawable.createFromStream(inputStream,null);
            imageView.setImageDrawable(drawable);
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RelativeLayout relativeLayout = view.findViewById(R.id.relative);
        View mask = view.findViewById(R.id.activity_game_play_item_file_mask);
        new CountDownTimer(delaytime,1000) {
            @Override
            public void onTick(long l) {
                int interval = (int) (l / 1000);
                countdown.setText("Time: " + interval + "/0");
               /* progressBar.setProgress(1000,true);*/
                Log.d("TTT", "onTick: " + interval);
            }

            @Override
            public void onFinish() {
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long l) {
                        int time = (int) (l / 1000);
                        progressBar.setProgress(progressBar.getMax()-time);
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
                startGame(mask, relativeLayout,i, arrayList);
            }
        }.start();

        return view;
    }
    private void startGame(View mask, RelativeLayout relativeLayout, int i, List<String> arrayList)
    {
        int interval=100;
        Handler handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                mask.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(runnable, interval);
        relativeLayout.setOnClickListener(v -> {

            if (click == 1) {
                mask.setVisibility(View.INVISIBLE);
                pos1 = i;//0
                firstview = mask;
                click = 3;

                runnable = new Runnable() {
                    public void run() {
                        click = 2;
                    }
                };
                handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                handler.postDelayed(runnable, 100);
                System.out.println("first click");
            }
            if (click == 2) {
                mask.setVisibility(View.INVISIBLE);
                pos2 = i;//5
                click = 3;
                System.out.println("second click");
                if (arrayList.get(pos1).equals(arrayList.get(pos2))) {
                    System.out.println("match");
                    runnable = new Runnable() {
                        public void run() {
                            click = 1;
                        }
                    };
                    handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                    handler.postDelayed(runnable, 500);
                } else {
                    System.out.println("not match");
                    runnable = new Runnable() {
                        public void run() {
                            mask.setVisibility(View.VISIBLE);
                            firstview.setVisibility(View.VISIBLE);
                            click = 1;
                        }
                    };
                    handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                    handler.postDelayed(runnable, 500);
                }
            }
        });
        /*if(arrayList==null)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(game_play_activity);
            builder1.setMessage("Write your message here.");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }*/
    }
}
