package com.example.tvshows.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshows.R
import com.example.tvshows.common.listeners.TvShowsListener
import com.example.tvshows.data.models.TvShow
import com.squareup.picasso.Picasso

class TVShowsAdapter(
    private var tvShows: List<TvShow>,
    private val tvShowsListener: TvShowsListener
) : RecyclerView.Adapter<TVShowsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.tv_show_item, parent, false)
        return ViewHolder(view, tvShowsListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    class ViewHolder(itemView: View, private val tvShowsListener: TvShowsListener) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.show_name)
        private val desc: TextView = itemView.findViewById(R.id.show_desc)
        private val showStarted: TextView = itemView.findViewById(R.id.show_started)
        private val showStatus: TextView = itemView.findViewById(R.id.tv_show_status)
        private val showImage: ImageView = itemView.findViewById(R.id.show_img)

        fun bind(tvShow: TvShow) {
            name.text = tvShow.name
            desc.text = tvShow.permalink
            showStarted.text = tvShow.start_date
            showStatus.text = tvShow.status

            Picasso.get()
                .load(tvShow.image_thumbnail_path)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(showImage)

            itemView.setOnClickListener {
                tvShowsListener.onTvShowClicked(tvShow)
            }
        }
    }
}
