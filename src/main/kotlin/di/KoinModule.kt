package com.yourname.di

import com.yourname.repository.MainRepository
import com.yourname.repository.MainRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<MainRepository> { MainRepositoryImpl() }
}