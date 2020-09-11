package com.finnomena.project.candidate.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.finnomena.project.candidate.R
import com.finnomena.project.candidate.model.PokemonEntries
import com.finnomena.project.candidate.util.OnItemClickListener
import kotlinx.android.synthetic.main.list_item.view.*

class PokemonListAdapter(
    val context: Context,
    val listener: OnItemClickListener

) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private var pokemonList: ArrayList<PokemonEntries> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        pokemonList[position].let {
            holder.txtTitle.text = it.name
//            Glide.with(holder.img)
//                .load(it.url)
//                .error(android.R.drawable.stat_notify_error)
//                .into(holder.img)
            holder.ln.setOnClickListener { _ ->
                listener.onItemClick(position, it.name)
            }
        }
    }

    fun setDataList(pokemonEntries: ArrayList<PokemonEntries>) {
        this.pokemonList = pokemonEntries
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle: TextView = view.txtTitle
//        var img: ImageView = view.img
        var ln: LinearLayout = view.ln
    }
}