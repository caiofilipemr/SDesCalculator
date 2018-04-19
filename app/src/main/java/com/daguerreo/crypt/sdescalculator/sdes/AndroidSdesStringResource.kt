package com.daguerreo.crypt.sdescalculator.sdes

import android.content.Context
import com.daguerreo.crypt.sdescalculator.R

class AndroidSdesStringResource(context: Context) : SdesStringResource {
    override val plainTextOrEncryptedText = context.getString(R.string.sdes_text)
    override val binRepresentation = context.getString(R.string.binary_representation)
    override val orInDecimal = context.getString(R.string.orInDecimal)
    override val encrypted = context.getString(R.string.encrypted)
    override val decrypted = context.getString(R.string.decrypted)
    override val text = context.getString(R.string.text)
}