package aswanabidin.football_match_finalproject.database

import android.content.Context
import aswanabidin.football_match_finalproject.constants.FavoriteEventConstants
import aswanabidin.football_match_finalproject.constants.FavoriteTeamConstants
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.model.team.TeamModel
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DatabaseManager(private val mContext: Context) : IDatabaseRepository {

    override fun getFavoriteEvents(): List<EventModel> {
        return mContext.database.use {
            select(FavoriteEventConstants.TABLE).parseList(classParser<FavoriteEventConstants>()).map {
                EventModel(
                    it.eventId ?: "",
                    it.homeName ?: "-",
                    it.awayName ?: "-",
                    it.homeScore ?: "-",
                    it.awayScore ?: "-",
                    "",
                    it.date ?: "-",
                    it.time ?: "-",
                    "", "", "", "",
                    "", "", "", "",
                    "", "", "", "",
                    "", "", "Soccer"
                )
            }
        }
    }

    override fun addToFavoriteEvent(event: EventModel) {
        mContext.database.use {
            insert(
                FavoriteEventConstants.TABLE,
                FavoriteEventConstants.EVENT_ID to event.id,
                FavoriteEventConstants.HOME_NAME to event.homeName,
                FavoriteEventConstants.HOME_SCORE to event.homeScore,
                FavoriteEventConstants.AWAY_NAME to event.awayName,
                FavoriteEventConstants.AWAY_SCORE to event.awayScore,
                FavoriteEventConstants.DATE to event.date,
                FavoriteEventConstants.TIME to event.time
            )
        }
    }

    override fun removeFromFavoriteEvent(eventId: String) {
        mContext.database.use {
            delete(FavoriteEventConstants.TABLE, "${FavoriteEventConstants.EVENT_ID} = {eventId}", "eventId" to eventId)
        }
    }

    override fun isFavoriteEventExists(eventId: String): Boolean {
        return mContext.database.use {
            val event = select(FavoriteEventConstants.TABLE)
                .whereArgs("${FavoriteEventConstants.EVENT_ID} = {eventId}", "eventId" to eventId)
                .parseOpt(classParser<FavoriteEventConstants>())

            event?.let { true } ?: false
        }
    }

    override fun getFavoriteTeams(): List<TeamModel> {
        return mContext.database.use {
            select(FavoriteTeamConstants.TABLE).parseList(classParser<FavoriteTeamConstants>()).map {
                TeamModel(it.teamId ?: "", it.teamName ?: "-", "", "", it.teamBadge ?: "", "")
            }
        }
    }

    override fun addToFavoriteTeam(team: TeamModel) {
        mContext.database.use {
            insert(
                FavoriteTeamConstants.TABLE,
                FavoriteTeamConstants.TEAM_ID to team.id,
                FavoriteTeamConstants.TEAM_NAME to team.name,
                FavoriteTeamConstants.TEAM_BADGE to team.badgeUrl
            )
        }
    }

    override fun removeFromFavoriteTeam(teamId: String) {
        mContext.database.use {
            delete(FavoriteTeamConstants.TABLE, "${FavoriteTeamConstants.TEAM_ID} = {teamId}", "teamId" to teamId)
        }
    }

    override fun isFavoriteTeamExists(teamId: String): Boolean {
        return mContext.database.use {
            val team = select(FavoriteTeamConstants.TABLE)
                .whereArgs("${FavoriteTeamConstants.TEAM_ID} = {teamId}", "teamId" to teamId)
                .parseOpt(classParser<FavoriteTeamConstants>())

            team?.let { true } ?: false
        }
    }

}