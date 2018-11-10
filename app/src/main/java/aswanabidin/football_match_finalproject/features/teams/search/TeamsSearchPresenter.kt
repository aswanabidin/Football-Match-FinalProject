package aswanabidin.football_match_finalproject.features.teams.search

import aswanabidin.football_match_finalproject.features.teams.TeamsContracts
import aswanabidin.football_match_finalproject.model.team.TeamResponse
import aswanabidin.football_match_finalproject.network.IApiRepository
import kotlinx.coroutines.experimental.Deferred

class TeamsSearchPresenter(
    private val view: TeamsContracts.SearchView,
    private val api: IApiRepository
) : TeamsContracts.SearchPresenter {

    private var mDeferredSearch: Deferred<TeamResponse>? = null

    override suspend fun fetchSearchTeams(keywords: String) {
        if (keywords.isBlank()) return

        view.showLoading()

        mDeferredSearch?.cancel(IllegalStateException())
        mDeferredSearch = api.searchTeams(keywords)

        try {
            val teams = mDeferredSearch?.await()?.teams

            if (teams != null && teams.isNotEmpty()) {
                view.showTeams(teams)
                view.hideLoading()
            } else {
                view.showPlaceholder()
            }
        } catch (e: IllegalStateException) {
        }
    }

}