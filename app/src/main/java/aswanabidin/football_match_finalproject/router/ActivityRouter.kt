package aswanabidin.football_match_finalproject.router

import android.app.Activity
import android.content.Intent
import aswanabidin.football_match_finalproject.features.events.detail.EventsDetailActivity
import aswanabidin.football_match_finalproject.features.events.search.EventsSearchActivity
import aswanabidin.football_match_finalproject.features.players.detail.PlayersDetailActivity
import aswanabidin.football_match_finalproject.features.teams.detail.TeamsDetailActivity
import aswanabidin.football_match_finalproject.features.teams.search.TeamsSearchActivity

fun Activity.openEventsSearch() {
    startActivity(Intent(this, EventsSearchActivity::class.java))
}

fun Activity.openTeamsSearch() {
    startActivity(Intent(this, TeamsSearchActivity::class.java))
}

fun Activity.openEventsDetail(eventId: String) {
    startActivity(Intent(this, EventsDetailActivity::class.java).apply {
        putExtra("EVENT_ID", eventId)
    })
}

fun Activity.openTeamsDetail(teamId: String) {
    startActivity(Intent(this, TeamsDetailActivity::class.java).apply {
        putExtra("TEAM_ID", teamId)
    })
}

fun Activity.openPlayersDetail(playerId: String) {
    startActivity(Intent(this, PlayersDetailActivity::class.java).apply {
        putExtra("PLAYER_ID", playerId)
    })
}
