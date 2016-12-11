package com.developer.celulasdepaz.Interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Glosario;

/**
 * Fragmento que representa el contenido de cada pestaña dentro de la sección "Glosario"
 */
public class FragmentoCategoria extends Fragment {

    private static final String INDICE_SECCION
            = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;
    private AdaptadorGlosario adaptador;

    public static FragmentoCategoria nuevaInstancia(int indiceSeccion) {
        FragmentoCategoria fragment = new FragmentoCategoria();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                adaptador = new AdaptadorGlosario(Glosario.GlosarioA);
                break;
            case 1:
                adaptador = new AdaptadorGlosario(Glosario.GlosarioB);
                break;
            case 2:
                adaptador = new AdaptadorGlosario(Glosario.GlosarioC);
                break;
            case 3:
                adaptador = new AdaptadorGlosario(Glosario.Hacer);
                break;
            case 4:
                adaptador = new AdaptadorGlosario(Glosario.Glosarioe);
                break;

            case 5:
                adaptador = new AdaptadorGlosario(Glosario.Glosariof);
                break;

            case 6:
                adaptador = new AdaptadorGlosario(Glosario.Glosariog);
                break;
            case 7:
                adaptador = new AdaptadorGlosario(Glosario.Glosarioh);
                break;
            case 8:
                adaptador = new AdaptadorGlosario(Glosario.Glosarioi);
                break;
           /* case 9:
                adaptador= new AdaptadorGlosario(Glosario.Glosarioj);
                break;*/
            case 10:
                adaptador = new AdaptadorGlosario(Glosario.Glosariol);
                break;
            case 11:
                adaptador = new AdaptadorGlosario(Glosario.Glosariom);
                break;
           /* case 12:
                adaptador=new AdaptadorGlosario(Glosario.Glosarion);
                break;*/
            case 13:
                adaptador = new AdaptadorGlosario(Glosario.GlosarioO);
                break;
            case 14:
                adaptador = new AdaptadorGlosario(Glosario.Glosariop);
                break;
            case 15:
                adaptador = new AdaptadorGlosario(Glosario.Glosarior);
                break;
            case 16:
                adaptador = new AdaptadorGlosario(Glosario.Glosarios);
                break;
            case 17:
                adaptador = new AdaptadorGlosario(Glosario.Glosariot);
                break;
        }

        reciclador.setAdapter(adaptador);

        return view;
    }

}
