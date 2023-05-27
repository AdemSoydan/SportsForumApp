package com.example.sportsforumapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.sportsforumapp.ApiUtils.ForumApiUtil
import com.example.sportsforumapp.Models.User
import com.example.sportsforumapp.SportsApi.SportsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.HTTP


class SingUpFragment : Fragment() {
    lateinit var userNameText: TextView
    lateinit var emailText: TextView
    lateinit var passwordText: TextView
    lateinit var signUpBtn: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val forumApiUtil = ForumApiUtil()
        val retrofit = forumApiUtil.getRetrofit()
        val view = inflater.inflate(R.layout.fragment_sing_up, container, false)

        userNameText = view!!.findViewById(R.id.userNameText)
        emailText = view.findViewById(R.id.emailText)
        passwordText = view.findViewById(R.id.passwordText)
        signUpBtn = view.findViewById(R.id.signUpButton)

        val apiService = retrofit.create(SportsApiService::class.java)


        signUpBtn.setOnClickListener {
            val email : String = emailText.text.toString()
            val password : String = emailText.text.toString()
            if(userNameText.text== null)
                Toast.makeText(context, "Bu Email Zaten Kullnımda", Toast.LENGTH_SHORT).show()

            val user = User(userNameText.text.toString() as String?,email,
                password
            )
            var call : Call<User> = apiService.postUser(user);
            call.enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    // başarılı bir şekilde cevap aldıysak
                    if(response.code() == 200){
                        Toast.makeText(context, "Kullanıcı Oluşturuldu", Toast.LENGTH_SHORT).show()

                    }
                    else if(response.code() == 409){
                        Toast.makeText(context, "Kullanıcı Böyle bir kullanıcı zaten var", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Log.e("API","REQUEST FAIL")
                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable) {

                    Log.e("API","API MI CALISMIYO NOLUYO ABI YA")
                }

            })

        }
        return view

    }

}