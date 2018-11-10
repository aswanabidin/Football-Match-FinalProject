package aswanabidin.football_match_finalproject.features.events.search

import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.model.event.EventResponse
import aswanabidin.football_match_finalproject.network.IApiRepository
import kotlinx.coroutines.experimental.Deferred

class EventsSearchPresenter(
    private val view: EventsContracts.SearchView,
    private val api: IApiRepository
) : EventsContracts.SearchPresenter {

    private var deferredSearch: Deferred<EventResponse>? = null

    override suspend fun fetchSearchEvents(keywords: String) {
        if (keywords.isBlank()) return

        view.showLoading()

        deferredSearch?.cancel(IllegalStateException())
        deferredSearch = api.searchEvents(keywords)

        try {
            val events = deferredSearch?.await()?.eventsSearch

            if (events != null && events.isNotEmpty()) {
                view.showEvents(events.filter { it.sport == "Soccer" })
                view.hideLoading()
            } else {
                view.showPlaceholder()
            }
        } catch (e: IllegalStateException) {
        }
    }

}