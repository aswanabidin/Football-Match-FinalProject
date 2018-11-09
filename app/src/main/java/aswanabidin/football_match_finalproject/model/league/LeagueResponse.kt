package aswanabidin.football_match_finalproject.model.league

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("countrys")
    val leagues: List<LeagueModel>?
)