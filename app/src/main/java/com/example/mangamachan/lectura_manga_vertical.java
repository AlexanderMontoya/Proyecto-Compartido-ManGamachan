package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.mangamachan.controladores.PaginaController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class lectura_manga_vertical extends AppCompatActivity {
    List<PaginaController> elements;
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    PaginasAdapter paginasAdapter;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_manga_vertical);
        String aea=getIntent().getStringExtra("id_manga_chapter");
        init(aea);
        btnRegresar=findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void init(String aea){
        elements =new ArrayList<>();
        String url = "https://mangamachan.000webhostapp.com/api/paginas/?id_manga_chapter="+aea;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, (response) -> {
            JSONObject jsonObject = null;
            for (int j = 0; j < response.length(); j++) {
                try {
                    jsonObject = response.getJSONObject(j);
                    String url_page;
                    url_page=jsonObject.getString("url_page");
                    elements.add(new PaginaController(url_page));
                    paginasAdapter=new PaginasAdapter(elements, this);
                    recyclerView=findViewById(R.id.listRecyclerViewPages);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView.setAdapter(paginasAdapter);

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