package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.SearchView;
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

public class MangasList extends AppCompatActivity implements SearchView.OnQueryTextListener{
    SearchView txtBuscar;
    RecyclerView recyclerView;
    List<ListElement> elements;
    RequestQueue requestQueue;
    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangas_list);
        txtBuscar=findViewById(R.id.txtBuscar);
        init();
        txtBuscar.setOnQueryTextListener(this);
    }

    public void init(){
        elements =new ArrayList<>();
            String url = "https://mangamachan.000webhostapp.com/buscar_manga.php";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, (response) -> {
            JSONObject jsonObject = null;
            for (int j = 0; j < response.length(); j++) {
                try {
                    jsonObject = response.getJSONObject(j);
                    String imagen;
                    String titulo;
                    String id;
                    imagen=jsonObject.getString("url_image");
                    titulo=jsonObject.getString("titulo");
                    id=jsonObject.getString("idmanga");
                    elements.add(new ListElement(imagen,titulo,id));
                    listAdapter=new ListAdapter(elements, this);
                    recyclerView=findViewById(R.id.listRecyclerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(listAdapter);

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
        listAdapter.filtrado(s);
        return false;
    }
}