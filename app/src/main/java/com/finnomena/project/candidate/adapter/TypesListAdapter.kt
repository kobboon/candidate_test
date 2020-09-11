package com.finnomena.project.candidate.adapter

import android.content.Context
import android.graphics.Color
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
import com.finnomena.project.candidate.model.Type
import com.finnomena.project.candidate.util.OnItemClickListener
import kotlinx.android.synthetic.main.list_item_type.view.*

class TypesListAdapter(
    val context: Context,
    val listener: OnItemClickListener

) : RecyclerView.Adapter<TypesListAdapter.ViewHolder>() {

    private var typeList: ArrayList<Type> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_type,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        typeList[position].let {

//            val colorBg = it.name?.toLowerCase()
//            Color.parseColor(colorBg)
        }
    }

    fun setDataList(typeList: ArrayList<Type>) {
        this.typeList = typeList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtType: TextView = view.txtType
        var lnBg: LinearLayout = view.lnBg
    }
}