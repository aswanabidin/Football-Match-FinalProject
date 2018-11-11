package aswanabidin.football_match_finalproject.features.events.next

import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.model.event.EventResponse
import aswanabidin.football_match_finalproject.model.league.LeagueModel
import aswanabidin.football_match_finalproject.model.league.LeagueResponse
import aswanabidin.football_match_finalproject.network.IApiRepository
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.bg
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventsNextPresenterTest {


    private val LEAGUE_TYPE = "Soccer"
    private val LEAGUE_ID = "1234"

    private lateinit var presenter: EventsNextPresenter

    @Mock
    private lateinit var view: EventsContracts.NextView

    @Mock
    private lateinit var api: IApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventsNextPresenter(view, api)
    }

    @Test
    fun testEmptyLeagues_shouldNot_showLeagues() {
        launch(Unconfined) {
            Mockito.`when`(api.leagues(LEAGUE_TYPE)).thenReturn(bg { LeagueResponse(listOf()) })

            presenter.fetchLeagues()

            Mockito.verify(view).showPlaceholder()
        }
    }

    @Test
    fun testAvailableLeagues_should_showLeagues() {
        launch(Unconfined) {
            val leagues = listOf(LeagueModel("", ""))
            Mockito.`when`(api.leagues(LEAGUE_TYPE)).thenReturn(bg { LeagueResponse(leagues) })

            presenter.fetchLeagues()

            Mockito.verify(view).showLeagues(leagues)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testEmptyEvents_shouldNot_showEvents() {
        launch(Unconfined) {
            Mockito.`when`(api.nextEvents(LEAGUE_ID)).thenReturn(bg { EventResponse(listOf(), listOf()) })

            presenter.fetchEvents(LEAGUE_ID)

            Mockito.verify(view).showPlaceholder()
        }
    }

    @Test
    fun testAvailableEvents_should_showEvents() {
        launch(Unconfined) {
            val events = listOf(EventModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""))
            Mockito.`when`(api.nextEvents(LEAGUE_ID)).thenReturn(bg { EventResponse(events, events) })

            presenter.fetchEvents(LEAGUE_ID)

            Mockito.verify(view).showEvents(events)
            Mockito.verify(view).hideLoading()
        }
    }
}