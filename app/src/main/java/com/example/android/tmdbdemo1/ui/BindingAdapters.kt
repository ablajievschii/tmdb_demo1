package com.example.android.tmdbdemo1.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.tmdbdemo1.api.tmdbImageUrl

@BindingAdapter("app:posterPathThumb")
fun bindItems(imageView: ImageView, path: String?) {
    path?.let {
        imageView.load(tmdbImageUrl(path, "w342")) // FIXME Code smell, magic number :)
    }
}