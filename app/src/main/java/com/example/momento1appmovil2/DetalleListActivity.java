package com.example.momento1appmovil2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DetalleListActivity extends AppCompatActivity {
    ListView lv_list_detalle;
    ArrayList<String> List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_list);

        lv_list_detalle = findViewById(R.id.lv_list_detalle);
        cargarList();
    }

    private void cargarList() {
        List = Listar();
        ArrayAdapter<String> adplist = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,List);
        lv_list_detalle.setAdapter(adplist);
    }

    private ArrayList<String> Listar() {
        ArrayList<String> datos = new ArrayList<String>();
        BaseDatos helper = new BaseDatos(this,"BDARTICULOS",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "Select IdArticulo,TipoArticulo,Descripcion,Valor From articulos";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){
            do{
                String linea = "Articulo:  "+c.getString(3)+"\n"+"\n"+"Descripción:  "+c.getString(2)+"\n"+"\n"+"Valor:  "+c.getString(1);
                datos.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }
}
