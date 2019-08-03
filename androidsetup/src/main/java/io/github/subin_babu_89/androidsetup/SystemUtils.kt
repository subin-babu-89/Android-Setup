package io.github.subin_babu_89.androidsetup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

/**
 * System util methods to use throughout the system
 *
 * @author sbabu
 */

fun <T : AppCompatActivity> KClass<T>.start(
    activity: AppCompatActivity,
    finish: Boolean = false,
    bundle: Bundle = Bundle()
) {
    Intent(activity, this.java).apply {
        this.putExtras(bundle)
        activity.startActivity(this)
    }
    if (finish) activity.finish()
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


/**
 *
 * // Simple Activities
launchActivity<UserDetailActivity>()

// Add Intent extras
launchActivity<UserDetailActivity> {
putExtra(INTENT_USER_ID, user.id)
}

// Add custom flags
launchActivity<UserDetailActivity> {
putExtra(INTENT_USER_ID, user.id)
addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
}

// Add Shared Transistions
val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, avatar, "avatar")
launchActivity<UserDetailActivity>(options = options) {
putExtra(INTENT_USER_ID, user.id)
}

// Add requestCode for startActivityForResult() call
launchActivity<UserDetailActivity>(requestCode = 1234) {
putExtra(INTENT_USER_ID, user.id)
}
 *
 *
 */
inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) startActivityForResult(intent, requestCode, options)
    else startActivityForResult(intent, requestCode)
}

inline fun <reified T : Any> Context.launchActivity(options: Bundle? = null, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) startActivity(intent, options)
    else startActivity(intent)
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)