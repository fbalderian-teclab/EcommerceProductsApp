package com.example.ecommerceproductsapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ProductListTest {

    private fun getProductsToDisplay(products: List<Product>): List<String> {
        return products.map { "${it.name} - ${it.currency} ${it.price}" }
    }

    @Test
    fun givenProductList_whenDisplayingProducts_thenInformationIsShown() {
        val products = listOf(
            Product(1, "iPhone 13", "The latest iPhone from Apple", 999.99, "USD", true),
            Product(2, "Samsung Galaxy S21", "The latest Samsung phone", 899.99, "USD", true),
            Product(3, "Google Pixel 6", "The latest Google phone", 799.99, "USD", false)
        )

        val result = getProductsToDisplay(products)

        assertEquals(3, result.size)
        assertEquals("iPhone 13 - USD 999.99", result[0])
        assertEquals("Samsung Galaxy S21 - USD 899.99", result[1])
        assertEquals("Google Pixel 6 - USD 799.99", result[2])
    }

    @Test
    fun givenEmptyProductList_whenDisplayingProducts_thenNoInformationIsShown() {
        val products = emptyList<Product>()

        val result = getProductsToDisplay(products)

        assertTrue(result.isEmpty())
    }
}
