package com.evg.purchases.presentation.mvi

import androidx.lifecycle.ViewModel
import com.evg.purchases.presentation.mapper.groupByDate
import com.evg.purchases.domain.repository.PurchasesRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class PurchasesViewModel(
    private val purchasesRepository: PurchasesRepository,
) : ContainerHost<PurchasesState, PurchasesSideEffect>, ViewModel() {
    override val container = container<PurchasesState, PurchasesSideEffect>(PurchasesState()) {
        loadPurchases()
    }

    fun dispatch(action: PurchasesAction) {
        when (action) {
            is PurchasesAction.LoadPurchases -> loadPurchases()
        }
    }

    private fun loadPurchases() = intent {
        reduce { state.copy(isLoading = true, error = null) }
        
        purchasesRepository.getPurchases()
            .onSuccess { purchases ->
                val groupedPurchases = purchases.groupByDate()
                reduce {
                    state.copy(
                        purchaseGroups = groupedPurchases,
                        isLoading = false,
                        error = null
                    )
                }
            }
            .onFailure { exception ->
                reduce { 
                    state.copy(
                        isLoading = false, 
                        error = exception.message
                    ) 
                }
            }
    }
}