package aswanabidin.football_match_finalproject.features.events.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.database.DatabaseManager
import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.network.ApiRepositoryImpl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_events_detail.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast

class EventsDetailActivity : AppCompatActivity(), EventsContracts.DetailView {


    private val api by lazy { ApiRepositoryImpl() }
    private val database by lazy { DatabaseManager(this) }
    private val presenter by lazy { EventsDetailPresenter(this, api, database) }

    private var addedToFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        launch(UI) {
            presenter.fetchEventDetail(intent?.getStringExtra("EVENT_ID") ?: "")
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_item, menu)

        val favoriteMenu = menu?.findItem(R.id.action_favorite)
        if (addedToFavorite) {
            favoriteMenu?.setIcon(R.drawable.ic_added_to_favorite)
            favoriteMenu?.setOnMenuItemClickListener {
                launch(UI) { presenter.removeFromFavorite() }
                toast("Removed from favorite")
                true
            }
        } else {
            favoriteMenu?.setIcon(R.drawable.ic_add_to_favorite)
            favoriteMenu?.setOnMenuItemClickListener {
                launch(UI) { presenter.addToFavorite() }
                toast("Added to favorite")
                true
            }
        }

        return true
    }

    override fun showEventDetail(event: EventModel) {
        tv_date.text = event.readableDate
        tv_time.text = event.readableTime

        tv_home_name.text = event.homeName
        tv_home_score.text = event.homeScore
        tv_home_goals.text = event.homeGoals ?: "-"
        tv_home_goalkeeper.text = event.homeLineupGoalKeeper?.replace("; ", ", ") ?: "-"
        tv_home_defense.text = event.homeLineupDefense?.replace("; ", ", ") ?: "-"
        tv_home_midfield.text = event.homeLineupMidfield?.replace("; ", ", ") ?: "-"
        tv_home_forward.text = event.homeLineupForward?.replace("; ", ", ") ?: "-"
        tv_home_substitute.text = event.homeLineupSubstitutes?.replace("; ", ", ") ?: "-"

        tv_away_name.text = event.awayName
        tv_away_score.text = event.awayScore
        tv_away_goals.text = event.awayGoals ?: "-"
        tv_away_goalkeeper.text = event.awayLineupGoalKeeper?.replace("; ", ", ") ?: "-"
        tv_away_defense.text = event.awayLineupDefense?.replace("; ", ", ") ?: "-"
        tv_away_midfield.text = event.awayLineupMidfield?.replace("; ", ", ") ?: "-"
        tv_away_forward.text = event.awayLineupForward?.replace("; ", ", ") ?: "-"
        tv_away_substitute.text = event.awayLineupSubstitutes?.replace("; ", ", ") ?: "-"

        launch(UI) {
            presenter.fetchHomeBadge(event.homeId)
            presenter.fetchAwayBadge(event.awayId)
        }
    }

    override fun showMenuAddToFavorite() {
        addedToFavorite = false
        invalidateOptionsMenu()
    }

    override fun showMenuAddedToFavorite() {
        addedToFavorite = true
        invalidateOptionsMenu()
    }

    override fun showHomeBadge(badgeUrl: String) {
        Picasso.get().load(badgeUrl).into(iv_home_badge)
    }

    override fun showAwayBadge(badgeUrl: String) {
        Picasso.get().load(badgeUrl).into(iv_away_badge)
    }

    override fun showLoading() {
        clp_detail?.show()
        container_detail?.visibility = View.GONE
    }

    override fun hideLoading() {
        clp_detail?.hide()
        container_detail?.visibility = View.VISIBLE
    }
}
