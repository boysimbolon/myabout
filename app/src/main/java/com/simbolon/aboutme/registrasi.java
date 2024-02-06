package com.simbolon.aboutme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

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
        TextInputLayout dpn = findViewById(R.id.dpn);
        TextInputLayout konfpwd = findViewById(R.id.konfpwd);

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
                // Reset the background color of EditText views
                txtn_dpn.setBackgroundResource(R.drawable.edit_text);
                txtnblkng.setBackgroundResource(R.drawable.edit_text);
                txtnim.setBackgroundResource(R.drawable.edit_text);
                txtthn_angkatan.setBackgroundResource(R.drawable.edit_text);
                txtno_mkn.setBackgroundResource(R.drawable.edit_text);
                txtpwd.setBackgroundResource(R.drawable.edit_text);
                txtkon_pwd.setBackgroundResource(R.drawable.edit_text);

                if (TextUtils.isEmpty(namaDepan)) {
                    txtn_dpn.setBackgroundResource(R.drawable.edit_text_red_background);

                }
                if (TextUtils.isEmpty(namaBelakang)) {
                    txtnblkng.setBackgroundResource(R.drawable.edit_text_red_background);
                }
                if (TextUtils.isEmpty(nim)) {
                    txtnim.setBackgroundResource(R.drawable.edit_text_red_background);
                }
                if (TextUtils.isEmpty(tahunAngkatan)) {
                    txtthn_angkatan.setBackgroundResource(R.drawable.edit_text_red_background);
                }
                if (TextUtils.isEmpty(noMakanan)) {
                    txtno_mkn.setBackgroundResource(R.drawable.edit_text_red_background);
                }
                if (TextUtils.isEmpty(password)) {
                    txtpwd.setBackgroundResource(R.drawable.edit_text_red_background);
                }
                if (TextUtils.isEmpty(konfirmasiPassword)) {
                    txtkon_pwd.setBackgroundResource(R.drawable.edit_text_red_background);
                }
                if (TextUtils.isEmpty(namaDepan) || TextUtils.isEmpty(namaBelakang) || TextUtils.isEmpty(nim) ||
                        TextUtils.isEmpty(tahunAngkatan) || TextUtils.isEmpty(noMakanan) || TextUtils.isEmpty(password) ||
                        TextUtils.isEmpty(konfirmasiPassword)) {
                    showAlert("WARNING!", "ADA FIELD YANG KOSONG!!!");

                } else if (!password.equals(konfirmasiPassword)) {
                    showAlert("WARNING!", "Password dan Konfirmasi tidak sama!!!");
                } else {
                    try {
                        // Membuat objek JSON untuk pengguna baru
                        JSONObject newUser = new JSONObject();
                        String fullName = namaDepan + " " + namaBelakang;
                        newUser.put("name", fullName);
                        newUser.put("nim", nim);
                        newUser.put("tahun_angkatan", tahunAngkatan);
                        newUser.put("no_makanan", noMakanan);
                        newUser.put("pwd", password);

                        // Membaca data JSON dari file
                        JSONArray userArray;
                        File file = new File(getExternalFilesDir(null), "data.json");

                        // Mengecek apakah file sudah ada
                        if (file.exists()) {
                            FileInputStream fis = new FileInputStream(file);
                            int size = fis.available();
                            byte[] buffer = new byte[size];
                            fis.read(buffer);
                            fis.close();

                            String jsonString = new String(buffer, "UTF-8");
                            JSONObject jsonObject = new JSONObject(jsonString);
                            userArray = jsonObject.getJSONArray("user"); // Mengambil array user dari objek JSON
                        } else {
                            // Jika file tidak ada, inisialisasi JSONArray baru
                            userArray = new JSONArray();
                        }

                        // Menambahkan data baru ke dalam array JSON
                        userArray.put(newUser);

                        // Menyimpan data ke dalam file JSON
                        JSONObject updatedJsonObject = new JSONObject();
                        updatedJsonObject.put("user", userArray); // Simpan array user ke dalam objek JSON
                        String updatedJsonString = updatedJsonObject.toString();

                        // Menyimpan string JSON ke file
                        FileWriter fileWriter = new FileWriter(file);
                        fileWriter.write(updatedJsonString);
                        fileWriter.flush();
                        fileWriter.close();

                        // Menampilkan dialog sukses
                        showSuccessDialog(fullName);
                    } catch (JSONException | IOException e) {
                        e.printStackTrace(); // Pesan pengecualian ditampilkan di konsol
                    }
                };

    }

            private void showAlert(String title, String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(registrasi.this);
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


            private void showSuccessDialog(String fullName) {
                AlertDialog.Builder builder = new AlertDialog.Builder(registrasi.this);
                builder.setTitle("Sukses!");
                builder.setMessage("Pendaftaran " + fullName + " berhasil.");
                builder.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(registrasi.this, login.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
            });

        }}
