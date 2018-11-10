package aswanabidin.football_match_finalproject.features.players

import aswanabidin.football_match_finalproject.model.player.PlayerModel
import aswanabidin.football_match_finalproject.model.player.PlayerResponse

class PlayersContracts {

    interface PlayersView {

        fun showPlayers(players: List<PlayerModel>)

        fun showLoading()

        fun hideLoading()

        fun showPlaceholder()

        fun hidePlaceholder()

    }

    interface PlayersPresenter {

        suspend fun fetchPlayers(teamId: String)
    }

    interface DetailView {

        fun showPlayerDetail(player: PlayerModel)

        fun showLoading()

        fun hideLoading()

    }

    interface DetailPresenter {

        suspend fun fetchPlayerDetail(playerId: String);

    }
}