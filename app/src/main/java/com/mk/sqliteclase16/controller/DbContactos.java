package com.mk.sqliteclase16.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mk.sqliteclase16.model.Contacto;
import com.mk.sqliteclase16.util.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class DbContactos extends DbHelper {
    Context context;
    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /*public long insertarContacto(String nombre, String telefono, String correo_electronico){
        long id = 0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("telefono",telefono);
            values.put("correo_electronico",correo_electronico);
            id = db.insert(TABLE_CONTACTOS,null, values);
        } catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        return id;
    }*/

    public long insertarContacto(Contacto contacto){
        long id = 0;
        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", contacto.getNombre());
            values.put("telefono", contacto.getTelefono());
            values.put("correo_electronico", contacto.getCorreoElectronico());
            id = db.insert(TABLE_CONTACTOS,null, values);
        } catch (Exception ex){
            System.out.println("Error: " + ex);
        }
        return id;
    }

    public List<Contacto> obtenerContactos() {
        List<Contacto> listaContactos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTOS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Contacto contacto = new Contacto();
                int idIndex = cursor.getColumnIndex("id");
                int nombreIndex = cursor.getColumnIndex("nombre");
                int telefonoIndex = cursor.getColumnIndex("telefono");
                int correoIndex = cursor.getColumnIndex("correo_electronico");
                if (idIndex != -1) {
                    contacto.setId(cursor.getLong(idIndex));
                    contacto.setNombre(cursor.getString(nombreIndex));
                    contacto.setTelefono(cursor.getString(telefonoIndex));
                    contacto.setCorreoElectronico(cursor.getString(correoIndex));
                }
                listaContactos.add(contacto);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaContactos;
    }
}
