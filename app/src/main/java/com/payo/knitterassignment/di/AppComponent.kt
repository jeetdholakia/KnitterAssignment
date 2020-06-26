package com.payo.transactionanalysis.di

import android.app.Application
import com.payo.knitterassignment.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.jeetdholakia.androidmvvmdagger.di.ActivityBuildersModule
import dev.jeetdholakia.androidmvvmdagger.di.AppModule
import dev.jeetdholakia.androidmvvmdagger.di.ViewModelFactoryModule
import dev.jeetdholakia.androidmvvmdagger.di.main.MainModule
import dev.jeetdholakia.androidmvvmdagger.di.main.MainViewModelsModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        MainModule::class,
        MainViewModelsModule::class
    ]
)
public interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}