package com.mk.sqliteclase16.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mk.sqliteclase16.R;
import com.mk.sqliteclase16.controller.DbContactos;
import com.mk.sqliteclase16.model.Contacto;
import com.mk.sqliteclase16.ui.adapter.ContactoAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnNuevo;
    RecyclerView listaContactos;
    ContactoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, NuevoActivity.class);
            startActivity(i);
        });

        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        DbContactos dbContactos = new DbContactos(this);
        List<Contacto> contactos = dbContactos.obtenerContactos();
        dbContactos.close();
        adapter = new ContactoAdapter(contactos);
        listaContactos.setAdapter(adapter);
    }
}