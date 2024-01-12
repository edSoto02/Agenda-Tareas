package com.example.proyectofinalas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectofinalas.modelo.BaseDatos;
import com.example.proyectofinalas.controlador.Tarea;

import java.util.ArrayList;

public class Nueva_Tarea extends AppCompatActivity {

    ImageView regresar;
    ArrayList<String> tarea, fecha, desc;
    Button guardar;
    EditText nombreTarea, dia, mes, anio, descripcion;
    Tarea objtarea;

    BaseDatos baseDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        baseDatos = new BaseDatos(this);

        tarea = new ArrayList<String>();
        fecha = new ArrayList<String>();
        desc = new ArrayList<String>();

        nombreTarea = (EditText)findViewById(R.id.edtEditarTarea);
        dia = (EditText)findViewById(R.id.edtEditarDia);
        mes = (EditText)findViewById(R.id.edtEditarMes);
        anio = (EditText)findViewById(R.id.edtEditarAnio);
        descripcion = (EditText)findViewById(R.id.edtEditarDescripcion);
        guardar = (Button)findViewById(R.id.btnGuardarEditado);


        regresar = (ImageView) findViewById(R.id.btnRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
                //enviaDatos();

            }
        });
    }

    public void guardarDatos(){
        String tarea1, fecha1, desc1;

        tarea1 = nombreTarea.getText().toString();
        fecha1 = dia.getText().toString() + "/" + mes.getText().toString() + "/" + anio.getText().toString();
        desc1 = descripcion.getText().toString();

        /*tarea.add(tarea1);
        fecha.add(fecha1);
        desc.add(desc1);*/
        //objtarea = new Tarea(tarea1,fecha1,desc1);


        baseDatos.guardaDatos(tarea1,fecha1,desc1);
        //Toast.makeText(getApplicationContext(),"Tarea Guardada", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "LA TAREA SE HA GUARADO \n CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        finish();


    }

    /*public void enviaDatos(){
        Intent intent = new Intent(this,TodasMisTareas.class);
        intent.putExtra("tareas_arreglo",tarea);
        startActivity(intent);
    }*/



    /*public void mostrar(){
        Toast.makeText(this, tarea.get(0), Toast.LENGTH_LONG).show();
        Toast.makeText(this, fecha.get(0), Toast.LENGTH_LONG).show();
        Toast.makeText(this, desc.get(0), Toast.LENGTH_LONG).show();
    }*/
}