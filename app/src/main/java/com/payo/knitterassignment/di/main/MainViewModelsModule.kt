package dev.jeetdholakia.androidmvvmdagger.di.main

import androidx.lifecycle.ViewModel
import com.payo.knitterassignment.viewmodel.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.jeetdholakia.androidmvvmdagger.di.ViewModelKey

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}