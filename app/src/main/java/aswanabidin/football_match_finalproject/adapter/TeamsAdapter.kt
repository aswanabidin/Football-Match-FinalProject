package aswanabidin.football_match_finalproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.model.team.TeamModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_team.view.*

class TeamsAdapter(
    private val mItems: List<TeamModel>,
    private val mOnClick: (team: TeamModel) -> Unit
) : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TeamsViewHolder(inflater.inflate(R.layout.item_list_team, parent, false))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bind(mItems[position], mOnClick)
    }

    inner class TeamsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(team: TeamModel, onClick: (team: TeamModel) -> Unit) {
            with(itemView) {
                Picasso.get().load(team.badgeUrl).into(iv_icon)
                tv_name.text = team.name

                setOnClickListener { onClick(team) }
            }
        }

    }

}