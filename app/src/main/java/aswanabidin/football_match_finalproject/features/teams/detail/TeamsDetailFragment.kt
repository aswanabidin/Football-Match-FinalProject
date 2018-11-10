package aswanabidin.football_match_finalproject.features.teams.detail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import aswanabidin.football_match_finalproject.R
import kotlinx.android.synthetic.main.fragment_teams_detail.*

class TeamsDetailFragment : Fragment() {

    private lateinit var mOverview: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_overview.text = mOverview
    }

    fun setOverview(text: String) {
        mOverview = text
    }

}