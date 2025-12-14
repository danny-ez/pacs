package com.example.pacs

import android.app.Application
import com.example.pacs.data.local.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PacsApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.buildDatabase(this) }
}
