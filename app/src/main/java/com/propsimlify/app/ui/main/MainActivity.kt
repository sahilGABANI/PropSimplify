package com.propsimlify.app.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.propsimlify.R
import com.propsimlify.app.ui.main.view.MainHomeTabAdapter
import com.propsimlify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainHomeTabAdapter: MainHomeTabAdapter
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        mainHomeTabAdapter = MainHomeTabAdapter(this@MainActivity)
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.offscreenPageLimit = 5
        binding.viewPager.adapter = mainHomeTabAdapter
        binding.viewPager.currentItem = 0
        binding.navigationBottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    binding.viewPager.setCurrentItem(0, false)
                    true
                }
                R.id.navigation_map -> {
                    binding.viewPager.setCurrentItem(1, false)
                    true
                }
                R.id.navigation_favourite -> {
                    binding.viewPager.setCurrentItem(2, false)
                    true
                }
                R.id.navigation_message -> {
                    binding.viewPager.setCurrentItem(3, false)
                    true
                }
                R.id.navigation_profile -> {
                    binding.viewPager.setCurrentItem(4, false)
                    true
                }
                else -> false
            }
        }
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position)  {
                    0 -> {
                        binding.navigationBottomNavigationView.selectedItemId = R.id.navigation_home
                    }
                    1 -> {
                        binding.navigationBottomNavigationView.selectedItemId = R.id.navigation_map
                    }
                    2 -> {
                        binding.navigationBottomNavigationView.selectedItemId = R.id.navigation_favourite
                    }
                    3 -> {
                        binding.navigationBottomNavigationView.selectedItemId = R.id.navigation_message
                    }
                    4 -> {
                        binding.navigationBottomNavigationView.selectedItemId = R.id.navigation_profile
                    }
                    else -> {

                    }
                }

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

}