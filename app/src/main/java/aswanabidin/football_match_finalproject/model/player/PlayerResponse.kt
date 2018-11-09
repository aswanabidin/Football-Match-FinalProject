package aswanabidin.football_match_finalproject.model.player

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("player") val players: List<PlayerModel>?,
    @SerializedName("players") val playersDetail: List<PlayerModel>?
)