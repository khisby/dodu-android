package id.khisoft.dodu.fragment.keuangan;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.khisoft.dodu.R;
import id.khisoft.dodu.adapter.RecyclerViewAdapterKeuangan;
import id.khisoft.dodu.entity.Kategori;
import id.khisoft.dodu.entity.Pengguna;
import id.khisoft.dodu.entity.Transaksi;

public class keuangan extends Fragment {

    private KeuanganViewModel mViewModel;
    private RecyclerViewAdapterKeuangan adapterKeuangan;
    private ArrayList<Transaksi> transaksi;

    public static keuangan newInstance() {
        return new keuangan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.keuangan_fragment, container, false);
        View view = inflater.inflate(R.layout.keuangan_fragment, container, false);
        transaksi = new ArrayList<>();
        RecyclerView recycleView = view.findViewById(R.id.rvKeuangan);
        DataDummy();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
        recycleView.setHasFixedSize(true);

//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line));
//        recyclerView.addItemDecoration(itemDecoration);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapterKeuangan = new RecyclerViewAdapterKeuangan(transaksi);
        recycleView.setAdapter(adapterKeuangan);
        return view;
    }

    private void DataDummy() {
        Transaksi a = new Transaksi();
        a.setId(1);
        a.setJenis("Jenis a");

        Kategori kat = new Kategori();
        kat.setId(1);
        kat.setNama("Kategori1");
        a.setKategori(kat);

        Pengguna peng = new Pengguna();
        peng.setId(1);
        peng.setNama("Khisby");
        peng.setSandi("khisby");
        peng.setSurel("khisby@gmail.com");
        a.setPengguna(peng);

        a.setWaktu("23-09-2019");
        a.setNominal(10000);
        a.setKeterangan("Buat anu");

        transaksi.add(a);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(KeuanganViewModel.class);
        // TODO: Use the ViewModel
    }

}
