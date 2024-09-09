package com.example.roommates.clases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roommates.R

class AlquileresAdapter (private val alquileresList: List<Alquiler>): RecyclerView.Adapter<AlquileresAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = MyViewHolder(layoutInflater.inflate(R.layout.alquiler_fila, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val alquiler = alquileresList[position]
        holder.direccion.setText(alquiler.direccion)
        holder.descripcion.setText(alquiler.descripcion)
        holder.disponibilidad.setText(alquiler.disponibilidad)

    }

    override fun getItemCount(): Int {
        return alquileresList.size
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val direccion = view.findViewById<TextView>(R.id.direccion)
        val descripcion = view.findViewById<TextView>(R.id.descripcion)
        val disponibilidad = view.findViewById<TextView>(R.id.disponibilidad)

    }

}