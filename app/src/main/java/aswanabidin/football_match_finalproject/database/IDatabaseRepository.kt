package aswanabidin.football_match_finalproject.database

import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.model.team.TeamModel

interface IDatabaseRepository {

    fun getFavoriteEvents(): List<EventModel>
    fun removeFromFavoriteEvent(eventId: String)
    fun addToFavoriteEvent(event: EventModel)
    fun isFavoriteEventExists(eventId: String): Boolean
    fun getFavoriteTeams(): List<TeamModel>
    fun removeFromFavoriteTeam(teamId: String)
    fun addToFavoriteTeam(team: TeamModel)
    fun isFavoriteTeamExists(teamId: String): Boolean

}