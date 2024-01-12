package com.example.proyectofinalas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnNuevaTarea, btnTodasLasTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNuevaTarea = (Button) findViewById(R.id.btnNuevaTarea);
        btnTodasLasTareas = (Button) findViewById(R.id.btnTodasLasTareas);

        btnNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Nueva_Tarea.class);
                startActivity(intent);

                //accion que se realiza al dar clic
                //Toast.makeText(getApplicationContext(),"Nueva Tarea", Toast.LENGTH_LONG).show();
            }
        });

        btnTodasLasTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TodasMisTareas.class);
                startActivity(intent);
                //accion que se realiza al dar clic
                //Toast.makeText(getApplicationContext(),"Todas las Tareas", Toast.LENGTH_LONG).show();
            }
        });
    }
}