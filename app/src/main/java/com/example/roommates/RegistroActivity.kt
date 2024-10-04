package com.example.roommates

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roommates.DAO.UsuarioDAO


class RegistroActivity : AppCompatActivity() {

    private lateinit var usuarioDAO: UsuarioDAO
    private lateinit var correoElectronicoI: EditText
    private lateinit var nombreI: EditText
    private lateinit var apellidoI: EditText
    private lateinit var telefonoI: EditText
    private lateinit var contrasenaI: EditText
    private lateinit var contrasenaCI: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        usuarioDAO = UsuarioDAO(this)

        correoElectronicoI = findViewById(R.id.correoElectronico)
        nombreI = findViewById(R.id.nombres)
        apellidoI = findViewById(R.id.apellidos)
        telefonoI = findViewById(R.id.telefonoUsuario)
        contrasenaI = findViewById(R.id.contrasenaUser)
        contrasenaCI = findViewById(R.id.contrasenaConfirmar)

        val registroBtn = findViewById<Button>(R.id.btnRegistrar)
        registroBtn.setOnClickListener {
            registrarUsuario()
        }


    }

    private fun registrarUsuario() {

        val correoElectronico = correoElectronicoI.text.toString()
        val nombre = nombreI.text.toString()
        val apellido = apellidoI.text.toString()
        val telefono = telefonoI.text.toString()
        val contrasena = contrasenaI.text.toString()
        val contrasenaConfirmar = contrasenaCI.text.toString()

        if (correoElectronico.isBlank()|| nombre.isBlank() || apellido.isBlank() || telefono.isBlank() || contrasena.isBlank()) {
            Toast.makeText(this, "Faltan ingresar campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (contrasena != contrasenaConfirmar ) {
            Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }


        val id = usuarioDAO.insertar(correoElectronico,nombre, apellido, telefono, contrasena, "")

        if (id > 0) {
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
        }
    }


    fun IrLogin(view: View){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

}