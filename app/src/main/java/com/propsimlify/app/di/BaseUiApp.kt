package com.propsimlify.app.di

import android.app.Application

abstract class BaseUiApp : Application() {

    abstract fun getAppComponent(): BaseAppComponent
    abstract fun setAppComponent(baseAppComponent: BaseAppComponent)
}


interface BaseAppComponent {
    fun inject(app: Application)
}