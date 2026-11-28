package com.example.examen_parcial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MuebleAdapter(
    private val lista: List<Mueble>,
    private val onItemClick: (Mueble) -> Unit
) : RecyclerView.Adapter<MuebleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMueble: ImageView = view.findViewById(R.id.imgMueble)
        val txtNombre: TextView = view.findViewById(R.id.txtNombreMueble)
        val txtDescripcion: TextView = view.findViewById(R.id.txtDescripcionMueble)
        val txtPrecio: TextView = view.findViewById(R.id.txtPrecioMueble)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mueble, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mueble = lista[position]
        holder.imgMueble.setImageResource(mueble.imagen)
        holder.txtNombre.text = mueble.nombre
        holder.txtDescripcion.text = mueble.descripcion
        holder.txtPrecio.text = mueble.precio

        holder.itemView.setOnClickListener {
            onItemClick(mueble)
        }
    }

    override fun getItemCount(): Int = lista.size
}