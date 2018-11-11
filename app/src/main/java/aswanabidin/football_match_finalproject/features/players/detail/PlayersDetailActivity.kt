package aswanabidin.football_match_finalproject.features.players.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.features.players.PlayersContracts
import aswanabidin.football_match_finalproject.model.player.PlayerModel
import aswanabidin.football_match_finalproject.network.ApiRepositoryImpl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_players_detail.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class PlayersDetailActivity : AppCompatActivity(), PlayersContracts.DetailView{

    private val api by lazy { ApiRepositoryImpl() }
    private val presenter by lazy { PlayersDetailPresenter(this, api) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }


        launch(UI) {
            presenter.fetchPlayerDetail(intent?.getStringExtra("PLAYER_ID") ?: "")
        }
    }

    override fun showPlayerDetail(player: PlayerModel) {
        Picasso.get().load(player.bannerUrl).into(iv_banner)
        tv_position.text = player.position
        tv_weight.text = player.weight
        tv_height.text = player.height
        tv_overview.text = player.overview

        supportActionBar?.title = player.name
    }

    override fun showLoading() { }

    override fun hideLoading() { }
}
