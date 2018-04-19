package com.daguerreo.crypt.sdescalculator.sdes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.daguerreo.crypt.sdescalculator.R
import com.daguerreo.crypt.sdescalculator.StepByStepFragment
import com.daguerreo.crypt.sdescalculator.utils.OperationResult
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

    fun encrypt(view: View) {
        if (validateFields()) {
            val sDesCrypt = SDesCrypt(AndroidSdesStringResource(this))
            val result = sDesCrypt.encrypt(etSdesText.text.toString(), etKey.text.toString())
            showResult(result)
        }
    }

    fun decrypt(view: View) {
        if (validateFields()) {
            val sDesCrypt = SDesCrypt(AndroidSdesStringResource(this))
            val result = sDesCrypt.decrypt(etSdesText.text.toString(), etKey.text.toString())
            showResult(result)
        }
    }

    private fun showResult(result: OperationResult) {
        val stepByStepFragment = supportFragmentManager.findFragmentById(R.id.step_by_step_fragment) as StepByStepFragment?
        stepByStepFragment?.showSteps(result)
    }

    fun validateFields(): Boolean {
        if (!validRequiredField(this, etSdesText)) return false
        if (!validRequiredField(this, etKey)) return false
        if (!SDesCrypt.validKey(etKey.text.toString())) {
            etKey.error = getString(R.string.sdes_invalid_key)
            return false
        }
        return true
    }
}
