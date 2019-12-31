package id.khisoft.dodu.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import id.khisoft.dodu.Interface.Callback;
import id.khisoft.dodu.adapter.RecyclerViewAdapterKeuangan;
import id.khisoft.dodu.adapter.RecyclerViewAdapterLaporan;
import id.khisoft.dodu.entity.Kategori;
import id.khisoft.dodu.entity.Laporan;
import id.khisoft.dodu.entity.Transaksi;
import id.khisoft.dodu.home_screen;
import id.khisoft.dodu.utils.ConfigApi;

import static android.content.Context.MODE_PRIVATE;

public class KeuanganController {
    private Context ctx;
    private RequestQueue queue;
    private Activity activity;
    private String url;
    private SharedPreferences pref;
    private SharedPreferences prefTransaksi;
    ArrayList<Transaksi> transaksi;
    ArrayList<Laporan> laporan;
//    int currentPage;
//    int countPage;

    public KeuanganController(Activity activity) {
//        currentPage = 1;
//        countPage = 1;
        this.ctx = activity.getApplicationContext();;
        this.queue = Volley.newRequestQueue(this.ctx);
        this.activity = activity;
        this.url = ConfigApi.url;
        pref = this.ctx.getSharedPreferences("UserLoginDodu", MODE_PRIVATE);
        prefTransaksi = this.ctx.getSharedPreferences("TransaksiDodu", MODE_PRIVATE);
        transaksi = new ArrayList<>();
        laporan = new ArrayList<>();
    }

    public ArrayList<Transaksi> getAllTransaksi(final RecyclerView recycleView,int page){
        if(page == 1){
            String url = this.url + "keuangan/index";
            JSONObject params = new JSONObject();
            try {
                params.put("token", pref.getString("token", null));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            queue.add(new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getInt("status") == 200){
                            JSONObject data = new JSONObject(response.getString("data"));
                            Iterator iterator = data.keys();
                            String key  = (String) iterator.next();
                            JSONArray b = data.getJSONArray(key);
                            ArrayList<Transaksi> trans = new ArrayList<>();

                            for (int i = 0; i < b.length() ; i++) {
                                JSONObject keuangan = new JSONObject(b.getString(i));

                                Kategori k = new Kategori();
                                k.setNama(keuangan.getString("NAMA_KATEGORI"));
                                k.setId(keuangan.getInt("ID_KATEGORI"));

                                Transaksi t = new Transaksi();
                                t.setId(keuangan.getInt("ID_TRANSAKSI"));
                                t.setKeterangan(keuangan.getString("KETERANGAN_TRANSAKSI"));
                                t.setNominal(keuangan.getInt("NOMINAL_TRANSAKSI"));
                                t.setKategori(k);
                                t.setJenis(keuangan.getString("JENIS_TRANSAKSI"));
                                t.setWaktu(keuangan.getString("WAKTU_TRANSAKSI"));

                                trans.add(t);
                            }

//                        System.out.println(b.toString());

//                            SharedPreferences.Editor editor = prefTransaksi.edit();
//                            editor.putString("transaksi", b.toString());
//                            editor.putInt("jumlahPage", data.getInt("jumlahPage"));
//                            editor.putInt("currentPage", prefTransaksi.getInt("currentPage",1)+1);
//                            editor.apply();

                            RecyclerViewAdapterKeuangan adapterKeuangan = new RecyclerViewAdapterKeuangan(trans);
                            recycleView.setAdapter(adapterKeuangan);
//                        countPage = data.getInt("jumlahPage");
//                        currentPage = currentPage + 1;

                        }else{
                            Toast.makeText(ctx, response.getString("pesan"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", error.toString());
                }
            }));


//            try{
//                JSONArray b = new JSONArray(prefTransaksi.getString("transaksi",""));
//                for (int i = 0; i < b.length() ; i++) {
//                    JSONObject keuangan = new JSONObject(b.getString(i));
//
//                    Kategori k = new Kategori();
//                    k.setNama(keuangan.getString("NAMA_KATEGORI"));
//                    k.setId(keuangan.getInt("ID_KATEGORI"));
//
//                    Transaksi t = new Transaksi();
//                    t.setId(keuangan.getInt("ID_TRANSAKSI"));
//                    t.setKeterangan(keuangan.getString("KETERANGAN_TRANSAKSI"));
//                    t.setNominal(keuangan.getInt("NOMINAL_TRANSAKSI"));
//                    t.setKategori(k);
//                    t.setJenis(keuangan.getString("JENIS_TRANSAKSI"));
//                    t.setWaktu(keuangan.getString("WAKTU_TRANSAKSI"));
//
//                    transaksi.add(t);
//                }
//            }catch(JSONException e){
//                Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
//            }
        }
//        else{
//            if(page <= prefTransaksi.getInt("jumlahPage",1)){
//                String url = this.url + "keuangan/index/" + page;
//                JSONObject params = new JSONObject();
//                try {
//                    params.put("token", pref.getString("token", null));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                queue.add(new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            if(response.getInt("status") == 200){
//                                JSONObject data = new JSONObject(response.getString("data"));
//                                Iterator iterator = data.keys();
//                                String key  = (String) iterator.next();
//                                JSONArray b = data.getJSONArray(key);
//                                ArrayList<Transaksi> trans = new ArrayList<>();
//
//                                for (int i = 0; i < b.length() ; i++) {
//                                    JSONObject keuangan = new JSONObject(b.getString(i));
//
//                                    Kategori k = new Kategori();
//                                    k.setNama(keuangan.getString("NAMA_KATEGORI"));
//                                    k.setId(keuangan.getInt("ID_KATEGORI"));
//
//                                    Transaksi t = new Transaksi();
//                                    t.setId(keuangan.getInt("ID_TRANSAKSI"));
//                                    t.setKeterangan(keuangan.getString("KETERANGAN_TRANSAKSI"));
//                                    t.setNominal(keuangan.getInt("NOMINAL_TRANSAKSI"));
//                                    t.setKategori(k);
//                                    t.setJenis(keuangan.getString("JENIS_TRANSAKSI"));
//                                    t.setWaktu(keuangan.getString("WAKTU_TRANSAKSI"));
//
//                                    trans.add(t);
//                                }
//
////                        System.out.println(b.toString());
//
//                                SharedPreferences.Editor editor = prefTransaksi.edit();
//                                editor.putString("transaksi", b.toString());
//                                editor.putInt("jumlahPage", data.getInt("jumlahPage"));
//                                editor.putInt("currentPage", prefTransaksi.getInt("currentPage",1)+1);
//                                editor.apply();
//
//                        RecyclerViewAdapterKeuangan adapterKeuangan = new RecyclerViewAdapterKeuangan(transaksi);
//                        recycleView.setAdapter(adapterKeuangan);
////                        countPage = data.getInt("jumlahPage");
////                        currentPage = currentPage + 1;
//
//                            }else{
//                                Toast.makeText(ctx, response.getString("pesan"), Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("error", error.toString());
//                    }
//                }));
//
//
//                try{
//                    JSONArray b = new JSONArray(prefTransaksi.getString("transaksi",""));
//                    for (int i = 0; i < b.length() ; i++) {
//                        JSONObject keuangan = new JSONObject(b.getString(i));
//
//                        Kategori k = new Kategori();
//                        k.setNama(keuangan.getString("NAMA_KATEGORI"));
//                        k.setId(keuangan.getInt("ID_KATEGORI"));
//
//                        Transaksi t = new Transaksi();
//                        t.setId(keuangan.getInt("ID_TRANSAKSI"));
//                        t.setKeterangan(keuangan.getString("KETERANGAN_TRANSAKSI"));
//                        t.setNominal(keuangan.getInt("NOMINAL_TRANSAKSI"));
//                        t.setKategori(k);
//                        t.setJenis(keuangan.getString("JENIS_TRANSAKSI"));
//                        t.setWaktu(keuangan.getString("WAKTU_TRANSAKSI"));
//
//                        transaksi.add(t);
//                    }
//                }catch(JSONException e){
//                    Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }
        return transaksi;
    }

