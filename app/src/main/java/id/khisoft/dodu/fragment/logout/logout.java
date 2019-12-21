package id.khisoft.dodu.fragment.logout;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.khisoft.dodu.Controller.AkunController;
import id.khisoft.dodu.R;
import id.khisoft.dodu.login_screen;

import static android.content.Context.MODE_PRIVATE;

public class logout extends Fragment {
    private AkunController ac;
    private LogoutViewModel mViewModel;

    public static logout newInstance() {
        return new logout();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        Intent i = new Intent(getContext(), login_screen.class);
//        startActivity(i);
        ac = new AkunController(getActivity());
        ac.Logout();
//        View view = inflater.inflate(R.layout.logout_fragment, container, false);
//        getActivity().finish();
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LogoutViewModel.class);
        // TODO: Use the ViewModel
    }

}
