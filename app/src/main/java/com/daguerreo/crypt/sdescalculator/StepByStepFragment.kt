package com.daguerreo.crypt.sdescalculator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.daguerreo.crypt.sdescalculator.utils.OperationResult

class StepByStepFragment : Fragment() {
    private val stepAdapter = MyStepRecyclerViewAdapter(emptyList())

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_step_list, container, false)

        if (view is RecyclerView) {
            val context = view.getContext()
            view.layoutManager = LinearLayoutManager(context)
            view.adapter = stepAdapter
        }
        return view
    }

    fun showSteps(result: OperationResult) {
        stepAdapter.steps = result.stepByStep
    }

    companion object {
        fun newInstance(): StepByStepFragment {
            return StepByStepFragment()
        }
    }
}
