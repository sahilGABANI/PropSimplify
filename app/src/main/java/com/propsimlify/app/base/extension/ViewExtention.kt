package com.propsimlify.app.base.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat
import kotlin.math.abs

fun View.onClick(callback: () -> Unit) {
    setOnClickListener { callback.invoke() }
}

fun EditText.isNotValidEmail(): Boolean {
    if (!Patterns.EMAIL_ADDRESS.matcher(this.text.toString().trim()).matches()) {
        this.requestFocus()
        return true
    }
    return false
}

fun EditText.isFieldBlank(): Boolean {
    if (this.text.toString().isEmpty()) {
        this.requestFocus()
        return true
    }
    return false
}

fun EditText.isPhoneNumber(): Boolean {
    if (this.text.toString().isEmpty()) {
        this.requestFocus()
        return true
    }
    return false
}

fun EditText.isNotValidPhoneLength(): Boolean {
    if (this.text.toString().trim().length < 8 || this.text.toString().trim().length > 15) {
        this.requestFocus()
        return true
    }
    return false
}

fun Any?.toDollar(): String {
    val df = DecimalFormat("0.00")
    val value : String? =  df.format(this)
    return if (value == null) {
        "$0"
    } else {
        "$${value}"
    }
}
fun Any?.toDollarWithOutFormat(): String {
    return if (this == null) {
        "-$0"
    } else {
        "-$${this}"
    }
}
fun Double?.toMinusDollar(): Double? {
    return this?.let { abs(it) }
}
fun Double?.toConvertDecimalFormat(): Double {
    val df = DecimalFormat("0.00")
    return df.format(this).toDouble()
}

fun TextView.leftDrawable(drawable: Drawable?) {
    this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}

fun TextView.clearRightDrawable() {
    this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
}


fun getDrawable(context : Context, name: String): Drawable? {
    val resources: Resources = context.resources
    val resourceId: Int = resources.getIdentifier(
        name, "drawable",
        context.packageName
    )
    return resources.getDrawable(resourceId)
}