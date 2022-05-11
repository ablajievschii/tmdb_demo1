package com.example.android.tmdbdemo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.tmdbdemo1.databinding.ActivityTmdbBinding

class TMDBActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTmdbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTmdbBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}