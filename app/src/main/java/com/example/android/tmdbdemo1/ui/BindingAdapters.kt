package com.example.android.tmdbdemo1.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import coil.load
import com.example.android.tmdbdemo1.R
import com.example.android.tmdbdemo1.api.tmdbImageUrl
import com.google.android.material.chip.ChipGroup
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:posterPathThumb")
fun bindPosterPathThumb(imageView: ImageView, path: String?) {
    path?.let {
        imageView.load(tmdbImageUrl(path, "w342")) // FIXME Code smell, magic number :)
    }
}

@BindingAdapter("android:posterPath")
fun bindPosterPath(imageView: ImageView, path: String?) {
    path?.let {
        imageView.load(tmdbImageUrl(path, "w500")) // FIXME Code smell, magic number :)
    }
}

@BindingAdapter("genreChips")
fun bindGenreChips(chipGroup: ChipGroup, genres: LiveData<List<String>?>) {
    val context = chipGroup.context
    val textColor = ContextCompat.getColor(context, R.color.white)
    if (chipGroup.childCount == 0) { // add children once, assume genres does not change between binds
        genres.value?.map { genre ->
            chipGroup.addView(
                getGenreChip(context, genre, textColor)
            )
        }
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