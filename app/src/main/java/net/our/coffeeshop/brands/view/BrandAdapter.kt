package net.our.coffeeshop.brands.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import net.our.coffeeshop.R

class BrandAdapter(val itemClick:View.OnClickListener) : RecyclerView.Adapter<BrandViewHolder>() {

    var brands: ArrayList<String> = ArrayList()

    open fun setBrandData(brands: ArrayList<String>) {
        this.brands = brands
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BrandViewHolder {
        return BrandViewHolder(parent?.context, R.layout.item_cafe_brand, parent, itemClick)
    }

    override fun onBindViewHolder(holder: BrandViewHolder?, position: Int) {
        holder?.textview?.setText(brands.get(position))
    }

    override fun getItemCount(): Int = brands.size

    fun getItem(itemClickPosition: Int):String {
        return brands.get(itemClickPosition)
    }

}