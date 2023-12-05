package com.mk.sqliteclase16.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mk.sqliteclase16.R;
import com.mk.sqliteclase16.model.Contacto;

import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>{
    private final List<Contacto> listaContactos;

    public ContactoAdapter(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item, parent, false);
        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        Contacto contacto = listaContactos.get(position);
        holder.nombre.setText(contacto.getNombre());
        holder.telefono.setText(contacto.getTelefono());
        holder.correo.setText(contacto.getCorreoElectronico());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView telefono;
        TextView correo;

        public ContactoViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.viewNombre);
            telefono = itemView.findViewById(R.id.viewTelefono);
            correo = itemView.findViewById(R.id.viewCorreo);
        }
    }
}
