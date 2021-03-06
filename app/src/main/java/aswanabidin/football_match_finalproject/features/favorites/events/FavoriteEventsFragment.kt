package aswanabidin.football_match_finalproject.features.favorites.events

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.adapter.EventsAdapter
import aswanabidin.football_match_finalproject.database.DatabaseManager
import aswanabidin.football_match_finalproject.features.favorites.FavoritesContracts
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.router.openEventsDetail
import kotlinx.android.synthetic.main.layout_base_lists.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class FavoriteEventsFragment : Fragment(), FavoritesContracts.EventsView {

    private val mDb by lazy { DatabaseManager(context!!) }
    private val mPresenter by lazy { FavoriteEventsPresenter(this, mDb) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_base_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch(UI) { mPresenter.fetchFavoriteEvents() }
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
        ph_data?.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        ph_data?.visibility = View.GONE
    }

}
