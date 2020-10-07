package com.example.momento1appmovil2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDatos extends SQLiteOpenHelper {
    String articulos = "CREATE TABLE ARTICULOS (IDARTICULO INTEGER PRIMARY KEY AUTOINCREMENT, TIPOARTICULO TEXT, DESCRIPCION TEXT, VALOR TEXT)";
    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(articulos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE ARTICULOS");
        db.execSQL(articulos);
    }
}
