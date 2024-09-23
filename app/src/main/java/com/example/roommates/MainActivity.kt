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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roommates.DAO.AlquilerDAO
import com.example.roommates.DAO.DAOException
import com.example.roommates.Model.Alquiler
import com.example.roommates.Model.Tools

class MainActivity : AppCompatActivity() {
    lateinit var resultados : ArrayList<Alquiler>
    lateinit var listaResultados : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciar()
        val btn_General = findViewById<Button>(R.id.btnGeneral)

        btn_General.setOnClickListener(){
            buscar()
        }
    }

    private fun iniciar(){
        val dao = AlquilerDAO(baseContext)
        dao.insertar("Jr. Andahuaylas 256", "Cercado de Lima - Lima","3 dorm, 2 baños", 2, "S/.550",0)
        dao.insertar("Jr. León Velarde 3667", "Lince - Lima","4 dorm, 3 baños", 1, "S/.700",0)
    }

    fun buscar() {
        val criterio = findViewById<View>(R.id.criterio) as EditText
        val dao = AlquilerDAO(baseContext)
        try {
            resultados = dao.buscar(criterio.text.toString())
            val encontrados = arrayOfNulls<String>(resultados.size)
            var i = 0
            for (gm in resultados) {
                encontrados[i++] = gm.direccion + " | " + gm.distrito + " | " + gm.descripcion + " | " + gm.disponibilidad + " | " + gm.precio+ " | " + gm.favorito
            }
            val adaptador = ArrayAdapter(
                this.baseContext,
                android.R.layout.simple_list_item_1,
                encontrados
            )
            listaResultados = findViewById(R.id.listaResultados)
            listaResultados.setAdapter(adaptador)
            registerForContextMenu(listaResultados)
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