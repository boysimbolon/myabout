package com.simbolon.aboutme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class registrasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        EditText txtn_dpn = findViewById(R.id.n_dpn);
        EditText txtnblkng = findViewById(R.id.n_blkng);
        EditText txtnim = findViewById(R.id.NIM);
        EditText txtthn_angkatan = findViewById(R.id.thn_angkatan);
        EditText txtno_mkn = findViewById(R.id.no_mkn);
        EditText txtpwd = findViewById(R.id.pwd);
        EditText txtkon_pwd = findViewById(R.id.kon_pwd);

        Button btnDaftar = findViewById(R.id.btn_dft);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaDepan = txtn_dpn.getText().toString();
                String namaBelakang = txtnblkng.getText().toString();
                String nim = txtnim.getText().toString();
                String tahunAngkatan = txtthn_angkatan.getText().toString();
                String noMakanan = txtno_mkn.getText().toString();
                String password = txtpwd.getText().toString();
                String konfirmasiPassword = txtkon_pwd.getText().toString();

                TextInputLayout dpn = findViewById(R.id.dpn);
                TextInputLayout konfpwd = findViewById(R.id.konfpwd);

                // Reset the background color of TextInputLayouts
                dpn.setError(null);
                konfpwd.setError(null);

                boolean isError = false;

                if (TextUtils.isEmpty(namaDepan)) {
                    dpn.setError("Nama depan harus diisi");
                    isError = true;
                }
                if (TextUtils.isEmpty(namaBelakang)) {
                    dpn.setError("Nama belakang harus diisi");
                    isError = true;
                }
                if (TextUtils.isEmpty(nim)) {
                    dpn.setError("NIM harus diisi");
                    isError = true;
                }
                if (TextUtils.isEmpty(tahunAngkatan)) {
                    dpn.setError("Tahun angkatan harus diisi");
                    isError = true;
                }
                if (TextUtils.isEmpty(noMakanan)) {
                    dpn.setError("Nomor makanan harus diisi");
                    isError = true;
                }
                if (TextUtils.isEmpty(password)) {
                    konfpwd.setError("Password harus diisi");
                    isError = true;
                }
                if (TextUtils.isEmpty(konfirmasiPassword)) {
                    konfpwd.setError("Konfirmasi password harus diisi");
                    isError = true;
                }
                if (isError) {
                    return;
                }

                if (!password.equals(konfirmasiPassword)) {
                    showAlert("Peringatan", "Password dan konfirmasi tidak sama!");
                    return;
                }

                else{
                    showSuccessDialog(namaDepan,namaBelakang,nim,noMakanan,tahunAngkatan);
                }

            }
        });
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setNeutralButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSuccessDialog(String NamaDepan,String Namabelakang,String NIM,String Nomkn, String Angkatan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sukses!");
        builder.setMessage("Pendaftaran berhasil.");
        builder.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(registrasi.this, KonfirmasiRegistrasi.class);
                intent.putExtra("nama_depan",NamaDepan);
                intent.putExtra("nama_belakang",Namabelakang);
                intent.putExtra("nim",NIM);
                intent.putExtra("no_makan",Nomkn);
                intent.putExtra("angkatan",Angkatan);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Clear EditText fields
                EditText txtn_dpn = findViewById(R.id.n_dpn);
                EditText txtnblkng = findViewById(R.id.n_blkng);
                EditText txtnim = findViewById(R.id.NIM);
                EditText txtthn_angkatan = findViewById(R.id.thn_angkatan);
                EditText txtno_mkn = findViewById(R.id.no_mkn);
                EditText txtpwd = findViewById(R.id.pwd);
                EditText txtkon_pwd = findViewById(R.id.kon_pwd);

                txtn_dpn.getText().clear();
                txtnblkng.getText().clear();
                txtnim.getText().clear();
                txtthn_angkatan.getText().clear();
                txtno_mkn.getText().clear();
                txtpwd.getText().clear();
                txtkon_pwd.getText().clear();

                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
