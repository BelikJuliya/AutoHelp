package android.example.autohelp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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