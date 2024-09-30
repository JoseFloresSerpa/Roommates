package com.example.roommates

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roommates.DAO.AlquilerDAO
import com.example.roommates.DAO.DAOException
import com.example.roommates.Model.Alquiler
import com.example.roommates.Model.AlquilerAdapter
import com.example.roommates.Model.Tools

class MainActivity : AppCompatActivity() {
    lateinit var resultados : List<Alquiler>
    lateinit var alquilerAdapter : AlquilerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buscar()
        val btn_General = findViewById<Button>(R.id.btnGeneral)
        btn_General.setOnClickListener(){
            buscar()
        }

    }

    fun buscar() {
        val criterio = findViewById<View>(R.id.criterio) as EditText
        val dao = AlquilerDAO(baseContext)
        try {
            resultados = dao.buscar(criterio.text.toString())

            alquilerAdapter= AlquilerAdapter(this, resultados)
            val listView=findViewById<ListView>(R.id.listaResultados)
            listView.adapter=alquilerAdapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val alquiler = resultados[position]


                val intent = Intent(this, DetalleActivity::class.java).apply {
                    putExtra("direccion", alquiler.direccion)
                    putExtra("distrito", alquiler.distrito)
                    putExtra("descripcion", alquiler.descripcion)
                    putExtra("disponibilidad", alquiler.disponibilidad)
                    putExtra("precio", alquiler.precio)
                    putExtra("favorito", alquiler.favorito)
                    putExtra("imagen", alquiler.imagen)
                }

                startActivity(intent)
            }


        } catch (e: DAOException) {
            Log.i(Tools.LOGTAG, "MainActivity ==> " + e.message)
        }
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

}