package com.example.proyectofinalas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.proyectofinalas.modelo.BaseDatos;
import com.example.proyectofinalas.controlador.Tarea;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TodasMisTareas extends AppCompatActivity {
    ListView listTareas;
    ArrayAdapter<String> adapter;
    ArrayList<String> nombreTareas;
    ImageView regresar;
    FloatingActionButton btnFloat;
    BaseDatos baseDatos;
    ArrayList<Tarea>tareasBD;
    Map<String, Integer> mapaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas_mis_tareas);
        regresar = (ImageView)findViewById(R.id.btnRegresar);
        btnFloat=(FloatingActionButton)findViewById(R.id.btnEditarTarea);
        baseDatos = new BaseDatos(this);
        tareasBD = new ArrayList<Tarea>();
        tareasBD = obtenDatos();
        nombreTareas = new ArrayList<String>();
        //nombreTareas = (ArrayList<String>)getIntent().getSerializableExtra("tareas_areglo");
        mapaTareas = new HashMap<String, Integer>();

        //obtenDatos();
        llenarArreglo();
        llenaMapa();

        listTareas = (ListView) findViewById(R.id.listTareas);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombreTareas);

        listTareas.setAdapter(adapter);
        listTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = null;
                String nombre = adapter.getItem(i);
                //Toast.makeText(getApplicationContext(), "ID:" + mapaTareas.get(nombre), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TodasMisTareas.this, Tareas.class);
                intent.putExtra("ID TAREA", mapaTareas.get(nombre).toString());
                startActivity(intent);
                eliminaTarea(id);
            }
        });


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Nueva_Tarea.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("Range")
    public ArrayList<Tarea> obtenDatos(){
        Cursor datos = baseDatos.getTareas();
        ArrayList<Tarea> tareas = new ArrayList<Tarea>();
        int id;
        String name,date,desc;

        while(datos.moveToNext()){
            id = datos.getInt(datos.getColumnIndex("id_tarea"));
            name = datos.getString(datos.getColumnIndex("nombre"));
            date = datos.getString(datos.getColumnIndex("fecha"));
            desc = datos.getString(datos.getColumnIndex("descripcion"));
            Tarea tarea = new Tarea(id,name,date,desc);//
            tareas.add(tarea);

        }
        return tareas;
    }

    public void llenarArreglo(){
        //ArrayList<Tarea> tarea = obtenDatos();
        for (int i =0; i< tareasBD.size();i++){
            nombreTareas.add(tareasBD.get(i).getNombre());
        }
    }

    public void llenaMapa(){
        String llave;
        int valor;
        for(int i=0;i<tareasBD.size();i++){
            llave = tareasBD.get(i).getNombre();
            valor = tareasBD.get(i).getIdtarea();
            mapaTareas.put(llave,valor);
        }
    }

    public void eliminaTarea(String id) {
        baseDatos.eliminaTarea(id);
        // Actualiza la lista de tareas y la interfaz de usuario
        tareasBD = obtenDatos();
        nombreTareas.clear();
        llenarArreglo();
        adapter.notifyDataSetChanged();
    }



}