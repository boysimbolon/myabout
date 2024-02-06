package com.simbolon.aboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView txtView = findViewById(R.id.regis);
        Button btnMasuk = findViewById(R.id.btnlogin);
        TextView txtnim = findViewById(R.id.nim);
        TextView txtpwd = findViewById(R.id.pwdlgn);

        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, registrasi.class);
                startActivity(intent);
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nimInput = txtnim.getText().toString();
                String pwdInput = txtpwd.getText().toString();

                if (TextUtils.isEmpty(nimInput) || TextUtils.isEmpty(pwdInput)) {
                    String errorMessage = "";

                    if (TextUtils.isEmpty(nimInput)) {
                        // Menandai EditText nim dengan border merah

                        errorMessage += "NIM, ";
                    } else {
                        // Menghapus pesan error jika EditText nim tidak kosong

                    }

                    if (TextUtils.isEmpty(pwdInput)) {
                        // Menandai EditText password dengan border merah

                        errorMessage += "Password";
                    } else {
                        // Menghapus pesan error jika EditText password tidak kosong

                    }

                    // Menampilkan AlertDialog dengan pesan error
                    showAlert("Masukan dengan benar", "Field " + errorMessage + " tidak boleh kosong");
                    return;
                }


                try {
                    // Load JSON from assets
                    InputStream inputStream = getAssets().open("data.json");
                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();

                    // Parse JSON
                    String json = new String(buffer, StandardCharsets.UTF_8);
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("user");

                    // Check credentials
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject userObject = jsonArray.getJSONObject(i);
                        String nim = userObject.getString("nim");
                        String pwd = userObject.getString("pwd");
                        Log.d("Login", "NIM: " + nim + ", PWD: " + pwd);
                        if (nimInput.equals(nim) && pwdInput.equals(pwd)) {
                            // Credentials match, start main activity
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent);
                            return;
                        }
                    }

                    // No matching credentials found
                    // Display error message
                    // You can use AlertDialog or Toast to display the message
                    Log.e("Login", "Invalid credentials");
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Log.e("Login", "Error loading or parsing JSON: " + e.getMessage());
                }
            }
            // Pada method showAlert, tambahkan parameter title dan message
            private void showAlert(String title, String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(login.this); // Gunakan login.this sebagai context
                builder.setTitle(title); // Set judul AlertDialog
                builder.setMessage(message); // Set pesan AlertDialog
                builder.setCancelable(true); // Set agar AlertDialog dapat di-cancel
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Tutup AlertDialog saat tombol OK ditekan
                    }
                });
                AlertDialog dialog = builder.create(); // Buat AlertDialog dari builder
                dialog.show(); // Tampilkan AlertDialog
            }
        });
    }
}
