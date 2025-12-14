package com.example.pacs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pacs.ui.navigation.AppNavHost
import com.example.pacs.ui.theme.PacsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PacsTheme {
                AppNavHost()
            }
        }
    }
}
