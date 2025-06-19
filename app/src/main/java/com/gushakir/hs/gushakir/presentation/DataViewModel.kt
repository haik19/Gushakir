package com.gushakir.hs.gushakir.presentation

import androidx.lifecycle.ViewModel
import com.gushakir.hs.gushakir.domain.GetDataUseCase

class DataViewModel(private val getDataUseCase: GetDataUseCase) : ViewModel() {
    fun loadData(): String = getDataUseCase()
}

