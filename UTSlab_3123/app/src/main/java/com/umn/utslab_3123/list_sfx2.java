package com.umn.utslab_3123;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

public class list_sfx2 extends AppCompatActivity {
    TextView lagu1,lagu2,lagu3,lagu4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sfx_list2);

        Intent go = getIntent();
        String message = go.getStringExtra("EXTRA_MESSAGE");

        // Capture the layout's TextView and set the string as its text
        setTitle(message);
        lagu1 = findViewById(R.id.Lagu1);
        lagu2 = findViewById(R.id.Lagu2);
        lagu3 = findViewById(R.id.Lagu3);
        lagu4 = findViewById(R.id.Lagu4);

        Context context = getApplicationContext();
        CharSequence text = "Selamat Datang, "+message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.aboutme){
            startActivity(new Intent(this,profile.class));
        }
        if (item.getItemId()==R.id.logout){
            startActivity(new Intent(this,login.class));
        }

        return true;
    }

    public void otwlagu1(View view){
        Intent go = new Intent(this,lagu1.class);
        startActivity(go);
    }

    public void otwlagu2(View view){
        Intent go = new Intent(this,lagu2.class);
        startActivity(go);
    }
    public void otwlagu3(View view){
        Intent go = new Intent(this,lagu3.class);
        startActivity(go);
    }
    public void otwlagu4(View view){
        Intent go = new Intent(this,lagu4.class);
        startActivity(go);
    }

    public void delete1(View view){
        LinearLayout tmp = findViewById(R.id.lay1);
        tmp.removeAllViews();
    }

    public void delete2(View view){
        LinearLayout tmp = findViewById(R.id.lay2);
        tmp.removeAllViews();
    }
    public void delete3(View view){
        LinearLayout tmp = findViewById(R.id.lay3);
        tmp.removeAllViews();
    }
    public void delete4(View view){
        LinearLayout tmp = findViewById(R.id.lay4);
        tmp.removeAllViews();
    }
}
