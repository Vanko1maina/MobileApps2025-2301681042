package com.example.moviehub

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val btnAction = findViewById<Button>(R.id.btnAction)
        val btnComedy = findViewById<Button>(R.id.btnComedy)
        val btnDrama = findViewById<Button>(R.id.btnDrama)
        val btnSciFi = findViewById<Button>(R.id.btnSciFi)
        val btnHorror = findViewById<Button>(R.id.btnHorror)
        val btnThriller = findViewById<Button>(R.id.btnThriller)

        btnAction.setOnClickListener {
            Toast.makeText(this, "Action movies selected", Toast.LENGTH_SHORT).show()
        }

        btnComedy.setOnClickListener {
            Toast.makeText(this, "Comedy movies selected", Toast.LENGTH_SHORT).show()
        }

        btnDrama.setOnClickListener {
            Toast.makeText(this, "Drama movies selected", Toast.LENGTH_SHORT).show()
        }

        btnSciFi.setOnClickListener {
            Toast.makeText(this, "Sci-Fi movies selected", Toast.LENGTH_SHORT).show()
        }

        btnHorror.setOnClickListener {
            Toast.makeText(this, "Horror movies selected", Toast.LENGTH_SHORT).show()
        }

        btnThriller.setOnClickListener {
            Toast.makeText(this, "Thriller movies selected", Toast.LENGTH_SHORT).show()
        }
    }
}