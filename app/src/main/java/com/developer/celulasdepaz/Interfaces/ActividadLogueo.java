package com.developer.celulasdepaz.Interfaces;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.developer.celulasdepaz.R;
import com.facebook.FacebookSdk;

import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import android.net.Uri;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 26/08/2016.
 */
public class ActividadLogueo extends AppCompatActivity {
    public static final String TAG = "Obtener hash";
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Uri imageuri;
    private AccessTokenTracker accessTokenTracker;
    private String username, firstname, lastname;
    private TextView tv_profile_name;
    private TextView tv_email;
    private ImageView iv_profile_pic;
    private CircleImageView perfil;
    private ProfileTracker profileTracker;
    //  ProfilePictureView facebook_perfil;
    private String id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());



        setContentView(R.layout.actividad_login);


        tv_profile_name = (TextView) findViewById(R.id.tv_nombre);
        iv_profile_pic = (ImageView) findViewById(R.id.Perfil);
        tv_email = (TextView) findViewById(R.id.tv_email);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        //  facebook_perfil = (ProfilePictureView)findViewById(R.id.FotoPerfil);
        // loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        loginButton.setReadPermissions(Arrays.asList("public_profile,email"));
        callbackManager = CallbackManager.Factory.create();  //Se llama al boton mediante un callback
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override

            public void onSuccess(LoginResult loginResult) {


                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();


/**
 * AccessTokenTracker to manage logout
 */
                accessTokenTracker = new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                               AccessToken currentAccessToken) {
                        if (currentAccessToken == null)
                        {

                            tv_profile_name.setText("");

                            iv_profile_pic.setImageBitmap(null);
                            iv_profile_pic.buildDrawingCache();
                            iv_profile_pic.getDrawingCache();
                        }
                    }
                };
                profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                        nextActivity(newProfile);
                    }


                };
                accessTokenTracker.startTracking();
                profileTracker.startTracking();


            }


            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                System.out.print("Aqui>>>");
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void nextActivity(Profile profile) {
        if (profile != null) {
            Intent main = new Intent(getApplicationContext(), ActividadPrincipal.class);
            String nombre = profile.getFirstName();
            String apellido = profile.getLastName();
            id = profile.getId();
            String nombre_completo = nombre + apellido;
            tv_profile_name.setText(nombre_completo);
            String imageurl = "https://graph.facebook.com/" + id + "/picture?type=large";
            Picasso.with(ActividadLogueo.this).load(imageurl).into(iv_profile_pic);
            iv_profile_pic.buildDrawingCache();
            Bitmap image = iv_profile_pic.getDrawingCache();
            Bundle extras = new Bundle();
            extras.putParcelable("imagebitmap", image);
            extras.putString("nombre", nombre_completo);
            Log.d("NombreLogin", nombre_completo);
            main.putExtras(extras);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(main);


        }


    }

    @Override
    protected void onResume() {

        super.onResume();

        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }


}
