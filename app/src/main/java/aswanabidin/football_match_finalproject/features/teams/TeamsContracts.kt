package aswanabidin.football_match_finalproject.features.teams

import aswanabidin.football_match_finalproject.model.league.LeagueModel
import aswanabidin.football_match_finalproject.model.team.TeamModel

class TeamsContracts {

    interface TeamsView {

        fun showLeagues(leagues: List<LeagueModel>)

        fun showTeams(teams: List<TeamModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }

    interface TeamsPresenter {

        suspend fun fetchLeagues()

        suspend fun fetchTeams(leagueName: String)
    }

    interface DetailView {

        fun showTeamDetail(team: TeamModel)

        fun showMenuAddToFavorite()

        fun showMenuAddedToFavorite()

        fun showLoading()

        fun hideLoading()

    }

    interface DetailPresenter {

        suspend fun fetchTeamDetail(teamId: String)

        fun addToFavorite()

        fun removeFromFavorite()
    }

    interface SearchView {

        fun showTeams(teams: List<TeamModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }

    interface SearchPresenter {

        suspend fun fetchSearchTeams(keywords: String)

    }
}