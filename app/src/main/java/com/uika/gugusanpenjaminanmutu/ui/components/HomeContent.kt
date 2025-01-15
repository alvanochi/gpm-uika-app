package com.uika.gugusanpenjaminanmutu.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.uika.gugusanpenjaminanmutu.model.Dosen

@Composable
fun HomeContent(
    listDosen: List<Dosen>,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
        items(listDosen) { dosen ->
            DosenItem(
                photo = dosen.photo,
                name = dosen.name,
                position = dosen.position
            )
        }
    }
}