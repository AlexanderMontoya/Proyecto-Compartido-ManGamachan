package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CapitulosList extends AppCompatActivity{
    TextView lblNombreManga;
    ImageView imgManga;
    Button btnRegresarMangas;
    Button btnSipnosis,btnFavorito;
    List<CapituloController> elements;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    CapitulosAdapter capitulosAdapter;
    String url_img_manga;
    String title_manga;
    String synopsis_manga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitulos_list);
        String aea=getIntent().getStringExtra("ID");
        mostrarContenido(aea);
        imgManga=findViewById(R.id.imagenManga);
        lblNombreManga=findViewById(R.id.lblNombreManga);
        btnRegresarMangas=findViewById(R.id.btnRegresarMangas);
        btnSipnosis=findViewById(R.id.btnSipnosis);
        btnFavorito=findViewById(R.id.btnFavorito);
        init(aea);
        btnRegresarMangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Opcion aun no disponible :v", Toast.LENGTH_SHORT).show();
            }
        });
        btnSipnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(CapitulosList.this,leerSipnosis.class);
                a.putExtra("titulo",title_manga);
                a.putExtra("imagen",url_img_manga);
                a.putExtra("sipnosis",synopsis_manga);
                startActivity(a);
            }
        });
    }

    public void mostrarContenido(String idManga){
        String url = "https://mangamachan.000webhostapp.com/api/?id_manga="+idManga;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, (response) -> {
            JSONObject jsonObject = null;
            for (int j = 0; j < response.length(); j++) {
                try {
                    jsonObject = response.getJSONObject(j);
                    String id_manga;
                    id_manga=jsonObject.getString("id_manga");
                    url_img_manga=jsonObject.getString("url_img_manga");
                    title_manga=jsonObject.getString("title_manga");
                    synopsis_manga=jsonObject.getString("synopsis_manga");
                    Picasso.get()
                            .load(url_img_manga)
                            .error(R.mipmap.ic_launcher_round)
                            .into(imgManga);
                    lblNombreManga.setText(title_manga);
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

    public void init(String aea){
        elements =new ArrayList<>();
        String url = "https://mangamachan.000webhostapp.com/api/capitulos/?id_manga="+aea;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, (response) -> {
            JSONObject jsonObject = null;
            for (int j = 0; j < response.length(); j++) {
                try {
                    jsonObject = response.getJSONObject(j);
                    String chapter_pages;
                    String manga_chapter;
                    String id_manga_chapter;
                    chapter_pages=jsonObject.getString("chapter_pages");
                    manga_chapter=jsonObject.getString("manga_chapter");
                    id_manga_chapter=jsonObject.getString("id_manga_chapter");
                    String id_manga=jsonObject.getString("id_manga");
                    String url_page=jsonObject.getString("url_page");
                    elements.add(new CapituloController(chapter_pages,manga_chapter,id_manga_chapter,id_manga,url_page));

                    capitulosAdapter=new CapitulosAdapter(elements, this);
                    recyclerView=findViewById(R.id.listRecyclerViewCapitulos);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(capitulosAdapter);

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

}