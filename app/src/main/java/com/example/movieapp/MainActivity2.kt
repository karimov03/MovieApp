package com.example.movieapp

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.movieapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private val binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }
    private  var boolean:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()

        binding.apply {

            try {
                btnSave.setOnClickListener {
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
                                    this@MainActivity2,
                                    "Date is empty",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else Toast.makeText(
                                this@MainActivity2,
                                "About movie is empty",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else Toast.makeText(
                            this@MainActivity2,
                            "Movie authors is empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else Toast.makeText(
                        this@MainActivity2,
                        "Movie name is empty",
                        Toast.LENGTH_SHORT
                    )

                }

            }
            catch (e:Exception){
                boolean=false
            }
        }

    }


    override fun onPause() {
        super.onPause()

        val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()
        editor.putBoolean("boolean",boolean)
        editor.apply()

    }

}