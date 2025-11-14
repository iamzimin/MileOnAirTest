package com.evg.purchases.di

import com.evg.purchases.data.repository.PurchasesRepositoryImpl
import com.evg.purchases.domain.repository.PurchasesRepository
import com.evg.purchases.presentation.mvi.PurchasesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val purchasesModule = module {
    single<PurchasesRepository> {
        PurchasesRepositoryImpl(

        )
    }
    viewModel { PurchasesViewModel(purchasesRepository = get()) }
}