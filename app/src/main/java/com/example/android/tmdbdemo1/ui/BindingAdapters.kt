package com.example.android.tmdbdemo1.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.android.tmdbdemo1.api.tmdbImageUrl
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:posterPathThumb")
fun bindPosterPathThumb(imageView: ImageView, path: String?) {
    path?.let {
        imageView.load(tmdbImageUrl(path, "w342")) // FIXME Code smell, magic number :)
    }
}

@BindingAdapter("formatDate")
fun bindFormatDate(textView: TextView, value: String?) {
    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    textView.text = value?.let { dateString ->
        try {
            df.parse(dateString)?.let { formatter.format(it) }
        } catch (ex: Exception) {
            // this is not critical error, so just log it as a warning
            Timber.w("Failed to parse the date")
            ""
        }
    }
}