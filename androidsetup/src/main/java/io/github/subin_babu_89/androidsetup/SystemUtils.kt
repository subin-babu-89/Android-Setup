package io.github.subin_babu_89.androidsetup

import android.content.Context
import android.content.Intent
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

fun showToast(context: Context,message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}