package com.yacine.listmovieapp.utils.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yacine.listmovieapp.network.PaginateResultState
import kotlinx.android.synthetic.main.list_item_lazy_load.view.*


class LazyLoadItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    fun bind(resultState: PaginateResultState?) {
        if (resultState != null) {
            when (resultState) {
                PaginateResultState.LOADING -> {
                    itemView.progress_bar_item.visibility = View.VISIBLE
                }
                PaginateResultState.ERROR -> {
                    itemView.progress_bar_item.visibility = View.GONE
                    itemView.txt_msg_error.visibility = View.VISIBLE
                    itemView.txt_msg_error.text = resultState.message
                }
                PaginateResultState.EOF -> {
                    itemView.progress_bar_item.visibility = View.GONE
                    itemView.txt_msg_error.visibility = View.VISIBLE
                    itemView.txt_msg_error.text = resultState.message
                }
                else -> {
                    itemView.progress_bar_item.visibility = View.GONE
                }
            }
        }
    }
}
