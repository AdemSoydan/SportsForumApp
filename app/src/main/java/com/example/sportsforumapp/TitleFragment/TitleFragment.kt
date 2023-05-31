package com.example.sportsforumapp.TitleFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsforumapp.ApiUtils.ForumApiUtil
import com.example.sportsforumapp.MainActivity
import com.example.sportsforumapp.Models.TitleResponse
import com.example.sportsforumapp.Models.User
import com.example.sportsforumapp.R
import com.example.sportsforumapp.SportsApi.TitleService
import com.example.sportsforumapp.TitleAdapter
import com.example.sportsforumapp.UserObject
import com.example.sportsforumapp.addTitleFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TitleFragment : Fragment() {

    lateinit var rvTitles : RecyclerView
    lateinit var titleAdapter: TitleAdapter
    lateinit var addTitlebBtn: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_title, container, false)

        setRvAndAdapter(view)
        val forumApiUtil = ForumApiUtil()
        val retrofit = forumApiUtil.getRetrofit()
        val apiService = retrofit.create(TitleService::class.java)
        var call : Call<List<TitleResponse>> = apiService.getTitles()

        call.enqueue(object: Callback<List<TitleResponse>> {
            override fun onResponse(call: Call<List<TitleResponse>>, response: Response<List<TitleResponse>>) {
                Log.e("API","METHODA GIRDI")
                if(response.code() == 200){
                    Log.e("API","200 GELDI")
                    val result = response.body()!!
                    val reversed = result.reversed()
                    titleAdapter.addTitle(reversed)
                }
                else if(response.code() == 404){
                    Toast.makeText(context, "Kullanıcı Adı Veya Şifre Yanlış", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.e("API","REQUEST FAIL")
                }
            }
            override fun onFailure(call: Call<List<TitleResponse>>, t: Throwable) {

                Log.e("API","API MI CALISMIYO NOLUYO ABI YA")
            }
        })
        addTitlebBtn.setOnClickListener {
            (activity as MainActivity).replaceFragment(addTitleFragment())
        }

        return view;
    }

    private fun setRvAndAdapter(view: View){
        addTitlebBtn = view.findViewById(R.id.addTitleBtn)
        rvTitles = view.findViewById(R.id.titleRv)
        titleAdapter = TitleAdapter()
        rvTitles.setHasFixedSize(true)
        rvTitles.setLayoutManager(LinearLayoutManager(context));
        rvTitles.adapter = titleAdapter
    }


}