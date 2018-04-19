package com.daguerreo.crypt.sdescalculator

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.daguerreo.crypt.sdescalculator.utils.Step

class MyStepRecyclerViewAdapter(steps: List<Step>) : RecyclerView.Adapter<MyStepRecyclerViewAdapter.ViewHolder>() {
    var steps = steps
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_step, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.step = steps[position]
        holder.stepView.text = steps[position].step
    }

    override fun getItemCount(): Int {
        return steps.size
    }

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        var step: Step? = null
        val stepView: TextView = mView.findViewById<View>(R.id.step_item) as TextView

        override fun toString(): String {
            return super.toString() + " '" + stepView.text + "'"
        }
    }
}
