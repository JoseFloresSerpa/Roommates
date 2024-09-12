package com.example.roommates

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegistroActivity : AppCompatActivity() {
    private lateinit var correoEditText: EditText
    private lateinit var nombresEditText: EditText
    private lateinit var apellidosEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var contraseñaEditText: EditText
    private lateinit var confirmarContraseñaEditText: EditText
    private lateinit var registrarButton: Button
    private lateinit var cancelarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        correoEditText = findViewById(R.id.editTextText8)
        nombresEditText = findViewById(R.id.editTextText9)
        apellidosEditText = findViewById(R.id.editTextText10)
        telefonoEditText = findViewById(R.id.editTextText11)
        contraseñaEditText = findViewById(R.id.editTextText12)
        confirmarContraseñaEditText = findViewById(R.id.editTextText13)
        registrarButton = findViewById(R.id.button4)
        cancelarButton = findViewById(R.id.button5)

        registrarButton.setOnClickListener {
            val correo = correoEditText.text.toString()
            val nombres = nombresEditText.text.toString()
            val apellidos = apellidosEditText.text.toString()
            val telefono = telefonoEditText.text.toString()
            val contraseña = contraseñaEditText.text.toString()
            val confirmarContraseña = confirmarContraseñaEditText.text.toString()

            if (correo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else if (contraseña != confirmarContraseña) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            }
        }

        fun IrLogin(view: View){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}