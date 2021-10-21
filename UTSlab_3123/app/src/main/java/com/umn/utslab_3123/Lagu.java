package com.umn.utslab_3123;

public class Lagu {

    private String nama;
    private String pencipta;
    private String alamat;


    public Lagu(String nama,String pencipta,String alamat){
        this.nama=nama;
        this.pencipta=pencipta;
        this.alamat=alamat;
    }

    public String getNama(){
        return nama;
    }

    public String getPencipta(){
        return pencipta;
    }

    public String getAlamat(){
        return alamat;
    }
}
