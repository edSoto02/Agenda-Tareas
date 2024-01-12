package com.example.proyectofinalas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.proyectofinalas.modelo.BaseDatos;

public class EditarTarea extends AppCompatActivity {

    ImageView regresar;

    EditText nombre;
    EditText dia;
    EditText mes;
    EditText anio;
    EditText descripcion;

    Button btnEditar;

    BaseDatos db;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea);

        db = new BaseDatos(this);

        nombre = (EditText) findViewById(R.id.edtEditarTarea);
        dia = (EditText) findViewById(R.id.edtEditarDia);
        mes = (EditText) findViewById(R.id.edtEditarMes);
        anio = (EditText) findViewById(R.id.edtEditarAnio);
        descripcion = (EditText) findViewById(R.id.edtEditarDescripcion);
        btnEditar = (Button)findViewById(R.id.btnGuardarEditado);
        regresar =(ImageView)findViewById(R.id.btnRegresar);

        id = getIntent().getExtras().getString("ID");
        //Toast.makeText(this,"ID"+ id,Toast.LENGTH_LONG).show();
        obtenDatos();

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fecha = dia.getText().toString() + "/" + mes.getText().toString() + "/" + anio.getText().toString();
                db.actualizaTarea(id,nombre.getText().toString(), fecha, descripcion.getText().toString());
                finish();


            }


        });


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @SuppressLint("Range")
    public void obtenDatos() {
        Cursor datos = db.getTareaByid(id);
        //ArrayList<Tarea> tareas = new ArrayList<Tarea>();
        int id;
        String name, date, desc;

        while (datos.moveToNext()) {
            //id = datos.getInt(datos.getColumnIndex("idtarea"));
            name = datos.getString(datos.getColumnIndex("nombre"));
            date = datos.getString(datos.getColumnIndex("fecha"));
            desc = datos.getString(datos.getColumnIndex("descripcion"));
            //Tarea tarea = new Tarea(id,name,date,desc);//
            //tareas.add(tarea);
            nombre.setText(name);
            dia.setText(date.substring(0,2));
            mes.setText(date.substring(3,6));
            anio.setText(date.substring(7));
            //tvFecha.setText(date);
            descripcion.setText(desc);

        }

    }
}