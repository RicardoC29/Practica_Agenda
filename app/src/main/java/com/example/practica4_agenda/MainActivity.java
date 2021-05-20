package com.example.practica4_agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import OpenHelper.baseSQLite;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Spinner
            categoria;

    EditText
            etFecha,
            etHora,
            txtName,
            txtLast,
            txtPhone;

    ImageButton
            ibObtenerFecha,
            ibObtenerHora;

    Button
            btnGuardar,
            btnShow;

    private int
            dia,
            mes,
            ano,
            hora,
            minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoria = (Spinner)findViewById(R.id.sp_category);
        ArrayList<String> comboDatosList=new ArrayList<String>();
        comboDatosList.add("Actividad Física");
        comboDatosList.add("Trabajo");
        comboDatosList.add("Compras");
        comboDatosList.add("Recreativo");
        comboDatosList.add("Otros");
        ArrayAdapter<CharSequence> adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, comboDatosList);
        categoria.setAdapter(adapter);
        etFecha = (EditText) findViewById(R.id.et_mostrar_fecha_picker);
        etHora = (EditText) findViewById(R.id.et_mostrar_hora_picker);
        ibObtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);
        ibObtenerHora = (ImageButton) findViewById(R.id.ib_obtener_hora);
        txtName = findViewById(R.id.txtName);
        txtLast = findViewById(R.id.txtLate);
        txtPhone = findViewById(R.id.txtPhone);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnShow= findViewById(R.id.btnShow);
        ibObtenerFecha.setOnClickListener(this);
        ibObtenerHora.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v==ibObtenerFecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }
        if (v==ibObtenerHora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etHora.setText(hourOfDay+":"+minute);
                }
            }
                    ,hora,minutos, false);
            timePickerDialog.show();
        }
        if(v==btnShow){
            Intent i = new Intent(MainActivity.this, ListDatesActivity.class);
            startActivity(i);
        }
        if(v==btnGuardar){
            String nom, ape, phone, fech, hour;
            nom=txtName.getText().toString();
            ape=txtLast.getText().toString();
            phone=txtPhone.getText().toString();
            fech=etFecha.getText().toString();
            hour=etHora.getText().toString();

            baseSQLite bdUsuarios =new baseSQLite(MainActivity.this, "datos", null,1);
            if(bdUsuarios!=null) {
                SQLiteDatabase db = bdUsuarios.getWritableDatabase();
                ContentValues con = new ContentValues();
                con.put("nombre", nom);
                con.put("apellido", ape);
                con.put("telefono", phone);
                con.put("fecha", fech);
                con.put("hora", hour);
                long guarda = db.insert("datos", null, con);
                if (guarda > 0) {
                    Toast.makeText(MainActivity.this, "DATOS GUARDADOS", Toast.LENGTH_LONG).show();
                    txtName.setText("");
                    txtLast.setText("");
                    txtPhone.setText("");
                    etFecha.setText("");
                    etHora.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "ERROR AL GUARDAR", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}