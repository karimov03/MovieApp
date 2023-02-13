package com.example.movieapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.`class`.Movie
import com.example.movieapp.databinding.ActivityMain2Binding
import com.example.movieapp.databinding.ActivityMain3Binding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity3 : AppCompatActivity() {
    private val binding by lazy { ActivityMain3Binding.inflate(layoutInflater) }
    var boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()
        binding.apply {
            val name = intent.getStringExtra("name")
            val authirs = intent.getStringExtra("authors")
            val about = intent.getStringExtra("about")
            val date = intent.getStringExtra("date")
            Name.text.append(name)
            Authors.text.append(authirs)
            About.text.append(about)
            Date.text.append(date)
            btnSave.setOnClickListener {

                try {
                    if (!Name.text.isEmpty()) {
                        if (!Authors.text.isEmpty()) {
                            if (!About.text.isEmpty()) {
                                if (!Date.text.isEmpty()) {
                                    boolean = true
                                    editor.putString("name", Name.text.toString())
                                    editor.putString("authors", Authors.text.toString())
                                    editor.putString("about", About.text.toString())
                                    editor.putString("date", Date.text.toString())
                                    editor.apply()
                                    finish()
                                } else Toast.makeText(
                                    this@MainActivity3,
                                    "Date is empty",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else Toast.makeText(
                                this@MainActivity3,
                                "About movie is empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else Toast.makeText(
                            this@MainActivity3,
                            "Movie authors is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else Toast.makeText(
                        this@MainActivity3,
                        "Movie name is empty",
                        Toast.LENGTH_SHORT
                    )

                } catch (e: Exception) {
                    boolean = false
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()
        editor.putBoolean("boolean", boolean)
        editor.apply()

    }


}


