package com.example.mvvm_with_clean.util

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InstantTaskExtensionRule : BeforeEachCallback, AfterAllCallback {
    override fun beforeEach(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

            override fun postToMainThread(runnable: Runnable) = runnable.run()

            override fun isMainThread(): Boolean = true
        })
    }

    override fun afterAll(context: ExtensionContext?) {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}