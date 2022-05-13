package com.example.android.tmdbdemo1.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import coil.load
import com.example.android.tmdbdemo1.R
import com.example.android.tmdbdemo1.api.tmdbImageUrl
import com.google.android.material.chip.ChipGroup
import com.google.android.material.progressindicator.CircularProgressIndicator
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

@BindingAdapter("voteAverage")
fun bindVoteAverageProgress(progressView: CircularProgressIndicator, voteAverage: Float?) {
    voteAverage?.let {
        progressView.progress = getVotesPercent(it)
    }
}

@BindingAdapter("voteAverageLive")
fun bindVoteAverageProgress(progressView: CircularProgressIndicator, voteAverage: LiveData<Float?>?) {
    voteAverage?.value?.let {
        progressView.progress = getVotesPercent(it)
    }
}

@BindingAdapter("voteAverage")
fun bindVoteAverageText(textView: TextView, voteAverage: Float?) {
    voteAverage?.let {
        textView.text = textView.resources.getString (R.string.votes_percent, getVotesPercent(it))
    }
}

@BindingAdapter("voteAverageLive")
fun bindVoteAverageText(textView: TextView, voteAverage: LiveData<Float?>?) {
    voteAverage?.value?.let {
        textView.text = textView.resources.getString (R.string.votes_percent, getVotesPercent(it))
    }
}

private fun getVotesPercent(votes: Float): Int {
    return (votes * 10).toInt()
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

@BindingAdapter("homepage")
fun bindHomePage(imageView: ImageView, homepage: LiveData<String?>) {
    homepage.value?.let { url ->
        imageView.visibility = View.VISIBLE
        imageView.setOnClickListener { _ ->
            goToHomePage(imageView.context, url)
        }
    } ?: imageView.setVisibility(View.GONE)
}

private fun goToHomePage(context: Context, url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
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