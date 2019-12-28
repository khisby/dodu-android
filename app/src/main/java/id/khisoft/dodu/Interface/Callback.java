package id.khisoft.dodu.Interface;

import java.util.ArrayList;

import id.khisoft.dodu.entity.Transaksi;

public interface Callback {
    void onSuccess(ArrayList<Transaksi> transaksis);

    void onFail(String msg);
}