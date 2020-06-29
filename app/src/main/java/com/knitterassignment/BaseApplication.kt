package com.knitterassignment

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.payo.transactionanalysis.di.AppComponent
import com.payo.transactionanalysis.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * This class is an entry point to the application
 */
class BaseApplication: DaggerApplication() {
    private val component: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }

    override fun onCreate() {
        super.onCreate()

        // If the build is DEBUG, initialize the logger
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
        }
    }
}