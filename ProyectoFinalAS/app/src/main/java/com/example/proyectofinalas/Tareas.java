package com.example.proyectofinalas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalas.modelo.BaseDatos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Tareas extends AppCompatActivity {
    ImageView regresar;
    TextView tvNombre;
    TextView tvFecha;
    TextView tvDesc;
    BaseDatos bd;
    String id;
    FloatingActionButton btnEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);

        tvNombre = (TextView) findViewById(R.id.tvTitulo);
        tvFecha = (TextView) findViewById(R.id.tvFechaTarea);
        tvDesc = (TextView) findViewById(R.id.tvDescripcionTarea);
        btnEditar = (FloatingActionButton)findViewById(R.id.btnEditarTarea);
        bd = new BaseDatos(this);

        id = getIntent().getExtras().getString("ID TAREA");
        Toast.makeText(this, "ID:" + id, Toast.LENGTH_LONG).show();

        muestraDatos();

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tareas.this, EditarTarea.class);
                intent.putExtra("ID", id);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "LA TAREA SE HA ACTUALIZADO \n CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        regresar = (ImageView) findViewById(R.id.btnRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @SuppressLint("Range")
    public void muestraDatos() {
        Cursor datos = bd.getTareaByid(id);
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
            tvNombre.setText(name);
            tvFecha.setText(date);
            tvDesc.setText(desc);

        }

    }
}