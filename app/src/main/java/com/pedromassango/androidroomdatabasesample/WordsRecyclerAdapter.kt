package com.pedromassango.androidroomdatabasesample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class WordsRecyclerAdapter(private val data: ArrayList<Phrase>): RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_phrase, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount() = data.count()

    override fun onBindViewHolder(holder: MyViewHolder, index: Int) = holder.bindView(data[index])

    fun add(phrase: Phrase){
        data.add(phrase)
        notifyDataSetChanged()
    }

    fun add(phrases: ArrayList<Phrase>){
        data.addAll(phrases)
        notifyDataSetChanged()
    }
}