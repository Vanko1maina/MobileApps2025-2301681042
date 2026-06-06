package com.example.moviehub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnCategories = findViewById<Button>(R.id.btnCategories)
        val btnFavorites = findViewById<Button>(R.id.btnFavorites)
        val btnProfile = findViewById<Button>(R.id.btnProfile)

        val btnInception = findViewById<Button>(R.id.btnInception)
        val btnInterstellar = findViewById<Button>(R.id.btnInterstellar)
        val btnAvatar = findViewById<Button>(R.id.btnAvatar)

        btnCategories.setOnClickListener {
            startActivity(
                Intent(this, CategoriesActivity::class.java)
            )
        }

        btnFavorites.setOnClickListener {
            startActivity(
                Intent(this, FavoritesActivity::class.java)
            )
        }

        btnProfile.setOnClickListener {
            startActivity(
                Intent(this, ProfileActivity::class.java)
            )
        }

        btnInception.setOnClickListener {
            openDetails("Inception", "Sci-Fi • Action", "8.8")
        }

        btnInterstellar.setOnClickListener {
            openDetails("Interstellar", "Sci-Fi • Drama", "8.7")
        }

        btnAvatar.setOnClickListener {
            openDetails("Avatar", "Adventure • Fantasy", "7.9")
        }
    }

    private fun openDetails(
        title: String,
        genre: String,
        rating: String
    ) {
        val intent = Intent(
            this,
            MovieDetailsActivity::class.java
        )

        intent.putExtra("title", title)
        intent.putExtra("genre", genre)
        intent.putExtra("rating", rating)

        startActivity(intent)
    }
}