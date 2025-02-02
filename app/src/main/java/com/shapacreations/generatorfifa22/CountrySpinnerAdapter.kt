package com.shapacreations.generatorfifa22

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CountrySpinnerAdapter(context: Context, private val items: List<ItemForSpinner>) :

    ArrayAdapter<ItemForSpinner>(context, R.layout.country_spinner_item_layout, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.country_spinner_item_layout, parent, false)
        val item = items[position]
        val iconImageView = view.findViewById<ImageView>(R.id.flag_image)
        val textTextView = view.findViewById<TextView>(R.id.country_name_text)

        iconImageView.setImageResource(item.icon)
        textTextView.text = item.text

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}