package com.example.sportsforumapp.TitleDetailFragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsforumapp.ApiUtils.ForumApiUtil
import com.example.sportsforumapp.EntryAdapter
import com.example.sportsforumapp.MainActivity
import com.example.sportsforumapp.Models.Entry
import com.example.sportsforumapp.Models.Title
import com.example.sportsforumapp.Models.TitleSaveRequest
import com.example.sportsforumapp.Models.User
import com.example.sportsforumapp.R
import com.example.sportsforumapp.SportsApi.SportsApiService
import com.example.sportsforumapp.SportsApi.TitleService
import com.example.sportsforumapp.TitleFragment.TitleFragment
import com.example.sportsforumapp.TitleObject
import com.example.sportsforumapp.UserObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class titleDetailFragment : Fragment() {

    lateinit var titleNameText : TextView
    lateinit var entriesRv : RecyclerView
    lateinit var entryAdapter : EntryAdapter
    lateinit var newEntryText : EditText
    lateinit var sendEntryButton : Button
    lateinit var title: Title
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_title_detail, container, false)
        title = TitleObject.getTitle()
        bindings(view)
        titleNameText.text = title.titleName
        entryAdapter.addAllEntries(title.entries!!)
        setSendEntryButttonClicked()
        setOnBackPressed()
        println("--")

        return view
    }

    private fun setOnBackPressed(){
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).replaceFragment(TitleFragment())
            }
        }
        (activity as MainActivity).onBackPressedDispatcher.addCallback(callback)
    }

    private fun bindings(view : View){
        newEntryText = view.findViewById(R.id.addEntryText)
        sendEntryButton = view.findViewById(R.id.sendEntryButton)
        titleNameText = view.findViewById(R.id.titleTitleText)
        entriesRv = view.findViewById(R.id.entryRv)
        entriesRv = view.findViewById(R.id.entryRv)
        entryAdapter = EntryAdapter()
        entriesRv.setHasFixedSize(true)
        entriesRv.setLayoutManager(LinearLayoutManager(context));
        entriesRv.adapter = entryAdapter
    }

    private fun setSendEntryButttonClicked(){
        val forumApiUtil = ForumApiUtil()
        val retrofit = forumApiUtil.getRetrofit()
        val apiService = retrofit.create(SportsApiService::class.java)
        sendEntryButton.setOnClickListener {
            val entry : Entry = Entry(-1,newEntryText.text.toString(),Title(title.tId,null,null),User(UserObject.getUser().userId,null,null,null,null),0)
            var call : Call<Entry> = apiService.saveEntry(entry)
            call.enqueue(object: Callback<Entry> {
                override fun onResponse(call: Call<Entry>, response: Response<Entry>) {
                    if(response.code() == 200){
                        val dbEntry : Entry = response.body()!!
                        entryAdapter.addEntry(Entry(dbEntry.eId,entry.entryText,title,UserObject.getUser(),dbEntry.noOfLiked))
                        newEntryText.setText("")
                        hideKeyboard()
                        Toast.makeText(context, "Entry Eklendi", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("API","-")
                    }
                }
                override fun onFailure(call: Call<Entry>, t: Throwable) {

                    Log.e("API","-")
                }

            })
        }
    }
    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}