package com.propsimlify.app.ui.welcome

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.propsimlify.R
import com.propsimlify.app.base.BaseActivity
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.base.extension.throttleClicks
import com.propsimlify.app.ui.propy.PropyActivity
import com.propsimlify.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, WelcomeActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.propsimplify_info)
//        binding.videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
//            mp.isLooping = true
//            mp.setVolume(0f, 0f);
//        })
//        binding.videoView.requestFocus();
//        binding.videoView.setVideoURI(uri);
//        binding.videoView.start();

//        val loginBottomSheet = WelcomeBottomSheet.newInstance()
//        loginBottomSheet.isCancelable = false
//        loginBottomSheet.show(supportFragmentManager, WelcomeActivity::class.java.name)
        binding.startButton.throttleClicks().subscribeAndObserveOnMainThread {
            startActivity(PropyActivity.getIntent(this))
            finish()
        }.autoDispose()
    }


}