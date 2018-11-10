package aswanabidin.football_match_finalproject.features.events.last

import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.network.IApiRepository

class EventsLastPresenter(
    private val view: EventsContracts.LastView,
    private val api: IApiRepository
) : EventsContracts.LastPresenter {

    override suspend fun fetchLeagues() {
        view.showLoading()

        val leagues = api.leagues().await().leagues
        if (leagues != null && leagues.isNotEmpty()) {
            view.showLeagues(leagues)
            view.hideLoading()

            fetchEvents(leagues[0].id)
        } else {
            view.showPlaceholder()
        }
    }

    override suspend fun fetchEvents(leagueId: String) {
        view.showLoading()

        val events = api.pastEvents(leagueId).await().events
        if (events != null && events.isNotEmpty()) {
            view.showEvents(events)
            view.hideLoading()
        } else {
            view.showPlaceholder()
        }
    }

}