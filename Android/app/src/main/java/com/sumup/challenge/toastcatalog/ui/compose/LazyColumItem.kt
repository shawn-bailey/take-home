package com.sumup.challenge.toastcatalog.ui.compose

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
import com.sumup.challenge.toastcatalog.data.Item


@Composable
fun LazyColumnItem(item: Item) {
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
                    val priceString = when (item.currency) {
                        EURO -> item.price + "€"
                        else -> { "$" }
                    }
                    Text(
                        text = priceString,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = item.last_sold,
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
    val item = Item(
        name = "Item Name Here",
        price = "500.00",
        id = 1,
        currency = "EUR",
        last_sold = "2020-11-28T15:14:22Z"
    )
    LazyColumnItem(item)
}