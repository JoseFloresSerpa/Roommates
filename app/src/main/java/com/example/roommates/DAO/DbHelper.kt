package com.example.roommates.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(myContext: Context): SQLiteOpenHelper(myContext,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "roommates.db"
        private const val DATABASE_VERSION = 6
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE IF NOT EXISTS alquiler (id INTEGER PRIMARY KEY AUTOINCREMENT, direccion TEXT NOT NULL, distrito TEXT NOT NULL,descripcion TEXT NOT NULL, disponibilidad INTEGER NOT NULL,precio TEXT NOT NULL, favorito INTEGER NOT NULL, imagen TEXT NOT NULL)"
        db.execSQL(sql)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS alquiler")
        onCreate(db)

    }
}