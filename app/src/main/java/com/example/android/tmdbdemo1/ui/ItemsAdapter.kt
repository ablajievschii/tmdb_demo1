package com.example.android.tmdbdemo1.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.tmdbdemo1.databinding.DiscoverMovieListItemBinding
import com.example.android.tmdbdemo1.model.DiscoverMovie
import com.example.android.tmdbdemo1.ui.UiModel.DiscoverMovieItem
import javax.inject.Inject

class ItemsAdapter @Inject constructor() : PagingDataAdapter<UiModel, ItemsViewHolder>(MovieComparator) {

    private var listener: ((UiModel, View) -> Unit)? = null

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(getItem(position) as DiscoverMovieItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder.from(parent).also { holder ->
            listener?.let { onClick ->
                holder.listen { pos, type, view ->
                    getItem(pos)?.let { item -> onClick(item, view) }
                }
            }
        }
    }

    fun setOnClickListener(listener: (UiModel?, View) -> Unit) {
        this.listener = listener
    }
}

class ItemsViewHolder(private val binding: DiscoverMovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DiscoverMovieItem) {
        binding.movie = item.movie
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ItemsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DiscoverMovieListItemBinding.inflate(layoutInflater, parent, false)
            return ItemsViewHolder(binding)
        }
    }
}

object MovieComparator : DiffUtil.ItemCallback<UiModel>() {
    override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
        return if (oldItem is DiscoverMovieItem && newItem is DiscoverMovieItem) {
            oldItem.movie.id == newItem.movie.id
        } else {
            oldItem == newItem
        }
    }

    override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
        return oldItem == newItem
    }
}

fun <T : RecyclerView.ViewHolder> T.listen(listener: (pos: Int, type: Int, view: View) -> Unit): T {
    itemView.setOnClickListener {
        listener(bindingAdapterPosition, itemViewType, itemView)
    }
    return this
}