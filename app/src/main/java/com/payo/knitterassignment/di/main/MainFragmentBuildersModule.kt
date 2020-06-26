package dev.jeetdholakia.androidmvvmdagger.di.main

import com.payo.knitterassignment.ui.main.fragment.TimelineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeTimelineFragment(): TimelineFragment
}