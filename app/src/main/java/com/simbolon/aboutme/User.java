package com.simbolon.aboutme;

public class User {
    private String name;
    private String nim;
    private String tahunAngkatan;
    private String noMakanan;
    private String pwd;

    // Konstruktor kosong (diperlukan oleh Gson)
    public User() {}

    // Getter dan setter untuk setiap atribut
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getTahunAngkatan() {
        return tahunAngkatan;
    }

    public void setTahunAngkatan(String tahunAngkatan) {
        this.tahunAngkatan = tahunAngkatan;
    }

    public String getNoMakanan() {
        return noMakanan;
    }
    public void setNoMakanan(String noMakanan) {
        this.noMakanan = noMakanan;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
