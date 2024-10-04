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

    fun insertar(direccion: String, distrito: String, descripcion:String, disponibilidad:Int, precio:String, favorito:Int, imagen:String, descripcionDetallada:String, correoContacto:String, telefonoContacto:String): Long {
        Log.i(Tools.LOGTAG, "Ingresó al método insertar()")
        val indice: Long
        val values = ContentValues().apply {
            put("direccion", direccion)
            put("distrito", distrito)
            put("descripcion", descripcion)
            put("disponibilidad", disponibilidad)
            put("precio", precio)
            put("favorito", favorito)
            put("imagen",imagen)
            put("descripcionDetallada",descripcionDetallada)
            put("correoContacto",correoContacto)
            put("telefonoContacto",telefonoContacto)

        }
        val db = dbHelper.writableDatabase
        try {
            indice = db.insert(Tools.MITABLALQUILER,null, values)
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
            val c: Cursor = db.rawQuery("select id, direccion, distrito, descripcion, disponibilidad, precio ,favorito, imagen, descripcionDetallada, correoContacto, telefonoContacto from " + Tools.MITABLALQUILER, null)
            if (c.count > 0) {
                c.moveToFirst()
                do {
                    val id: Int = c.getInt(c.getColumnIndex("id"))
                    val direccion: String = c.getString(c.getColumnIndex("direccion"))
                    val distrito: String = c.getString(c.getColumnIndex("distrito"))
                    val descripcion: String = c.getString(c.getColumnIndex("descripcion"))
                    val disponibilidad: Int = c.getInt(c.getColumnIndex("disponibilidad"))
                    val precio: String = c.getString(c.getColumnIndex("precio"))
                    val favorito: Int = c.getInt(c.getColumnIndex("favorito"))
                    val imagen: String = c.getString(c.getColumnIndex("imagen"))
                    val descripcionDetallada: String = c.getString(c.getColumnIndex("descripcionDetallada"))
                    val correoContacto: String = c.getString(c.getColumnIndex("correoContacto"))
                    val telefonoContacto: String = c.getString(c.getColumnIndex("telefonoContacto"))

                    modelo.id = id
                    modelo.direccion = direccion
                    modelo.distrito = distrito
                    modelo.descripcion = descripcion
                    modelo.disponibilidad = disponibilidad
                    modelo.precio = precio
                    modelo.favorito = favorito
                    modelo.imagen = imagen
                    modelo.descripcionDetallada = descripcionDetallada
                    modelo.correoContacto = correoContacto
                    modelo.telefonoContacto = telefonoContacto
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
                "select id, direccion, distrito,descripcion, disponibilidad,precio, favorito, imagen, descripcionDetallada, correoContacto, telefonoContacto from " + Tools.MITABLALQUILER + " where direccion like '%$criterio%' or descripcion like '%$criterio%'",
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
                    val precio: String = c.getString(c.getColumnIndex("precio"))
                    val favorito: Int = c.getInt(c.getColumnIndex("favorito"))
                    val imagen: String = c.getString(c.getColumnIndex("imagen"))
                    val descripcionDetallada: String = c.getString(c.getColumnIndex("descripcionDetallada"))
                    val correoContacto: String = c.getString(c.getColumnIndex("correoContacto"))
                    val telefonoContacto: String = c.getString(c.getColumnIndex("telefonoContacto"))
                    val modelo = Alquiler()
                    modelo.id = id
                    modelo.direccion = direccion
                    modelo.distrito = distrito
                    modelo.descripcion = descripcion
                    modelo.disponibilidad = disponibilidad
                    modelo.precio = precio
                    modelo.favorito = favorito
                    modelo.imagen = imagen
                    modelo.descripcionDetallada = descripcionDetallada
                    modelo.correoContacto = correoContacto
                    modelo.telefonoContacto = telefonoContacto
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

    @SuppressLint("Range")
    fun buscarFavorito(): ArrayList<Alquiler> {
        Log.i(Tools.LOGTAG, "Ingresó al método buscar()")
        val db = dbHelper.readableDatabase
        val lista = ArrayList<Alquiler>()
        try {
            val c: Cursor = db.rawQuery(
                "select id, direccion, distrito,descripcion, disponibilidad,precio, favorito, imagen, descripcionDetallada, correoContacto, telefonoContacto from " + Tools.MITABLALQUILER + " where favorito like '%1%'",
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
                    val precio: String = c.getString(c.getColumnIndex("precio"))
                    val favorito: Int = c.getInt(c.getColumnIndex("favorito"))
                    val imagen: String = c.getString(c.getColumnIndex("imagen"))
                    val descripcionDetallada: String = c.getString(c.getColumnIndex("descripcionDetallada"))
                    val correoContacto: String = c.getString(c.getColumnIndex("correoContacto"))
                    val telefonoContacto: String = c.getString(c.getColumnIndex("telefonoContacto"))
                    val modelo = Alquiler()
                    modelo.id = id
                    modelo.direccion = direccion
                    modelo.distrito = distrito
                    modelo.descripcion = descripcion
                    modelo.disponibilidad = disponibilidad
                    modelo.precio = precio
                    modelo.favorito = favorito
                    modelo.imagen = imagen
                    modelo.descripcionDetallada = descripcionDetallada
                    modelo.correoContacto = correoContacto
                    modelo.telefonoContacto = telefonoContacto
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
            db.execSQL("DELETE FROM " + Tools.MITABLALQUILER + " WHERE id=?", args)
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
            db.execSQL("DELETE FROM " + Tools.MITABLALQUILER)
        } catch (e: Exception) {
            throw DAOException("AlquilerDAO: Error al eliminar todos: " + e.message)
        } finally {
            db.close()
        }
    }
}