package com.example.roommates

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val direccion = intent.getStringExtra("direccion")
        val distrito = intent.getStringExtra("distrito")
        val descripcion = intent.getStringExtra("descripcion")
        val disponibilidad = intent.getIntExtra("disponibilidad", 0)
        val precio = intent.getStringExtra("precio")
        val favorito = intent.getIntExtra("favorito", 0)
        val imagen = intent.getStringExtra("imagen")
        val descripcionDetallada = intent.getStringExtra("descripcionDetallada")
        val correoContacto = intent.getStringExtra("correoContacto")
        val telefonoContacto = intent.getStringExtra("telefonoContacto")



        val direccionText = findViewById<TextView>(R.id.direccion)
        val distritoText = findViewById<TextView>(R.id.distrito)
        val descripcionText = findViewById<TextView>(R.id.descripcion)
        val disponibilidadText = findViewById<TextView>(R.id.disponibilidad)
        val precioText = findViewById<TextView>(R.id.precio)
        val favoritoText = findViewById<TextView>(R.id.favorito)
        val imagenImage = findViewById<ImageView>(R.id.imagen)
        val descripcionDetalladaText = findViewById<TextView>(R.id.descripcionDetallada)


        direccionText.text = direccion
        distritoText.text = distrito
        descripcionText.text = descripcion
        disponibilidadText.text = "$disponibilidad disponibles"
        precioText.text = precio
        favoritoText.text = "$favorito"
        descripcionDetalladaText.text = descripcionDetallada

        imagen?.let { Picasso.get().load(it).into(imagenImage)}

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.superior, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sup_perfil -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_sup_logout -> {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_sup_main -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun IrContactar(view: View){
        val intent = Intent(this,ContactActivity::class.java)
        startActivity(intent)
    }

}