package com.example.roommates.DAO


import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.roommates.Model.Tools

class UsuarioDAO (myContext: Context)  {
    private var dbHelper: DbHelper = DbHelper(myContext)

    fun insertar(correo:String,nombre: String, apellido: String, telefono:String, contrasena:String, imagen:String): Long {
        Log.i(Tools.LOGTAG, "Ingresó al método insertar()")
        val indice: Long
        val values = ContentValues().apply {
            put("correo", correo)
            put("nombre", nombre)
            put("apellido", apellido)
            put("telefono", telefono)
            put("contrasena", contrasena)
            put("imagen", imagen)

        }
        val db = dbHelper.writableDatabase
        try {
            indice = db.insert(Tools.MITABLAUSUARIO,null, values)
            return indice
        } catch (e: Exception) {
            throw DAOException("UsuarioDAO: Error al insertar: " + e.message)
        } finally {
            db.close()
        }
    }
    fun validarUsuario(correo: String, contrasena: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "usuario",
            arrayOf("id"),
            "correo = ? AND contrasena = ?",
            arrayOf(correo, contrasena),
            null,
            null,
            null
        )
        val isValid = cursor.moveToFirst()
        cursor.close()
        return isValid
    }

    fun eliminarTodos() {
        Log.i(Tools.LOGTAG, "Ingresó al método eliminarTodos()")
        val db = dbHelper.writableDatabase
        try {
            db.execSQL("DELETE FROM " + Tools.MITABLAUSUARIO)
        } catch (e: Exception) {
            throw DAOException("UsuarioDAO: Error al eliminar todos: " + e.message)
        } finally {
            db.close()
        }
    }

}