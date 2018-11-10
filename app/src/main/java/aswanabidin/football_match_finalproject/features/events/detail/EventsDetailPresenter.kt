package aswanabidin.football_match_finalproject.features.events.detail

import aswanabidin.football_match_finalproject.database.IDatabaseRepository
import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.network.IApiRepository

class EventsDetailPresenter(
    private val view: EventsContracts.DetailView, private val api: IApiRepository,
    private val database: IDatabaseRepository
) : EventsContracts.DetailPresenter {

    private var eventDetail: EventModel? = null

    override suspend fun fetchEventDetail(eventId: String) {
        view.showLoading()
        eventDetail = api.detailEvent(eventId).await().events?.get(0)
        eventDetail?.let { view.showEventDetail(it) }
        invalidateFavorite(eventId)
        view.hideLoading()
    }

    override suspend fun fetchHomeBadge(teamId: String) {
        val teamDetail = api.detailTeam(teamId).await().teams?.get(0)
        teamDetail?.let { view.showHomeBadge(it.badgeUrl) }
    }

    override suspend fun fetchAwayBadge(teamId: String) {
        val teamDetail = api.detailTeam(teamId).await().teams?.get(0)
        teamDetail?.let { view.showAwayBadge(it.badgeUrl) }
    }

    override fun addToFavorite() {
        eventDetail?.let {
            database.addToFavoriteEvent(it)
            invalidateFavorite(it.id)
        }
    }

    override fun removeFromFavorite() {
        eventDetail?.let {
            database.removeFromFavoriteEvent(it.id)
            invalidateFavorite(it.id)
        }
    }

    override fun invalidateFavorite(eventId: String) {
        val isFavorited = database.isFavoriteEventExists(eventId)
        if (isFavorited) {
            view.showMenuAddedToFavorite()
        } else {
            view.showMenuAddToFavorite()
        }
    }

}