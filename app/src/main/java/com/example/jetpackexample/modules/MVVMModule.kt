package com.example.jetpackexample.modules

import com.example.jetpackexample.api.Api
import com.example.jetpackexample.main.MainActivityViewModel
import com.example.jetpackexample.main.MainActivityViewModelFactory
import com.example.jetpackexample.main.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MVVMModule {

    @Provides
    fun provideRepository(api: Api): Repository {
        return Repository(api)
    }

    @Provides
    fun provideMainViewModel(repository: Repository): MainActivityViewModel {
        return MainActivityViewModel(repository)
    }

    @Provides
    fun provideFactory(mainActivityViewModel: MainActivityViewModel): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(mainActivityViewModel)
    }
}