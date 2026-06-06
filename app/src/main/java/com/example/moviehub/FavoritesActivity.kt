package com.example.moviehub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val txtFavoritesList = findViewById<TextView>(R.id.txtFavoritesList)
        val etMovieId = findViewById<EditText>(R.id.etMovieId)
        val etNewRating = findViewById<EditText>(R.id.etNewRating)

        val btnUpdateRating = findViewById<Button>(R.id.btnUpdateRating)
        val btnDeleteLast = findViewById<Button>(R.id.btnDeleteLast)
        val btnBackFromFavorites = findViewById<Button>(R.id.btnBackFromFavorites)

        val dbHelper = DatabaseHelper(this)

        loadFavorites(dbHelper, txtFavoritesList)

        btnUpdateRating.setOnClickListener {
            val idText = etMovieId.text.toString()
            val ratingText = etNewRating.text.toString()

            if (idText.isEmpty() || ratingText.isEmpty()) {
                Toast.makeText(this, "Enter movie ID and new rating", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedRows = dbHelper.updateRating(
                idText.toInt(),
                ratingText
            )

            if (updatedRows > 0) {
                Toast.makeText(this, "Rating updated", Toast.LENGTH_SHORT).show()
                loadFavorites(dbHelper, txtFavoritesList)
            } else {
                Toast.makeText(this, "Movie not found", Toast.LENGTH_SHORT).show()
            }
        }

        btnDeleteLast.setOnClickListener {
            val cursor = dbHelper.getAllFavorites()
            var lastId = -1

            while (cursor.moveToNext()) {
                lastId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)
                )
            }

            cursor.close()

            if (lastId != -1) {
                dbHelper.deleteFavorite(lastId)
                Toast.makeText(this, "Movie deleted", Toast.LENGTH_SHORT).show()
                loadFavorites(dbHelper, txtFavoritesList)
            } else {
                Toast.makeText(this, "No movie to delete", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackFromFavorites.setOnClickListener {
            finish()
        }
    }

    private fun loadFavorites(
        dbHelper: DatabaseHelper,
        txtFavoritesList: TextView
    ) {
        val cursor = dbHelper.getAllFavorites()
        val result = StringBuilder()

        if (cursor.count == 0) {
            result.append("No favorite movies")
        } else {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)
                )

                val title = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE)
                )

                val genre = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENRE)
                )

                val rating = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_RATING)
                )

                result.append("ID: $id\n")
                result.append("🎬 $title\n")
                result.append("Genre: $genre\n")
                result.append("Rating: $rating\n\n")
            }
        }

        txtFavoritesList.text = result.toString()
        cursor.close()
    }
}