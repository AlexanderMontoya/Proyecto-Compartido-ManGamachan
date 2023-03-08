package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.squareup.picasso.Picasso;

public class lectura_manga extends AppCompatActivity {
    Button btnAvanzar,btnRetroceder,btnRegresar;
    TextView lblPagina;
    ImageView imagen;
    int cont=1;
    String url_image="aea";
    int paginas=1;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_manga);
        String aea=getIntent().getStringExtra("idMangacap");
        int paginas=Integer.parseInt(getIntent().getStringExtra("cantPaginas"));
        String idmanga=getIntent().getStringExtra("idmanga");
        btnAvanzar=findViewById(R.id.btnAvanzar);
        btnRetroceder=findViewById(R.id.btnRetroceder);
        btnRegresar=findViewById(R.id.btnRegresar);
        imagen=findViewById(R.id.imgManga);
        lblPagina=findViewById(R.id.lblPag);
        //cantidad_paginas(aea);
        buscarImagen(aea,1);
        lblPagina.setText((cont)+"\n/\n"+paginas);
        btnAvanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int foto;
                if(cont>=paginas){
                    Toast.makeText(getApplicationContext(),
                            "Esta es la ultima pagina",Toast.LENGTH_SHORT).show();
                }else{
                    cont++;
                    buscarImagen(aea,cont);
                    lblPagina.setText((cont)+"\n/\n"+paginas);
                }
            }
        });
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int foto;
                if(cont>=paginas){
                    Toast.makeText(getApplicationContext(),
                            "Esta es la ultima pagina",Toast.LENGTH_SHORT).show();
                }else{
                    cont++;
                    buscarImagen(aea,cont);
                    lblPagina.setText((cont)+"\n/\n"+paginas);
                }
            }
        });
        btnRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int foto;
                if(cont<=1){
                    Toast.makeText(getApplicationContext(),
                            "Esta es la primera pagina",Toast.LENGTH_SHORT).show();
                }else{
                    cont--;
                    buscarImagen(aea,cont);
                    lblPagina.setText((cont)+"\n/\n"+paginas);

                }
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(lectura_manga.this,CapitulosList.class);
                a.putExtra("ID", idmanga);
                startActivity(a);
            }
        });

    }
    public void buscarImagen(String id,int ou){
        String url="https://mangamachan.000webhostapp.com/buscar_pag_manga.php?idManga="+id+"&nroPag="+ou;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, (response) -> {
            JSONObject jsonObject = null;
            url_image="gaaa";
            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = response.getJSONObject(i);
                    url_image = jsonObject.getString("urlpagina");

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            Picasso.get()
                    .load(url_image)
                    .error(R.mipmap.ic_launcher_round)
                    .into(imagen);
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

}