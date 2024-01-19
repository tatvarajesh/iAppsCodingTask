package com.example.iappscodingtask.common

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(
    view: View, imageUrl: String?
) {
    val image: ImageView = view as ImageView
    Glide.with(image.context).load(imageUrl).into(image)
}

@BindingAdapter("loadHtml")
fun loadHtml(textView: TextView, content: String?) {
    if (!content.isNullOrEmpty()) {
        textView.text = Html.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY).trim()
    }
}