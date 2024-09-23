package com.example.roommates.DAO

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.roommates.Model.Alquiler
import com.example.roommates.Model.Tools

class AlquilerDAO (myContext: Context)  {
    private var dbHelper: DbHelper = DbHelper(myContext)

    fun insertar(direccion: String, distrito: String, descripcion:String, disponibilidad:Int, favorito:Int): Long {
        Log.i(Tools.LOGTAG, "Ingresó al método insertar()")
        val indice: Long
        val values = ContentValues().apply {
            put("direccion", direccion)
            put("distrito", distrito)
            put("descripcion", descripcion)
            put("disponibilidad", disponibilidad)
            put("favorito", favorito)
        }
        val db = dbHelper.writableDatabase
        try {
            indice = db.insert(Tools.MITABLA,null, values)
            return indice
        } catch (e: Exception) {
            throw DAOException("AlquilerDAO: Error al insertar: " + e.message)
        } finally {
            db.close()
        }
    }

    @SuppressLint("Range")
    fun obtener(): Alquiler {
        Log.i(Tools.LOGTAG, "Ingresó al método obtener()")
        val db = dbHelper.readableDatabase
        val modelo = Alquiler()
        try {
            val c: Cursor = db.rawQuery("select id, direccion, distrito, descripcion, disponibilidad, favorito from " + Tools.MITABLA, null)
            if (c.count > 0) {
                c.moveToFirst()
                do {
                    val id: Int = c.getInt(c.getColumnIndex("id"))
                    val direccion: String = c.getString(c.getColumnIndex("direccion"))
                    val distrito: String = c.getString(c.getColumnIndex("distrito"))
                    val descripcion: String = c.getString(c.getColumnIndex("descripcion"))
                    val disponibilidad: Int = c.getInt(c.getColumnIndex("disponibilidad"))
                    val favorito: Int = c.getInt(c.getColumnIndex("favorito"))
                    modelo.id = id
                    modelo.direccion = direccion
                    modelo.distrito = distrito
                    modelo.descripcion = descripcion
                    modelo.disponibilidad = disponibilidad
                    modelo.favorito = favorito
                } while (c.moveToNext())
            }
            c.close()
        } catch (e: Exception) {
            throw DAOException("AlquilerDAO: Error al obtener: " + e.message)
        } finally {
            db.close()
        }
        return modelo
    }

    @SuppressLint("Range")
    fun buscar(criterio: String): ArrayList<Alquiler> {
        Log.i(Tools.LOGTAG, "Ingresó al método buscar()")
        val db = dbHelper.readableDatabase
        val lista = ArrayList<Alquiler>()
        try {
            val c: Cursor = db.rawQuery(
                "select id, direccion, distrito,descripcion, disponibilidad, favorito from " + Tools.MITABLA + " where direccion like '%$criterio%' or descripcion like '%$criterio%'",
                null
            )
            if (c.count > 0) {
                c.moveToFirst()
                do {
                    val id: Int = c.getInt(c.getColumnIndex("id"))
                    val direccion: String = c.getString(c.getColumnIndex("direccion"))
                    val distrito: String = c.getString(c.getColumnIndex("distrito"))
                    val descripcion: String = c.getString(c.getColumnIndex("descripcion"))
                    val disponibilidad: Int = c.getInt(c.getColumnIndex("disponibilidad"))
                    val favorito: Int = c.getInt(c.getColumnIndex("favorito"))
                    val modelo = Alquiler()
                    modelo.id = id
                    modelo.direccion = direccion
                    modelo.distrito = distrito
                    modelo.descripcion = descripcion
                    modelo.disponibilidad = disponibilidad
                    modelo.favorito = favorito
                    lista.add(modelo)
                } while (c.moveToNext())
            }
            c.close()
        } catch (e: Exception) {
            throw DAOException("AlquilerDAO: Error al obtener: " + e.message)
        } finally {
            db.close()
        }
        return lista
    }

    fun eliminar(id: Int) {
        Log.i(Tools.LOGTAG, "Ingresó al método eliminar()")
        val db = dbHelper.writableDatabase
        try {
            val args = arrayOf(id.toString())
            db.execSQL("DELETE FROM " + Tools.MITABLA + " WHERE id=?", args)
        } catch (e: Exception) {
            throw DAOException("AlquilerDAO: Error al eliminar: " + e.message)
        } finally {
            db.close()
        }
    }

    fun eliminarTodos() {
        Log.i(Tools.LOGTAG, "Ingresó al método eliminarTodos()")
        val db = dbHelper.writableDatabase
        try {
            db.execSQL("DELETE FROM " + Tools.MITABLA)
        } catch (e: Exception) {
            throw DAOException("AlquilerDAO: Error al eliminar todos: " + e.message)
        } finally {
            db.close()
        }
    }
}