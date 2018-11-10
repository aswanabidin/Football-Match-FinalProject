package aswanabidin.football_match_finalproject.features.teams.teams

import aswanabidin.football_match_finalproject.features.teams.TeamsContracts
import aswanabidin.football_match_finalproject.network.IApiRepository

class TeamsPresenter(
    private val view: TeamsContracts.TeamsView,
    private val api: IApiRepository
) : TeamsContracts.TeamsPresenter {

    override suspend fun fetchLeagues() {
        view.showLoading()
        val leagues = api.leagues().await().leagues
        if (leagues != null && leagues.isNotEmpty()) {
            view.showLeagues(leagues)
            view.hideLoading()

            fetchTeams(leagues[0].name)
        } else {
            view.showPlaceholder()
        }
    }

    override suspend fun fetchTeams(leagueName: String) {
        view.showLoading()
        val teams = api.teams(leagueName).await().teams
        if (teams != null && teams.isNotEmpty()) {
            view.showTeams(teams)
            view.hideLoading()
        } else {
            view.showPlaceholder()
        }
    }
}