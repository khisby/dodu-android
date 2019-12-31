package id.khisoft.dodu.fragment.laporan;

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
import android.widget.TextView;

import org.w3c.dom.Text;

import id.khisoft.dodu.Controller.KeuanganController;
import id.khisoft.dodu.R;

public class laporan extends Fragment {

    private LaporanViewModel mViewModel;
    private RecyclerView rvLaporan;
    private KeuanganController kc;
    private TextView tvTotalKeuangan;

    public static laporan newInstance() {
        return new laporan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.laporan_fragment, container, false);
        kc = new KeuanganController(getActivity());
        rvLaporan = view.findViewById(R.id.rvLaporan);
        tvTotalKeuangan = view.findViewById(R.id.tvTotalKeuangan);

        kc.getAllLaporan(rvLaporan,tvTotalKeuangan);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvLaporan.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LaporanViewModel.class);
        // TODO: Use the ViewModel
    }

}
