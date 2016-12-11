package com.developer.celulasdepaz.FragmentFacebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.celulasdepaz.Adaptadores.AdaptadorPost;
import com.developer.celulasdepaz.Deserializador.Fecha;
import com.developer.celulasdepaz.Deserializador.FooDeserializer;
import com.developer.celulasdepaz.R;
import com.developer.celulasdepaz.modelo.Post;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import  java.util.Date.*;
import java.util.Calendar.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irving on 09/12/2016.
 */
public class PostsFacebook extends Fragment

{
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorPost adaptador;
    private List<Post> post;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

            View view=inflater.inflate(R.layout.feed_page_facebook,container,false);

            reciclador = (RecyclerView) view.findViewById(R.id.reciclador_post);
            layoutManager=new LinearLayoutManager(getActivity());
            reciclador.setLayoutManager(layoutManager);
            post= new ArrayList<>();
            adaptador=new AdaptadorPost(post);
            reciclador.setAdapter(adaptador);
            cargarFeed();


        return  view;
    }

    public void cargarFeed()
    {
        GraphRequest request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/689426364518414",
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response)
                    {
                        // Insert your code here
                        JSONObject jso = response.getJSONObject();
                        // JSONArray arr = jso.getJSONArray("data");
                        //Log.d("Feed",jso.toString());

                        try
                        {
                            //JSONObject arr = jso.getJSONObject("feed");
                            //Log.d(">>",arr.toString());
                            JSONArray posts = jso.getJSONObject("feed").optJSONArray("data");
                           // JSONArray data=jso.getJSONArray("data");
                            for (int i = 0; i < (posts.length()); i++)
                            {
                                JSONObject json_obj = posts.getJSONObject(i);
                                String id=json_obj.getString("id");
                                String mensaje=json_obj.getString("message");
                                String fecha=json_obj.getString("created_time");
                                String formatedDate=new String();
                                Gson gson=new Gson();
                                Post feed_post=gson.fromJson(response.getJSONObject().toString(),Post.class);
                                //GsonBuilder builder = new GsonBuilder();
                                //builder.registerTypeAdapter(Fecha.class, new FooDeserializer());

                                //Gson gson1 = builder.create();
                               // Fecha myObject = gson1.fromJson(fecha, Fecha.class);
                                try
                                {


                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                                    Date date = dateFormat.parse(fecha);

                                    dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                                    formatedDate = dateFormat.format(date);

                                    Log.d("Date", formatedDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                feed_post.setMensaje(mensaje);
                                feed_post.setId_publicacion(id);
                                feed_post.setFecha_creacion(formatedDate);
                                adaptador=new AdaptadorPost(post);
                                reciclador.setAdapter(adaptador);
                                post.add(feed_post);

                               // Log.d("id -", id+" name -"+mensaje+ " date-"+ fecha);

                            }



                        } catch (JSONException e)
                        {


                            e.printStackTrace();
                         }
        }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "feed{message,id,created_time}");
        request.setParameters(parameters);
        request.executeAsync();
        }
        }
