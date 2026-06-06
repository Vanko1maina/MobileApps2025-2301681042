package com.example.moviehub

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MovieHub.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "favorites"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_GENRE = "genre"
        const val COLUMN_RATING = "rating"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT,
                $COLUMN_GENRE TEXT,
                $COLUMN_RATING TEXT
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addFavorite(
        title: String,
        genre: String,
        rating: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put(COLUMN_TITLE, title)
        values.put(COLUMN_GENRE, genre)
        values.put(COLUMN_RATING, rating)

        val result = db.insert(TABLE_NAME, null, values)

        db.close()

        return result != -1L
    }

    fun getAllFavorites(): Cursor {

        val db = readableDatabase

        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )
    }

    fun deleteFavorite(id: Int): Int {

        val db = writableDatabase

        return db.delete(
            TABLE_NAME,
            "$COLUMN_ID=?",
            arrayOf(id.toString())
        )
    }
    fun updateRating(
        id: Int,
        newRating: String
    ): Int {

        val db = writableDatabase

        val values = ContentValues()

        values.put(COLUMN_RATING, newRating)

        return db.update(
            TABLE_NAME,
            values,
            "$COLUMN_ID=?",
            arrayOf(id.toString())
        )
    }
}