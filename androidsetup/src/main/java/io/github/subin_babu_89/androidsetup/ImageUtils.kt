package io.github.subin_babu_89.androidsetup

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 * Util class for handling image related methods
 *
 * @author sbabu
 */

fun glideOptions(@DrawableRes placeholderImage: Int, @DrawableRes errorImage: Int) =
    RequestOptions().centerCrop().placeholder(placeholderImage).error(errorImage).diskCacheStrategy(
        DiskCacheStrategy.ALL
    ).priority(Priority.HIGH)

fun setupGlide(
    context: Context,
    uri: String,
    options: RequestOptions,
    view: ImageView,
    failure: () -> Unit,
    success: () -> Unit
) {
    Glide.with(context).load(uri).listener(object : RequestListener<Drawable> {

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            success()
            return false
        }

        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            failure()
            return false
        }
    }).apply(options).into(view)
}

fun drawableToBitmap(drawable: Drawable): Bitmap {
    if (drawable is BitmapDrawable) return drawable.bitmap

    val bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) Bitmap.createBitmap(
        1,
        1,
        Bitmap.Config.ARGB_8888
    )
    else Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)

    val canvas = Canvas(bitmap)
    drawable.setBounds(0,0,canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}