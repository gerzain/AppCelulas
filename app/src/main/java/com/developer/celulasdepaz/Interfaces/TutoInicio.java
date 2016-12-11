package com.developer.celulasdepaz.Interfaces;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.celulasdepaz.R;

import com.developer.celulasdepaz.utils.Utils;

public class TutoInicio extends AppCompatActivity {

    static final String TAG = "PagerActivity";
    ImageButton mNextBtn;
    Button mSkipBtn, mFinishBtn;
    ImageView zero, one, two, tres, cuatro;
    ImageView[] indicators;

    int lastLeftValue = 0;

    CoordinatorLayout mCoordinator;
    int page = 0;   //  to track page position
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuto_inicio);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        // Boton para cambiar entre los fragmnets
        mNextBtn = (ImageButton) findViewById(R.id.intro_btn_next);
        //if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP)
        //mNextBtn.setImageDrawable(
        // Utils.tintMyDrawable(ContextCompat.getDrawable(this, R.drawable.siguiente), Color.WHITE)
        //);

        mSkipBtn = (Button) findViewById(R.id.intro_btn_skip);
        mFinishBtn = (Button) findViewById(R.id.intro_btn_finish);
        zero = (ImageView) findViewById(R.id.intro_indicator_0);
        one = (ImageView) findViewById(R.id.intro_indicator_1);
        two = (ImageView) findViewById(R.id.intro_indicator_2);
        tres = (ImageView) findViewById(R.id.intro_indicator_3);
        cuatro = (ImageView) findViewById(R.id.intro_indicator_4);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.main_content);
        indicators = new ImageView[]{zero, one, two, tres, cuatro};
        mViewPager.setCurrentItem(page);
        actualizar(page);

        final int color1 = ContextCompat.getColor(this, R.color.color_fragmnet);
        final int color2 = ContextCompat.getColor(this, R.color.color_fragment2);
        final int color3 = ContextCompat.getColor(this, R.color.color_fragment2);
        final int color4 = ContextCompat.getColor(this, R.color.color_fragent3);
        final int color5 = ContextCompat.getColor(this, R.color.color_azul);
        final int[] colorList = new int[]{color1, color2, color3, color4, color5};
        final ArgbEvaluator evaluator = new ArgbEvaluator();


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[position == 4 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);

            }

            @Override
            public void onPageSelected(int position) {
                page = position;

                actualizar(page);

                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(color1);
                        break;
                    case 1:
                        mViewPager.setBackgroundColor(color2);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(color3);
                        break;
                    case 3:
                        mViewPager.setBackgroundColor(color4);
                        break;
                    case 4:
                        mViewPager.setBackgroundColor(color5);
                        break;
                }
                mNextBtn.setVisibility(position == 4 ? View.GONE : View.VISIBLE);
                mFinishBtn.setVisibility(position == 4 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                mViewPager.setCurrentItem(page, true);
            }
        });
        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent introIntent = new Intent(getApplicationContext(), ActividadPrincipal.class);
                startActivity(introIntent);
                //Mensajealterta(v);
            }
        });
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent introIntent = new Intent(getApplicationContext(), ActividadPrincipal.class);
                startActivity(introIntent);
                // Utils.saveSharedSetting(TutoInicio.this, ActividadPrincipal.PREF_USER_FIRST_TIME, "false");
            }
        });


    } // Termina la función onCreate

    public void actualizar(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.indicador : R.drawable.indicador_cambio
            );
        }

    } // Termina la función actualizar los fragments


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tuto_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void Mensajealterta(View view) {

        AlertDialog.Builder alerta = new AlertDialog.Builder(TutoInicio.this);
        alerta.setTitle("Saltar el tutorial");
        alerta.setMessage("¿Realmente deseas saltar el tutorial?");
        alerta.setIcon(R.drawable.ic_launcher);


        alerta.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Intent volver = new Intent(getApplicationContext(), ActividadPrincipal.class);
                        startActivity(volver);

                    }
                });

        alerta.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                        return;
                    }
                });
        alerta.show();

    } // Termina el mensaje de alerta

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        ImageView img;
        TextView textos;
        TextView secciones;
        TextView nombre_app;

        int[] seccio = new int[]{R.string.item_inicia, R.string.item_consejos, R.string.item_glosario, R.string.item_eventos, R.string.item_comenzar};
        int[] texto = new int[]{R.string.inicia_app, R.string.consejos, R.string.explicación_glosario, R.string.explicacion_eventod, R.string.comienza};

        int[] bgs = new int[]{R.drawable.mano, R.drawable.cuentahabiente, R.drawable.rendimiento, R.drawable.fechacorte, R.drawable.comenzar};

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tuto_inicio, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(seccio[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

            TextView txt = (TextView) rootView.findViewById(R.id.titulo_app);
            //  txt.setText(getString(R.string.app_name, getArguments().getInt(ARG_SECTION_NUMBER)));

            textos = (TextView) rootView.findViewById(R.id.informacion);
            textos.setText(texto[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

            img = (ImageView) rootView.findViewById(R.id.section_img);
            img.setBackgroundResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "Seccion 4";
                case 4:
                    return "Seccion 5";
            }
            return null;
        }
    }
}
