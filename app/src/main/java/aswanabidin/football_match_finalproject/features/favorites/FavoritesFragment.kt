package aswanabidin.football_match_finalproject.features.favorites

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
import aswanabidin.football_match_finalproject.features.favorites.events.FavoriteEventsFragment
import aswanabidin.football_match_finalproject.features.favorites.teams.FavoriteTeamsFragment
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vp_favorites.adapter = FavoritesPagerAdapter(childFragmentManager)
        tab_favorites.setupWithViewPager(vp_favorites)
    }

}

internal class FavoritesPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val mPages by lazy { listOf(FavoriteEventsFragment(), FavoriteTeamsFragment()) }
    private val mTitles by lazy { listOf("Matches", "Teams") }

    override fun getItem(position: Int): Fragment {
        return mPages[position]
    }

    override fun getCount(): Int {
        return mPages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

}