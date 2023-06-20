package com.example.sportsforumapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sportsforumapp.ApiUtils.ForumApiUtil
import com.example.sportsforumapp.Models.Entry
import com.example.sportsforumapp.Models.Title
import com.example.sportsforumapp.Models.TitleSaveRequest
import com.example.sportsforumapp.Models.User
import com.example.sportsforumapp.SportsApi.SportsApiService
import com.example.sportsforumapp.SportsApi.TitleService
import com.example.sportsforumapp.TitleFragment.TitleFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addTitleFragment : Fragment() {

    private lateinit var titleText : EditText
    private lateinit var entryText : EditText
    private lateinit var addTitleBtn : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_title, container, false)

        titleText = view.findViewById(R.id.titleNameText)
        entryText = view.findViewById(R.id.entryText)
        addTitleBtn = view.findViewById(R.id.addTitleAndEntryBtn)
        val forumApiUtil = ForumApiUtil()
        val retrofit = forumApiUtil.getRetrofit()
        val apiService = retrofit.create(TitleService::class.java)

        addTitleBtn.setOnClickListener {
            var title : Title = Title(-1,titleText.text.toString(), null)
            val entry : Entry = Entry(-1,entryText.text.toString(),null,User(UserObject.getUser().userId,null,null,null,null),0)
            println("onceki "+title.toString())
            val titleSaveRequest : TitleSaveRequest = TitleSaveRequest(title,entry)
            var call : Call<Title> = apiService.saveTitleWithRequest(titleSaveRequest)
            call.enqueue(object: Callback<Title> {
                override fun onResponse(call: Call<Title>, response: Response<Title>) {
                    if(response.code() == 200){
                        (activity as MainActivity).replaceFragment(TitleFragment())
                    }
                    else if(response.code() == 409){
                        Toast.makeText(context, "Kullanıcı Adı Veya Şifre Yanlış", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("API","-")
                    }

                }

                override fun onFailure(call: Call<Title>, t: Throwable) {

                    Log.e("API","-")
                }

            })
        }


        return view
    }


}