package aswanabidin.football_match_finalproject.features.players.players

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.adapter.PlayersAdapter
import aswanabidin.football_match_finalproject.features.players.PlayersContracts
import aswanabidin.football_match_finalproject.model.player.PlayerModel
import aswanabidin.football_match_finalproject.network.ApiRepositoryImpl
import aswanabidin.football_match_finalproject.router.openPlayersDetail
import kotlinx.android.synthetic.main.layout_base_lists.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class PlayersFragment : Fragment(), PlayersContracts.PlayersView {

    private val api by lazy { ApiRepositoryImpl() }
    private val presenter by lazy { PlayersPresenter(this, api) }

    private var mTeamId: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mTeamId?.let {
            launch(UI) { presenter.fetchPlayers(it) }
        }
    }

    fun setTeamId(teamId: String) {
        mTeamId = teamId
    }

    override fun showPlayers(players: List<PlayerModel>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = LinearLayoutManager(context)
                adapter = PlayersAdapter(players) { player ->
                    activity?.openPlayersDetail(player.id)
                }
            }
        }
    }

    override fun showLoading() {
        rv_data?.visibility = View.GONE
        clp_data?.show()
        hidePlaceholder()
    }

    override fun hideLoading() {
        rv_data?.visibility = View.VISIBLE
        clp_data?.hide()
        hidePlaceholder()
    }

    override fun showPlaceholder() {
        rv_data?.visibility = View.GONE
        clp_data?.hide()
        ph_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        ph_data?.visibility = View.GONE
    }

}

