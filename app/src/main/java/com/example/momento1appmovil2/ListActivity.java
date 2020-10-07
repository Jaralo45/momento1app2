package com.example.momento1appmovil2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    FloatingActionButton fab_list_create;
    TextView tv_list_item_articulo, tv_list_item_valor;
    ListView lv_list;
    ArrayList<String> List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_list_create = findViewById(R.id.fab_list_create);
        tv_list_item_articulo = findViewById(R.id.tv_list_item_articulo);
        tv_list_item_valor = findViewById(R.id.tv_list_item_valor);
        lv_list = findViewById(R.id.lv_list);
        cargarList();

        fab_list_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(intent);
            }
        });

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetalleListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarList() {
        List = Listar();
        ArrayAdapter<String> adplist = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,List);
        lv_list.setAdapter(adplist);
    }

    private ArrayList<String> Listar() {
        ArrayList<String> datos = new ArrayList<String>();
        BaseDatos helper = new BaseDatos(this,"BDARTICULOS",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "Select IdArticulo,TipoArticulo,Descripcion,Valor From articulos";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()){
            do{
                String linea = "Articulo:  "+c.getString(3)+"\n"+"Valor:  "+c.getString(1);
                datos.add(linea);
            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }

}
