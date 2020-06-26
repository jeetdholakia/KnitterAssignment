package dev.jeetdholakia.androidmvvmdagger.di

import com.payo.knitterassignment.ui.main.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.jeetdholakia.androidmvvmdagger.di.main.MainFragmentBuildersModule
import dev.jeetdholakia.androidmvvmdagger.di.main.MainModule
import dev.jeetdholakia.androidmvvmdagger.di.main.MainViewModelsModule


@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
        MainViewModelsModule::class, MainModule::class])
    abstract  fun contributeMainActivity(): MainActivity

}