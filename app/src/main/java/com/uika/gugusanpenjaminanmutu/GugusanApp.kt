package com.uika.gugusanpenjaminanmutu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uika.gugusanpenjaminanmutu.common.UiState
import com.uika.gugusanpenjaminanmutu.data.GugusanRepository
import com.uika.gugusanpenjaminanmutu.ui.components.TextEmpty
import com.uika.gugusanpenjaminanmutu.ui.screen.HomeScreen

@Composable
fun GugusanApp(
    viewModel: GugusanViewModel = viewModel(factory = ViewModelFactory(GugusanRepository())),
){
    val query by viewModel.query
    val listDosen by viewModel.listDosenQuery.collectAsState()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getListDosen()
            }
            is UiState.Success -> {
                HomeScreen(
                    listDosen = listDosen,
                    query = query,
                    onQueryChange = viewModel::search
                )
            }
            is UiState.Error -> {
                TextEmpty(stringResource(R.string.data_empty))
            }
        }
    }

    if (listDosen.isEmpty()){
        TextEmpty(stringResource(R.string.data_empty))
    }
}