package com.example.roommates.Model

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.roommates.R
import com.squareup.picasso.Picasso

class AlquilerAdapter (val activity: Activity, val list: List<Alquiler>): ArrayAdapter<Alquiler>(activity,R.layout.alquiler_fila){


    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val contexs = activity.layoutInflater
        val rowView=contexs.inflate(R.layout.alquiler_fila, null)

        val imagen = rowView.findViewById<ImageView>(R.id.imagen)
        val direccion = rowView.findViewById<TextView>(R.id.direccion)
        val distrito = rowView.findViewById<TextView>(R.id.distrito)
        val descripcion= rowView.findViewById<TextView>(R.id.descripcion)
        val disponibilidad = rowView.findViewById<TextView>(R.id.disponibilidad)
        val precio = rowView.findViewById<TextView>(R.id.precio)
        val favorito = rowView.findViewById<TextView>(R.id.favorito)


        Picasso.get().load(list[position].imagen).into(imagen)
        direccion.text=list[position].direccion
        distrito.text=list[position].distrito
        descripcion.text=list[position].descripcion
        disponibilidad.text= list[position].disponibilidad.toString() +" disponibles"
        precio.text=list[position].precio
        favorito.text= list[position].favorito.toString()

        return rowView
    }



}
