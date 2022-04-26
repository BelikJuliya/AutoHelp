package android.example.autohelp.presentation.main

import android.example.autohelp.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        //setTheme(R.style.RickMortyGreenTheme)
        //setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//        val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
//        navigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.characters -> {
//                    navController.navigate(R.id.characterListFragment)
//                    true
//                }
//                R.id.locations -> {
//                    navController.navigate(R.id.locationListFragment)
//                    true
//                }
//                R.id.episodes -> {
//                    navController.navigate(R.id.episodesListFragment)
//                    true
//                }
//                else -> false
//            }
//        }
    }
}