    public ArrayList<Laporan> getAllLaporan(final RecyclerView recycleView, final TextView tvTotalKeuangan){
            String url = this.url + "laporan/index";
            JSONObject params = new JSONObject();
            try {
                params.put("token", pref.getString("token", null));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            queue.add(new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if(response.getInt("status") == 200){
                            int totalKeuangan = 0;
                            JSONObject data = new JSONObject(response.getString("data"));
                            JSONArray kategori = data.getJSONArray("kategori");
                            JSONArray jumlah = data.getJSONArray("angka");
                            JSONArray keluar = data.getJSONArray("keluar");
                            JSONArray masuk = data.getJSONArray("masuk");
                            JSONArray total = data.getJSONArray("total");
                            ArrayList<Laporan> laporan = new ArrayList<>();

                            for (int i = 0; i < kategori.length() ; i++) {
                                Laporan l = new Laporan();
                                l.setJumlah(jumlah.getInt(i));
                                l.setKategori(kategori.getString(i));
                                l.setKeluar(keluar.getInt(i));
                                l.setMasuk(masuk.getInt(i));
                                l.setTotal(total.getInt(i));
                                laporan.add(l);

                                totalKeuangan = totalKeuangan + (masuk.getInt(i) - keluar.getInt(i));
                            }

                            RecyclerViewAdapterLaporan adapterLaporan = new RecyclerViewAdapterLaporan(laporan);
                            recycleView.setAdapter(adapterLaporan);

                            Locale localeID = new Locale("in", "ID");
                            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                            tvTotalKeuangan.setText(String.valueOf(formatRupiah.format(totalKeuangan)));

                        }else{
                            Toast.makeText(ctx, response.getString("pesan"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", error.toString());
                }
            }));

        return laporan;
    }
}
