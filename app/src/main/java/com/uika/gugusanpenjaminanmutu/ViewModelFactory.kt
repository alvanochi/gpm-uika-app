package com.uika.gugusanpenjaminanmutu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uika.gugusanpenjaminanmutu.data.GugusanRepository

class ViewModelFactory(private val repository: GugusanRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GugusanViewModel::class.java)) {
            return GugusanViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}