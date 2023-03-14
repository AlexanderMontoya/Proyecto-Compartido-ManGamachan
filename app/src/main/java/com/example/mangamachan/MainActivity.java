package com.example.mangamachan;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mangamachan.db.DbHelper;

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
        DbHelper dbHelper=new DbHelper(MainActivity.this);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
        }

        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, MangasList.class);
                startActivity(i);
                finish();
            }
        };

        Timer tiempo = new Timer();
        tiempo.schedule(tarea,5000);
    }
}