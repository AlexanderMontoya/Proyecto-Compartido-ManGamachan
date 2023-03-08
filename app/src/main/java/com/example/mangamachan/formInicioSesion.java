package com.example.mangamachan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class formInicioSesion extends AppCompatActivity {
    Button registrarse, iniciarS;
    EditText txtNN,txtC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_inicio_sesion);
        iniciarS = findViewById(R.id.btnRegistrarme);
        registrarse = findViewById(R.id.btnIniciarSesion);
        txtNN = findViewById(R.id.txtNN);
        txtC = findViewById(R.id.txtCon);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(formInicioSesion.this, formRegistro.class);
                startActivity(a);
            }
        });

        iniciarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario("https://mangamachan.000webhostapp.com/verificarUsuario.php");
            }
        });
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Intent intent = new Intent (getApplicationContext(), MangasList.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(formInicioSesion.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(formInicioSesion.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("usuario",txtNN.getText().toString());
                parametros.put("contra",txtC.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(formInicioSesion.this);
        requestQueue.add(stringRequest);
    }
}