package com.example.roommates

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roommates.DAO.UsuarioDAO

class LoginActivity : AppCompatActivity() {
    private lateinit var usuarioDAO: UsuarioDAO
    private lateinit var correoI: EditText
    private lateinit var contrasenaI: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuarioDAO = UsuarioDAO(this)
        correoI = findViewById(R.id.correo)
        contrasenaI = findViewById(R.id.contrasena)

        val loginButton = findViewById<Button>(R.id.btnIngresar)
        loginButton.setOnClickListener {
            iniciarSesion()
        }


    }

    fun IrRegistro(view: View){
        val intent = Intent(this,RegistroActivity::class.java)
        startActivity(intent)
    }
    private fun iniciarSesion(){

        val correo = correoI.text.toString()
        val contrasena = contrasenaI.text.toString()

        if (usuarioDAO.validarUsuario(correo, contrasena)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {

            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}