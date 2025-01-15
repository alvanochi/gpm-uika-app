package com.uika.gugusanpenjaminanmutu.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uika.gugusanpenjaminanmutu.model.Dosen
import com.uika.gugusanpenjaminanmutu.ui.components.HomeContent
import com.uika.gugusanpenjaminanmutu.ui.components.Search

@Composable
fun HomeScreen(
    listDosen: List<Dosen>,
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Search(
            query = query,
            onQueryChange = onQueryChange
        )
        HomeContent(listDosen)
    }
}