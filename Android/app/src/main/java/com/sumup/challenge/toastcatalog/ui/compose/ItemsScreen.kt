package com.sumup.challenge.toastcatalog.ui.compose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sumup.challenge.toastcatalog.ui.ItemListViewModel

@Composable
fun ItemsScreen(itemViewModel: ItemListViewModel = viewModel()) {

    val itemState by itemViewModel.stateFlow.collectAsState()

    if (itemState.loading) {
        LoadingScreen()
    } else if (itemState.itemList.isEmpty()) {
        ErrorScreen()
    }

    LazyColumn {
        items(itemState.itemList) { item ->
            LazyColumnItem(item)
        }
    }
}

@Preview
@Composable
fun LoadingScreen() {
    Surface (
        Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Text(
            text = "Loading",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun ErrorScreen() {
    Surface (
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "Error",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
