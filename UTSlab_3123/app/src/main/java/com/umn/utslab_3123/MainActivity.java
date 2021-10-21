package com.umn.utslab_3123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin,btnProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLibrary);
        btnProfile = findViewById(R.id.btnProfile);

    }



    public void kuylogin(View view){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }

    public void aboutme(View view){
        Intent intent = new Intent(this,profile.class);
        startActivity(intent);
    }
}