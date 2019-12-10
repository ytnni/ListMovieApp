package com.yacine.listmovieapp.ui.main.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.yacine.listmovieapp.BuildConfig
import com.yacine.listmovieapp.R
import com.yacine.listmovieapp.ext.textParseFromDate
import com.yacine.listmovieapp.model.movies.Movie
import com.yacine.listmovieapp.model.tvshows.TvShow
import com.yacine.listmovieapp.network.PaginateResultState
import com.yacine.listmovieapp.utils.recyclerview.LazyLoadItemViewHolder
import kotlinx.android.synthetic.main.list_item_tvshows.view.*



class TvShowAdapter(val clickListener: (TvShow?) -> Unit) : PagedListAdapter<TvShow, RecyclerView.ViewHolder>(TvShowsDiffUtils()) {
    val TVSHOW_VIEW_TYPE = 1
    val LAZY_LOAD_VIEW_TYPE = 2
    private var state: PaginateResultState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        return if (viewType == TVSHOW_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.list_item_tvshows, parent, false)
            TvShowItemViewHolder(view).apply {
                itemView.setOnClickListener { clickListener(getItem(adapterPosition)) }
            }
        } else {
            view = layoutInflater.inflate(R.layout.list_item_lazy_load, parent, false)
            LazyLoadItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TVSHOW_VIEW_TYPE) {
            (holder as TvShowItemViewHolder).bind(getItem(position))
        } else {
            (holder as LazyLoadItemViewHolder).bind(state)
        }
    }

    private fun isHavingExtraRow(): Boolean {
        return state != null && state != PaginateResultState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isHavingExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (isHavingExtraRow() && position == itemCount - 1) {
            LAZY_LOAD_VIEW_TYPE
        } else {
            TVSHOW_VIEW_TYPE
        }
    }

    class TvShowsDiffUtils : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }

    }

    class TvShowItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(tvshow: TvShow?) {
            itemView.txt_title_content.text = tvshow?.originalName
            itemView.txt_year_content.textParseFromDate(tvshow?.firstAirDate)
            itemView.img_poster.load(BuildConfig.BASE_POSTER_IMG_URL + tvshow?.posterPath)
        }
    }

    fun setState(state: PaginateResultState) {
        val prevState = this.state
        val hadExtraRow = isHavingExtraRow()
        this.state = state
        val hasExtraRow = isHavingExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {                             //hadExtraRow is true and hasExtraRow false
                notifyItemRemoved(super.getItemCount())    //remove the progressbar at the end
            } else {                                       //hasExtraRow is true and hadExtraRow false
                notifyItemInserted(super.getItemCount())   //add the progressbar at the end
            }
        } else if (hasExtraRow && prevState != state) { //hasExtraRow is true and hadExtraRow true and (NetworkState.ERROR or NetworkState.ENDOFLIST)
            notifyItemChanged(itemCount - 1)       //add the network message at the end
        }
    }
}