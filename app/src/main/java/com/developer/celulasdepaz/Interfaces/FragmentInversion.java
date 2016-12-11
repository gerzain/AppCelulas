package com.developer.celulasdepaz.Interfaces;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.developer.celulasdepaz.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Gerza on 05/04/2016.
 */
public class FragmentInversion extends Fragment {
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmento_tarjetas);
        WebView vis=(WebView)findViewById(R.id.Vista);
        WebSettings webSetting = vis.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);

        String htmlFilename = "inversion.html";
        AssetManager mgr =getApplicationContext().getAssets();
        try
        {
            InputStream in = mgr.open(htmlFilename, AssetManager.ACCESS_BUFFER);
            String htmlContentInStringFormat = StreamToString(in);
            in.close();
            vis.loadDataWithBaseURL(null, htmlContentInStringFormat, "text/html", "utf-8", null);

        } catch (IOException e)
        {
            e.printStackTrace();
        }


        agregarToolbar();
    }*/


    public static String StreamToString(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {

        }
        return writer.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_tarjetas, container, false);

        WebView vis = (WebView) view.findViewById(R.id.Vista);
        WebSettings webSetting = vis.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);

        String htmlFilename = "inversion.html";
        AssetManager mgr = getActivity().getAssets();
        try {
            InputStream in = mgr.open(htmlFilename, AssetManager.ACCESS_BUFFER);
            String htmlContentInStringFormat = StreamToString(in);
            in.close();
            vis.loadDataWithBaseURL(null, htmlContentInStringFormat, "text/html", "utf-8", null);

        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }


}