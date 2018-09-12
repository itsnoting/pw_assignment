package com.phunware.android.phunwareproducthomework.features.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.phunware.android.phunwareproducthomework.R
import com.phunware.android.phunwareproducthomework.features.list.fragment.ZipCodeListFragmentDirections
import kotlinx.android.synthetic.main.item_zipcode.view.*

class ZipCodeAdapter(var items: List<String>?) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_zipcode, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.zipCode.text = items!![position]

        holder.zipCode.setOnClickListener {
            val action = ZipCodeListFragmentDirections.showDetail(items!![position])
            Navigation.findNavController(holder.zipCode).navigate(
                    action)
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val zipCode: TextView = view.zipCodeTextView
}