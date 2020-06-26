package dev.jeetdholakia.androidmvvmdagger.di

import androidx.lifecycle.ViewModelProvider
import com.payo.transactionanalysis.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}
