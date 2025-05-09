package com.example.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DramaAdapter(
    private val listDrama: ArrayList<Series>,
    private val onWikiClick: (String) -> Unit,
    private val onDetailClick: (String, Int, String, String, String) -> Unit
) : RecyclerView.Adapter<DramaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvPlot: TextView = itemView.findViewById(R.id.tv_item_plot)
        val btnWiki: Button = itemView.findViewById(R.id.btn_wiki)
        val btnDetail: Button = itemView.findViewById(R.id.button_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listDrama.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (title, link, photo, plot, year, cast) = listDrama[position]
        holder.tvTitle.text = title
        holder.imgPhoto.setImageResource(photo)
        holder.tvPlot.text = "$cast"
        holder.btnWiki.setOnClickListener { onWikiClick(link) }
        holder.btnDetail.setOnClickListener { onDetailClick(title, photo, plot, year, cast) }
    }
}