package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.Adapters.Adapter
import com.example.movieapp.`class`.Movie
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: Adapter
    private var list = ArrayList<Movie>()
    var boolean: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()
        editor.putInt("position", -2)

        adapter = Adapter(list, object : Adapter.RvAction {
            override fun deleteitem(movie: Movie, position: Int) {
                if (list.size < 1) {

                } else {
                    list.removeAt(position)
                    adapter.notifyDataSetChanged()

                }
            }

            override fun aboutmovie(movie: Movie, position: Int) {
                val intent = Intent(this@MainActivity, MainActivity4::class.java)
                intent.putExtra("name", list[position].name)
                intent.putExtra("authors", list[position].authors)
                intent.putExtra("about", list[position].about)
                intent.putExtra("date", list[position].date)
                startActivity(intent)
            }

            override fun editmovie(movie: Movie, position: Int) {

                val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
                val editor = SharedPreferences.edit()
                editor.putInt("position", position)
                editor.apply()
                val intent = Intent(this@MainActivity, MainActivity3::class.java)
                intent.putExtra("name",list[position].name)
                intent.putExtra("authors",list[position].authors)
                intent.putExtra("about",list[position].about)
                intent.putExtra("date",list[position].date)
                startActivity(intent)
            }
        })


        val sharedPreferences = getSharedPreferences("mk", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("list_key", "")
        if (json != "") {
            val gson = Gson()

            val type = object : TypeToken<ArrayList<Movie>>() {}.type
            val dataList = gson.fromJson<ArrayList<Movie>>(json, type)
            for (item in dataList) {
                val name = item.name
                val authors = item.authors
                val about = item.about
                val date = item.date
                list.add(Movie(name, authors, about, date))
                binding.rv.adapter = adapter
                adapter.notifyDataSetChanged()

            }
        }

        binding.btnPlus.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()

        val position = SharedPreferences.getInt("position", -77)

        val name = SharedPreferences.getString("name", "").toString()
        val authors = SharedPreferences.getString("authors", "").toString()
        val about = SharedPreferences.getString("about", "").toString()
        val date = SharedPreferences.getString("date", "").toString()

        var boolean = SharedPreferences.getBoolean("boolean", false)
        if (boolean == true) {
            if (position != -77) {
                list.removeAt(position)
                list.add(position,Movie(name, authors, about, date))
                editor.putInt("position", -77)
                editor.apply()
            } else {

                list.add(Movie(name, authors, about, date))
            }

            binding.rv.adapter = adapter
            adapter.notifyDataSetChanged()
            editor.putBoolean("boolean", false)
            editor.apply()
        }


    }

    override fun onRestart() {
        super.onRestart()
        val sharetwrite = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        boolean = sharetwrite.getBoolean("boolean", false)

    }

    override fun onPause() {
        super.onPause()
        val SharedPreferences = getSharedPreferences("Movie", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()
        editor.putBoolean("boolean", false)
        editor.apply()

        val sharedPreferences = getSharedPreferences("mk", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(list)
        val editor1 = sharedPreferences.edit()
        editor1.putString("list_key", json).apply()
    }


}

