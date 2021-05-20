package com.example.twitchinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitchinfo.R
import com.example.twitchinfo.api.ApiService.Companion.SMALL_POSTER_SIZE
import com.example.twitchinfo.databinding.ItemGameInfoBinding
import com.example.twitchinfo.pojo.MovieInfo
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieInfoList: List<MovieInfo> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onReachEndListener: OnReachEndListener? = null

    var onPosterClickListener: OnPosterClickListener? = null

    interface OnReachEndListener {
        fun onReachEnd()
    }

    interface OnPosterClickListener {
        fun onPosterClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game_info, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (position > movieInfoList.size - 4 && onReachEndListener != null) {
            onReachEndListener?.onReachEnd()
        }
        val movie = movieInfoList[position]
        with(holder) {
            tvGameTitle.text = movie.title
            tvNumbersOfChannels.text = movie.voteAverage.toString()
            tvViews.text = movie.popularity.toString()
            Picasso.get().load(movie.getFullImageUrl(SMALL_POSTER_SIZE)).into(ivGameLogo)
            itemView.setOnClickListener {
                onPosterClickListener?.onPosterClick(movie.id)
            }
        }
    }

    override fun getItemCount(): Int = movieInfoList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ui: ItemGameInfoBinding = ItemGameInfoBinding.bind(itemView)

        val ivGameLogo = ui.ivGameLogo
        val tvGameTitle = ui.tvGameTitle
        val tvNumbersOfChannels = ui.tvNumbersOfChannels
        val tvViews = ui.tvViews
    }
}