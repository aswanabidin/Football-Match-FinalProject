package aswanabidin.football_match_finalproject.features.teams.detail

import aswanabidin.football_match_finalproject.database.IDatabaseRepository
import aswanabidin.football_match_finalproject.features.teams.TeamsContracts
import aswanabidin.football_match_finalproject.model.team.TeamModel
import aswanabidin.football_match_finalproject.network.IApiRepository

class TeamsDetailPresenter(
    private val view: TeamsContracts.DetailView,
    private val api: IApiRepository,
    private val database: IDatabaseRepository
) : TeamsContracts.DetailPresenter {

    private var mTeamDetail: TeamModel? = null

    override suspend fun fetchTeamDetail(teamId: String) {
        view.showLoading()

        mTeamDetail = api.detailTeam(teamId).await().teams?.get(0)
        mTeamDetail?.let { view.showTeamDetail(it) }

        invalidateFavorite(teamId)

        view.hideLoading()
    }

    override fun addToFavorite() {
        mTeamDetail?.let {
            database.addToFavoriteTeam(it)
            invalidateFavorite(it.id)
        }
    }

    override fun removeFromFavorite() {
        mTeamDetail?.let {
            database.removeFromFavoriteTeam(it.id)
            invalidateFavorite(it.id)
        }
    }

    private fun invalidateFavorite(teamId: String) {
        val isFavorited = database.isFavoriteTeamExists(teamId)
        if (isFavorited) {
            view.showMenuAddedToFavorite()
        } else {
            view.showMenuAddToFavorite()
        }
    }

}