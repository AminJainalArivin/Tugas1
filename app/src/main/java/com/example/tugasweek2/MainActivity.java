package com.example.tugasweek2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etJumlahBeli;
    private TextView tvNota;
    private Button btnTotal, btnBersih;
    private RadioGroup rgBarang;
    private RadioButton rbCatAnti, rbCatMinyak, rbCatTembok, rbMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etJumlahBeli = findViewById(R.id.etJumlahBeli);
        tvNota = findViewById(R.id.tvNota);
        btnTotal = findViewById(R.id.btnTotal);
        btnBersih = findViewById(R.id.btnBersih);
        rgBarang = findViewById(R.id.rgBarang);
        rbCatAnti = findViewById(R.id.rbCatAnti);
        rbCatMinyak = findViewById(R.id.rbCatMinyak);
        rbCatTembok = findViewById(R.id.rbCatTembok);
        rbMember = findViewById(R.id.rbMember);

        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungTotal();
            }
        });

        btnBersih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bersihkanNota();
            }
        });
    }

    private void hitungTotal() {
        double hargaBarang = 0;
        double biayaAdmin = 0;
        double totalHarga;
        double diskon = 0;
        double totalBayar;

        int selectedId = rgBarang.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(getApplicationContext(), "Pilih salah satu barang", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedId == R.id.rbCatAnti) {
            hargaBarang = 50000; // Harga Cat Anti Bocor
            biayaAdmin = 2000;
        } else if (selectedId == R.id.rbCatMinyak) {
            hargaBarang = 75000; // Harga Cat Minyak
            biayaAdmin = 2500;
        } else if (selectedId == R.id.rbCatTembok) {
            hargaBarang = 60000; // Harga Cat Tembok
            biayaAdmin = 3000;
        }

        int jumlahBeli = Integer.parseInt(etJumlahBeli.getText().toString());

        totalHarga = hargaBarang * jumlahBeli;

        if (rbMember.isChecked()) {
            diskon = totalHarga * 0.05; // Diskon 5% untuk member
        }

        totalBayar = totalHarga + biayaAdmin - diskon;

        // Menampilkan nota
        String nota = "Nama Barang: " + getSelectedBarangName(selectedId) +
                "\nBanyak Barang: " + jumlahBeli +
                "\nTotal Harga Barang: " + totalHarga +
                "\nBiaya Admin: " + biayaAdmin +
                "\nDiskon: " + diskon +
                "\nPemotongan: " + biayaAdmin +
                "\nTotal Bayar: " + totalBayar;

        tvNota.setText(nota);
        tvNota.setVisibility(View.VISIBLE);
    }

    private void bersihkanNota() {
        etJumlahBeli.setText("");
        rgBarang.clearCheck();
        rbMember.setChecked(false);
        tvNota.setVisibility(View.GONE);
    }

    private String getSelectedBarangName(int selectedId) {
        if (selectedId == R.id.rbCatAnti) {
            return "Cat Anti Bocor";
        } else if (selectedId == R.id.rbCatMinyak) {
            return "Cat Minyak";
        } else if (selectedId == R.id.rbCatTembok) {
            return "Cat Tembok";
        }
        return "";
    }
}