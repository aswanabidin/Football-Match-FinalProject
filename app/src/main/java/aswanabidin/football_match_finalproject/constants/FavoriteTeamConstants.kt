package aswanabidin.football_match_finalproject.constants

data class FavoriteTeamConstants(
    val id: Long?,
    val teamId: String?,
    val teamName: String?,
    val teamBadge: String?
) {

    companion object {
        const val TABLE = "TABLE_FAVORITE_TEAM"
        const val ID = "_ID"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_BADGE = "TEAM_BADGE"
    }

}