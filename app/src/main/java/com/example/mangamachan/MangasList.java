package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mangamachan.controladores.MangasController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MangasList extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView txtBuscar;
    RecyclerView recyclerView;
    List<MangasController> elements;
    Button btnVerFavoritos;
    RequestQueue requestQueue;
    MangasAdapter mangasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangas_list);
        txtBuscar=findViewById(R.id.txtBuscar);
        btnVerFavoritos=findViewById(R.id.btnVerFavoritos);
        init();
        txtBuscar.setOnQueryTextListener(this);
        btnVerFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(MangasList.this,FavoritosList.class);
                startActivity(a);
            }
        });
    }

    public void init(){
        elements =new ArrayList<>();
            String url = "https://mangamachan.000webhostapp.com/api/";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, (response) -> {
            JSONObject jsonObject = null;
            for (int j = 0; j < response.length(); j++) {
                try {
                    jsonObject = response.getJSONObject(j);
                    String url_img_manga;
                    String title_manga;
                    String id_manga;
                    url_img_manga=jsonObject.getString("url_img_manga");
                    title_manga=jsonObject.getString("title_manga");
                    id_manga=jsonObject.getString("id_manga");
                    elements.add(new MangasController(url_img_manga,title_manga,id_manga));
                    mangasAdapter =new MangasAdapter(elements, this);
                    recyclerView=findViewById(R.id.listRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(mangasAdapter);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonArrayRequest);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mangasAdapter.filtrado(s);
        return false;
    }
}