package com.example.android.tmdbdemo1

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.tmdbdemo1.databinding.ActivityTmdbBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TMDBActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTmdbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTmdbBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar(binding.toolbar, findNavControllerSupport(R.id.nav_host_fragment_activity_main))
    }

    private fun setupActionBar(toolbar: Toolbar? = null, navController: NavController) {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.popularMoviesFragment
            )
        )
        toolbar?.let {
            setSupportActionBar(it)
            it.setupWithNavController(navController, appBarConfiguration)
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}

fun TMDBActivity.findNavControllerSupport(@IdRes fragmentId: Int): NavController {
    val navHostFragment = supportFragmentManager.findFragmentById(fragmentId) as NavHostFragment
    return navHostFragment.navController
}