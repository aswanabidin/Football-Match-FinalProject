package aswanabidin.football_match_finalproject.features.teams.teams

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.adapter.TeamsAdapter
import aswanabidin.football_match_finalproject.features.teams.TeamsContracts
import aswanabidin.football_match_finalproject.model.league.LeagueModel
import aswanabidin.football_match_finalproject.model.team.TeamModel
import aswanabidin.football_match_finalproject.network.ApiRepositoryImpl
import aswanabidin.football_match_finalproject.router.openTeamsDetail
import aswanabidin.football_match_finalproject.router.openTeamsSearch
import kotlinx.android.synthetic.main.fragment_teams.*
import kotlinx.android.synthetic.main.layout_base_spinner.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class TeamsFragment : Fragment(), TeamsContracts.TeamsView {

    private val mApi by lazy { ApiRepositoryImpl() }
    private val mPresenter by lazy { TeamsPresenter(this, mApi) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch(UI) { mPresenter.fetchLeagues() }

        with(toolbar) {
            inflateMenu(R.menu.search_item)

            val searchMenu = menu.findItem(R.id.action_search)
            searchMenu.actionView = null
            searchMenu.setOnMenuItemClickListener {
                activity?.openTeamsSearch()
                true
            }
        }
    }

    override fun showLeagues(leagues: List<LeagueModel>) {
        sp_leagues?.let {
            with(sp_leagues) {
                adapter = android.widget.ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_spinner_dropdown_item,
                    android.R.id.text1,
                    leagues.map { it.name })
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        kotlinx.coroutines.experimental.launch(kotlinx.coroutines.experimental.android.UI) {
                            mPresenter.fetchTeams(
                                leagues[position].name
                            )
                        }
                    }
                }
            }
        }
    }

    override fun showTeams(teams: List<TeamModel>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = android.support.v7.widget.LinearLayoutManager(context)
                adapter = TeamsAdapter(teams) { team ->
                    activity?.openTeamsDetail(team.id)
                }
            }
        }
    }

    override fun showLoading() {
        rv_data?.visibility = View.GONE
        clp_data?.visibility = View.VISIBLE
        hidePlaceholder()
    }

    override fun hideLoading() {
        rv_data?.visibility = View.VISIBLE
        clp_data?.visibility = View.GONE
        hidePlaceholder()
    }

    override fun showPlaceholder() {
        rv_data?.visibility = View.GONE
        clp_data?.visibility = View.GONE
        tv_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        tv_data?.visibility = View.GONE
    }
}
