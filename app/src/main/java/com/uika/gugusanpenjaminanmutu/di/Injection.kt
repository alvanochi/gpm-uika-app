package com.uika.gugusanpenjaminanmutu.di

import com.uika.gugusanpenjaminanmutu.data.GugusanRepository

object Injection {
    fun provideRepository(): GugusanRepository {
        return GugusanRepository.getInstance()
    }
}