package com.propsimlify.app.application

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.propsimlify.BuildConfig
import com.propsimlify.app.base.ActivityManager
import com.propsimlify.app.di.BaseAppComponent
import com.propsimlify.app.di.BaseUiApp
import timber.log.Timber

open class PropSimplifyApplication :  BaseUiApp() {
    companion object {
        lateinit var component: BaseAppComponent

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        context = this
        ActivityManager.getInstance().init(this)
        setupLog()

    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    private fun setupLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG)
    }


    override fun getAppComponent(): BaseAppComponent {
        return component
    }
    override fun setAppComponent(baseAppComponent: BaseAppComponent) {
        component = baseAppComponent
    }
}