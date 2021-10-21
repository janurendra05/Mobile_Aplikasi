package com.umn.utslab_3123;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class lagu2 extends AppCompatActivity {
    Button play;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lagu2);
        play = findViewById(R.id.button);
        MediaPlayer lagu = MediaPlayer.create(this,R.raw.hah_gay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lagu.start();


            }
        });

    }


}
