package aswanabidin.football_match_finalproject.model.league

import com.google.gson.annotations.SerializedName

data class LeagueModel(
    @SerializedName("idLeague") val id: String,
    @SerializedName("strLeague") val name: String
)