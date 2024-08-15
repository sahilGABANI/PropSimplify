package com.propsimlify.app.di

import android.app.Application
import android.content.Context
import com.propsimlify.app.application.PropSimplify
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PropSimplifyModule(val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }
}


@Singleton
@Component(
    modules = [
        PropSimplifyModule::class,
    ]
)
interface PropSimplifyAppComponent : BaseAppComponent {
    fun inject(app: PropSimplify)
}