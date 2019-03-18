package br.com.ricardo.androidsamples;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentA extends Fragment {

    private EditText fragmentEdit;
    private Button fragmentButton;
    private Comunicador listener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_a, container, false);

        fragmentEdit = (EditText) v.findViewById(R.id.fragment1_edit);
        fragmentButton = (Button) v.findViewById(R.id.fragment1_button);

        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menesagem = fragmentEdit.getText().toString();
                listener.insereTexto(menesagem);
            }
        });

        return v;

    }

    public interface Comunicador{
        public void insereTexto(String texto);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Comunicador){
            listener = (Comunicador) context;
        } else {
            throw new ClassCastException();
        }
    }
}
