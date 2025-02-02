package com.shapacreations.generatorfifa22

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DivisionSpinnerAdapter(context: Context, private val items: ArrayList<String>) :

    ArrayAdapter<String>(context, R.layout.division_spinner_item_layout, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.division_spinner_item_layout, parent, false)
        val item = items[position]
        val textTextView = view.findViewById<TextView>(R.id.division_name_text)

        textTextView.text = item

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}