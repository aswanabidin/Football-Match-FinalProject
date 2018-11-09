package aswanabidin.football_match_finalproject.model.team

import com.google.gson.annotations.SerializedName

data class TeamResponse(@SerializedName("teams") val teams: List<TeamModel>?)