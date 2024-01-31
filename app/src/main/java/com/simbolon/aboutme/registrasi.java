package com.simbolon.aboutme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simbolon.aboutme.R;

public class registrasi extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
//     mendata setiap objek yang kita akan digunakan
        EditText txtn_dpn = (EditText) findViewById(R.id.n_dpn);
        EditText txtnblkng = (EditText) findViewById(R.id.n_blkng);
        EditText txtnim = (EditText) findViewById(R.id.NIM);
        EditText txtthn_angkatan = (EditText) findViewById(R.id.thn_angkatan);
        EditText txtno_mkn = (EditText) findViewById(R.id.no_mkn);
        EditText txtpwd = (EditText) findViewById(R.id.pwd);
        EditText txtkon_pwd = (EditText) findViewById(R.id.kon_pwd);
        Button btnDaftar = (Button) findViewById(R.id.btn_dft);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtn_dpn.getText()) || TextUtils.isEmpty(txtnblkng.getText()) || TextUtils.isEmpty(txtnim.getText()) || TextUtils.isEmpty(txtthn_angkatan.getText())
                || TextUtils.isEmpty(txtno_mkn.getText()) || TextUtils.isEmpty(txtpwd.getText()) || TextUtils.isEmpty(txtkon_pwd.getText())){
//              Membuat alertdialog bahwa ada field yang kosong
                    AlertDialog.Builder apakahKosong = new AlertDialog.Builder(registrasi.this);
                    apakahKosong.setTitle("WARNING!");
                    apakahKosong.setMessage("ADA FIELD YANG KOSONG!!!");
                    apakahKosong.setCancelable(true);
                    apakahKosong.setNeutralButton("OKE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog kosong = apakahKosong.create();
                    kosong.show();
                } else if (!txtpwd.getText().toString().equals(txtkon_pwd.getText().toString())) {
                    AlertDialog.Builder apakahsama = new AlertDialog.Builder(registrasi.this);
                    apakahsama.setTitle("WARNING!");
                    apakahsama.setMessage("Password dan Konfirmasi tidak sama!!!");
                    apakahsama.setCancelable(true);
                    apakahsama.setNeutralButton("OKE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sama = apakahsama.create();
                    sama.show();
                }
                else {
                    AlertDialog.Builder sukses = new AlertDialog.Builder(registrasi.this);
                    sukses.setTitle("Sukses!");
                    sukses.setMessage("Pendaftaran Kamu berhasil :o");
                    sukses.setCancelable(true);
                    sukses.setNeutralButton("OKE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog Sukses = sukses.create();
                    Sukses.show();
                }
            }

        });
    }
}

