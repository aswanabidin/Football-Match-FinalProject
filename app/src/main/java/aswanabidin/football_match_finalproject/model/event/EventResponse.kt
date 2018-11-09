package aswanabidin.football_match_finalproject.model.event

import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("events") val events: List<EventModel>?,
    @SerializedName("event") val eventsSearch: List<EventModel>?
)