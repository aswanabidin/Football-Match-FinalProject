package aswanabidin.football_match_finalproject.features.teams.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.database.DatabaseManager
import aswanabidin.football_match_finalproject.features.players.players.PlayersFragment
import aswanabidin.football_match_finalproject.features.teams.TeamsContracts
import aswanabidin.football_match_finalproject.model.team.TeamModel
import aswanabidin.football_match_finalproject.network.ApiRepositoryImpl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_teams_detail.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.toast

class TeamsDetailActivity : AppCompatActivity(), TeamsContracts.DetailView {

    private val mApi by lazy { ApiRepositoryImpl() }
    private val mDb by lazy { DatabaseManager(applicationContext) }
    private val mPresenter by lazy { TeamsDetailPresenter(this, mApi, mDb) }

    private var mAddedToFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        launch(UI) {
            mPresenter.fetchTeamDetail(intent?.getStringExtra("TEAM_ID") ?: "")
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_item, menu)

        val favoriteMenu = menu?.findItem(R.id.action_favorite)
        if (mAddedToFavorite) {
            favoriteMenu?.setIcon(R.drawable.ic_added_to_favorite)
            favoriteMenu?.setOnMenuItemClickListener {
                launch(UI) { mPresenter.removeFromFavorite() }
                toast("Removed from favorite")
                true
            }
        } else {
            favoriteMenu?.setIcon(R.drawable.ic_add_to_favorite)
            favoriteMenu?.setOnMenuItemClickListener {
                launch(UI) { mPresenter.addToFavorite() }
                toast("Added to favorite")
                true
            }
        }

        return true
    }

    override fun showTeamDetail(team: TeamModel) {
        Picasso.get().load(team.badgeUrl).into(iv_icon)
        tv_name.text = team.name
        tv_year.text = team.formedYear
        tv_desc.text = team.stadium

        vp_team_detail.adapter = TeamPagerAdapter(team.overview ?: "No overview", team.id, supportFragmentManager)
        tab_team_detail.setupWithViewPager(vp_team_detail)
    }

    override fun showMenuAddToFavorite() {
        mAddedToFavorite = false
        invalidateOptionsMenu()
    }

    override fun showMenuAddedToFavorite() {
        mAddedToFavorite = true
        invalidateOptionsMenu()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

}

internal class TeamPagerAdapter(
    private val mOverview: String,
    private val mTeamId: String,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val mPages by lazy {
        listOf(
            TeamsDetailFragment().apply { setOverview(mOverview) },
            PlayersFragment().apply { setTeamId(mTeamId) })
    }

    private val mTitles by lazy { listOf("Overview", "Players") }

    override fun getItem(position: Int): Fragment {
        return mPages[position]
    }

    override fun getCount(): Int {
        return mPages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

}