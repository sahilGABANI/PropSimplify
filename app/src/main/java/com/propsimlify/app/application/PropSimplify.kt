package com.propsimlify.app.application

import android.app.Activity
import android.app.Application
import com.propsimlify.app.di.DaggerPropSimplifyAppComponent
import com.propsimlify.app.di.PropSimplifyAppComponent
import com.propsimlify.app.di.PropSimplifyModule

class PropSimplify : PropSimplifyApplication() {

    companion object {

        operator fun get(app: Application): PropSimplify {
            return app as PropSimplify
        }

        operator fun get(activity: Activity): PropSimplify {
            return activity.application as PropSimplify
        }

        lateinit var component: PropSimplifyAppComponent
            private set
    }
    override fun onCreate() {
        super.onCreate()
        try {
            component = DaggerPropSimplifyAppComponent.builder()
                .propSimplifyModule(PropSimplifyModule(this))
                .build()
            component.inject(this)
            super.setAppComponent(component)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}