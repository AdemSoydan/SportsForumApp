package com.example.sportsforumapp.TitleDetailFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsforumapp.ApiUtils.ForumApiUtil
import com.example.sportsforumapp.EntryAdapter
import com.example.sportsforumapp.Models.Title
import com.example.sportsforumapp.Models.TitleResponse
import com.example.sportsforumapp.R
import com.example.sportsforumapp.SportsApi.TitleService
import com.example.sportsforumapp.TitleAdapter
import com.example.sportsforumapp.TitleObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class titleDetailFragment : Fragment() {

    lateinit var titleNameText : TextView
    lateinit var entriesRv : RecyclerView
    lateinit var entryAdapter : EntryAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_title_detail, container, false)
        val title = TitleObject.getTitle()
        bindings(view)
        titleNameText.text = title.titleName
        entryAdapter.addEntry(title.entries!!)
        println("--")
        return view
    }

    private fun bindings(view : View){
        titleNameText = view.findViewById(R.id.titleTitleText)
        entriesRv = view.findViewById(R.id.entryRv)
        entriesRv = view.findViewById(R.id.entryRv)
        entryAdapter = EntryAdapter()
        entriesRv.setHasFixedSize(true)
        entriesRv.setLayoutManager(LinearLayoutManager(context));
        entriesRv.adapter = entryAdapter

    }

}