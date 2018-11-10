package aswanabidin.football_match_finalproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.model.event.EventModel
import kotlinx.android.synthetic.main.item_list_event.view.*

class EventsAdapter(
    private val mEvents: List<EventModel>,
    private val mOnClick: (event: EventModel) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EventsViewHolder(inflater.inflate(R.layout.item_list_event, parent, false))
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(mEvents[position], mOnClick)
    }

    inner class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: EventModel, onClick: (event: EventModel) -> Unit) {
            with(itemView) {
                tv_date.text = event.readableDate
                tv_time.text = event.readableTime
                tv_home_name.text = event.homeName
                tv_home_score.text = event.homeScore
                tv_away_name.text = event.awayName
                tv_away_score.text = event.awayScore

                setOnClickListener { onClick(event) }
            }
        }

    }

}