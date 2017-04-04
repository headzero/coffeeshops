package net.our.coffeeshop.brands.view

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.our.coffeeshop.R

class BrandViewHolder(itemView: View, itemClick: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {

    constructor(context: Context?, @LayoutRes id: Int, parent: ViewGroup?, itemClick: View.OnClickListener)
            : this(LayoutInflater.from(context).inflate(id, parent, false), itemClick)

    var textview: TextView? = null

    init {
        textview = itemView.findViewById(R.id.brand_title) as TextView?
        itemView.setOnClickListener(itemClick)
    }
}