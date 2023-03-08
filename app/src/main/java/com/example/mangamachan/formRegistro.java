package com.example.mangamachan;

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

public class formRegistro extends AppCompatActivity {
    EditText txtNickName, txtCorreo, txtContra;
    Button registrarse, iniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_registro);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#288643")));
        registrarse = findViewById(R.id.btnRegistrarme);
        iniciarSesion = findViewById(R.id.btnIniciarSesion);
        txtNickName = findViewById(R.id.txtNN);
        txtCorreo = findViewById(R.id.txtC);
        txtContra = findViewById(R.id.txtCon);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(formRegistro.this, formInicioSesion.class);
                startActivity(a);
            }
        });
    }

    public void validar(View v){

        final String nickname=txtNickName.getText().toString();
        final String correo=txtCorreo.getText().toString();
        final String contra=txtContra.getText().toString();

        if (nickname.equals("") || correo.equals("") || contra.equals("")){
            Toast.makeText(getApplicationContext(),"Ingresa todos los datos", Toast.LENGTH_SHORT).show();
        }else{
            String url="https://mangamachan.000webhostapp.com/registroUsuarios.php?usuario="+nickname+"&correo="+correo+"&contra="+contra;
            RequestQueue servicio= Volley.newRequestQueue(this);
            StringRequest respuesta=new StringRequest(
                    Request.Method.GET, url, response -> Toast.makeText(getApplicationContext(),
                    "¡REGISTRADO CON EXITO!",Toast.LENGTH_LONG).show(), new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),
                            "Error comunicación",Toast.LENGTH_SHORT).show();
                }
            });
            servicio.add(respuesta);
        }

    }
/*
    private void insertarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "¡REGISTRADO CON EXITO!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR AL INGRESAR REGISTRO", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", txtNickName.getText().toString());
                parametros.put("correo", txtCorreo.getText().toString());
                parametros.put("contra", txtContra.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(formRegistro.this);
        requestQueue.add(stringRequest);
    }*/
}