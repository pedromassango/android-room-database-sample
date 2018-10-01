package com.pedromassango.androidroomdatabasesample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pedromassango.androidroomdatabasesample.data.Phrase
import kotlinx.android.synthetic.main.row_phrase.view.*

/**
 * This is just the ViewHolder of the Phrase adapter below
 */
class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun bindView(phrase: Phrase){
        with(itemView){
            tv_item_phrase.text = phrase.phrase
        }
    }
}

/**
 * This is the Phrase adapter
 */
class WordsRecyclerAdapter: PagedListAdapter<Phrase, MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_phrase, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val phrase = getItem(position)

        // we need to check if the item is null, because the tem can be null
        when(phrase != null){
            true -> holder.bindView( phrase!!)
            false -> holder.bindView( Phrase(" "))
        }
    }

    companion object {


        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Phrase>(){
            override fun areItemsTheSame(oldItem: Phrase,
                                         newItem: Phrase): Boolean =
                    oldItem.phrase.equals( newItem.phrase, true)


            override fun areContentsTheSame(oldItem: Phrase,
                                            newItem: Phrase): Boolean =
                    oldItem.phrase == newItem.phrase
        }


    }
}