package com.example.roommates

import android.os.Bundle
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roommates.clases.Alquiler
import com.example.roommates.clases.AlquileresAdapter

class MainActivity : AppCompatActivity() {
    private val alquilerList: ArrayList<Alquiler> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: AlquileresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.recycler_view)
        mAdapter = AlquileresAdapter(alquilerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = mAdapter
        prepareAlquilerData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.superior, menu)
        return super.onCreateOptionsMenu(menu)
    }
    private fun prepareAlquilerData() {
        var alquiler = Alquiler("Jr. Andahuaylas 256", "Cercado de Lima - Lima 3 Dorm. 2 baños", "2 Disponibles")
        alquilerList.add(alquiler)
        alquiler = Alquiler("Jr. León Velarde 3667", "Lince - Lima 4 Dorm. 3 baños", "1 Disponible")
        alquilerList.add(alquiler)
        alquiler = Alquiler("Av. Brasil 1569", "Jesús Maria - Lima 3 Dorm. 3 baños", "3 Disponibles")
        alquilerList.add(alquiler)
        alquiler = Alquiler("Av Tomas Marsano 5566", "Surquillo - Lima 3 Dorm. 2 baños", "1 Disponible")
        alquilerList.add(alquiler)

        mAdapter.notifyDataSetChanged()
    }
}