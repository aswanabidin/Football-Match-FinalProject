package aswanabidin.football_match_finalproject.network

object UrlFactory {

    private const val HOST = "https://www.thesportsdb.com"
    const val LEAGUES = "$HOST/api/v1/json/1/search_all_leagues.php"
    const val EVENTS_PAST = "$HOST/api/v1/json/1/eventspastleague.php"
    const val EVENTS_NEXT = "$HOST/api/v1/json/1/eventsnextleague.php"
    const val EVENTS_SEARCH = "$HOST/api/v1/json/1/searchevents.php"
    const val EVENTS_DETAIL = "$HOST/api/v1/json/1/lookupevent.php"
    const val TEAMS = "$HOST/api/v1/json/1/search_all_teams.php"
    const val TEAMS_SEARCH = "$HOST/api/v1/json/1/searchteams.php"
    const val TEAMS_DETAIL = "$HOST/api/v1/json/1/lookupteam.php"
    const val PLAYERS = "$HOST/api/v1/json/1/lookup_all_players.php"
    const val PLAYERS_DETAIL = "$HOST/api/v1/json/1/lookupplayer.php"

}