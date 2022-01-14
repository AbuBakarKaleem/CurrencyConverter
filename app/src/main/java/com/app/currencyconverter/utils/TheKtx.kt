package com.app.currencyconverter.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.showSnack(message: String, action: String = "", actionListener: () -> Unit = {}): Snackbar {
    var snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    if (action != "") {
        snackbar.duration = Snackbar.LENGTH_INDEFINITE
        snackbar.setAction(action) {
            actionListener()
            snackbar.dismiss()
        }
    }
    snackbar.show()
    return snackbar
}
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

