package aswanabidin.football_match_finalproject.features.events.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.adapter.EventsAdapter
import aswanabidin.football_match_finalproject.features.events.EventsContracts
import aswanabidin.football_match_finalproject.model.event.EventModel
import aswanabidin.football_match_finalproject.network.ApiRepositoryImpl
import aswanabidin.football_match_finalproject.router.openEventsDetail
import kotlinx.android.synthetic.main.layout_base_search.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class EventsSearchActivity : AppCompatActivity(), EventsContracts.SearchView {

    private val api by lazy { ApiRepositoryImpl() }
    private val presenter by lazy { EventsSearchPresenter(this, api) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_base_search)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        hideLoading()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_item, menu)

        val menuSearch = menu?.findItem(R.id.action_search)
        val searchView = menuSearch?.actionView as SearchView?
        searchView?.let {
            with(it) {
                isIconified = false
                setOnCloseListener {
                    searchView.setQuery("", false)
                    true
                }
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean = false
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return newText?.let {
                            launch(UI) { presenter.fetchSearchEvents(it) }
                            true
                        } ?: false
                    }
                })
            }
        }

        return true
    }

    override fun showEvents(events: List<EventModel>) {
        rv_data?.let {
            with(rv_data) {
                layoutManager = LinearLayoutManager(context)
                adapter = EventsAdapter(events) { event ->
                    openEventsDetail(event.id)
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