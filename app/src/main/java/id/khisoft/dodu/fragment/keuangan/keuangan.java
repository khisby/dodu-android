package id.khisoft.dodu.fragment.keuangan;

import androidx.lifecycle.ViewModelProviders;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import id.khisoft.dodu.Controller.KeuanganController;
import id.khisoft.dodu.R;
import id.khisoft.dodu.adapter.RecyclerViewAdapterKeuangan;
import id.khisoft.dodu.entity.Kategori;
import id.khisoft.dodu.entity.Pengguna;
import id.khisoft.dodu.entity.Transaksi;

import static android.content.Context.MODE_PRIVATE;

public class keuangan extends Fragment {

    public KeuanganViewModel mViewModel;
    private ArrayList<Transaksi> transaksi;
    private KeuanganController kc;
    private RecyclerView recycleView;
    private ProgressBar progressBar;
    private boolean isScrolling;
    private RecyclerViewAdapterKeuangan adapterKeuangan;
    private int currentPage;

    public static keuangan newInstance() {
        return new keuangan();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        currentPage = 1;
//        return inflater.inflate(R.layout.keuangan_fragment, container, false);
        View view = inflater.inflate(R.layout.keuangan_fragment, container, false);
        kc = new KeuanganController(getActivity());
        isScrolling = false;
        recycleView = view.findViewById(R.id.rvKeuangan);
        progressBar = view.findViewById(R.id.progressBar);

        transaksi = kc.getAllTransaksi(recycleView,currentPage);
//        DataDummy();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycleView.setLayoutManager(layoutManager);
//        recycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            private int mPreviousTotal = 0;
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
//                    isScrolling = true;
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int visibleItemCount = recyclerView.getChildCount();
//                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
//                int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//                if (isScrolling) {
//                    if (totalItemCount > mPreviousTotal) {
//                        isScrolling = false;
//                        mPreviousTotal = totalItemCount;
//                    }
//                }
//                int visibleThreshold = 2;
////                System.out.println("Total item " + totalItemCount);
////                System.out.println("Visible item " + visibleItemCount);
////                System.out.println("First item " + firstVisibleItem );
////                System.out.println("Kiri " + (totalItemCount - visibleItemCount));
////                System.out.println("Kanan " + (firstVisibleItem + visibleThreshold));
//                if (!isScrolling && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
//                    progressBar.setVisibility(View.VISIBLE);
//                    isScrolling = true;
//                    currentPage = currentPage + 1;
//
//                    ArrayList<Transaksi> trans = kc.getAllTransaksi(recycleView,currentPage);
//                    transaksi.clear();
//                    transaksi.addAll(trans);
//
//                    for(Transaksi t: transaksi){
//                        System.out.println(t.getId());
//                    }
//                    System.out.println("===============");
//
////                    isScrolling = false;
////                    progressBar.setVisibility(View.GONE);
//                }
//
//            }
//        });
//        recycleView.setHasFixedSize(true);

//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.line));
//        recyclerView.addItemDecoration(itemDecoration);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        adapterKeuangan = new RecyclerViewAdapterKeuangan(transaksi);
//        recycleView.setAdapter(adapterKeuangan);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(KeuanganViewModel.class);
        // TODO: Use the ViewModel
    }

}
