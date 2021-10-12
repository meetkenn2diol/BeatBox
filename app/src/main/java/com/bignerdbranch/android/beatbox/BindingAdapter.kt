package com.bignerdbranch.android.beatbox


import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter

/**
 * A Binding Adapter that is called whenever the value of the attribute `app:shouldToast`
 * changes. Sends a Toast Message
 * <!--note:: this has not been implemented. It's just a demo code to show the syntax of @BindingAdapter-->
 */
@BindingAdapter("app:shouldToast")
fun bindshouldToast(button: AppCompatButton, counter: Int) {
    Toast.makeText(
        button.context,
        "The name of the Song is: $counter",
        Toast.LENGTH_LONG
    ).show()
}

/**
 *  Sets the value of the progress bar so that 5 likes will fill it up.
 *
 *  Showcases Binding Adapters with multiple attributes. Note that this adapter is called
 *  whenever any of the attribute changes.
 */
@BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
    progressBar.progress = (likes * max / 5).coerceAtMost(max)
}