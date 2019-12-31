package id.khisoft.dodu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

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
        NumberFormat formatter = new DecimalFormat("#.###");
//        String formattedNumber = formatter.format(transaksi.get(position).getNominal());

        SimpleDateFormat formatIncoming = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat formatOutgoing = new SimpleDateFormat("dd MMM yyyy");
        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");

        formatOutgoing.setTimeZone(tz);
        String s = "";
        try {
            s = formatOutgoing.format(formatIncoming.parse(transaksi.get(position).getWaktu()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvKategori.setText(transaksi.get(position).getKategori().getNama());

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        holder.tvNominal.setText(formatRupiah.format(transaksi.get(position).getNominal()));
        if(Integer.valueOf(transaksi.get(position).getJenis()) == 0){
            holder.tvJenis.setImageResource(R.drawable.transaksi_keluar);
        }else{
            holder.tvJenis.setImageResource(R.drawable.transaksi_masuk);
        }
//        holder.tvJenis.setText(transaksi.get(position).getJenis());
        holder.tvWaktu.setText(s);
        holder.tvDeskripsi.setText(transaksi.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return transaksi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKategori;
        private TextView tvNominal;
        private ImageView tvJenis;
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
