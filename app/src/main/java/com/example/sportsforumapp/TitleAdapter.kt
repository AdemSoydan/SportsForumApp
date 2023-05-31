package com.example.sportsforumapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsforumapp.Models.TitleResponse
import com.example.sportsforumapp.databinding.TitleItemViewBinding

class TitleAdapter () : RecyclerView.Adapter<TitleAdapter.GameViewHolder>()
{
    var titles =  ArrayList<TitleResponse>()
    inner class GameViewHolder(val binding: TitleItemViewBinding) : RecyclerView.ViewHolder(binding.root)
    // if the user scrolled little bit and another item was recycled and new item needs to be viewed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        // it will crash if we wouldn't have set last parameter as false in rv
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TitleItemViewBinding.inflate(layoutInflater, parent, false)
        return GameViewHolder(binding)

    }
    // sets the text and other viewed stuff initially
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.apply {
            titleNameText.text = titles[position].titleName
            entryNumberText.text = titles[position].numberOfEntries.toString()
        }
    }

    fun addTitle(newTitles : List<TitleResponse>){
        titles.addAll(newTitles)
        notifyDataSetChanged()
    }

    // how many items we have currently in recyclerview,
    override fun getItemCount(): Int {
        return titles.size
    }

}