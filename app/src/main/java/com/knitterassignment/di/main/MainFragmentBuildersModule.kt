package dev.jeetdholakia.androidmvvmdagger.di.main

import com.knitterassignment.ui.comments.CommentsFragment
import com.knitterassignment.ui.posts.fragment.TimelineFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeTimelineFragment(): TimelineFragment

    @ContributesAndroidInjector
    abstract fun contributeCommentsFragment(): CommentsFragment
}