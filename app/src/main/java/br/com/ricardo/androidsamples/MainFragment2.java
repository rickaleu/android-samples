package br.com.ricardo.androidsamples;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainFragment2 extends Fragment {

    private TextView textReceptor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        textReceptor = (TextView) container.findViewById(R.id.text_receiver);

        return inflater.inflate(R.layout.activity_receptor_frag_par, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null){
            String ttt = bundle.getString("TEXTO");
            textReceptor.setText(ttt);
        }
    }
}
