package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class leerSipnosis extends AppCompatActivity {
    ImageView imgSipnosis;
    TextView NombreManga,txtSipnosis;
    Button btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_sipnosis);
        String titulo=getIntent().getStringExtra("titulo");
        String imagen=getIntent().getStringExtra("imagen");
        String sipnosis=getIntent().getStringExtra("sipnosis");
        imgSipnosis=findViewById(R.id.imagenMangaSipnosis);
        NombreManga=findViewById(R.id.lblNombreMangaSipnosis);
        btnRegresar=findViewById(R.id.btnRegresarCapitulosManga);
        txtSipnosis=findViewById(R.id.txtSipnosis);
        NombreManga.setText(titulo);
        txtSipnosis.setText(sipnosis);
        Picasso.get()
                .load(imagen)
                .error(R.mipmap.ic_launcher_round)
                .into(imgSipnosis);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}