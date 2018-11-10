package aswanabidin.football_match_finalproject.features.events

import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.model.event.EventResponse
import aswanabidin.football_match_finalproject.model.league.LeagueModel
import aswanabidin.football_match_finalproject.model.league.LeagueResponse

class EventsContracts {

    interface DetailView {

        fun showEventDetail(event: EventModel)

        fun showMenuAddToFavorite()

        fun showMenuAddedToFavorite()

        fun showHomeBadge(badgeUrl: String)

        fun showAwayBadge(badgeUrl: String)

        fun showLoading()

        fun hideLoading()

    }


    interface DetailPresenter {

        suspend fun fetchEventDetail(eventId: String)
        suspend fun fetchHomeBadge(teamId: String)
        suspend fun fetchAwayBadge(teamId: String)
        fun addToFavorite()
        fun removeFromFavorite()
        fun invalidateFavorite(eventId: String)

    }


    interface NextView {
        fun showEvents(events: List<EventModel>)

        fun showLeagues(leagues: List<LeagueModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }


    interface NextPresenter {

        suspend fun fetchLeagues()
        suspend fun fetchEvents(leagueId: String)

    }


    interface LastView {

        fun showEvents(events: List<EventModel>)

        fun showLeagues(leagues: List<LeagueModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }


    interface LastPresenter {

        suspend fun fetchLeagues()
        suspend fun fetchEvents(leagueId: String)

    }

    interface SearchView {

        fun showEvents(events: List<EventModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }

    interface SearchPresenter {

        suspend fun fetchSearchEvents(keywords: String)

    }
}