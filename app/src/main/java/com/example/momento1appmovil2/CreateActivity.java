package com.example.momento1appmovil2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    FloatingActionButton fab_create_save,fab_create_back,fab_create_clear;
    EditText et_create_valor,et_create_articulo,et_create_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_create_save = findViewById(R.id.fab_create_save);
        fab_create_back = findViewById(R.id.fab_create_back);
        fab_create_clear = findViewById(R.id.fab_create_clear);
        et_create_valor = findViewById(R.id.et_create_valor);
        et_create_articulo = findViewById(R.id.et_create_articulo);
        et_create_description = findViewById(R.id.et_create_description);

        fab_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_create_valor.setText("");
                et_create_articulo.setText("");
                et_create_description.setText("");
            }
        });

        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(et_create_valor.getText().toString(),et_create_articulo.getText().toString(),et_create_description.getText().toString());
                et_create_valor.setText("");
                et_create_articulo.setText("");
                et_create_description.setText("");
            }
        });

    }

    private void guardar(String TipoArticulo, String Valor, String Descripcion) {
        BaseDatos odb = new BaseDatos(this, "BDARTICULOS", null, 1);
        SQLiteDatabase db = odb.getWritableDatabase();
        try {
            ContentValues v = new ContentValues();
            v.put("TipoArticulo", TipoArticulo);
            v.put("Valor", Valor);
            v.put("Descripcion", Descripcion);
            db.insert("ARTICULOS", null, v);
            db.close();
            Toast.makeText(this,"Articulo agregado correctamente...", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this,"Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
