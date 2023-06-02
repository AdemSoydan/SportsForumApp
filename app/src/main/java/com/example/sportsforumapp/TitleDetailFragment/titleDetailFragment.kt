package com.example.sportsforumapp.TitleDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsforumapp.R
import com.example.sportsforumapp.TitleObject
import org.w3c.dom.Text

class titleDetailFragment : Fragment() {

    lateinit var titleNameText : TextView
    lateinit var entriesRv : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_title_detail, container, false)
        val title = TitleObject.getTitle()
        titleNameText = view.findViewById(R.id.titleTitleText)
        entriesRv = view.findViewById(R.id.entryRv)

        titleNameText.text = title.titleName

        println("--")
        return view
    }


}