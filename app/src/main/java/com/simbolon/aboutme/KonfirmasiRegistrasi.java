package com.simbolon.aboutme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KonfirmasiRegistrasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_registrasi);
        EditText txtNamaDepan = findViewById(R.id.isiNamadepan);
        EditText txtNamaBelakang = findViewById(R.id.isiNamaBelakang);
        EditText txtNIM = findViewById(R.id.isiNIM);
        EditText txtNoMakan = findViewById(R.id.isiNoMakan);
        EditText txtAngkatan = findViewById(R.id.isiAngkatan);
        Button btnKonfirmasi = findViewById(R.id.btnkonfirmasi);

        String nama_depan = getIntent().getStringExtra("nama_depan");
        String nama_belakang = getIntent().getStringExtra("nama_belakang");
        String nim = getIntent().getStringExtra("nim");
        String no_makan = getIntent().getStringExtra("no_makan");
        String angkatan = getIntent().getStringExtra("angkatan");

        txtNamaDepan.setText(nama_depan);
        txtNamaBelakang.setText(nama_belakang);
        txtNIM.setText(nim);
        txtNoMakan.setText(no_makan);
        txtAngkatan.setText(angkatan);
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KonfirmasiRegistrasi.this);
                builder.setTitle("Selamat");
                builder.setMessage("Yeay selamat "+ nama_depan + " " + nama_belakang + " Sudah terkonfirmasi" );
                builder.setCancelable(true);
                builder.setNeutralButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
                AlertDialog sukses = builder.create();
                sukses.show();
            }
        });

    }
}