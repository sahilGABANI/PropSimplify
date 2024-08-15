package com.propsimlify.app.ui.propy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.propsimlify.app.base.BaseActivity
import com.propsimlify.app.base.RxBus
import com.propsimlify.app.base.RxEvent
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.base.extension.throttleClicks
import com.propsimlify.app.ui.main.MainActivity
import com.propsimlify.app.ui.propy.view.PropyNavigationAdapter
import com.propsimlify.databinding.ActivityPropyBinding

class PropyActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, PropyActivity::class.java)
        }
    }
    private lateinit var binding: ActivityPropyBinding
    private lateinit var propyNavigationAdapter: PropyNavigationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.progressBar.max = 100
        binding.progressBar.progress = 0
        propyNavigationAdapter =  PropyNavigationAdapter(this)
        binding.propertyViewPager.apply {
            offscreenPageLimit = 1
            adapter = propyNavigationAdapter
            isUserInputEnabled = false
        }
        binding.flNextButton.throttleClicks().subscribeAndObserveOnMainThread {
            if (binding.propertyViewPager.currentItem < 2) {
                binding.propertyViewPager.currentItem = binding.propertyViewPager.currentItem + 1
                setMessage(binding.propertyViewPager.currentItem)
            } else if (binding.propertyViewPager.currentItem == 2) {
                RxBus.publish(RxEvent.CheckIsSelectAnyOptionInLifeStyle)
            } else if (binding.propertyViewPager.currentItem == 3) {
                startActivity(MainActivity.getIntent(this))
                finish()
            }
        }.autoDispose()
        binding.ivBack.throttleClicks().subscribeAndObserveOnMainThread {
            manageBackPress(binding.propertyViewPager.currentItem)
        }.autoDispose()
    }

    private fun manageBackPress(currentItem: Int) {
        when (currentItem) {
            0 -> {
                onBackPressed()
            }
            1 -> {
                binding.propertyViewPager.currentItem = currentItem - 1
                binding.progressBar.progress = 0
                setMessage(binding.propertyViewPager.currentItem)
            }
            2 -> {
                binding.propertyViewPager.currentItem = currentItem - 1
                binding.progressBar.progress = 25
                setMessage(binding.propertyViewPager.currentItem)
            }
            3 -> {
                binding.propertyViewPager.currentItem = currentItem - 1
                binding.progressBar.progress = 50
                setMessage(binding.propertyViewPager.currentItem)
            }
            else -> {
            }
        }
    }

    private fun setMessage(currentItem: Int) {
        when (currentItem) {
            0 -> {
                binding.tvHeading.text = "Where do you want to live?"
                binding.progressBar.progress = 0
                binding.tvHeading.isVisible = true
                binding.tv.isVisible = true
            }
            1 -> {
                binding.tvHeading.text = "What are you looking for?"
                binding.progressBar.progress = 25
                binding.tvHeading.isVisible = true
                binding.tv.isVisible = true
            }
            2 -> {
                binding.tvHeading.text = "Select your lifestyle."
                binding.progressBar.progress = 50
                binding.tvHeading.isVisible = true
                binding.tv.isVisible = true
            }
            else -> {
                binding.tvHeading.text = "Where do you want to live?"
                binding.progressBar.progress = 0
            }
        }
    }

    override fun onBackPressed() {
        when (binding.propertyViewPager.currentItem) {
            0 -> {
                super.onBackPressed()
            }
            1 -> {
                binding.propertyViewPager.currentItem = binding.propertyViewPager.currentItem - 1
                binding.progressBar.progress = 0
                setMessage(binding.propertyViewPager.currentItem)
            }
            2 -> {
                binding.propertyViewPager.currentItem = binding.propertyViewPager.currentItem - 1
                binding.progressBar.progress = 25
                setMessage(binding.propertyViewPager.currentItem)
            }
            3 -> {
                binding.propertyViewPager.currentItem = binding.propertyViewPager.currentItem - 1
                binding.progressBar.progress = 50
                setMessage(binding.propertyViewPager.currentItem)
            }
            else -> {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        RxBus.listen(RxEvent.NavigateBudgetScreen::class.java).subscribeAndObserveOnMainThread {
            binding.propertyViewPager.currentItem = binding.propertyViewPager.currentItem + 1
            binding.progressBar.progress = 75
            binding.tvHeading.isVisible = false
            binding.tv.isVisible = false
        }.autoDispose()
    }
}