package aswanabidin.football_match_finalproject.features.favorites.teams

import aswanabidin.football_match_finalproject.database.IDatabaseRepository
import aswanabidin.football_match_finalproject.features.favorites.FavoritesContracts

class FavoriteTeamsPresenter(
    private val view: FavoritesContracts.TeamsView,
    private val database: IDatabaseRepository
) : FavoritesContracts.TeamsPresenter {

    override suspend fun fetchFavoriteTeams() {
        view.showLoading()

        val teams = database.getFavoriteTeams()
        if (teams.isNotEmpty()) {
            view.showFavoriteTeams(teams)
            view.hideLoading()
        } else {
            view.showPlaceholder()
        }
    }
}