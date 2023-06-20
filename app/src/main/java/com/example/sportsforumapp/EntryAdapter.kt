package com.example.sportsforumapp

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsforumapp.ApiUtils.ForumApiUtil
import com.example.sportsforumapp.Models.Entry
import com.example.sportsforumapp.SportsApi.SportsApiService
import com.example.sportsforumapp.databinding.EntryItemViewBinding
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntryAdapter  : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {
    var entries =  ArrayList<Entry>()
    inner class EntryViewHolder(val binding: EntryItemViewBinding) : RecyclerView.ViewHolder(binding.root)
    // if the user scrolled little bit and another item was recycled and new item needs to be viewed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        // it will crash if we wouldn't have set last parameter as false in rv
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = EntryItemViewBinding.inflate(layoutInflater, parent, false)
        return EntryViewHolder(binding)
    }
    // sets the text and other viewed stuff initially
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.binding.apply {
            entryViewEntryText.text = entries[position].entryText
            entryViewUsernameText.text = entries[position].user?.userName
            noOfLikedText.text = entries[position].noOfLiked.toString()
            likebutton.setOnClickListener {
                val mLastPosition = holder.bindingAdapterPosition
                var firstLikeNumber : Int = entries[position].noOfLiked!!
                val forumApiUtil = ForumApiUtil()
                val retrofit = forumApiUtil.getRetrofit()
                val apiService = retrofit.create(SportsApiService::class.java)
                if(firstLikeNumber == noOfLikedText.text.toString().toInt()){
                    val call : Call<Int> = apiService.likeEntry(entries[mLastPosition].eId!!)
                    call.enqueue(object: Callback<Int> {
                        override fun onResponse(call: Call<Int>, response: Response<Int>) {
                            // başarılı bir şekilde cevap aldıysak
                            if(response.code() == 200){
                                firstLikeNumber = response.body()!!
                                (it as MaterialButton).setIconTintResource(R.color.entryLikedText);
                                noOfLikedText.text = firstLikeNumber.toString()
                            }
                            else{
                                Log.e("API",response.code().toString())
                            }
                        }
                        override fun onFailure(call: Call<Int>, t: Throwable) {
                            Log.e("- ","-")
                        }

                    })
                }
                else if(firstLikeNumber == noOfLikedText.text.toString().toInt() - 1){
                    val call : Call<Int> = apiService.unlikeEntry(entries[mLastPosition].eId!!)
                    call.enqueue(object: Callback<Int> {
                        override fun onResponse(call: Call<Int>, response: Response<Int>) {
                            // başarılı bir şekilde cevap aldıysak
                            if(response.code() == 200){
                                firstLikeNumber = response.body()!!
                                (it as MaterialButton).setIconTintResource(R.color.entryButonNormalColor);
                                noOfLikedText.text = firstLikeNumber.toString()
                            }
                            else{
                                Log.e("RESPONSE CODE : ",response.code().toString())
                            }
                        }
                        override fun onFailure(call: Call<Int>, t: Throwable) {
                            Log.e("API","-")
                        }

                    })
                }
            }
        }
    }

    fun addAllEntries(newTitles : List<Entry>){
        entries.addAll(newTitles)
        notifyDataSetChanged()
    }

    fun addEntry(entry: Entry){
        entries.add(entry)
        notifyDataSetChanged()
    }
    // how many items we have currently in recyclerview,
    override fun getItemCount(): Int {
        return entries.size
    }

}