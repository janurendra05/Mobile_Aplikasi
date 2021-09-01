  package com.umn.kalkulatorteori_31273;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

  public class MainActivity extends AppCompatActivity {
      Button penjumlahan,pengurangan,pembagian,perkalian,samadengan,clear,hapus,plusmin,koma;
      Button satu,dua,tiga,empat,lima,enam,tujuh,delapan,sembilan,nol,nolnol;
      int i,n;
      TextView tampil1,tampil2,tampil3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        satu = (Button) this.findViewById(R.id.satu);
        dua = (Button) this.findViewById(R.id.dua);
        tiga = (Button) this.findViewById(R.id.tiga);
        empat = (Button) this.findViewById(R.id.empat);
        lima = (Button) this.findViewById(R.id.lima);
        enam = (Button) this.findViewById(R.id.enam);
        tujuh = (Button) this.findViewById(R.id.tujuh);
        delapan = (Button) this.findViewById(R.id.delapan);
        sembilan = (Button) this.findViewById(R.id.sembilan);
        nol = (Button) this.findViewById(R.id.nol);
        nolnol = (Button) this.findViewById(R.id.nolnol);
        koma = (Button) this.findViewById(R.id.koma);
        penjumlahan = (Button) this.findViewById(R.id.penjumlahan);
        perkalian = (Button) this.findViewById(R.id.perkalian);
        pembagian = (Button) this.findViewById(R.id.pembagian);
        pengurangan = (Button) this.findViewById(R.id.pengurangan);
        samadengan = (Button) this.findViewById(R.id.samadengan);
        clear = (Button) this.findViewById(R.id.clear);
        hapus = (Button) this.findViewById(R.id.hapus);

        tampil1 = (TextView) this.findViewById(R.id.tampil1);
        tampil2 = (TextView) this.findViewById(R.id.tampil2);
        tampil3 = (TextView) this.findViewById(R.id.tampil3);

        satu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(1,' '); }});

        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(2,' '); }});

        tiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(3,' '); }});

        empat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(4,' '); }});

        lima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(5,' '); }});

        enam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(6,' '); }});

        tujuh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(7,' '); }});

        delapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(8,' '); }});

        sembilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(9,' '); }});

        nol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(10,' '); }});

        nolnol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(100,' '); }});




        penjumlahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(-666,'+'); }});

        pengurangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(-666,'-'); }});

        perkalian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(-666,'x'); }});

        pembagian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(-666,'/'); }});

        samadengan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { logicgate(-666,'='); }});



        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { delete1(); }});

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { deleteall(); }});



    }

      private void deleteall() {
        tampil1.setText("");
        tampil2.setText("");
        tampil3.setText("");
      }

      private void delete1() {
          String tmp,tmp1;
        if(tampil3.getText().toString().isEmpty()==false){
            tmp = tampil3.getText().toString();
            tmp1= tmp.substring(0,tmp.length()-1);

            tampil3.setText(String.valueOf(tmp1));
        }
        if(tampil3.getText().toString().isEmpty()&&tampil1.getText().toString().isEmpty()==false){
            tampil2.setText("");
        }
        if(tampil2.getText().toString().isEmpty()&&tampil1.getText().toString().isEmpty()==false){
            tmp = tampil1.getText().toString();
            tmp1= tmp.substring(0,tmp.length()-1);

            tampil1.setText(String.valueOf(tmp1));
        }
        if (tampil1.getText().toString().isEmpty())
            tampil1.setText("");

      }

      protected void tampilan1(int tmp){
        if(tampil1.getText().toString().isEmpty()){
            if (tmp==10){tmp=0;}
            else if (tmp==100){tmp=0;}
        }

        else{
            if (tmp==10){tmp = (Integer.parseInt(tampil1.getText().toString())) * 10;}
            else if (tmp==100){tmp = (Integer.parseInt(tampil1.getText().toString())) * 100;}
            else {
            tmp = tmp + (Integer.parseInt(tampil1.getText().toString()) * 10);
            }
        }
        tampil1.setText(String.valueOf(tmp));
    }

      protected void tampilan2(int tmp){
          if(tampil3.getText().toString().isEmpty()){
              if (tmp==10){tmp=0;}
              else if (tmp==100){tmp=0;}
          }

          else{
              if (tmp==10){tmp = (Integer.parseInt(tampil3.getText().toString())) * 10;}
              else if (tmp==100){tmp = (Integer.parseInt(tampil3.getText().toString())) * 100;}
              else {
                  tmp = tmp + (Integer.parseInt(tampil3.getText().toString()) * 10);
              }
          }
          tampil3.setText(String.valueOf(tmp));
      }


    protected void hitung(char operator){
        int hasil = 0;
        if(tampil3.getText().toString().isEmpty()){
            tampil2.setText(String.valueOf(operator));
        }
        else if(operator=='='||hasil==0){

            int tmp1 = Integer.parseInt(tampil1.getText().toString());
            char opr = tampil2.getText().charAt(0);
            int tmp2 = Integer.parseInt(tampil3.getText().toString());

            switch (opr){
                case('+'): hasil = tmp1 + tmp2;break;
                case('-'): hasil = tmp1 - tmp2;break;
                case('x'): hasil = tmp1 * tmp2;break;
                case('/'): hasil = tmp1 / tmp2;break;

            }
            tampil2.setText("");
            tampil3.setText("");
            tampil1.setText(String.valueOf(hasil));

        }
    }

      protected void logicgate(int tmp,char operator) {

          if (operator == ' ' && tampil2.getText().toString().isEmpty()) {
              tampilan1(tmp);
          }

          if (tmp==(-666)){
              hitung(operator);
          }

          if(tampil2.getText().toString().isEmpty()==false&&operator==' '){
              tampilan2(tmp);
          }
      }
  }