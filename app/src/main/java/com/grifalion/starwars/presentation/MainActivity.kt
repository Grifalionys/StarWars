package com.grifalion.starwars.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.grifalion.starwars.R
import com.grifalion.starwars.databinding.ActivityMainBinding
import com.grifalion.starwars.presentation.fragments.favourite.FavouriteFragment
import com.grifalion.starwars.presentation.fragments.main.MainFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnclickBottomNav()
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .add(R.id.nav_host,fragment)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host,fragment)
            .commit()
    }

    private fun setOnclickBottomNav() = with(binding){
        setFragment(MainFragment::class.java.newInstance())
        bottomNavigationView.setOnItemSelectedListener { menu->
            when(menu.itemId){
                R.id.menu_main -> replaceFragment(MainFragment::class.java.newInstance())
                R.id.menu_favourite -> replaceFragment(FavouriteFragment::class.java.newInstance())
            }
            true
        }
    }
}
