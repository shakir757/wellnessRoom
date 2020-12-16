package com.example.wellnessroomv1.ui.products

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellnessroomv1.*
import kotlinx.android.synthetic.main.fragment_products.*

class ProductsFragment : Fragment() {

    private lateinit var productsViewModel: ProductsViewModel
    val viewAdapter = ProductsAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productsViewModel =
            ViewModelProvider(this).get(ProductsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_products.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
        }

        viewAdapter.submitList(testData())
        viewAdapter.notifyDataSetChanged()

        button_scan_check.setOnClickListener {
            val intent = Intent(this.activity, ScannerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun testData(): List<Product> {
        return listOf(
            Product("Колбаса", 366.40, "11.12.2020"),
            Product("Йогурт", 50.80, "10.12.2020"),
            Product("Творог", 44.50, "04.12.2020"),
            Product("Яйца куриные", 60.90, "29.11.2020"),
            Product("Кока-кола 1л.", 80.00, "29.11.2020"),
            Product("Чипсы Lays", 89.50, "29.11.2020"),
            Product("Kit-Kat", 39.99, "29.11.2020")
        )
    }
}