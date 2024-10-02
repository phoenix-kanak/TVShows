package com.example.tvshows.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.R
import com.example.tvshows.data.models.TvShow
import com.example.tvshows.databinding.TvShowItemBinding
import com.squareup.picasso.Picasso

class TVShowsAdapter(
    private var tvShows: List<TvShow>,
//    private val onItemClick: (TvShow) -> Unit
) : RecyclerView.Adapter<TVShowsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowsAdapter.ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.tv_show_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVShowsAdapter.ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.name.text = tvShow.name
        holder.desc.text = tvShow.permalink
        holder.showStarted.text = tvShow.start_date
        holder.showStatus.text = tvShow.status
        Picasso.get()
            .load(tvShow.image_thumbnail_path)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.showImage)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.show_name)
        val desc: TextView = itemView.findViewById(R.id.show_desc)
        val showStarted: TextView = itemView.findViewById(R.id.show_started)
        val showStatus: TextView = itemView.findViewById(R.id.tv_show_status)
        val showImage: ImageView = itemView.findViewById(R.id.show_img)
    }
}
