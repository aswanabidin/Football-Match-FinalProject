package aswanabidin.football_match_finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import aswanabidin.football_match_finalproject.features.events.EventFragment
import aswanabidin.football_match_finalproject.features.favorites.FavoritesFragment
import aswanabidin.football_match_finalproject.features.teams.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            showEvents()
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_events -> {
                    showEvents()
                    true
                }
                R.id.nav_teams -> {
                    showTeams()
                    true
                }
                R.id.nav_favorites -> {
                    showFavorites()
                    true
                }
                else -> false
            }
        }
    }

    private fun showEvents() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_main, EventFragment())
            .commit()
    }

    private fun showTeams() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_main, TeamsFragment())
            .commit()
    }

    private fun showFavorites() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_main, FavoritesFragment())
            .commit()
    }
}
