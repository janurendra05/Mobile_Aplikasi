package com.umn.utslab_3123;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class login extends AppCompatActivity {
    private Button next;
    private EditText nickname2,nickname;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        next = findViewById(R.id.btnNama);
        nickname2 = findViewById(R.id.nickname2);
    }

    public void nonnon(View v){
        Intent go = new Intent(this,list_sfx2.class);
        EditText editText = (EditText) findViewById(R.id.nickname2);
        String message = editText.getText().toString();
        go.putExtra("EXTRA_MESSAGE", message);
        startActivity(go);
    }

    public void masuk(View view){
        Intent go = new Intent(this,list_sfx.class);
        startActivity(go);
    }
}
