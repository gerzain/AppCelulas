package com.developer.celulasdepaz.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Noticia;

/**
 * Created by lenovo on 25/08/2016.
 */
public class FragmentoGrid extends Fragment {
    private GridView gridView;
    private AdaptadorNoticias adaptadorNoticias;

    public FragmentoGrid() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_noticias, container, false);

        gridView = (GridView) view.findViewById(R.id.grid);


        adaptadorNoticias = new AdaptadorNoticias(getContext());
        gridView.setAdapter(adaptadorNoticias);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Noticia item = (Noticia) parent.getItemAtPosition(position);

                //  Toast toast = Toast.makeText(getContext(), "Noticias presionada", Toast.LENGTH_SHORT);
                //toast.show();
                Intent intent = new Intent(getContext(), DetalleNoticia.class);
                intent.putExtra(DetalleNoticia.EXTRA_PARAM_ID, item.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                Pair<View, String> pair1 = Pair.create(view.findViewById(R.id.imagen_coche), DetalleNoticia.VIEW_NAME_HEADER_IMAGE);
                //   p1 = Pair.create((View) view.findViewById(imagen_coche));
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), pair1);
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());


            }
        });

        return view;
    }

    public void Animacion() {


        Fragment newFragment = new FragmentoGrid();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FragNoticias, newFragment)
                .addToBackStack(null)
                .commit();

    }


}

