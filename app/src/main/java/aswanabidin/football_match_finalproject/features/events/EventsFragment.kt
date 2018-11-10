package aswanabidin.football_match_finalproject.features.events

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.features.events.last.EventsLastFragment
import aswanabidin.football_match_finalproject.features.events.next.EventsNextFragment
import aswanabidin.football_match_finalproject.router.openEventsSearch
import kotlinx.android.synthetic.main.fragment_event.*

class EventFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view_pager_events.adapter = EventsPagerAdapter(childFragmentManager)
        tab_events.setupWithViewPager(view_pager_events)

        with(toolbar) {
            inflateMenu(R.menu.search_item)

            val searchMenu = menu.findItem(R.id.action_search)
            searchMenu.actionView = null
            searchMenu.setOnMenuItemClickListener {
                activity?.openEventsSearch()
                true
            }
        }
    }

}

internal class EventsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val mPages by lazy { listOf(EventsNextFragment(), EventsLastFragment()) }
    private val mTitles by lazy { listOf("Next", "Last") }

    override fun getItem(position: Int): Fragment {
        return mPages[position]
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

}