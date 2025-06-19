package com.gushakir.hs.gushakir.di

import com.gushakir.hs.gushakir.data.DataRepo
import com.gushakir.hs.gushakir.data.DataRepoImpl
import com.gushakir.hs.gushakir.domain.GetDataUseCase
import com.gushakir.hs.gushakir.presentation.DataViewModel
import org.koin.dsl.module

val appModule = module {
    single<DataRepo> { DataRepoImpl() }
    factory { GetDataUseCase(get()) }
    factory { DataViewModel(get()) }
}
