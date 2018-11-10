package aswanabidin.football_match_finalproject.features.favorites.events

import aswanabidin.football_match_finalproject.database.IDatabaseRepository
import aswanabidin.football_match_finalproject.features.favorites.FavoritesContracts

class FavoriteEventsPresenter(
    private val view: FavoritesContracts.EventsView,
    private val database: IDatabaseRepository
) : FavoritesContracts.EventsPresenter {

    override suspend fun fetchFavoriteEvents() {
        view.showLoading()

        val events = database.getFavoriteEvents()
        if (events.isNotEmpty()) {
            view.showEvents(events)
            view.hideLoading()
        } else {
            view.showPlaceholder()
        }
    }
}