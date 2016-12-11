package com.developer.celulasdepaz.Interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.developer.celulasdepaz.Fragments.*;
import com.developer.celulasdepaz.Fragments.AmbientesFamiliares;
import com.developer.celulasdepaz.Fragments.Espacios;
import com.developer.celulasdepaz.R;

/**
 * Created by lenovo on 25/08/2016.
 */
public class FragmentoInfografia extends Fragment {
    private ListView listView;
    private String names[] = {
            "Embarazo a temprana edad",
            "Consumo y abuso de drogas leagles e ilegales",
            "Ambientes familiares deteriorado o problemáticos",
            "Deserción Escolar",
            "Falta de oportunidades laborales, Informalidad y Desocupación ",
            "Capital social debilitado y participacion ciudadana incipiente",
            "Espacios Públicos para la convivencia insuficientes y deteriorados",
            "Marginación y Exclusión social",
    };
    private String desc[] = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",

    };
    private Integer imageid[] = {
            R.drawable.ic_about,
            R.drawable.ic_acerca,
            R.drawable.ic_consejo,
            R.drawable.ic_camara,
            R.drawable.ic_ayuda,
            R.drawable.ic_like,
            R.drawable.ic_glosario,
            R.drawable.ic_like,


    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_infografia, container, false);
        AdaptadorInfografia customList = new AdaptadorInfografia(getActivity(), names, desc, imageid);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(customList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)

                {
                    case 0:

                        Fragment newFragment = new FragmentInversion();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.exit_left);
                        transaction.replace(R.id.contenedor_principal, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;

                    case 1:
                        Fragment drogas = new FragmentConsumoAbuso();
                        FragmentTransaction transicion = getFragmentManager().beginTransaction();
                        transicion.replace(R.id.Principal, drogas);
                        transicion.addToBackStack(null);
                        transicion.commit();
                        break;
                    case 2:
                        Fragment ambientes_familiares = new AmbientesFamiliares();
                        FragmentTransaction trans = getFragmentManager().beginTransaction();
                        trans.replace(R.id.Principal, ambientes_familiares);
                        trans.addToBackStack(null);
                        trans.commit();
                        break;

                    case 3:
                        Fragment desercion_escolar = new DesercionEscolar();
                        FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                        transaction1.replace(R.id.Principal, desercion_escolar);
                        transaction1.addToBackStack(null);
                        transaction1.commit();
                        break;
                    case 4:
                        Fragment oportunidades = new OportunidadesLaborales();
                        FragmentTransaction pasar = getFragmentManager().beginTransaction();
                        pasar.replace(R.id.Principal, oportunidades);
                        pasar.commit();
                        break;
                    case 5:

                        Fragment capital = new CapitalSocial();
                        FragmentTransaction tras = getFragmentManager().beginTransaction();
                        tras.replace(R.id.Principal, capital);
                        tras.commit();
                        break;

                    case 6:

                        Fragment espacios = new Espacios();
                        FragmentTransaction change = getFragmentManager().beginTransaction();
                        change.replace(R.id.Principal, espacios);
                        change.commit();

                    case 7:
                        Fragment marginacion = new Marginacion();
                        FragmentTransaction pass = getFragmentManager().beginTransaction();
                        pass.replace(R.id.Principal, marginacion);
                        pass.commit();


                }
            }
        });


        return view;


    }
}
