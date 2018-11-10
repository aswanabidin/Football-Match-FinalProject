package aswanabidin.football_match_finalproject.features.events.next

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.adapter.EventsAdapter
import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.model.league.LeagueModel
import aswanabidin.football_match_finalproject.network.ApiRepositoryImpl
import aswanabidin.football_match_finalproject.router.openEventsDetail
import kotlinx.android.synthetic.main.layout_base_spinner.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class EventsNextFragment : Fragment(), EventsContracts.NextView {

    private val mApi by lazy { ApiRepositoryImpl() }
    private val mPresenter by lazy { EventsNextPresenter(this, mApi) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_spinner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch(UI) { mPresenter.fetchLeagues() }
    }

    override fun showEvents(events: List<EventModel>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = android.support.v7.widget.LinearLayoutManager(context)
                adapter = EventsAdapter(events) { event ->
                    activity?.openEventsDetail(event.id)
                }
            }
        }
    }

    override fun showLeagues(leagues: List<LeagueModel>) {
        sp_leagues?.let {
            with(sp_leagues) {
                adapter = android.widget.ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_spinner_dropdown_item,
                    android.R.id.text1,
                    leagues.map { it.name })

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        kotlinx.coroutines.experimental.launch(kotlinx.coroutines.experimental.android.UI) {
                            mPresenter.fetchEvents(
                                leagues[position].id
                            )
                        }
                    }
                }
            }
        }
    }

    override fun showLoading() {
        rv_data?.visibility = View.GONE
        clp_data?.show()
        hidePlaceholder()
    }

    override fun hideLoading() {
        rv_data?.visibility = View.VISIBLE
        clp_data?.hide()
        hidePlaceholder()
    }

    override fun showPlaceholder() {
        rv_data?.visibility = View.GONE
        clp_data?.hide()
        tv_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        tv_data?.visibility = View.GONE
    }
}
