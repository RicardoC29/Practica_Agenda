package com.example.practica4_agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import OpenHelper.baseSQLite;

public class ListDatesActivity extends AppCompatActivity {

    ListView datos;
    ArrayList<String> mostrarL;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dates);
        datos = (ListView) findViewById(R.id.lista);
        cargarListado();
    }

    private void cargarListado(){
        mostrarL=ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mostrarL);
        datos.setAdapter(adapter);
    }

    private ArrayList<String> ListaPersonas(){
        ArrayList<String> datos = new ArrayList<String>();
        baseSQLite helper=new baseSQLite(ListDatesActivity.this, "datos", null, 1);
        SQLiteDatabase db= helper.getReadableDatabase();
        String sql = "SELECT * FROM datos ";
        Cursor c = db.rawQuery(sql, null);
        if(c.moveToFirst()){

            do{
                String linea = c.getInt(0)+" "+ c.getString(1)+" "+ c.getString(2)
                        +" "+ c.getString(3)+" "+ c.getString(4)+" "+ c.getString(5);
                datos.add(linea);

            }while (c.moveToNext());

        }
        db.close();
        return datos;
    }


}