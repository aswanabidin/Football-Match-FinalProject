package aswanabidin.football_match_finalproject.model.player

import com.google.gson.annotations.SerializedName

data class PlayerModel(
    @SerializedName("idPlayer") val id: String,
    @SerializedName("strPlayer") val name: String,
    @SerializedName("strPosition") val position: String,
    @SerializedName("strDescriptionEN") val overview: String,
    @SerializedName("strHeight") val height: String,
    @SerializedName("strWeight") val weight: String,
    @SerializedName("strCutout") val avatarUrl: String,
    @SerializedName("strThumb") val bannerUrl: String
)