package com.developer.celulasdepaz.Interfaces;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.celulasdepaz.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragmento que contiene otros fragmentos anidados para representar  al Glosario
 */
public class FragmentoGlosario extends Fragment {
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public FragmentoGlosario() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);

        if (savedInstanceState == null) {

            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            viewPager.setOffscreenPageLimit(2);
            poblarViewPager(viewPager);

            tabLayout.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBarLayout = (AppBarLayout) padre.findViewById(R.id.appbar);

        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBarLayout.addView(tabLayout);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(0), getString(R.string.titulo_tab_a));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(1), getString(R.string.titulo_tab_b));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(2), getString(R.string.titulo_tab_c));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(3), getString(R.string.titulo_tab_d));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(4), getString(R.string.titulo_tab_e));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(5), getString(R.string.titulo_tab_f));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(6), getString(R.string.titulo_tab_g));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(7), getString(R.string.titulo_tab_h));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(8), getString(R.string.titulo_tab_i));
        // adapter.addFragment(FragmentoCategoria.nuevaInstancia(9),getString(R.string.titulo_tab_j));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(10), getString(R.string.titulo_tab_l));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(11), getString(R.string.titulo_tab_m));
        //.adapter.addFragment(FragmentoCategoria.nuevaInstancia(12),getString(R.string.titulo_tab_n));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(13), getString(R.string.titulo_tab_o));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(14), getString(R.string.titulo_tab_p));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(15), getString(R.string.titulo_tab_r));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(16), getString(R.string.titulo_tab_s));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(17), getString(R.string.titulo_tab_t));

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // inflater.inflate(R.menu.menu_glosario, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBarLayout.removeView(tabLayout);
    }


    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }
}
