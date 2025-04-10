package com.shawnie.catalog

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.shawnie.catalog.presentation.compose.ItemsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Composition Entry Point
        setContent {
            MaterialTheme {
                Surface {
                    Box(Modifier.safeDrawingPadding()) {
                        ItemsScreen()
                    }
                }
            }
        }
    }
}
