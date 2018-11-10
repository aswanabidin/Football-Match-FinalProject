package aswanabidin.football_match_finalproject.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import aswanabidin.football_match_finalproject.R
import aswanabidin.football_match_finalproject.model.player.PlayerModel
import aswanabidin.football_match_finalproject.model.player.PlayerResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_player.view.*

class PlayersAdapter(
    private val mItems: List<PlayerModel>,
    private val mOnClick: (player: PlayerModel) -> Unit
) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerViewHolder(inflater.inflate(R.layout.item_list_player, parent, false))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(mItems[position], mOnClick)
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: PlayerModel, onClick: (player: PlayerModel) -> Unit) {
            with(itemView) {
                Picasso.get().load(player.avatarUrl).into(iv_icon)
                tv_name.text = player.name
                tv_position.text = player.position

                setOnClickListener { onClick(player) }
            }
        }

    }


}



