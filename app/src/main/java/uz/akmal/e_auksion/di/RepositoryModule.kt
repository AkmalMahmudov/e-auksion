package uz.akmal.e_auksion.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.akmal.e_auksion.model.ApiService
import uz.akmal.e_auksion.model.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun getRepository(service: ApiService): MainRepository = MainRepository(service)
}