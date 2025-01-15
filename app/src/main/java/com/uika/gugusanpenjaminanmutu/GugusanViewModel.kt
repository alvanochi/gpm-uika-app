package com.uika.gugusanpenjaminanmutu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.uika.gugusanpenjaminanmutu.common.UiState
import com.uika.gugusanpenjaminanmutu.data.GugusanRepository
import com.uika.gugusanpenjaminanmutu.model.Dosen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class GugusanViewModel(private val repository: GugusanRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Dosen>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Dosen>>>
        get() = _uiState

    private val _listDosen: MutableStateFlow<List<Dosen>> = MutableStateFlow(emptyList())

    private val _listDosenQuery: MutableStateFlow<List<Dosen>> = MutableStateFlow(emptyList())
    val listDosenQuery: StateFlow<List<Dosen>> get() = _listDosenQuery

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    fun getListDosen() {
        viewModelScope.launch {
            repository.getListDosen()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { dosen ->
                    _uiState.value = UiState.Success(dosen)
                    _listDosen.value = dosen
                    _listDosenQuery.value = dosen
                }
        }
    }

    fun search(newQuery: String) {
        _query.value = newQuery
        _listDosenQuery.value =
            if(newQuery.isEmpty()) {
                _listDosen.value
            } else {
                _listDosen.value.filter { it.name.contains(newQuery, ignoreCase = true) }
            }
    }
}

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