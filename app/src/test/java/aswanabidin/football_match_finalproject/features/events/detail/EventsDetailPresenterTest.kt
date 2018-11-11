package aswanabidin.football_match_finalproject.features.events.detail

import aswanabidin.football_match_finalproject.database.IDatabaseRepository
import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.model.event.EventResponse
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

class EventsDetailPresenterTest {
    private val EVENT_ID = "1234"

    private lateinit var presenter: EventsDetailPresenter

    @Mock
    private lateinit var view: EventsContracts.DetailView

    @Mock
    private lateinit var api: IApiRepository

    @Mock
    private lateinit var database: IDatabaseRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventsDetailPresenter(view, api, database)
    }

    @Test
    fun testEmpty_ShouldNot_ShowEventDetail() {
        launch (Unconfined) {
            Mockito.`when`(api.detailEvent(EVENT_ID)).thenReturn(bg { EventResponse(listOf(), listOf()) })

            presenter.fetchEventDetail(EVENT_ID)

            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun testNotEmpty_Should_ShowEventDetail() {
        launch (Unconfined) {
            val event = EventModel("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
            Mockito.`when`(api.detailEvent(EVENT_ID)).thenReturn(bg { EventResponse(listOf(event), listOf(event)) })

            presenter.fetchEventDetail(EVENT_ID)

            Mockito.verify(view).showEventDetail(event)
            Mockito.verify(view).hideLoading()
        }
    }
}