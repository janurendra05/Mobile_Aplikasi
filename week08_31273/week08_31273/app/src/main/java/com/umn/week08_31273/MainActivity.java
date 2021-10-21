package com.umn.week08_31273;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
private RadioGroup rgJenis;
private EditText etFileName;
private EditText etText;

private File tmpDir;
private File lokalDir;
private File extDir;
private File currDir;


private Context context;
private Button btnOpen;
private static PopupMenu pilihFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tmpDir = getCacheDir();
        lokalDir = getFilesDir();

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            extDir = null;
        } else{
            findViewById(R.id.rbExternal).setEnabled(false);
            extDir = null;
        }
        currDir = lokalDir;


        rgJenis = findViewById(R.id.rgJenis);
        etFileName = findViewById(R.id.etNameFile);
        etText = findViewById(R.id.etText);

        context = this;
        btnOpen = findViewById(R.id.btnOpen);

        pilihFile.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                etFileName.setText(menuItem.getTitle().toString());
                etFileName.setText("");
                return true;
            }
        });



    rgJenis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int cackedId) {
            String pilihan = ((RadioButton) findViewById(rgJenis.getCheckedRadioButtonId())).getText().toString();
            if (pilihan.equalsIgnoreCase("Temporary"))
                currDir=tmpDir;
            else if(pilihan.equalsIgnoreCase("Internal"))
                currDir = lokalDir;
            else currDir = extDir;
        }
    });
    }

    public void openFile(View view) {
    File[] files = null;
    if (currDir!=null)files = currDir.listFiles();
    if (files!=null){
        int n= files.length;
        pilihFile.getMenu().clear();
        for (int i=0;i<files.length;i++)
            pilihFile.getMenu().add(files[i].getName());
            pilihFile.show();
            bacaFile();
    }else {
        Toast.makeText(context,"Ada Masalah akses folder"+"atau folder masih kosong",Toast.LENGTH_LONG).show();
    }
    }

    public void bacaFile(){
        if (etFileName.getText().toString().length()>0){
            File file = new File(currDir,etFileName.getText().toString());
            String isiFile="";
            try {
                InputStream inStream = new FileInputStream(file);
                if(inStream!=null){
                    InputStreamReader isReader = new InputStreamReader(inStream);
                    BufferedReader bReader = new BufferedReader(isReader);
                    String terimaString = "";
                    StringBuilder sb = new StringBuilder();
                    while ((terimaString=bReader.readLine())!=null){
                        sb.append(terimaString).append("\n");
                    }
                    inStream.close();
                    isiFile = sb.toString();
                    etText.setText(isiFile);
                }
            } catch (FileNotFoundException e) {
                Toast.makeText(context,"File Tidak Ditemukan",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(context,"Error I/O",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void saveFile(View view) {
    String nFile = etFileName.getText().toString();
    String isiText = etText.toString();
    if(nFile.length()>0 && isiText.length()>0 && currDir != null){
        File file = new File(currDir,nFile);

        try {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(file));
            writer.write(isiText);
            writer.close();
            Toast.makeText(this,"Text Sudah Tersimpan",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public void deleteFile(View view) {
    if (etFileName.getText().toString().length()>0){
        boolean sukses = false;
        if(currDir!=null&&currDir==lokalDir){
            sukses=context.deleteFile(etFileName.getText().toString());
        }else {
            sukses = new File(currDir,etFileName.getText().toString()).delete();
        }
        if (sukses) Toast.makeText(context,"File berhasil Dihapus",Toast.LENGTH_LONG).show();
        else Toast.makeText(context,"File Gagal Dihapus",Toast.LENGTH_LONG).show();


        etFileName.setText("");
        etText.setText("");
        }
    }

    public void clearText(View view) {
    etText.setText("");
    etFileName.setText('.');
    }

    public void keluarApp(View view) {
        finishAffinity();
    }
}