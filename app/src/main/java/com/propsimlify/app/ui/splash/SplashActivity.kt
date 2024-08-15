package com.propsimlify.app.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.propsimlify.app.base.extension.subscribeAndObserveOnMainThread
import com.propsimlify.app.base.BaseActivity
import com.propsimlify.app.ui.welcome.WelcomeActivity
import com.propsimlify.databinding.ActivitySplashBinding
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Observable.timer(2000,TimeUnit.MILLISECONDS).subscribeAndObserveOnMainThread {
            startActivity(WelcomeActivity.getIntent(this@SplashActivity))
            finish()
        }.autoDispose()
    }
}