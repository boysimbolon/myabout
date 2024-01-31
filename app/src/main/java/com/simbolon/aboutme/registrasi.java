package com.simbolon.aboutme;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simbolon.aboutme.R;

public class registrasi extends AppCompatActivity {

    private EditText konPwdEditText, nDpnEditText, nBlkngEditText, nIMEditText, thnAngkatanEditText, noMknEditText, pwdEditText;
    private Button btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        konPwdEditText = findViewById(R.id.kon_pwd);
        nDpnEditText = findViewById(R.id.n_dpn);
        nBlkngEditText = findViewById(R.id.n_blkng);
        nIMEditText = findViewById(R.id.NIM);
        thnAngkatanEditText = findViewById(R.id.thn_angkatan);
        noMknEditText = findViewById(R.id.no_mkn);
        pwdEditText = findViewById(R.id.pwd);

        btnRegis = findViewById(R.id.btn_regis);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }

    private void validateForm() {
        if (TextUtils.isEmpty(konPwdEditText.getText()) || TextUtils.isEmpty(nDpnEditText.getText()) ||
                TextUtils.isEmpty(nBlkngEditText.getText()) || TextUtils.isEmpty(nIMEditText.getText()) ||
                TextUtils.isEmpty(thnAngkatanEditText.getText()) || TextUtils.isEmpty(noMknEditText.getText()) ||
                TextUtils.isEmpty(pwdEditText.getText())) {
            // Salah satu atau lebih field kosong, tampilkan pesan kesalahan
            Toast.makeText(registrasi.this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
        } else {
            // Semua field diisi, lanjutkan proses registrasi
            // Implementasikan logika registrasi di sini
        }
    }
}
