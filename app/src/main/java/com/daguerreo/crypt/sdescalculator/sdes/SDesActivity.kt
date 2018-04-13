package com.daguerreo.crypt.sdescalculator.sdes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.daguerreo.crypt.sdescalculator.R
import com.daguerreo.crypt.sdescalculator.utils.validRequiredField
import kotlinx.android.synthetic.main.activity_sdes.*

import kotlinx.android.synthetic.main.content_sdes.*

class SDesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sdes)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun crypt(view: View) {
        if (validateFields()) {
            val sDesCrypt = SDesCrypt()
            val str = sDesCrypt.crypt(etPlainText.text.toString(), etKey.text.toString())
            tvResult.text = str
        }
    }

    fun validateFields(): Boolean {
        if (!validRequiredField(this, etPlainText)) return false
        if (!validRequiredField(this, etKey)) return false
        if (!SDesCrypt.validKey(etKey.text.toString())) {
            etKey.error = getString(R.string.sdes_invalid_key)
            return false
        }
        return true
    }
}
