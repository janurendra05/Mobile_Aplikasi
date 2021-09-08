package com.umn.week04_31273;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.umn.week04_31273.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText etIsian,etUrl;
    private Button btnkirim,btnBrowse;
    private TextView tvJawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etIsian = findViewById(R.id.isian);
        etUrl = findViewById(R.id.url);

        btnBrowse = findViewById(R.id.buttonBrowse);
        btnkirim = findViewById(R.id.buttonKirim);
        tvJawaban = findViewById(R.id.jawaban);


        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDua = new Intent(MainActivity.this,ActivityDua.class);

                String isian = etIsian.getText().toString();
                intentDua.putExtra("PesanDariMain",isian);
                startActivityForResult(intentDua,1);
            }
        });


        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlText = etUrl.getText().toString();
                if(urlText.isEmpty())
                    urlText = "http://umn.ac.id";

                Intent browseIntent = new Intent(Intent.ACTION_VIEW);
                browseIntent.setData(Uri.parse(urlText));

                if(browseIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(browseIntent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String jawaban = data.getStringExtra("jawaban");
                tvJawaban.setText(jawaban);
            }
        }
    }


}