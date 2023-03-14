package com.example.mangamachan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.mangamachan.controladores.FavoritoController;

import java.util.ArrayList;

public class DbFavoritos extends DbHelper{
    Context context;

    public DbFavoritos(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertarFavorito(String idManga,String nombre_manga, String imagen_manga){
        long id=0;
        try{
            DbHelper dbHelper=new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values= new ContentValues();
            values.put("id_manga", idManga);
            values.put("nombre_manga", nombre_manga);
            values.put("imagen_manga", imagen_manga);

            id=db.insert(TABLE_FAVORITOS, null,values);
        }catch(Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<FavoritoController> mostrarFavoritos(){
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<FavoritoController> listaFavoritos= new ArrayList<>();
        FavoritoController favorito= null;
        Cursor cursorFavoritos= null;

        cursorFavoritos=db.rawQuery("SELECT * FROM "+ TABLE_FAVORITOS,null);
        if(cursorFavoritos.moveToFirst()){
            do{
                favorito=new FavoritoController(cursorFavoritos.getInt(0),cursorFavoritos.getString(1),cursorFavoritos.getString(2),cursorFavoritos.getString(3));
                listaFavoritos.add(favorito);
            }while(cursorFavoritos.moveToNext());
        }
        cursorFavoritos.close();
        return listaFavoritos;
    }

    public boolean consultarFavorito(String idManga){
        boolean i=false;
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor result= null;

        result=db.rawQuery("SELECT * FROM "+ TABLE_FAVORITOS + " WHERE id_manga='"+idManga+"'",null);
        if(result.moveToFirst()){
            i=true;
        }
        result.close();
        return i;
    }

    public boolean eliminarFavorito(String idManga){
        boolean i=false;
        DbHelper dbHelper=new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor result= null;

        result=db.rawQuery("DELETE FROM "+ TABLE_FAVORITOS + " WHERE id_manga='"+idManga+"'" ,null);
        if(result.moveToFirst()){
            i=true;
        }
        result.close();
        return i;
    }
}
