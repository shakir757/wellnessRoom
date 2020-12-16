package com.example.wellnessroomv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter(var listProducts: List<Product>):
        RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ProductsViewHolder {
        val element = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_product_element, parent, false)

        return ProductsViewHolder(element)
    }

    class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.text_view_name)
        val date: TextView = view.findViewById(R.id.text_view_date)
        val cost: TextView = view.findViewById(R.id.text_view_cost)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.name.text = listProducts[position].name
        holder.date.text = listProducts[position].date
        holder.cost.text = listProducts[position].cost.toString() + " р."
    }

    override fun getItemCount(): Int = listProducts.size

    fun submitList(list: List<Product>){
        listProducts = list
    }
}