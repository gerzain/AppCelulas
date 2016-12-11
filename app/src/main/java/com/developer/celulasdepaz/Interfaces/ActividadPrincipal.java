package com.developer.celulasdepaz.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;


import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.celulasdepaz.R;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;


public class ActividadPrincipal extends AppCompatActivity {

    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String key = "nameKe";
    public String nombre_usuario;
    public Uri direccion;
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedpreferences;
    private String id_usuario;
    private Profile profile;
    private Bitmap btmap;
    private CircleImageView perfil;
    private TextView tv_facebook_name;
    private final String Tag="GGG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        initItemViews();
        if (navigationView != null)
        {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(3));
        }
        Bundle extras = getIntent().getExtras();

       if (extras != null)
        {
            //Verificar la existencia de datos
            Bitmap c = extras.getParcelable("imagebitmap");
            nombre_usuario = extras.getString("nombre");
            Log.d("PrincipalNom", nombre_usuario);
            LayoutInflater inflater = getLayoutInflater();
            View view=initItemViews();
            tv_facebook_name.setText(nombre_usuario);
        }

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            View view=getLayoutInflater().inflate(R.layout.drawer_cabezera,null);
            tv_facebook_name=(TextView)view.findViewById(R.id.tv_perfil_nombre_facebook);
            tv_facebook_name.setText(Tag);
        }

        if (sharedpreferences.contains(key)) {
           /* perfil=(CircleImageView)findViewById(R.id.iv_perfil_circular);
            String set=sharedpreferences.getString(key,null);
            Log.e("UrlOnCreate>>",set);
            Picasso.with(getApplicationContext()).load(set).into(perfil);*/


        }


    } // Finaliza metodo OnCreate





 /*   public void PasarParametros()
    {
        String nombre;
        Bundle extras =getIntent().getExtras();
        if (extras != null)
        {
            nombre= extras.getString("nombre");
            Log.e("NombreActividad", nombre);

            //tv_nombre.setText(nombre);


            Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");
            perfil=(CircleImageView)findViewById(R.id.circle_image);
            //assert perfil != null;
           // perfil.setImageBitmap(bmp);
        }

        }*/

    public void Logout() {
        LoginManager.getInstance().logOut();
        IniciarSesion();
    }

    private void IniciarSesion() {
        Intent intent = new Intent(getApplicationContext(), ActividadLogueo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void Save()
    {
        //nombre_usuario = tv_nombre.getText().toString();
        profile = Profile.getCurrentProfile();
        if (profile != null) {
            id_usuario = profile.getId();
            String nombre = profile.getFirstName();
            String apellido = profile.getLastName();
            String nombre_completo = nombre + apellido;

            View view=getLayoutInflater().inflate(R.layout.drawer_cabezera,null);
            tv_facebook_name=(TextView)view.findViewById(R.id.tv_perfil_nombre_facebook);
            String imageurl = "https://graph.facebook.com/" + id_usuario + "/picture?type=large";
            tv_facebook_name.setText(nombre_completo);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Name, nombre_completo);
            editor.putString(key, imageurl);
            // Log.d("Image Log>>:", imageurl);
            editor.apply();
        }
    }

    public void Get() {
        //tv_nombre = (TextView) findViewById(R.id.username);
        View view=getLayoutInflater().inflate(R.layout.drawer_cabezera,null);
        tv_facebook_name=(TextView)view.findViewById(R.id.tv_perfil_nombre_facebook);
        CircleImageView foto_perfil=(CircleImageView)view.findViewById(R.id.circular_facebook);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            //tv_nombre.setText(sharedpreferences.getString(Name, ""));
           // tv_facebook_name.setText(sharedpreferences.getString(Name,""));
            tv_facebook_name.setText(sharedpreferences.getString(Name,""));
        }


        if (sharedpreferences.contains(key)) {
            // perfil=(CircleImageView)findViewById(R.id.iv_perfil_circular);
            String set = sharedpreferences.getString(key, null);
            // Log.e("UrlOnResume>>",set);
            Picasso.with(getApplicationContext()).load(set).into(foto_perfil);

        }

    }


    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
           /* case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                break;*/
            case R.id.item_infografia:
                fragmentoGenerico = new FragmentoInfografia();
                break;
            case R.id.item_noticias:
                fragmentoGenerico = new FragmentoGrid();
                break;
            case R.id.item_hacer:
                fragmentoGenerico = new FragmentoQueHacer();
                break;
            case R.id.item_perfil:
                fragmentoGenerico = new FragmentoPerfil();
                break;
            case R.id.item_reconpensas:
                fragmentoGenerico = new FragmentoReconpensas();
                break;
            case R.id.item_Participa:
                fragmentoGenerico = new FragmentParticipa();
                break;
            case R.id.nav_log_out:
                // Toast toast1 = Toast.makeText(getApplicationContext(), "Cerrar sesion ", Toast.LENGTH_SHORT);
                // toast1.show();
                Logout();
                finish();
                break;

        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }
    private View initItemViews()
    {
        LayoutInflater inflater =getLayoutInflater();
        View view = inflater.inflate(R.layout.drawer_cabezera, null);

        tv_facebook_name=(TextView)view.findViewById(R.id.tv_perfil_nombre_facebook);
        perfil=(CircleImageView)view.findViewById(R.id.circular_facebook);

        return  view;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_carrito:

                Toast toast = Toast.makeText(getApplicationContext(), "Boton del perfil ", Toast.LENGTH_SHORT);
                toast.show();
                Fragment fragmentoGenerico = new FragmentoPerfil();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.contenedor_principal, fragmentoGenerico)
                        .commit();
                break;


            case R.id.action_salir:
                this.finish();
                System.exit(0);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
       // Save();
        Get();
        Log.d("En el metodo onResumme", "onRessume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Save();

    }
}
