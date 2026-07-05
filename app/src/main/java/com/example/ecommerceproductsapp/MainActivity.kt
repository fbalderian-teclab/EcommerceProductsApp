package com.example.ecommerceproductsapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var productsTextView: TextView
    private val apiUrl = "https://jsonkeeper.com/b/MX0A"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productsTextView = findViewById(R.id.productsTextView)

        getProducts()
    }

    private fun getProducts() {
        thread {
            try {
                val response = URL(apiUrl).readText()
                val jsonObject = JSONObject(response)
                val products = jsonObject.getJSONArray("products")

                val result = StringBuilder()
                result.append("Productos disponibles\n\n")

                for (i in 0 until products.length()) {
                    val product = products.getJSONObject(i)

                    val name = product.getString("name")
                    val price = product.getDouble("price")
                    val currency = product.getString("currency")

                    result.append("Producto: $name\n")
                    result.append("Precio: $currency $price\n\n")
                }

                runOnUiThread {
                    productsTextView.text = result.toString()
                }

            } catch (e: Exception) {
                runOnUiThread {
                    productsTextView.text = "Error al obtener productos: ${e.message}"
                }
            }
        }
    }
}