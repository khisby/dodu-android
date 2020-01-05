package id.khisoft.dodu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import id.khisoft.dodu.Controller.KeuanganController;

public class tambah_keuangan extends AppCompatActivity {
    public Spinner spKategori;
    private RadioButton rbPengeluaran, rbPemasukan;
    private int pemasukan = 0;
    private Button btnTambahKeuangan, btnTambahKategori;
    private EditText etNominal, etKeterangan;
    private KeuanganController kc;

    @Override
    protected void onResume() {
        super.onResume();
        kc.getKategori(spKategori);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_keuangan);

        kc = new KeuanganController(this);

        rbPengeluaran = findViewById(R.id.rbPengeluaran);
        rbPemasukan = findViewById(R.id.rbPemasukan);
        btnTambahKeuangan = findViewById(R.id.btnTambahKeuangan);
        btnTambahKategori = findViewById(R.id.btnTambahKategori);
        etNominal = findViewById(R.id.etNominal);
        etKeterangan = findViewById(R.id.etKeterangan);
        spKategori = findViewById(R.id.spinnerKategori);

        rbPemasukan.setChecked(false);
        rbPengeluaran.setChecked(true);

        rbPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbPengeluaran.setChecked(true);
                rbPemasukan.setChecked(false);
                pemasukan = 0;
            }
        });

        rbPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbPemasukan.setChecked(true);
                rbPengeluaran.setChecked(false);
                pemasukan = 1;
            }
        });

        kc.getKategori(spKategori);

        btnTambahKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), tambah_kategori.class);
                startActivity(i);
            }
        });

        btnTambahKeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kategoriName = String.valueOf(spKategori.getSelectedItem());
                int kategoriIndex = spKategori.getSelectedItemPosition();
                char keluarMasuk = rbPengeluaran.isChecked() ? 'K' : 'M';
                int nominal = Integer.valueOf(etNominal.getText().toString());
                String keterangan = etKeterangan.getText().toString();

                kc.addKeuangan(kategoriName, kategoriIndex, keluarMasuk, nominal,keterangan);

                finish();
            }
        });


    }
}
