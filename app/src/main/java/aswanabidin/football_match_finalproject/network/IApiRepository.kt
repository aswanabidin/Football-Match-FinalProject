package aswanabidin.football_match_finalproject.network

import aswanabidin.football_match_finalproject.model.event.EventResponse
import aswanabidin.football_match_finalproject.model.league.LeagueResponse
import aswanabidin.football_match_finalproject.model.player.PlayerResponse
import aswanabidin.football_match_finalproject.model.team.TeamResponse
import kotlinx.coroutines.experimental.Deferred

interface IApiRepository {

    fun leagues(type: String = "soccer"): Deferred<LeagueResponse>

    fun pastEvents(leagueId: String): Deferred<EventResponse>
    fun nextEvents(leagueId: String): Deferred<EventResponse>
    fun searchEvents(keywords: String): Deferred<EventResponse>
    fun detailEvent(eventId: String): Deferred<EventResponse>

    fun teams(leagueName: String): Deferred<TeamResponse>
    fun searchTeams(keywords: String): Deferred<TeamResponse>
    fun detailTeam(teamId: String): Deferred<TeamResponse>

    fun players(teamId: String): Deferred<PlayerResponse>
    fun detailPlayer(playerId: String): Deferred<PlayerResponse>

}