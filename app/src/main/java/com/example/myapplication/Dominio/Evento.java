package com.example.myapplication.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.AdminSQLiteOpenHelper;
import com.example.myapplication.Config.Config;
import com.example.myapplication.Routes.Routes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Evento {
    private int Id;
    private String Codigo;
    private String Nombre;
    private String FechaInicio;
    private String FechaFin;
    private String Descripcion;
    private String RutaFoto;
    private ArrayList<Sitio> Sitios;

    public ArrayList<Sitio> getSitios() {
        return Sitios;
    }

    public void setSitios(ArrayList<Sitio> sitios) {
        Sitios = sitios;
    }

    public Evento() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getRutaFoto() {
        return RutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        RutaFoto = rutaFoto;
    }

    public void Save(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, Config.database_name, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("id_evento", this.Id);
        registro.put("codigo", this.Codigo);
        registro.put("nombre", this.Nombre);
        registro.put("fecha_inicio", this.FechaInicio);
        registro.put("fecha_fin", this.FechaFin);
        registro.put("descripcion", this.Descripcion);
        registro.put("ruta_foto", this.RutaFoto);

        db.insert("Evento", null, registro);
        db.close();
    }

    public Evento Find(Context context, int id){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, Config.database_name, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String sql = "select * from Evento where id_evento="+id;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            this.Id = Integer.parseInt(cursor.getString(0));
            this.Codigo = cursor.getString(1);
            this.Nombre = cursor.getString(2);
            this.FechaInicio = cursor.getString(3);
            this.FechaFin = cursor.getString(4);
            this.Descripcion = cursor.getString(5);
            this.RutaFoto = cursor.getString(6);
            return this;
        }
        db.close();
        return null;
    }

    public static List<Evento> FindAll(Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, Config.database_name, null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        List<Evento> lista = new ArrayList<>();

        String sql = "select * from Evento";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do{
                Evento evento = new Evento();
                evento.Id = Integer.parseInt(cursor.getString(0));
                evento.Codigo = cursor.getString(1);
                evento.Nombre = cursor.getString(2);
                evento.FechaInicio = cursor.getString(3);
                evento.FechaFin = cursor.getString(4);
                evento.Descripcion = cursor.getString(5);
                evento.RutaFoto = cursor.getString(6);
                lista.add(evento);
            }while(cursor.moveToNext());
            return lista;
        }
        db.close();
        return null;
    }


}
