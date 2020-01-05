package id.khisoft.dodu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.khisoft.dodu.Controller.KeuanganController;

public class tambah_kategori extends AppCompatActivity {
    private EditText etKategori;
    private Button btnKategori;
    private KeuanganController kc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kategori);
        kc = new KeuanganController(this);
        etKategori = findViewById(R.id.etKategori);
        btnKategori = findViewById(R.id.btnSimpanKategori);

        btnKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etKategori.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Kategori tidak boleh kosong", Toast.LENGTH_LONG).show();
                }else{
                    kc.addKategori(etKategori.getText().toString());
                    finish();
                }
            }
        });
    }
}
