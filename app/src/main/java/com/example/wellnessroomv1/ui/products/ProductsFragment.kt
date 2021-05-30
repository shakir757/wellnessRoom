package com.example.wellnessroomv1.ui.products

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellnessroomv1.*
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
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
            Product("Колбаса Мираторг Московская", 366.40, "11.03.2021"),
            Product("Йогурт Danone 110 г.", 50.80, "10.03.2021"),
            Product("Творог Простоквашино 5%", 44.50, "04.03.2021"),
            Product("Яйца куриные", 60.99, "29.02.2021"),
            Product("Напиток coca-cola 1л.", 80.00, "29.02.2021"),
            Product("Чипсы Lays сметана и зелень", 89.50, "29.02.2021"),
            Product("Шоколад Alpen Gold 90г. с орехом", 39.99, "29.02.2021"),
            Product("Шоколад Alpe Gold 90г. обычный", 39.99, "29.02.2021")
        )
    }
}