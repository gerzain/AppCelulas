package com.developer.celulasdepaz.Interfaces;

import android.content.Context;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.developer.celulasdepaz.Adaptadores.AdaptadorFacebook;
import com.developer.celulasdepaz.FragmentFacebook.PostsFacebook;
import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Pages;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;


/**
 * Created by Irving on 13/11/2016.
 */
public class FragmentParticipa extends Fragment {


    CallbackManager callbackManager;
    ShareDialog shareDialog;
    List<Pages> pages;
    JSONObject object;
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorFacebook adaptador;
    private Context context;
    private ImageView imagen_perfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragmento_participa, container, false);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        reciclador = (RecyclerView) view.findViewById(R.id.recycler_feed);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        ButterKnife.inject(this, view);

        //imagen_perfil=(ImageView)view.findViewById(R.id.iv_Facebook_page) ;
        pages = new ArrayList<>();
        // adapter = new AdaptadorFeed(getActivity(), R.layout.item_lista_feed, actorsList);
        // listview.setAdapter(adapter);
        adaptador = new AdaptadorFacebook(pages);
        reciclador.setAdapter(adaptador);
       // getLikedPageInfo();
        //LoadImage();
        Cargar();
      //  getLikedPageInfo();


        context = container.getContext();
        ButterKnife.inject(this, view);










        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.PostFacebook);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /* Intent a=new Intent(getActivity().getApplicationContext(),ActividadFacebookPost.class);
                startActivity(a);*/

               Fragment prinicipal = new PostsFacebook();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_principal, prinicipal);
                transaction.commit();
                //Cargar();



            }
        });

        return view;

    }



    protected void getLikedPageInfo()
    {

        GraphRequest data_request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback()
                {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response)
                    {

                        try
                        {

                            JSONArray posts = json_object.getJSONObject("likes").optJSONArray("data");
                            // JSONObject img=json_object.getJSONObject("url");


                            Log.d("data1", posts.toString());
                            // Log.d("picture",img.toString());

                            for (int i = 0; i < posts.length(); i++)
                            {

                                JSONObject post = posts.optJSONObject(i);

                                String res = posts.toString();


                                GsonBuilder builder = new GsonBuilder();
                                Gson gson = builder.create();
                                pages = Arrays.asList(gson.fromJson(res, Pages[].class));
                                // pages=Arrays.asList(gson.fromJson(img_src,Pages[].class));
                                adaptador = new AdaptadorFacebook(pages);
                                reciclador.setAdapter(adaptador);
                                // Log.d("ddd",pages.toString());
                                String id = post.optString("id");
                                String category = post.optString("category");
                                //String pic=post.optString("picture");

                                String name = post.optString("name");
                                String imageurl = "https://graph.facebook.com/" + id + "/picture?type=large";
                                // Picasso.with(getContext()).load(imageurl).into(perfil_pagina);

                                int count = post.optInt("likes");

                                 Log.d("id -", id+" name -"+name+ " category-"+ category+ " likes count -" + count);

                            }

                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "likes{id,category,name,location,likes},picture{url},feed{message,id,created_time}");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

    public void Cargar()
    {
        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/likes",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        JSONObject jso = response.getJSONObject();

                         // Log.e("Imagen",jso.toString());
                        try {
                            JSONArray arr = jso.getJSONArray("data");
                            for (int i = 0; i < (arr.length()); i++) {
                                JSONObject json_obj = arr.getJSONObject(i);


                                String id = json_obj.getString("id");
                                String name = json_obj.getString("name");
                                String category = json_obj.getString("category");
                                String pic = json_obj.getString("picture");


                                //Se crea un nuevo objeto Json
                                JSONObject acces = new JSONObject(pic);
                                String data_pic = acces.getString("data");

                                //Creamos un nuevo objeto para acceder a data
                                JSONObject url = new JSONObject(data_pic);
                                String uri = url.getString("url");
                                String imageurl = "https://graph.facebook.com/" + id + "/picture?type=large";

                                Gson gson = new Gson();
                                Pages page = gson.fromJson(response.getJSONObject().toString(), Pages.class);
                                page.setNombre(name);
                               // page.setCategoria(category);
                               // page.setId(id);
                                page.setUrl(imageurl);

                                adaptador = new AdaptadorFacebook(pages);
                                reciclador.setAdapter(adaptador);
                                pages.add(page);


                                //String imageurl = "https://graph.facebook.com/" + id + "/picture?type=large";
                                //Picasso.with(getContext()).load(imageurl).into(imagen_perfil);


                                //for (int j = 0; j < (acces.length()); j++)
                                // {
                                // String url = acces.getString("url");
                                // Log.d("url",url);
                                // String data = acces.getString("data");
                                // Log.d("Data>>",data);
                                // JSONObject url=new JSONObject(data);

                                // String uri= (String) url.get("url");
                                //Log.d("Url",uri);


                                // }


                            /* Log.d("id -", id + " name -" + name + " category-" + category + "pic" + pic);
                                Log.d("data-",data_pic);
                                Log.d("url-",uri);*/

                            }// Termina el for para obtener JsonArray


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,category,location,picture{is_silhouette,url}");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
