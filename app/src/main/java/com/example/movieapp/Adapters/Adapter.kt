package com.example.movieapp.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.`class`.Movie
import com.example.movieapp.databinding.ItemrvBinding

class Adapter(val list: List<Movie>,val rvAction: RvAction):RecyclerView.Adapter<Adapter.vh>() {

    class vh(val itemrvBinding: ItemrvBinding):RecyclerView.ViewHolder(itemrvBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vh {
        return vh(ItemrvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: vh, position: Int) {
        val movie=list[position]
        holder.itemrvBinding.movieName.text=movie.name
        holder.itemrvBinding.movieAbout.text=movie.about
        holder.itemrvBinding.movieDate.text=movie.date

        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation))
        holder.itemrvBinding.btnDelete.setOnClickListener {
            rvAction.deleteitem(movie,position)
        }
        holder.itemrvBinding.btnEdit.setOnClickListener {
            rvAction.editmovie(movie,position)
            
        }
        holder.itemrvBinding.movieName.setOnClickListener {
            rvAction.aboutmovie(movie,position)
        }

        holder.itemView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,R.anim.animation))

    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface RvAction
    {
        fun deleteitem(movie: Movie, position: Int)
        fun aboutmovie(movie: Movie,position: Int)
        fun editmovie(movie: Movie,position: Int)
    }
}