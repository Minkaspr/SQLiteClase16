package com.mk.sqliteclase16.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mk.sqliteclase16.R;
import com.mk.sqliteclase16.controller.DbContactos;
import com.mk.sqliteclase16.model.Contacto;

public class NuevoActivity extends AppCompatActivity {
    EditText etNombre, etTelefono, etCorreoElectronico;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreoElectronico = findViewById(R.id.etCorreoElectronico);
        btnGuardar = findViewById(R.id.btnGuardar);

        /*btnGuardar.setOnClickListener(v->{
            if (
                    !etNombre.getText().toString().isEmpty() &&
                            !etTelefono.getText().toString().isEmpty() &&
                            !etCorreoElectronico.getText().toString().isEmpty()
            ){
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertarContacto(etNombre.getText().toString(),
                        etTelefono.getText().toString(),etCorreoElectronico.getText().toString());
                if (id>0){
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            }
        });*/
        btnGuardar.setOnClickListener(v->{
            if (
                    !etNombre.getText().toString().isEmpty() &&
                            !etTelefono.getText().toString().isEmpty() &&
                            !etCorreoElectronico.getText().toString().isEmpty()
            ){
                try (DbContactos dbContactos = new DbContactos(NuevoActivity.this)) {
                    Contacto contacto = new Contacto();
                    contacto.setNombre(etNombre.getText().toString());
                    contacto.setTelefono(etTelefono.getText().toString());
                    contacto.setCorreoElectronico(etCorreoElectronico.getText().toString());

                    long id = dbContactos.insertarContacto(contacto);
                    if (id>0){
                        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                        limpiar();
                        finish();
                    } else {
                        Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiar(){
        etNombre.setText("");
        etTelefono.setText("");
        etCorreoElectronico.setText("");
    }
}