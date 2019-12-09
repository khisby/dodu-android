package id.khisoft.dodu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.khisoft.dodu.R;
import id.khisoft.dodu.entity.Transaksi;

public class RecyclerViewAdapterKeuangan extends RecyclerView.Adapter<RecyclerViewAdapterKeuangan.ViewHolder> {

    private ArrayList<Transaksi> transaksi;

    public RecyclerViewAdapterKeuangan(ArrayList<Transaksi> transaksi) {
        this.transaksi = transaksi;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterKeuangan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.keuangan_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterKeuangan.ViewHolder holder, int position) {
        holder.tvKategori.setText(transaksi.get(position).getKategori().getNama());
        holder.tvNominal.setText(String.valueOf(transaksi.get(position).getNominal()));
        holder.tvJenis.setText(transaksi.get(position).getJenis());
        holder.tvWaktu.setText(transaksi.get(position).getWaktu());
        holder.tvDeskripsi.setText(transaksi.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return transaksi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKategori;
        private TextView tvNominal;
        private TextView tvJenis;
        private TextView tvWaktu;
        private TextView tvDeskripsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvNominal = itemView.findViewById(R.id.tvNominal);
            tvJenis = itemView.findViewById(R.id.tvJenis);
            tvWaktu = itemView.findViewById(R.id.tvWaktu);
            tvDeskripsi= itemView.findViewById(R.id.tvDeskripsi);
        }
    }
}
