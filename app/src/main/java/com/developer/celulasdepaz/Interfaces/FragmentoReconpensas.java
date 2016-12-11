package com.developer.celulasdepaz.Interfaces;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.developer.celulasdepaz.R;

/**
 * Created by Irving on 18/08/2016.
 */
public class FragmentoReconpensas extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_reconpensas, container, false);

        ImageButton btnListenerR1 = (ImageButton) view.findViewById(R.id.btn_regalo_1);
        btnListenerR1.setOnClickListener(this);
        ImageButton btnListenerR2 = (ImageButton) view.findViewById(R.id.btn_regalo_2);
        btnListenerR2.setOnClickListener(this);
        ImageButton btnListenerR3 = (ImageButton) view.findViewById(R.id.btn_regalo_3);
        btnListenerR3.setOnClickListener(this);

        ImageButton btnListenerP1 = (ImageButton) view.findViewById(R.id.btn_premio_1);
        btnListenerP1.setOnClickListener(this);
        ImageButton btnListenerP2 = (ImageButton) view.findViewById(R.id.btn_premio_2);
        btnListenerP2.setOnClickListener(this);
        ImageButton btnListenerP3 = (ImageButton) view.findViewById(R.id.btn_premio_3);
        btnListenerP3.setOnClickListener(this);

        ImageButton btnListenerC1 = (ImageButton) view.findViewById(R.id.btn_cupon_1);
        btnListenerC1.setOnClickListener(this);
        ImageButton btnListenerC2 = (ImageButton) view.findViewById(R.id.btn_cupon_2);
        btnListenerC2.setOnClickListener(this);
        ImageButton btnListenerC3 = (ImageButton) view.findViewById(R.id.btn_cupon_3);
        btnListenerC3.setOnClickListener(this);

        ImageButton btnListenerS1 = (ImageButton) view.findViewById(R.id.btn_sorpresa_1);
        btnListenerS1.setOnClickListener(this);
        ImageButton btnListenerS2 = (ImageButton) view.findViewById(R.id.btn_sorpresa_2);
        btnListenerS2.setOnClickListener(this);
        ImageButton btnListenerS3 = (ImageButton) view.findViewById(R.id.btn_sorpresa_3);
        btnListenerS3.setOnClickListener(this);

        ProgressBar barraRecompensas = (ProgressBar) view.findViewById(R.id.barra_progreso_recompensas);
        barraRecompensas.getProgressDrawable().setColorFilter(
                Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        barraRecompensas.setScaleY(4.0f);

        return view;
    }


    @Override
    public void onClick(View v)

    {
        switch (v.getId()) {
            case R.id.btn_regalo_1:
                Toast.makeText(getActivity(), "Regalo 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_regalo_2:
                Toast.makeText(getActivity(), "Regalo 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_regalo_3:
                Toast.makeText(getActivity(), "Regalo 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_premio_1:
                Toast.makeText(getActivity(), "Premio 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_premio_2:
                Toast.makeText(getActivity(), "Premio 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_premio_3:
                Toast.makeText(getActivity(), "Premio 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cupon_1:
                Toast.makeText(getActivity(), "Cupón 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cupon_2:
                Toast.makeText(getActivity(), "Cupón 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cupon_3:
                Toast.makeText(getActivity(), "Cupón 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sorpresa_1:
                Toast.makeText(getActivity(), "Sorpresa 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sorpresa_2:
                Toast.makeText(getActivity(), "Sorpresa 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_sorpresa_3:
                Toast.makeText(getActivity(), "Sorpresa 3", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}