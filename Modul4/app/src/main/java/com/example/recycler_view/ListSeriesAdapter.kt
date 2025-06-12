package com.example.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSeriesAdapter(
    private var listSeries: List<Series>,
    private val onWikiClick: (String) -> Unit,
    private val onDetailClick: (Series) -> Unit
) : RecyclerView.Adapter<ListSeriesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvCast: TextView = itemView.findViewById(R.id.tv_cast)
        val btnWiki: Button = itemView.findViewById(R.id.btn_wiki)
        val btnDetail: Button = itemView.findViewById(R.id.button_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listSeries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = listSeries[position]
        holder.tvTitle.text = series.title
        holder.tvCast.text = series.cast
        holder.imgPhoto.setImageResource(series.photo)
        holder.btnWiki.setOnClickListener { onWikiClick(series.link) }
        holder.btnDetail.setOnClickListener { onDetailClick(series) }
    }

    fun updateList(newList: List<Series>) {
        listSeries = newList
        notifyDataSetChanged()
    }
}