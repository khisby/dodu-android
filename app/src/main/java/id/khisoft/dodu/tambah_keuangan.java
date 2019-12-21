package id.khisoft.dodu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class tambah_keuangan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_keuangan);

        Spinner spKategori = findViewById(R.id.spinnerKategori);
        final ArrayAdapter<String> adapterSpKategori = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, new String[]{"Minum","Makan","Main", "Pajak"});
        spKategori.setAdapter(adapterSpKategori);
        spKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(tambah_keuangan.this, adapterSpKategori.getItem(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
