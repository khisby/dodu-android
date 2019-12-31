package id.khisoft.dodu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import id.khisoft.dodu.R;
import id.khisoft.dodu.entity.Laporan;

public class RecyclerViewAdapterLaporan extends RecyclerView.Adapter<RecyclerViewAdapterLaporan.ViewHolder> {
    private ArrayList<Laporan> laporan;

    public RecyclerViewAdapterLaporan(ArrayList<Laporan> laporan) {
        this.laporan = laporan;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterLaporan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.laporan_view, parent, false);
        return new RecyclerViewAdapterLaporan.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterLaporan.ViewHolder holder, int position) {
        holder.tvLaporanKategori.setText(laporan.get(position).getKategori());

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        holder.tvLaporanKeluar.setText(String.valueOf(formatRupiah.format(laporan.get(position).getKeluar())));
        holder.tvLaporanMasuk.setText(String.valueOf(formatRupiah.format(laporan.get(position).getMasuk())));
        holder.tvLaporanTotal.setText(String.valueOf(formatRupiah.format(laporan.get(position).getTotal())));
    }

    @Override
    public int getItemCount() {
        return laporan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLaporanKategori, tvLaporanKeluar, tvLaporanMasuk, tvLaporanTotal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLaporanKategori = itemView.findViewById(R.id.tvLaporanKategori);
            tvLaporanKeluar = itemView.findViewById(R.id.tvLaporanKeluar);
            tvLaporanMasuk = itemView.findViewById(R.id.tvLaporanMasuk);
            tvLaporanTotal = itemView.findViewById(R.id.tvLaporanTotal);
        }
    }
}
