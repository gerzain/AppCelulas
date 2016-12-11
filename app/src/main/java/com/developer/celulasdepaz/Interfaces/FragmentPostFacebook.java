package com.developer.celulasdepaz.Interfaces;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.utils.Utility;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.LoggingPermission;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Irving on 16/11/2016.
 */
public class FragmentPostFacebook extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int STORAGE_PERMISSION_CODE = 123;
    public String path;
    public Uri direccion;
    Button btnCamara;
    EditText titulo;
    EditText descripcion;
    Button publicar;
    CallbackManager callbackManager;
    ImageView subir_foto;
    ShareDialog shareDialog;
    Bitmap bitmap;
    LoginManager loginManager;
    private String userChoosenTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actividad_postear_facebook, container, false);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        btnCamara = (Button) view.findViewById(R.id.iv_img_postear);
        titulo = (EditText) view.findViewById(R.id.TituloAcrividad);
        descripcion = (EditText) view.findViewById(R.id.editDescripcion);
        publicar = (Button) view.findViewById(R.id.btnPublicar);
        subir_foto = (ImageView) view.findViewById(R.id.BtnCamara);
        bitmap = null;
        requestStoragePermission();

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"Tomar Foto", "Escoger de la galeria",
                        "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Agregar imagen!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //  boolean result = Utility.checkPermission(getActivity().getApplicationContext());
                        if (items[i].equals("Tomar Foto")) {
                            userChoosenTask = "Tomar Foto";
                            // if (result)
                            CargarImagenCamara();


                        } else if (items[i].equals("Escoger de la galeria")) {
                            userChoosenTask = "Escoger de la galeria";

                            CargarImagen();

                        } else if (items[i].equals("Cancelar")) {
                            subir_foto.setImageDrawable(null);
                            titulo.setText("");
                            descripcion.setText("");
                        }


                    }
                });
                builder.show();


            }
        });

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Share();
                Compartir();


            }
        });

        return view;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(getContext(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(getContext(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    public void CargarImagen() {
        Intent galeria = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, 0);
    }

    public void CargarImagenCamara() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, REQUEST_IMAGE_CAPTURE);

    }


    public void Share() {

        LoginManager.getInstance().logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)

                .build();
        ShareApi.share(content, new FacebookCallback<Result>() {
            @Override
            public void onSuccess(Result result) {

                Toast.makeText(getActivity(), "La publicacion se comparte", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Log.e("FACEBOOK_TEST", "share api cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("FACEBOOK_TEST", "share api error " + error);
            }
        });

    }

    public void Compartir() {


        String titulo_proyeco = titulo.getText().toString();
        String des = descripcion.getText().toString();
        if (ShareDialog.canShow(ShareLinkContent.class))


        {
            String dir = "http://cfile27.uf.tistory.com/image/2569B734583183C21038F4";

            //  direccion=Uri.parse(dir);

            //   Log.e("PathPublicar>>>",direccion.toString());
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(titulo_proyeco)
                    .setContentDescription(des)
                    .setImageUrl(Uri.parse(dir))
                    .build();
            shareDialog.show(linkContent);
        }
    }


    protected void getLikedPageInfo(LoginResult login_result) {

        GraphRequest data_request = GraphRequest.newMeRequest(
                login_result.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {

                        try {

                            JSONArray posts = json_object.getJSONObject("likes").optJSONArray("data");
                            Log.e("data1", posts.toString());
                            for (int i = 0; i < posts.length(); i++) {

                                JSONObject post = posts.optJSONObject(i);
                                String id = post.optString("id");
                                String url_img = "https://graph.facebook.com/" + id + "/picture?type=large";
                                String category = post.optString("category");
                                String name = post.optString("name");
                                int count = post.optInt("likes");

                                Log.e("id -", id + " name -" + name + " category-" + category + " likes count -" + count);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "likes{id,category,name,location,likes}");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == 0 && resultCode == Activity.RESULT_OK && null != data) {
                Uri selected_image = data.getData();
                //Bitmap bitmap = null;
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selected_image);
                subir_foto.setImageBitmap(bitmap);
                direccion = selected_image;
                Log.e("Imagen>>>", direccion.toString());


            } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                subir_foto.setImageBitmap(imageBitmap);
            }
        } catch (FileNotFoundException e)

        {
            e.printStackTrace();
            Toast.makeText(getActivity(), "No puedes usar la imagen selecionada ", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();

        }


    }// Termina Activity onResult de imagenes guardadas >>


}



