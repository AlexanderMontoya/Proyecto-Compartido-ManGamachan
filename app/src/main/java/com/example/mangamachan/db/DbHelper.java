package com.example.mangamachan.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "mangamachan.db";
    public static final String TABLE_FAVORITOS = "t_favoritos";

    public DbHelper(@Nullable Context context){
        super(context, DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_FAVORITOS + "("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "id_manga TEXT NOT NULL,"+
            "nombre_manga TEXT NOT NULL,"+
                "imagen_manga TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_FAVORITOS);
        onCreate(sqLiteDatabase);
    }
}
