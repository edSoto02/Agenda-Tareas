package com.example.proyectofinalas.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    public static final int base1 =1;
    public static final String dbname = "tareas.db";

    public BaseDatos(Context context){
        super(context,dbname,null,base1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Tarea (" + "id_tarea INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre VARCHAR NOT NULL, " +
                "fecha VARCHAR NOT NULL, " +
                "descripcion VARCHAR NOT NULL); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void guardaDatos(String tarea1, String fecha1, String desc1){
        getReadableDatabase().execSQL("INSERT INTO  Tarea VALUES("+null+", '"+ tarea1+"', '"+fecha1+"', '"+desc1+"'); ");
    }

    public Cursor getTareas(){
        return getReadableDatabase().query("Tarea", null,null,null,null,null, null);
    }

    public Cursor getTareaByid(String id){
        return getReadableDatabase().rawQuery("SELECT * " +
                                                    "FROM Tarea " +
                                                        "WHERE id_tarea = " + id + ";",null);
    }

    public void actualizaTarea(String id, String nombre, String fecha, String descripcion){
        getReadableDatabase().execSQL("UPDATE Tarea SET nombre = '"+nombre+ "', fecha =' "+fecha + "', descripcion ='"+descripcion+"' WHERE id_tarea = "+ id +";");
    }

    public void eliminaTarea(String id) {
        getWritableDatabase().execSQL("DELETE FROM Tarea WHERE id_tarea = " + id + ";");
    }
}
