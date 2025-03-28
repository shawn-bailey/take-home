package com.shawnie.catalog.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shawnie.catalog.presentation.model.ItemUiModel


@Composable
fun LazyColumnItem(item: ItemUiModel) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column (Modifier.padding(10.dp)) {
                ItemNumber(item.id)
            }
            Column(Modifier.padding(4.dp)) {
                Text(
                    text = item.name,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row {
                    Text(
                        text = item.price,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = item.lastSold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ItemNumber(value: Int = 1) {
    Column(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topEnd = 16.dp,
                    topStart = 16.dp,
                    bottomEnd = 16.dp,
                    bottomStart = 16.dp
                )
            )
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(8.dp)
    ) {
        Text(
            color = MaterialTheme.colorScheme.onPrimary,
            text = value.toString(),
            fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun ListPreview() {
    val item = ItemUiModel(
        name = "Item Name Here",
        price = "$500.00",
        id = 1,
        lastSold = "2020-11-28T15:14:22Z"
    )
    LazyColumnItem(item)
}