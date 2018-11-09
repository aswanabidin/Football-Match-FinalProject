package aswanabidin.football_match_finalproject.model.team

import com.google.gson.annotations.SerializedName

data class TeamModel(
    @SerializedName("idTeam") val id: String,
    @SerializedName("strTeam") val name: String,
    @SerializedName("intFormedYear") val formedYear: String,
    @SerializedName("strStadium") val stadium: String,
    @SerializedName("strTeamBadge") val badgeUrl: String,
    @SerializedName("strDescriptionEN") val overview: String?
)