package io.github.subin_babu_89.androidsetup

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

/**
 * Implementation of AppCompatActivity to reduce code for layout content
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayout())
        Timber.d(this::class.java.simpleName)
    }

    @LayoutRes abstract fun contentLayout() : Int
}