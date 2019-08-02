package io.github.subin_babu_89.androidsetup

import android.app.Application
import timber.log.Timber

/**
 * Implementation of a Android applicatoin to use Timber events with
 *
 * @author sbabu
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}