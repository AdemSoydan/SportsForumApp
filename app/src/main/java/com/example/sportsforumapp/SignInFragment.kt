package com.example.sportsforumapp

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
import com.example.sportsforumapp.TitleFragment.TitleFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignInFragment : Fragment() {
    lateinit var userNameText: TextView
    lateinit var passwordText: TextView
    lateinit var signInBtn: Button
    lateinit var goToSignUpBtn: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
        val forumApiUtil = ForumApiUtil()
        val retrofit = forumApiUtil.getRetrofit()
        val apiService = retrofit.create(SportsApiService::class.java)

        userNameText = view.findViewById(R.id.usernameText)
        passwordText = view.findViewById(R.id.passwdText)
        signInBtn = view.findViewById(R.id.signinBtn)
        goToSignUpBtn = view.findViewById(R.id.goToSignUpBtn)
        signInBtn.setOnClickListener {
            val password : String = passwordText.text.toString()
            val username : String = userNameText.text.toString()
            val user : User = User(0,username, "", password, null)
            var call : Call<User> = apiService.getUser(user);
            call.enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    // başarılı bir şekilde cevap aldıysak
                    val responseCode : String = response.code().toString()
                    Toast.makeText(context, responseCode, Toast.LENGTH_LONG).show()
                    if(response.code() == 200){

                        UserObject.setUser(response.body()!!)
                        (activity as MainActivity).replaceFragment(TitleFragment())
                    }
                    else if(response.code() == 409){
                        Toast.makeText(context, "Kullanıcı Adı Veya Şifre Yanlış", Toast.LENGTH_SHORT).show()
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

        goToSignUpBtn.setOnClickListener {
            (activity as MainActivity).replaceFragment(SingUpFragment())
        }

        return view

    }
}