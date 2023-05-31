package com.example.sportsforumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.sportsforumapp.TitleFragment.TitleFragment
import com.example.sportsforumapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(SignInFragment())

      /*  binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.gamebutton -> replaceFragment(GameFragment())
                R.id.favouritesbutton -> replaceFragment(FavoritesFragment())
            }
            true
        }*/
    }

    public fun replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragment,fragment)
        fragmentTransaction.commit()
    }


}