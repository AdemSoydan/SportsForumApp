package com.example.sportsforumapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.sportsforumapp.ApiUtils.ForumApiUtil
import com.example.sportsforumapp.SportsApi.SportsApiService


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

        userNameText = view.findViewById(R.id.usernameText)
        passwordText = view.findViewById(R.id.passwdText)
        signInBtn = view.findViewById(R.id.signinBtn)
        goToSignUpBtn = view.findViewById(R.id.goToSignUpBtn)


        signInBtn.setOnClickListener {

        }

        goToSignUpBtn.setOnClickListener {
            (activity as MainActivity).replaceFragment(SingUpFragment())
        }

        return view

    }
}