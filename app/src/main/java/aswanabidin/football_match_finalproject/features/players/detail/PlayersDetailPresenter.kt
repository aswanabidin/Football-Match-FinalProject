package aswanabidin.football_match_finalproject.features.players.detail

import aswanabidin.football_match_finalproject.features.players.PlayersContracts
import aswanabidin.football_match_finalproject.network.IApiRepository

class PlayersDetailPresenter(
    private val view: PlayersContracts.DetailView,
    private val api: IApiRepository
) : PlayersContracts.DetailPresenter {

    override suspend fun fetchPlayerDetail(playerId: String) {
        view.showLoading()

        val player = api.detailPlayer(playerId).await().playersDetail?.get(0)
        player?.let { view.showPlayerDetail(it) }

        view.hideLoading()
    }

}