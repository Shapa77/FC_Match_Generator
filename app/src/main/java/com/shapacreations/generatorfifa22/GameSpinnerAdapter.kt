package com.shapacreations.generatorfifa22

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView


class GameSpinnerAdapter(context: Context, private val items: List<GameIconForSpinner>) :

    ArrayAdapter<GameIconForSpinner>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val imageView = convertView as? ImageView ?: ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        imageView.setImageResource(items[position].icon)
        return imageView

    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.game_spinner_item_layout, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.game_icon)
        imageView.setImageResource(items[position].icon)
        return view
    }
}