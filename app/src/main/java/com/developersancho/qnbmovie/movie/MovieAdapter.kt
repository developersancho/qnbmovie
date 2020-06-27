package com.developersancho.qnbmovie.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.developersancho.common.utils.CommonUtils
import com.developersancho.qnbmovie.databinding.RowLoadMoreBinding
import com.developersancho.qnbmovie.databinding.RowMovieBinding
import com.developersancho.remote.model.Movie

class MovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onMovieClick: ((Movie) -> Unit)? = null
    var onLoadMore: (() -> Unit)? = null

    private var items = listOf<Movie>()

    fun setData(movie: List<Movie>) {
        items = movie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == ViewType.MOVIE_VIEW.value) {
            val itemBinding = RowMovieBinding.inflate(inflater, parent, false)
            MovieViewHolder(itemBinding)
        } else {
            val itemBinding = RowLoadMoreBinding.inflate(inflater, parent, false)
            LoadMoreViewHolder(itemBinding)
        }
    }

    override fun getItemCount(): Int = items.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == ViewType.MOVIE_VIEW.value) {
            (holder as MovieViewHolder).bind(items[position])
        } else {
            (holder as LoadMoreViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size) ViewType.LOAD_MORE_VIEW.value else ViewType.MOVIE_VIEW.value
    }

    inner class MovieViewHolder(private val binding: RowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.ivPoster.load(CommonUtils.getPosterImage(movie.posterPath ?: ""))
            binding.tvTitle.text = movie.title
            binding.root.setOnClickListener {
                onMovieClick?.invoke(movie)
            }
        }

    }

    inner class LoadMoreViewHolder(private val binding: RowLoadMoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.btnLoadMore.setOnClickListener {
                onLoadMore?.invoke()
            }
        }

    }

}

enum class ViewType(val value: Int) {
    LOAD_MORE_VIEW(0),
    MOVIE_VIEW(1)
}