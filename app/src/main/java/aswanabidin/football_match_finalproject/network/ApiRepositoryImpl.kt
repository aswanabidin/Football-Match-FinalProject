package aswanabidin.football_match_finalproject.network

import android.accounts.NetworkErrorException
import aswanabidin.football_match_finalproject.model.event.EventResponse
import aswanabidin.football_match_finalproject.model.league.LeagueResponse
import aswanabidin.football_match_finalproject.model.player.PlayerResponse
import aswanabidin.football_match_finalproject.model.team.TeamResponse
import com.androidnetworking.AndroidNetworking
import kotlinx.coroutines.experimental.Deferred
import org.jetbrains.anko.coroutines.experimental.bg

class ApiRepositoryImpl : IApiRepository {

    private fun req(url: String, type: Class<*>, vararg pairs: Pair<String, String>): Deferred<Any> {
        return bg {
            val response = AndroidNetworking
                .get(url)
                .apply { pairs.forEach { addQueryParameter(it.first, it.second) } }
                .build()
                .executeForObject(type)

            if (!response.isSuccess) {
                throw NetworkErrorException(response.error.localizedMessage)
            }

            response.result
        }
    }

    override fun leagues(type: String): Deferred<LeagueResponse> = req(
        UrlFactory.LEAGUES,
        LeagueResponse::class.java,
        "s" to type
    ) as Deferred<LeagueResponse>


    override fun pastEvents(leagueId: String): Deferred<EventResponse> = req(
        UrlFactory.EVENTS_PAST,
        EventResponse::class.java,
        "id" to leagueId
    ) as Deferred<EventResponse>


    override fun nextEvents(leagueId: String): Deferred<EventResponse> = req(
        UrlFactory.EVENTS_NEXT,
        EventResponse::class.java,
        "id" to leagueId
    ) as Deferred<EventResponse>

    override fun searchEvents(keywords: String): Deferred<EventResponse> {
        return req(
            UrlFactory.EVENTS_SEARCH,
            EventResponse::class.java,
            "e" to keywords
        ) as Deferred<EventResponse>
    }

    override fun detailEvent(eventId: String): Deferred<EventResponse> = req(
        UrlFactory.EVENTS_DETAIL,
        EventResponse::class.java,
        "id" to eventId
    ) as Deferred<EventResponse>

    override fun teams(leagueName: String): Deferred<TeamResponse> = req(
        UrlFactory.TEAMS,
        TeamResponse::class.java,
        "l" to leagueName
    ) as Deferred<TeamResponse>

    override fun searchTeams(keywords: String): Deferred<TeamResponse> = req(
        UrlFactory.TEAMS_SEARCH,
        TeamResponse::class.java,
        "t" to keywords
    ) as Deferred<TeamResponse>

    override fun detailTeam(teamId: String): Deferred<TeamResponse> = req(
        UrlFactory.TEAMS_DETAIL,
        TeamResponse::class.java,
        "id" to teamId
    ) as Deferred<TeamResponse>

    override fun players(teamId: String): Deferred<PlayerResponse> = req(
        UrlFactory.PLAYERS,
        PlayerResponse::class.java,
        "id" to teamId
    ) as Deferred<PlayerResponse>

    override fun detailPlayer(playerId: String): Deferred<PlayerResponse> = req(
        UrlFactory.PLAYERS_DETAIL,
        PlayerResponse::class.java,
        "id" to playerId
    ) as Deferred<PlayerResponse>


}

