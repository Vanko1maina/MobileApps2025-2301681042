package com.example.moviehub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val imgPoster = findViewById<ImageView>(R.id.imgPoster)

        val txtMovieTitle = findViewById<TextView>(R.id.txtMovieTitle)
        val txtMovieGenre = findViewById<TextView>(R.id.txtMovieGenre)
        val txtMovieRating = findViewById<TextView>(R.id.txtMovieRating)

        val btnFavorite = findViewById<Button>(R.id.btnFavorite)
        val btnShareMovie = findViewById<Button>(R.id.btnShareMovie)
        val btnBackHome = findViewById<Button>(R.id.btnBackHome)

        val dbHelper = DatabaseHelper(this)

        val title = intent.getStringExtra("title") ?: "Movie Title"
        val genre = intent.getStringExtra("genre") ?: "Genre"
        val rating = intent.getStringExtra("rating") ?: "0.0"

        txtMovieTitle.text = title
        txtMovieGenre.text = genre
        txtMovieRating.text = "⭐ $rating"

        when (title) {
            "Inception" -> imgPoster.setImageResource(R.drawable.inception)
            "Interstellar" -> imgPoster.setImageResource(R.drawable.interstellar)
            "Avatar" -> imgPoster.setImageResource(R.drawable.avatar)
        }

        btnFavorite.setOnClickListener {

            val success = dbHelper.addFavorite(
                title,
                genre,
                rating
            )

            if (success) {
                Toast.makeText(
                    this,
                    "$title added to favorites",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Error saving movie",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnShareMovie.setOnClickListener {

            val shareIntent = Intent(Intent.ACTION_SEND)

            shareIntent.type = "text/plain"

            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out $title on MovieHub! Genre: $genre, Rating: $rating"
            )

            startActivity(
                Intent.createChooser(
                    shareIntent,
                    "Share movie with"
                )
            )
        }

        btnBackHome.setOnClickListener {
            finish()
        }
    }
}