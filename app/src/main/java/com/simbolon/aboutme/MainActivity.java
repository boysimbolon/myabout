package com.simbolon.aboutme;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnexit = (Button) findViewById(R.id.btnexit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitConfirmationDialog();
            }
        });
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda yakin ingin keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Menampilkan pesan peringatan sebelum keluar
                showExitWarningDialog();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tidak melakukan apa-apa jika pengguna memilih untuk tidak keluar
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showExitWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Hati-hati di luar!");

        AlertDialog dialog = builder.create();
        dialog.show();

        // Menutup aplikasi setelah beberapa detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss(); // Menutup dialog
                finishAffinity(); // Menutup aplikasi
            }
        }, 3000); // Menggunakan delay 3000 ms (3 detik), sesuaikan sesuai kebutuhan
    }
}
