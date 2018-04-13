package com.daguerreo.crypt.sdescalculator.utils

import android.content.Context
import android.widget.TextView
import com.daguerreo.crypt.sdescalculator.R

fun validRequiredField(ctx: Context, field: TextView): Boolean {
    return if (field.text.isBlank()) {
        field.error = ctx.getString(R.string.required_field)
        false
    } else {
        true
    }
}