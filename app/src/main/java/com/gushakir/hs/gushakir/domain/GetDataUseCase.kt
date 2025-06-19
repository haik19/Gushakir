package com.gushakir.hs.gushakir.domain

import com.gushakir.hs.gushakir.data.DataRepo

class GetDataUseCase(private val repo: DataRepo) {
    operator fun invoke(): String = repo.getData()
}
