package aswanabidin.football_match_finalproject.features.favorites

import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.model.team.TeamModel

class FavoritesContracts {

    interface EventsView {

        fun showEvents(events: List<EventModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }

    interface EventsPresenter {

        suspend fun fetchFavoriteEvents()
    }

    interface TeamsView {

        fun showFavoriteTeams(teams: List<TeamModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }

    interface TeamsPresenter {

        suspend fun fetchFavoriteTeams()
    }

}

