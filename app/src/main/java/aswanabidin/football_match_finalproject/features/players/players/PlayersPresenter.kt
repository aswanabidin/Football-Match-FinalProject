package aswanabidin.football_match_finalproject.features.players.players

import aswanabidin.football_match_finalproject.features.players.PlayersContracts
import aswanabidin.football_match_finalproject.network.IApiRepository

class PlayersPresenter(
    private val view: PlayersContracts.PlayersView,
    private val api: IApiRepository
) : PlayersContracts.PlayersPresenter {

    override suspend fun fetchPlayers(teamId: String) {
        view.showLoading()

        val players = api.players(teamId).await().players
        if (players != null && players.isNotEmpty()) {
            view.showPlayers(players)
            view.hideLoading()
        } else {
            view.showPlaceholder()
        }
    }

}