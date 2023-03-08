package com.example.mangamachan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    ImageView manga_1,manga_2,manga_3,manga_4,manga_5;
    Button btn_1,btn_2,btn_3,btn_4,btn_5;
    Button ir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, formInicioSesion.class);
                startActivity(i);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea,5000);
    }
}