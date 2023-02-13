package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityMain4Binding
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity4 : AppCompatActivity() {
    private val binding by lazy { ActivityMain4Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            movieName.text=intent.getStringExtra("name")
            textView.text="Movie Name: "+intent.getStringExtra("name")
            textView2.text=intent.getStringExtra("authors")
            textView3.text=intent.getStringExtra("about")
            textView4.text=intent.getStringExtra("date")
            btnClose.setOnClickListener {
                finish()
            }
        }
    }
}