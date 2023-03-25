package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mangamachan.controladores.FavoritoController;
import com.example.mangamachan.db.DbFavoritos;

import java.util.ArrayList;

public class FavoritosList extends AppCompatActivity {
    RecyclerView listaFavoritos;
    ArrayList<FavoritoController> elements;
    Button btnRegresarListMangas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos_list);
    // Favoritos
        listaFavoritos=findViewById(R.id.listaFavoritos);
        btnRegresarListMangas= findViewById(R.id.btnRegresarListMangas);
        listaFavoritos.setLayoutManager(new LinearLayoutManager(this));
        DbFavoritos dbFavoritos=new DbFavoritos(FavoritosList.this);
        elements=new ArrayList<>();
        FavoritosAdapter adapter = new FavoritosAdapter(dbFavoritos.mostrarFavoritos());
        listaFavoritos.setAdapter(adapter);
        btnRegresarListMangas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}